package core.basic;

import core.basic.member.Grade;
import core.basic.member.Member;
import core.basic.member.MemberService;
import core.basic.member.MemberServiceImpl;
import core.basic.order.Order;
import core.basic.order.OrderService;
import core.basic.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        //MemberService memberService=new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();

/*
        AppConfig appConfig=new AppConfig();
        MemberService memberService=appConfig.membeService();
        OrderService orderService=appConfig.orderService();
*/

        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService=applicationContext.getBean("memberService",MemberService.class);
        OrderService orderService=applicationContext.getBean("orderService",OrderService.class);

        Long memberId=1L;
        Member member =new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);


        Order order= orderService.createOrder(memberId,"itemA",20000);

        System.out.println("order="+order.toString());
        System.out.println("order.calculate="+order.calculatePrice());
    }
}
