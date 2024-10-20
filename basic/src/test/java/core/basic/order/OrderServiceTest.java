package core.basic.order;

import core.basic.AppConfig;
import core.basic.discount.FixDiscountPolicy;
import core.basic.member.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    
    //MemberService memberService=new MemberServiceImpl();
   // OrderService orderService=new OrderServiceImpl();
    AppConfig appConfig=new AppConfig();

    MemberService memberService;
    OrderService orderService;

    //테스트를 실행전에 무조건 실행됨=>실행을 두번하면 두번 실행됨
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        memberService=appConfig.memberService();
        orderService=appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId=1L;
        Member member=new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);
        
        Order order=orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        
    }


   /* @Test
    void fieldInjectionTest(){
        //임의로 new 해서 생성한 객체는 autowired가 안됨=>그래서 setter필요함
        //OrderServiceImpl orderService = new OrderServiceImpl();

        //orderService.createOrder(1L,"itemA",10000);

    }*/
}
