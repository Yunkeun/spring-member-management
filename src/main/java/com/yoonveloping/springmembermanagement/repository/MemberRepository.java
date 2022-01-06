package com.yoonveloping.springmembermanagement.repository;

import com.yoonveloping.springmembermanagement.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

	Member save(Member member);

	Optional<Member> findById(Long id);

	Optional<Member> findByName(String name);

	List<Member> findAll();
}
