package services;

import daos.UserDao;
import entities.User;

import java.util.Optional;

public class UserService extends ServiceAbstract<User> {
    private UserDao userDao = new UserDao();
    public Optional<User> getByUsername(String userName) {
        return userDao.getByUsername(userName);
    }

    public int save(User user) {
        return userDao.save(user);
    }
}
