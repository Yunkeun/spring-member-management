package com.yoonveloping.springmembermanagement.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.yoonveloping.springmembermanagement.domain.Member;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();

	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}

	@Test
	void save() {
		// given
		Member member1 = new Member();
		member1.setName("Yunkeun");

		// when
		repository.save(member1);

		// then
		Member result = repository.findById(member1.getId()).get();
		assertThat(member1).isEqualTo(result);
	}

	@Test
	void findByName() {
		// given
		Member member1 = new Member();
		member1.setName("Cho1");

		Member member2 = new Member();
		member2.setName("Cho2");

		// when
		repository.save(member1);
		repository.save(member2);

		// then
		Member result = repository.findByName("Cho1").get();
		assertThat(member1).isEqualTo(result);
	}

	@Test
	void findAll() {
		// given
		Member member1 = new Member();
		member1.setName("Cho1");

		Member member2 = new Member();
		member2.setName("Cho2");

		// when
		repository.save(member1);
		repository.save(member2);

		// then
		List<Member> result = repository.findAll();
		assertThat(result.size()).isEqualTo(2);
	}
}