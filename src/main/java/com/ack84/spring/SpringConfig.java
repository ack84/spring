package com.ack84.spring;


import com.ack84.spring.repository.MemberRepository;
import com.ack84.spring.repository.MemoryMemberRepository;
import com.ack84.spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
