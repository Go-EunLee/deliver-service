package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component  // 개발자가 직접 작성한 class를 bean으로 등록하기 위한 어노테이션
// Filter : 스프링 부트에서 Client로 부터 오는 요청/응답에 대해서 최초/최종 단계에 위치
//          데이터가 변환되기 전 순수한 Client의 요청/응답 값을 확인할 수 있음
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // ContentCachingRequestWrapper : 뒤에서 누가 읽을 때 여기 있는 내용을 읽을 수 있게 해줌
        // HttpServletRequest : HTTP 요청 메시지를 파싱
        // 파싱 : 데이터들을 다루기 쉬운 형태로 바꿔주는 과정
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        log.info("INIT : {}", req.getRequestURI());

        // 필터 실행
        chain.doFilter(req, res);

        // request 정보
        var headerNames = req.getHeaderNames();  // HTTP header 이름을 읽어옴
        var headerValues = new StringBuilder();  // String 끼리 더하면 과부하가 올 수 있기 때문에 String 연산을 할 때는 StringBuilder 사용

        // asIterator : 열거에 포함된 요소들을 순회하는 Iterator를 리턴
        // forEachRemaining() 을 이용해서 Iterator를 ArrayList로 변환
        headerNames.asIterator().forEachRemaining( headerKey -> {
            var headerValue = req.getHeader(headerKey);
            headerValues.append("[").append(headerKey).append(" : ").append(headerValue).append("]");
        });

        var requestBody = new String(req.getContentAsByteArray());  // getContentAsByteArray : byte 배열을 읽고 이를 String으로 변환

        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info(">>>> uri : {}, method : {}, header : {}, body : {}", uri, method, headerValues, requestBody);

        // response 정보
        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append("]");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("<<<< uri : {}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);

        res.copyBodyToResponse();  // 이걸 안쓰면 response가 비어서 감
    }
}
