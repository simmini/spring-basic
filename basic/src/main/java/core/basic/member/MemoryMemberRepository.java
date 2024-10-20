package core.basic.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{


    //static을 사용하는 이유는 단하나의 map만 사용하기 위해 static으로 map을 생성
    //static을 제거하면 MemberRepository를 작성할때마다 각각의 인스턴스가 자기만의 map을 갖게됨
    // =>이 map은 인스턴스끼리 공유하지않고 자기혼자만 사용하는 map이됨
    private static Map<Long,Member> store=new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(),member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
