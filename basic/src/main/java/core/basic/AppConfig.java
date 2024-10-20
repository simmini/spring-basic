package core.basic;

import core.basic.discount.DiscountPolicy;
import core.basic.discount.FixDiscountPolicy;
import core.basic.discount.RateDiscountPolicy;
import core.basic.member.MemberRepository;
import core.basic.member.MemberService;
import core.basic.member.MemberServiceImpl;
import core.basic.member.MemoryMemberRepository;
import core.basic.order.OrderService;
import core.basic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
appConfig는 애플리케이션의 실제동작에 필요한 구현객체를 생성
생성한 객체인스턴스의 참조값(MemoryMemberRepository)을 생성자를 통해서 주입해준다.
=>사용영역과 구성영역을 분리하였기 떄문에 sw요소를 새롭게 확장해도 사용영역의 변경은 닫혀있음
* */

@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //두개의 new가 되고 있음 싱글톤이 깨지는것처럼 보임(싱글톤은 한번만 생성되야함)
    //call AppConfig.memberService
    //call AppConfig.memberRepository

    @Bean
    public MemberService memberService(){
        //생성자를 통해서 객체가 들어감=>생성자 주입
        //return new MemberServiceImpl(new MemoryMemberRepository());
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());

    }

    //역할과 구현을 잘드러나게 하기위해 분리
    //이렇게 만들면 나중에 MemoryMemberRepository=> JDBCMemberRepository사용할때 해당 부분만 변경하면됨!
    @Bean
    public   MemberRepository memberRepository() {

        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    //OrderService호출하면 orderServiceImpl를 반환하는데
    // 거기에  MemoryMemberRepository,FixDiscountPolicy들어감
    @Bean
    public OrderService orderService(){

        System.out.println("call AppConfig.orderService");
       //return null;
         return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();

    }

}
