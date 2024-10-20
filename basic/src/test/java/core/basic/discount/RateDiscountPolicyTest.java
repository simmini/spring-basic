package core.basic.discount;

import core.basic.member.Grade;
import core.basic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;//static으로 변경
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy=new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야한다.")
    void vip_0(){
        //given
        Member member=new Member(1L,"memberVip", Grade.VIP);
        //when
        int discount=discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){

        //given
        Member member =new Member(2L,"memberBasic",Grade.BASIC);
        //when
        int discount=discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(0);
    }

}