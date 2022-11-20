package com.ack84.spring;


import com.ack84.spring.repository.JdbcTemplateRepository;
import com.ack84.spring.repository.MemberRepository;
import com.ack84.spring.repository.MemoryMemberRepository;
import com.ack84.spring.service.MemberService;
import hello.hellospring.repository.JdbcMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //생성자 주입
    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
//      return new MemoryMemberRepository();
//      return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateRepository(dataSource);
    }
}
