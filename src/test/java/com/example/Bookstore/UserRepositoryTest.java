package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository UserRepo;
	
	@Test
	public void findByUsernameShouldReturnUser() {
		User users = UserRepo.findByUsername("admin");
		
		users.getRole().equals("admin");
	}
	
	@Test
	public void createNewUser() {
		User user = new User("tester", "password", "test@test.com", "user");
		UserRepo.save(user);
		
		assertThat(user.getId()).isNotNull();
	}

}
