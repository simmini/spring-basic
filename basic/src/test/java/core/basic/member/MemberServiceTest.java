package core.basic.member;

import core.basic.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

   // MemberService memberService=new MemberServiceImpl();
    MemberService memberService;

    //beforeEach밖(공통설정)에서 AppConfig를 선언하는 경우에
    //모든 테스트에서 동일한 appconfig객체를 사용하게 됨(전역변수처럼)
    //장점: 테스트 매서드들이 같은 설정을 공유하고자 할때 유용

    //beforEach안(독립적설정)에서 AppConfig를 선언하는 경우에
    //매테스트메서드마다 새로운 appconfig객체가 생성됨
    // 테스트를 독립적으로 실행하고 각테스트 메서드마다 서로 영향을 주지않는 장점
    //각 테스트마다 appconfig객체가 다른 경우에는 특정테스트에서 오류방지

            //테스트를 실행전에 무조건 실행됨=>실행을 두번하면 두번 실행됨
    @BeforeEach
    public void beforeEach(){

        AppConfig appConfig=new AppConfig();
        memberService=appConfig.memberService();
    }

    @Test
    void join(){
        //given
        Member member=new Member(1L,"memberA",Grade.VIP);
        //when
        memberService.join(member);
        Member findMember=memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);


    }
}
