package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //OCP, DIP 위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //해결책 -> 인터페이스에만 의존하도록 변경
    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository){
        this. memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
