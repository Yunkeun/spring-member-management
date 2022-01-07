package com.yoonveloping.springmembermanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.yoonveloping.springmembermanagement.domain.Member;
import com.yoonveloping.springmembermanagement.repository.MemberRepository;
import com.yoonveloping.springmembermanagement.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

	@Autowired
	MemberService memberService;

	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("Yunkeun3");

		// when
		Long saveId = memberService.join(member);

		// then
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	void 중복_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("Yunkeun");

		Member member2 = new Member();
		member2.setName("Yunkeun");

		// when
		memberService.join(member1);
		IllegalStateException ISE = assertThrows(IllegalStateException.class,
			() -> memberService.join(member2));
		assertThat(ISE.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}

	@Test
	void findMembers() {
		// given
		Member member1 = new Member();
		member1.setName("Yunkeun");

		Member member2 = new Member();
		member2.setName("Cho");

		// when
		memberService.join(member1);
		memberService.join(member2);

		// then
		int result = memberService.findMembers().size();
		assertThat(2).isEqualTo(result);
	}

	@Test
	void findOne() {
		// given
		Member member1 = new Member();
		member1.setName("Yunkeun");

		// when
		memberService.join(member1);

		// then
		Member result = memberService.findOne(member1.getId()).get();
		assertThat(member1.getName()).isEqualTo(result.getName());
	}
}