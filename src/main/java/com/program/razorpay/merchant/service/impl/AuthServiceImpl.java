package com.program.razorpay.merchant.service.impl;

import com.program.razorpay.common.enums.MerchantStatus;
import com.program.razorpay.common.enums.UserRole;
import com.program.razorpay.common.exception.DuplicateResourceException;
import com.program.razorpay.mapper.MerchantMapper;
import com.program.razorpay.merchant.dto.request.MerchantSignupRequest;
import com.program.razorpay.merchant.dto.response.MerchantResponse;
import com.program.razorpay.merchant.entity.AppUser;
import com.program.razorpay.merchant.entity.Merchant;
import com.program.razorpay.merchant.repository.AppUserRepository;
import com.program.razorpay.merchant.repository.MerchantRepository;
import com.program.razorpay.merchant.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantMapper merchantMapper;

    @Override
    @Transactional
    public MerchantResponse signup(MerchantSignupRequest request) {
        if (merchantRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("DUPLICATE_MERCHANT_EMAIL",
                    "Merchant with email already exists: " + request.email());
        }

        //here from merchantsignuprequest DTO to Merchant Entity using mapstruct
//        Merchant merchant = Merchant.builder()
//                .businessName(request.businessName())
//                .businessType(request.businessType())
//                .name(request.name())
//                .email(request.email())
//                .status(MerchantStatus.PENDING_KYC)
//                .build();
        Merchant merchant = merchantMapper.toEntityFromSignUpRequest(request);
        merchant.setStatus(MerchantStatus.PENDING_KYC);

        merchant = merchantRepository.save(merchant);

        //not using mapstruct because password is being hashed,also we are passing a merchant object
        AppUser appUser = AppUser.builder()
                .email(request.email())
                .merchant(merchant)
                .passwordHash(request.password()) // TODO: encrypt using Bcrypt
                .role(UserRole.OWNER)
                .build();
        appUserRepository.save(appUser);

        //here we can use
//        return new MerchantResponse(merchant.getId(), merchant.getName(),
//                merchant.getEmail(), merchant.getBusinessName(),
//                merchant.getBusinessType(), merchant.getStatus());
        return merchantMapper.toResponse(merchant);
    }
}















