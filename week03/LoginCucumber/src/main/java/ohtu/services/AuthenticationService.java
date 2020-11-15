package ohtu.services;

import java.lang.Character;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import ohtu.domain.User;

public class AuthenticationService {

  private UserDao userDao;

  public AuthenticationService(UserDao userDao) {
    this.userDao = userDao;
  }

  public boolean logIn(String username, String password) {
    for (User user : userDao.listAll()) {
      if (
        user.getUsername().equals(username) &&
        user.getPassword().equals(password)
      ) {
        return true;
      }
    }

    return false;
  }

  public boolean createUser(String username, String password) {
    if (userDao.findByName(username) != null) {
      return false;
    }

    if (invalid(username, password)) {
      return false;
    }

    userDao.add(new User(username, password));

    return true;
  }

  private boolean invalid(String username, String password) {
    // validity check of username and password
    if (
      username.length() < 3 ||
      password.length() < 8 ||
      containsLettersOnly(password)
    ) {
      return true;
    }

    return false;
  }

  private boolean containsLettersOnly(String str) {
    for (char ch : str.toCharArray()) {
      if (!Character.isLetter(ch)) {
        return false;
      }
    }
    return true;
  }
}
