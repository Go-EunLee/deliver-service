package org.delivery.storeadmin.domain.userorder.converter;

import lombok.RequiredArgsConstructor;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.storeadmin.domain.userorder.controller.model.UserOrderResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserOrderConverter {
    public UserOrderResponse toResponse(UserOrderEntity entity){
        return UserOrderResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .storeId(entity.getStoreId())
                .status(entity.getStatus())
                .amount(entity.getAmount())
                .orderedAt(entity.getOrderedAt())
                .acceptedAt(entity.getAcceptedAt())
                .cookingStartedAt(entity.getCookingStartedAt())
                .deliveryStartedAt(entity.getDeliveryStartedAt())
                .receivedAt(entity.getReceivedAt())
                .build();
    }

}
