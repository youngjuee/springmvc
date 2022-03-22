package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

// 로그를 찍을 수 있음
@Slf4j
// 응답값을 뷰를 찾지 않고 테스트할때 문자열을 보여줌
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                        HttpServletResponse response,
                        HttpMethod httpMethod,
                        Locale locale,
                        @RequestHeader MultiValueMap<String, String> headerMap,
                        @RequestHeader("host") String host,
                        @CookieValue(value="myCookie", required = false) String cookie){
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";

    }
}
