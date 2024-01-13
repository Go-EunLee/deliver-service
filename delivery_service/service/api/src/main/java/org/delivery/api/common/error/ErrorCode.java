package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/* extends : 부모에서 선언 / 정의를 모두하며 자식은 메소드 / 변수를 그대로 사용할 수 있음
  implements(interface 구현) : 부모 객체는 선언만 하며 정의(내용)은 자식에서 오버라이딩(재정의) 해서 사용해야 함
 */

// enum(열거 타입) : 한정된 값만을 갔는 데이터 타입
@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs{
    OK(200, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청" ),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null point")

    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;

}
