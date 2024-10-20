package core.basic.web;

import core.basic.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDomoService;
    //private final MyLogger myLogger;
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requsetURL=request.getRequestURL().toString();
        MyLogger myLogger=myLoggerProvider.getObject();
        myLogger.setRequestURL(requsetURL);

        myLogger.log("controller test");
        logDomoService.logic("testId");
        return "ok";
    }
}
