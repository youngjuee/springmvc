package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//    Slf4j annotation이 있으면 밑에 private와 똑같음
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("log-test")
    public String logTest(){
        String name = "Spring";

        log.trace("trace log ={}", name);
        log.debug("debug ={}", name);
        log.warn("warn ={}", name);
        log.info("info log={}", name);
        log.error("error log={}", name);

//      LEVEL TRACE > DEBUG > INFO > WARN > ERROR

        return "ok";
    }

}
