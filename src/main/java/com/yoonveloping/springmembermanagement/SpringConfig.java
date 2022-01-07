package com.yoonveloping.springmembermanagement;

import com.yoonveloping.springmembermanagement.repository.JdbcMemberRepository;
import com.yoonveloping.springmembermanagement.repository.JdbcTemplateMemberRepository;
import com.yoonveloping.springmembermanagement.repository.MemberRepository;
import com.yoonveloping.springmembermanagement.service.MemberService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	private DataSource dataSource;

	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource);
		return new JdbcTemplateMemberRepository(dataSource);
	}
}
