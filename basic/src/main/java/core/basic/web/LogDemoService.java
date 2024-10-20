package core.basic.web;

import core.basic.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;
    //private final MyLogger myLogger;//왜 주입하는지 모르겟음
    //사용할려면 무조건 주입받아야하나?
    public void logic(String id) {
        MyLogger myLogger =myLoggerProvider.getObject();
        myLogger.log("Service id= "+id);

    }
}
