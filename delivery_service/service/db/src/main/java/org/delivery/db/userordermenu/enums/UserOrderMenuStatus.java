package org.delivery.db.userordermenu.enums;


// @AllArgsConstructor
public enum UserOrderMenuStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    // @AllArgsConstructor 대신 사용
    UserOrderMenuStatus(String description){
        this.description = description;
    }
    private String description;
}
