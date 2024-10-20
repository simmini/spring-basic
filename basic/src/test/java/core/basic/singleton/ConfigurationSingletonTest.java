package core.basic.singleton;

import core.basic.AppConfig;
import core.basic.member.MemberRepository;
import core.basic.member.MemberServiceImpl;
import core.basic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        
        //구체클래스로 꺼내면 안좋지만 테스트용이라 구체(MemberServiceImpl,OrderServiceImpl)에 getMemberRepository함수에 넣었기 떄문에 해당함수로 가져옴
        MemberServiceImpl memberService=ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService=ac.getBean("orderService",OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1=memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep(){
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean=ac.getBean(AppConfig.class);
        System.out.println("bean = " + bean.getClass());
    }
}
