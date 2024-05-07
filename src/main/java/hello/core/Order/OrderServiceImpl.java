package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{


    //DIP 위반 -> 인터페이스 뿐만 아니라, 구현에도 의존하고 있다.
//    private final MemberRepository memberRepository= new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy= new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //해결책 -> 인터페이스에만 의존하도록 의존관계 변경
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자를 통해서 의존관계 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int disountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, disountPrice);
    }
}
