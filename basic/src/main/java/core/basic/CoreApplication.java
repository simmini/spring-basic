package core.basic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//AutoAppConfig와 같은 설정클래스를 만들지 않아도
//@SpringBootApplicatipn에 @componentScan에 의해 빈을 등록하지만
//,AutoAppConfig에 있는 빈스캔 범위를 제한하기위해 만듬

@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class,args);
    }
}



