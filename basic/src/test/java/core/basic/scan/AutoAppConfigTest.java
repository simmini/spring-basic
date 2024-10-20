package core.basic.scan;

import core.basic.AutoAppConfig;
import core.basic.member.MemberRepository;
import core.basic.member.MemberService;
import core.basic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages="core.basic.member",
        excludeFilters =@ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService=ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean=ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository=bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}
