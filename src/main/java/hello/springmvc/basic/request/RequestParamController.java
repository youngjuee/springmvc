package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j

@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParam1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("version1 username={}, age={}",username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParam2(@RequestParam("username") String username,
                              @RequestParam("age") int memberAge) throws IOException {
        log.info("version2 username={}, age={}",username, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    //@RequestParam("username") ()괄호를 생략하는 대신 변수명이 파라미터와 같아야한다.
    public String requestParam3(@RequestParam String username,
                                @RequestParam int age) {
        log.info("version3 username={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    //@RequestParam("username") 이 자체를 줄일수도 있음
    public String requestParam4(String username, int age) {
        log.info("version4 username={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    // required = true인 값이 없으면 오류남
    public String requestParamRequired(@RequestParam(required = true) String username,
                                @RequestParam(required = false) int age) {
        log.info("versionRequired username={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(defaultValue = "2") int age) {
        log.info("versionDefault username={}, age={}",username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    // 파라미터의 값이 여러개면 MultiValueMap을 사용한다.
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("requestMap username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute */
    @ResponseBody
    @RequestMapping("/model-attribute-v3")
    public String modelAttributeV3(HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
