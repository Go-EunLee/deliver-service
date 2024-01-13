package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* extends : 부모에서 선언 / 정의를 모두하며 자식은 메소드 / 변수를 그대로 사용할 수 있음
  implements(interface 구현) : 부모 객체는 선언만 하며 정의(내용)은 자식에서 오버라이딩(재정의) 해서 사용해야 함
 */

// enum(열거 타입) : 한정된 값만을 갔는 데이터 타입
@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs{
    INVALID_TOKEN(400, 2000, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(400, 2001, "만료된 토큰"),
    TOKEN_EXCEPTION(400, 2002, "토큰 알 수 없는 에러"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "인증 헤더 토큰 없음")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
