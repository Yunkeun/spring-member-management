package com.yoonveloping.springmembermanagement.service;

import com.yoonveloping.springmembermanagement.domain.Member;
import com.yoonveloping.springmembermanagement.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MemberService {

	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}


	/**
	 * 회원 등록
	 */
	public Long join(Member member) {
		long start = System.currentTimeMillis();
		try {
			validateDuplicateMember(member); // 중복 회원 검증
			memberRepository.save(member);
			return member.getId();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("join " + timeMs + "ms");
		}

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
		long start = System.currentTimeMillis();
		try {
			return memberRepository.findAll();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("findMembers " + timeMs + "ms");
		}
	}

	/**
	 * id로 회원 조회
	 */
	public Optional<Member> findOne(Long memberId) {
		long start = System.currentTimeMillis();
		try {
			return memberRepository.findById(memberId);
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("findMember by id " + timeMs + "ms");
		}
	}
}
