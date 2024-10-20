package core.basic.order;

import core.basic.annotation.MainDiscountPolicy;
import core.basic.discount.DiscountPolicy;
import core.basic.discount.FixDiscountPolicy;
import core.basic.discount.RateDiscountPolicy;
import core.basic.member.Member;
import core.basic.member.MemberRepository;
import core.basic.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //구체에 의존하지않고 인터페이스에만 의존하도록 변경=>dip지킴
//3. 필드주
    //private final MemberRepository memberRepository=new MemoryMemberRepository();
     private final MemberRepository memberRepository;


    //private final DiscountPolicy discountPolicy=new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy=new RateDiscountPolicy();
      private  final DiscountPolicy discountPolicy;
//2.setter주입
  /*  @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository=memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy){
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy=discountPolicy;
    }*/

//1. 생성자 주입
 // @Autowired//생성자 1개면 autowired를 생략 가능
//==@RequriedArgsConstructor
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy){
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository=memberRepository;
        this.discountPolicy=discountPolicy;
    }

//4. 일반메서드 주입
   /* @Autowired
    public void init(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
        this.discountPolicy=discountPolicy;
    }*/
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //할인은 나도 모르겠고 회원정보 줄테니깐 할인되서 값알려줘
        //할인 값이 변경될 경우 서비스에서 변경할 거없이 order만 변경하면 되서
        //단일책임원칙 잘지켜짐


        Member member=memberRepository.findById(memberId);
        int discountPrice=discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
