package services;

import models.User;
import repositories.UserDAO;

import java.util.List;

public class UserService {
    private static UserDAO ud;
    public UserService(UserDAO ud){
        this.ud = ud;
    }

    public static boolean checkUniqueUsername(String username){
        List<String> usernames = ud.getAllUsernames();
        for(int i = 0; i < usernames.size(); i++){
            if(usernames.get(i).equals(username)){
                return false;
            }
        }
        return true;
    }
    public User createUser(String username, String password, String name){
        if(checkUniqueUsername(username) == true){
            return ud.createUser(username, password, name);
        } else {
            return null;
        }
    }


}
