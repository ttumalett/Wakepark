package idk0071.ttu;

import idk0071.ttu.controller.ReservationService;
import idk0071.ttu.controller.UserService;
import idk0071.ttu.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BackEndTest {

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