package com.ack84.spring.service;

import com.ack84.spring.domain.Member;
import com.ack84.spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {


    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     *회원가입
     */
    public long join(Member member){
        //같은 이름의 회원안 안됨
        //주석처리되어있던 부분을 아래와 같이 변경하였고
        //이후 refactor에 extrac method로 생성함
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        //Optional로 데이터를 감싸고 있어서 ifPresent 또는 get등을 사용 가능함
        result.ifPresent(m -> {
            throw new IllegalStateException("이미존재하는 회원 입니다.");
        });
        */

        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원 입니다.");
                        });
    }

    /**
     * 전체회원조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
