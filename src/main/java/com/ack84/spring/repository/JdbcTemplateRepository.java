package com.ack84.spring.repository;

import com.ack84.spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public Member save(Member member) {
        //SimpleJdbcInsert 에서 테이블과 pk값을 지정해주면 insert query를 자동으로 생성하여 수행해줌
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("name",member.getName());

        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        List<Member> result = jdbcTemplate.query("select * from Member where id = ? ", memberRowMapper(), id);
        return result.stream().findAny();

        //조회한 결과값을 RowMapper로 list형태로 전달하고
        //Optional값인 list에 대해서 .stream().findAny()로 꺼냄
//        List<Member> result = jdbcTemplate.query("select * from Member where id = ?", memberRowMapper(),id);
//        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from Member where name = ?", memberRowMapper(),name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from Member",memberRowMapper());

    }

//    private RowMapper<Member> memberRowMapper(){
//        return (rs, rowNum) -> {
//            Member member = new Member();
//            member.setId(rs.getLong("id"));
//            member.setName(rs.getString("name"));
//            return member;
//        };
//    }

    private RowMapper<Member> memberRowMapper(){
        //lamda 스타일로 변경 가능
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
            }
        };

    }
}
