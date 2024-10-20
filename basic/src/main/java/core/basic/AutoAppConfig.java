package core.basic;

import core.basic.discount.DiscountPolicy;
import core.basic.member.MemberRepository;
import core.basic.member.MemoryMemberRepository;
import core.basic.order.OrderService;
import core.basic.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//appconfig.class는 @configuration이 붙어있어서 자동 스캔 대상이 되버림
//충돌을 피하기 위해서 configuration어노테이션 붙은 클래스 제외함
@ComponentScan(excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class))
public class AutoAppConfig {

 /*   @Autowired MemberRepository memberRepository;

    @Autowired
    DiscountPolicy discountPolicy;

    @Bean
    OrderService orderService(){
        return new OrderServiceImpl(memberRepository,discountPolicy);
    }*/
    //수동등록=>중복된 빈등록시 충돌 테스트
    /*@Bean(name="memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }*/


}
