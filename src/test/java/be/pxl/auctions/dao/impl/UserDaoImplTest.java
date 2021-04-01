package be.pxl.auctions.dao.impl;

import be.pxl.auctions.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
public class UserDaoImplTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UserDaoImpl userDao;

	@Test
	public void userCanBeSavedAndRetrievedById() {
		User user = new User();
		user.setFirstName("Mark");
		user.setLastName("Zuckerberg");
		user.setDateOfBirth(LocalDate.of(1989, 5, 3));
		user.setEmail("mark@facebook.com");
		long newUserId = userDao.saveUser(user).getId();
		entityManager.flush();
		entityManager.clear();

		Optional<User> retrievedUser = userDao.findUserById(newUserId);
		assertTrue(retrievedUser.isPresent());

		assertEquals(user.getFirstName(), retrievedUser.get().getFirstName());
		assertEquals(user.getLastName(), retrievedUser.get().getLastName());
		assertEquals(user.getEmail(), retrievedUser.get().getEmail());
		assertEquals(user.getDateOfBirth(), retrievedUser.get().getDateOfBirth());
	}
	@Test
	public void userCanBeSavedAndRetrievedByEmail() {
		User user = new User();
		user.setFirstName("Elon");
		user.setLastName("musk");
		user.setDateOfBirth(LocalDate.of(2099, 2, 3));
		user.setEmail("Elon@Musk.com");
		String newUserEmail = userDao.saveUser(user).getEmail();
		entityManager.flush();
		entityManager.clear();

		Optional<User> retrievedUser = userDao.findUserByEmail(newUserEmail);
		assertTrue(retrievedUser.isPresent());

		assertEquals(user.getFirstName(), retrievedUser.get().getFirstName());
		assertEquals(user.getLastName(), retrievedUser.get().getLastName());
		assertEquals(user.getEmail(), retrievedUser.get().getEmail());
		assertEquals(user.getDateOfBirth(), retrievedUser.get().getDateOfBirth());
	}

	@Test
	public void returnsNullWhenNoUserFoundWithGivenEmail() {
		Optional<User> retrievedUser = userDao.findUserByEmail("Fake@email.com");
		assertEquals(retrievedUser, Optional.empty());

	}

	@Test
	public void allUsersCanBeRetrieved() {
		// TODO implement this test
		// create and save one user
		User user = new User();
		user.setFirstName("jeff");
		user.setLastName("hardy");
		user.setDateOfBirth(LocalDate.of(1900, 2, 3));
		user.setEmail("jaffhardy@amazon.com");
		String newUserEmail = userDao.saveUser(user).getEmail();
		entityManager.flush();
		entityManager.clear();

		// retrieve all users

		List<User> allUsers = userDao.findAllUsers();

		// make sure there is at least 1 user in the list

		assertTrue(userDao.findUserByEmail(allUsers.get(0).getEmail()).isPresent());

		// make sure the newly created user is in the list (e.g. test if a user with this email address is in the list)
		int index =0;
		boolean exist = false;
		for (User now: allUsers) {
			index++;
			if(now.getEmail().equals(newUserEmail)) {
				exist = true;
				break;
			}
		}
		assertTrue(userDao.findUserByEmail(allUsers.get(0).getEmail()).isPresent());
		if(exist) {
			assertTrue(userDao.findUserByEmail(allUsers.get(index).getEmail()).isPresent());
		} else {
			fail();
		}


	}


}
