package core.basic.autowired;

import core.basic.AutoAppConfig;
import core.basic.discount.DiscountPolicy;
import core.basic.member.Grade;
import core.basic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);//왜 autoAppConfig를 땡겨오는지 모르겟음/ 컴포넌트 스캔을 하면서

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        int discountPrice=discountService.discount(member,10000,"fixDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscountPrice).isEqualTo(2000);
    }


    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List <DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy=policyMap.get(discountCode);//fixDiscountPolicy넣어줌
            return discountPolicy.discount(member,price);
        }
    }

}
