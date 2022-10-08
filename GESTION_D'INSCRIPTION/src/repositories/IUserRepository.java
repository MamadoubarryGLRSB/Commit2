package repositories;

import entities.User;

public interface IUserRepository {
    public User findLoginAndPassword(String login,String password);
}
