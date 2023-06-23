package com.example.demo.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository underTest;
	
	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}

	@Test
	void itShouldCheckIfStudentExistsByEmail() {
		// given
		String email = "mdreza1472@gmail.com";
		Student student = new Student(
			"Reza",
			email,
			Gender.MALE
		);
		underTest.save(student);
		
		// when
		boolean expected = underTest.selectExistsEmail(email);
		
		// then
		assertThat(expected).isTrue();
	}
	
	@Test
	void itShouldCheckIfStudentEmailDoesNotExist() {
		// given
		String email = "mdreza1472@gmail.com";
				
		// when
		boolean expected = underTest.selectExistsEmail(email);
		
		// then
		assertThat(expected).isFalse();
	}

}
