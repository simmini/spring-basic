package core.basic.singleton;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1=ac.getBean(StatefulService.class);
        StatefulService statefulService2=ac.getBean(StatefulService.class);

        //threadA: A사용자 10000원 주문
        int price1=statefulService1.order("userA",10000);
        //threadB: B사용자 20000원 주문
        int price2= statefulService2.order("userB",20000);
        System.out.println("price1 = " + price1);


        //threadA: 사용자 A주문금액 조회
        //int price=statefulService1.getPrice();//<-같은 인스턴스를 써서 statefulService2로 인해 바꿔치기되서  price는 20000원이됨
        //System.out.println("price = " + price);

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}