package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
    // -> 역할과 구현 클래스가 한 눈에 들어옴
    // MemoryMemberRepository의 중복을 제거하여, 이제 MemoryMemberRepository를 다른 구현체로 변경할 때, 한 부분만 변경하면 됨
    @Bean //(name ="mmm") -> 이렇게 이름을 임의로 바꿀 수 있음
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy(); -> 다른 구현체로 변경할때 (배우 변경할때 이부분만 변경하면됨)
        return new RateDiscountPolicy();
    }
}
