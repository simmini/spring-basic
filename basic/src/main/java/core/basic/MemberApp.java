package core.basic;

import core.basic.member.Grade;
import core.basic.member.Member;
import core.basic.member.MemberService;
import core.basic.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //1. appconfig에서 memberService달라하면 memberserviceImpl객체 생성함
        // memberserviceImpl객체는 memorymemberRepository를사용할거야 하면서 주입해줌
        //MemberService memberService=new MemberServiceImpl();

        //2. 스프링컨테이너에 객체를 스프링빈으로 등록하고 ,스프링컨테이너에서 스프링빈을 찾아서 사용하도록 변경
        //AppConfig appConfig=new AppConfig();
        //MemberService memberService=appConfig.membeService();

        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService= applicationContext.getBean("memberService",MemberService.class);



        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember="+findMember.getName());
        System.out.println("member="+member.getName());
    }
}
