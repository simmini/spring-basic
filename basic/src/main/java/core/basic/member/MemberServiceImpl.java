package core.basic.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements  MemberService {

    //dip어긋남 => serviceImpl이 MemberRepository 와 MemoryMemberRepository모두 다 의존하기때문
    //인터페이스뿐만 아니라 구현까지 모두 의존하는 문제점
    //private final MemberRepository memberRepository=new MemoryMemberRepository();

    //dip를지키기위한 방법
    //추상적인거(memberRepository)에만 의존,구체인 MemorymemberRepository는 의존안하고 있음(언급 안함)

    private final MemberRepository memberRepository;


    @Autowired//생성자위에 자동 의존관계주입 (수동의존관계방식:ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository){
       //선언한 memberRepository에 밖에서(appconfig) 선언한 객체가 들어감
        this.memberRepository=memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
