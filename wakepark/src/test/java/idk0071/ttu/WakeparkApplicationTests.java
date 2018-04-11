package idk0071.ttu;

import idk0071.ttu.controller.ReservationService;
import idk0071.ttu.controller.UserService;
import idk0071.ttu.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WakeparkApplicationTests {
	UserRepository userRepositoryMock;
	@Before
	public void setUp() {
		userRepositoryMock = mock(UserRepository.class);
	}

	@Test
	public void testGetUserStatus() {
		UserService.getUserStatus("kasutaja", userRepositoryMock);
		Mockito.verify(userRepositoryMock).findByUsername("kasutaja");
	}
	@Test
	public void testUserExists() {
		UserService.userExists("kasutaja", "kasutaja", userRepositoryMock);
		Mockito.verify(userRepositoryMock).existsByUsername("kasutaja");
	}
	@Test
	public void testUserNameTaken() {
		UserService.usernameTaken("kasutaja", userRepositoryMock);
		Mockito.verify(userRepositoryMock).findAll();
	}
	@Test
	public void testGetRideStart() {
		LocalDateTime now = LocalDateTime.now();
		assertEquals(LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 12, 15),
				ReservationService.getRideStart("12:15"));
	}


}
