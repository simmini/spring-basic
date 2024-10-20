package core.basic.beanfind;

import core.basic.discount.DiscountPolicy;
import core.basic.discount.FixDiscountPolicy;
import core.basic.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시,자식이 둘 이상 있으면,중복 오류가 발생한다.")
    void findBeanByParentTypeDuplicate(){
        //DiscountPolicy bean=ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,()->ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시,자식이 둘 이상 있으면,빈이름을 지정하면 된다.")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy=ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        //rateDiscountPolicy로 부터 반환된 실제 객체는 RateDiscountPolicy타입
        //rateDiscountPolicy변수내에 담긴 실제객체가 RateDiscountPolicy타입의 인스턴트인지 비교
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위타입으로 조회(안좋은 방법)")
    void findBeanBySubType(){
        //구체적인 하나의 타입으로 지정해서 찾음
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +" value = "+beansOfType.get(key));
        }
    }
    @Test
    @DisplayName("부모 타입으로 모두 조회하기-object")
    void findAllBeanByObjectType(){
        //자바객체는 모두 object타입이므로 스프링빈에 등록된 모든 객체가 나옴
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key +" value = "+beansOfType.get(key));
        }

    }

    @Configuration
    static class TestConfig{
        //DiscountPolicy타입으로 조회하면 두개 나오도록 설정
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDisCountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
