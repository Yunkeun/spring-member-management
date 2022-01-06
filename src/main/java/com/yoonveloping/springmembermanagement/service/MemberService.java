package com.yoonveloping.springmembermanagement.service;

import com.yoonveloping.springmembermanagement.domain.Member;
import com.yoonveloping.springmembermanagement.repository.MemberRepository;
import com.yoonveloping.springmembermanagement.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;

public class MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();

	/**
	 * 회원 등록
	 */
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
			.ifPresent(m -> {
				throw new IllegalStateException("이미 존재하는 회원입니다.");
			});
	}

	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	/**
	 * id로 회원 조회
	 */
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
}