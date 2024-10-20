package core.basic.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value="request")
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"] "+message);
        System.out.println("["+uuid+"] request scope bean create :"+this);
    }
    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
    }
    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean create : "+this);
    }
}
