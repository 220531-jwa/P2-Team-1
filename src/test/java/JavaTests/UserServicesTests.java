package JavaTests;

import models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.UserDAO;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServicesTests {
    @InjectMocks
    private static UserService mockUs;
    @Mock
    private static UserDAO mockUd;

    @BeforeEach
    public void setupEach(){
        mockUs = new UserService(mockUd);
    }

    @Test
    public void should_createNewUserBuyer(){
        //given
        User mockUser = new User("josh", "josh", 1, "Josh", 1, 0.00, 1);
        //when
        when(mockUd.createUser("josh", "josh", "Josh", 1)).thenReturn(mockUser);
        //then
        assertEquals(mockUser, mockUs.createUser("josh", "josh", "Josh", 1));
    }

    @Test
    public void should_createNewUserSeller(){
        //given
        User mockUser = new User("circlexine", "circle", 1, "Circle Xine", 2, 0.00, 0);
        //when
        when(mockUd.createUser("circlexine", "circle", "Circle Xine", 2)).thenReturn(mockUser);
        //then
        assertEquals(mockUser, mockUs.createUser("circlexine", "circle", "Circle Xine", 2));
    }
    @Test
    public void should_checkForUniqueUsername(){
        //given
        List<String> mockUsernames = new ArrayList<>();
        mockUsernames.add("Josh");
        mockUsernames.add("James");
        //when
        when(mockUd.getAllUsernames()).thenReturn(mockUsernames);
        //then
        assertEquals(true, mockUs.checkUniqueUsername("tinendo"));
    }

    @Test
    public void should_failCheckForUniqueUsername(){
        //given
        List<String> mockUsernames = new ArrayList<>();
        mockUsernames.add("Josh");
        mockUsernames.add("James");
        //when
        when(mockUd.getAllUsernames()).thenReturn(mockUsernames);
        //then
        assertFalse(mockUs.checkUniqueUsername("Josh"));

    }

    @Test
    public void should_loginUser(){
        //given
        User mockUser = new User("josh", "josh", 1, "Josh", 1, 0.00, 1);
        //when
        when(mockUd.getUser("josh")).thenReturn(mockUser);
        //then
        assertEquals(mockUser, mockUs.loginUser("josh", "josh"));
    }

    @Test
    public void should_notLoginUser(){
        //given
        User mockUser = new User("josh", "josh", 1, "Josh", 1, 0.00, 1);
        //when
        when(mockUd.getUser("josh")).thenReturn(mockUser);
        //then
        assertNotEquals(mockUser, mockUs.loginUser("josh", "john"));
    }

    @Test
    public void should_updateBalance(){
        //given
        User mockUser = new User("josh", "josh", 1, "Josh", 1, 100.00, 1);
        double currentBal = 40.02;
        double total = 59.98;
        //when
        when(mockUd.updateBalance(1, total)).thenReturn(currentBal);
        //then
        assertEquals(currentBal, mockUs.updateBalance(1, total));
    }

    @Test
    public void should_addRewardPoints(){
        //given
        int points = 1;
        //when
        when(mockUd.addRewardPoints(1, 1)).thenReturn(points);
        //then
        assertEquals(points, mockUs.addRewardPoints(1, 10.00));
    }

    @Test
    public void should_showRewardPoints(){
        //given
        User mockUser = new User("josh", "josh", 1, "Josh", 1, 100.00, 1);
        //when
        when(mockUd.showRewardPoints(1)).thenReturn(1);
        //then
        assertEquals(1, mockUs.showRewardPoints(1));
    }
}
