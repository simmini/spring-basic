package core.basic.order;

import core.basic.discount.FixDiscountPolicy;
import core.basic.member.Grade;
import core.basic.member.Member;
import core.basic.member.MemoryMemberRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createdOrder(){

        MemoryMemberRepository memberRepository=new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));

        //createOrder에 필요한 객체 MemberRepository,DiscountPolicy를 안넣어주면 컴파일오류 뜸
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository,new FixDiscountPolicy());
        Order order=orderService.createOrder(1L,"itemA",10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}