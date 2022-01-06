package com.yoonveloping.springmembermanagement;

import com.yoonveloping.springmembermanagement.repository.MemberRepository;
import com.yoonveloping.springmembermanagement.repository.MemoryMemberRepository;
import com.yoonveloping.springmembermanagement.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
