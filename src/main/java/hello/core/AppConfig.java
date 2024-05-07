package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

public class AppConfig {
    //애플리케이션의 전체 동작 방식을 구성(config)하기 위해서, 구현 객체를 생성하고,
    // 연결하는 책임을 가지는 별도의 설정 클래스를 만들자

    //AppConfig 리팩토링 전
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());//구현 객체 생성
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());//구현 객체 생성
//    }

    //AppConfig 리팩토링 후
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
