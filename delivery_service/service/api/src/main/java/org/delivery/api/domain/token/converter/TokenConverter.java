package org.delivery.api.domain.token.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeIfs;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.model.TokenDto;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {
    public TokenResponse toResponse(TokenDto accessToken, TokenDto refreshToken){
        // accessToken이 null 값이면 예외처리
        Objects.requireNonNull(accessToken, () ->{throw new ApiException(ErrorCode.NULL_POINT);});

        // refreshToken이 null 값이면 예외처리
        Objects.requireNonNull(refreshToken, () ->{throw new ApiException(ErrorCode.NULL_POINT);});

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
