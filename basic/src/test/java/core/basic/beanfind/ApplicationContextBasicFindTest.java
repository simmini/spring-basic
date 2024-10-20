package core.basic.beanfind;

import core.basic.AppConfig;
import core.basic.member.MemberService;
import core.basic.member.MemberServiceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈이름으로 조회")
    void findBeanByName(){
        MemberService memberService=ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() ="+ memberService.getClass());
        //AppConfig.memberService()로 부터 반환되는 실제 객체는 MemberServiceImpl타입
        //memberService변수 내에 담긴 실제 객체가 MemberServiceImpl타입의 인스턴스인지 비교
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

    }
    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService=ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("인터페이스 없이 구체로 조회")
    void findBeanByName2(){
        MemberService memberService=ac.getBean("memberService",MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈이름으로 조회x(실패테스트)")
    void findBeanByNameX() {
        //ac.getBean("xxxx",MemberService.class);

        //MemberService xxxx=ac.getBean("xxxx",MemberService.class);
        //왼쪽 에있는 걸 실행할 경우 오른쪽에 있는 에러가 무조건 터져야함
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
    }
}
