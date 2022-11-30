package com.ack84.spring;


import com.ack84.spring.repository.JpaMemberRepository;
import com.ack84.spring.repository.MemberRepository;
import com.ack84.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //springdata JPA
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //jpa
//    private final EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
    //jdbcTemplate
//    private final DataSource dataSource;

//    public SpringConfig(DataSource dataSource) {
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
//      return new JdbcTemplateRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
