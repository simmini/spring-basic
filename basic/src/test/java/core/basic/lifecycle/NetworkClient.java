package core.basic.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient //implements InitializingBean,DisposableBean
 {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
        connent();//객체를 생성하면서 연결하겠따!!
        call("초기화 연결 메시지");
    }
    public void setUrl(String url){
        this.url=url;
    }

    //서비스 시작시 호출
    public void connent(){
        System.out.println("connect: "+url);
    }

    //연결한 서버에 메시지 전달
    public void call(String message){
        System.out.println("call: "+url +" ,message= " +message);

    }
    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: "+url);

    }
  /*  @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connent();
        call("초기화 연결 메시지");
    }
    @PreDestroy
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }*/


    /*public void init(){
        System.out.println("NetworkClient.init");
        connent();
        call("초기화 연결 메시지");
    }
    public void close(){
        System.out.println("NetworkClient.close");
        disconnect();
    }*/
    //초기화 빈
  /*  @Override
    public void afterPropertiesSet() {//의존관계가 끝나면 주입해주겠따.
        System.out.println("NetworkClient.afterPropertiesSet");
        connent();
        call("초기화 연결 메시지");

    }

    @Override
    public void destroy() throws Exception {//빈이 종료될때 호출
        System.out.println("NetworkClient.destroy");
        disconnect();
    }*/
}
