package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.Order.OrderServiceImpl;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //모두 같은 인스턴스를 참고하고 있다.
        System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println("orderService -> orderRepository = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        //모두 같은 인스턴스를 참고하고 있다.
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = "+ bean.getClass());
        //출력 : bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$bd479d70

//        AppConfig@CGLIB 예상 코드
//        @Bean
//        public MemberRepository memberRepository() {
//            if (memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?) {
//                 return 스프링 컨테이너에서 찾아서 반환;

//            } else { //스프링 컨테이너에 없으면
//                기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
//                return 반환
//            }
//        }
    }


}
