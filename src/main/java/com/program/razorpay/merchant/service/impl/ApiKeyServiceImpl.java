package com.program.razorpay.merchant.service.impl;

import com.program.razorpay.common.exception.ResourceNotFoundException;
import com.program.razorpay.common.util.RandomizerUtil;
import com.program.razorpay.mapper.ApiKeyMapper;
import com.program.razorpay.merchant.dto.request.CreateApiKeyRequest;
import com.program.razorpay.merchant.dto.response.ApiKeyCreateResponse;
import com.program.razorpay.merchant.dto.response.ApiKeyResponse;
import com.program.razorpay.merchant.entity.ApiKey;
import com.program.razorpay.merchant.entity.Merchant;
import com.program.razorpay.merchant.repository.ApiKeyRepository;
import com.program.razorpay.merchant.repository.MerchantRepository;
import com.program.razorpay.merchant.service.ApiKeyService;
import com.program.razorpay.payment.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ApiKeyServiceImpl implements ApiKeyService {

    private final MerchantRepository merchantRepository;
    private final ApiKeyRepository apiKeyRepository;
    private final PaymentMapper paymentMapper;
    private final ApiKeyMapper apiKeyMapper;

    @Override
    @Transactional
    public ApiKeyCreateResponse create(UUID merchantId, CreateApiKeyRequest request) {
        Merchant merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResourceNotFoundException("merchant", merchantId));

        String keyId = "rzp_"+request.environment().name().toLowerCase()+"_"+ RandomizerUtil.randomBase64(24);
        String rawSecret = RandomizerUtil.randomBase64(40);

        //here, ideal case for mapstruct because everything is already present and being fetched. but want to return raw secret here but mapstruct will return hashed secret from apikey class.
        ApiKey apiKey = ApiKey.builder()
                .merchant(merchant)
                .keyId(keyId)
                .keySecretHash(rawSecret) // TODO: encode with BcryptPasswordEncoder
                .environment(request.environment())
                .build();

        apiKey = apiKeyRepository.save(apiKey);

        return new ApiKeyCreateResponse(apiKey.getId(), keyId, rawSecret, request.environment());
    }

    @Override
    public List<ApiKeyResponse> listByMerchant(UUID merchantId) {
        //correct usecase for mapstruct
//        return apiKeyRepository.findByMerchant_Id(merchantId).stream()
//                .map(apiKey ->
//                        new ApiKeyResponse(
//                                apiKey.getId(),
//                                apiKey.getKeyId(),
//                                apiKey.getEnvironment(),
//                                apiKey.isEnabled(),
//                                apiKey.getLastUsedAt(), null))
//                .toList();
         return apiKeyMapper.toResponseList(apiKeyRepository.findByMerchant_Id(merchantId));
    }

    @Override
    @Transactional
    public void revoke(UUID merchantId, UUID keyId) {
        ApiKey key = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        key.setEnabled(false);
    }

    @Override
    @Transactional
    public @Nullable ApiKeyCreateResponse rotate(UUID merchantId, UUID keyId) {
        ApiKey apiKey = apiKeyRepository.findById(keyId)
                .filter(k -> k.getMerchant().getId().equals(merchantId))
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey", keyId));

        if(!apiKey.isEnabled())
            throw new RuntimeException("Cannot rotate a disabled key");

        String newRawSecret = RandomizerUtil.randomBase64(40);
        apiKey.setPreviousKeySecretHash(apiKey.getKeySecretHash());
        apiKey.setKeySecretHash(newRawSecret);  // TODO: encode with BcryptPasswordEncoder
        apiKey.setRotatedAt(LocalDateTime.now());
        apiKey.setGracePeriodExpiresAt(LocalDateTime.now().plusHours(24));
        apiKey = apiKeyRepository.save(apiKey);

        //again cannot use mapstruct as we provide raw secret not hashed.
        return new ApiKeyCreateResponse(apiKey.getId(), apiKey.getKeyId(),
                newRawSecret, apiKey.getEnvironment());
    }

}



















