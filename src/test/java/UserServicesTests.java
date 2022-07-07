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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void should_createNewUser(){
        //given
        User mockUser = new User("josh", "josh", 1, "Josh", 1, 0.00);
        //when
        when(mockUd.createUser("josh", "josh", "Josh")).thenReturn(mockUser);
        //then
        assertEquals(mockUser, mockUs.createUser("josh", "josh", "Josh"));
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
        assertEquals(false, mockUs.checkUniqueUsername("Josh"));

    }

    @Test
    public void should_loginUser(){

    }

    @Test
    public void should_notLoginUserBadUName(){

    }

    @Test
    public void should_notLoginUserBadPW(){

    }
}
