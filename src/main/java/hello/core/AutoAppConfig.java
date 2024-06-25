package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan(
        basePackages = "hello.core",
        //basePackages = {"hello.core","hello.service"},
        //basePackageClasses = AutoAppConfig.class, -> 해당 클래스의 패키지인 hello.core
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}