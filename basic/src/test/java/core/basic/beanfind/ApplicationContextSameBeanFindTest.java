package core.basic.beanfind;

import core.basic.AppConfig;
import core.basic.member.MemberRepository;
import core.basic.member.MemberService;
import core.basic.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘이상있으면,중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        //MemberRepository bean = ac.getBean(MemberRepository.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }

    //rateDiscountPolicy 는 RateDiscountPolicy 클래스의 인스턴스기도
    // 하지만, RateDiscountPolicy가 구현하고 있는 DiscountPolicy 인터페이스의 인스턴스이기도 합니다!
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면,빈 이름을 저장하면 된다.")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +"value = "+beansOfType.get(key));
        }
        System.out.println("beanOfType = "+beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    //test용 컨피그 만듬
    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }
}
