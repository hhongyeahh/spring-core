package hello.core.Order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
//    @Test
//    void createOrder(){
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.createOrder(1L,"itemA",10000);
//        //NullPointerException
//    }


    //순수한 자바코드로 테스트 만들기
    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L,"itemA",10000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}