package com.yoonveloping.springmembermanagement.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.yoonveloping.springmembermanagement.domain.Member;
import com.yoonveloping.springmembermanagement.repository.MemberRepository;
import com.yoonveloping.springmembermanagement.repository.MemoryMemberRepository;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

	MemoryMemberRepository memberRepository;
	MemberService memberService;

	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("Yunkeun");

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

//		try {
//			memberService.join(member2);
//			fail();
//		} catch (IllegalStateException ISE) {
//			assertThat(ISE.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
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

		Member member2 = new Member();
		member2.setName("Cho");

		// when
		memberService.join(member1);
		memberService.join(member2);

		// then
		Member result = memberService.findOne(member1.getId()).get();
		assertThat(member1).isEqualTo(result);
	}
}