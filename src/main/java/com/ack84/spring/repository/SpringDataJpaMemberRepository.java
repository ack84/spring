package com.ack84.spring.repository;

import com.ack84.spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {

    //jpaRepository를 extends하면 인터페이스 만으로도 구현체를 알아서 생성
    @Override
    Optional<Member> findByName(String name);
}
