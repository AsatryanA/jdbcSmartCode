package am.smartCode.lesson1.repository;



import am.smartCode.lesson1.model.User;

import java.util.List;

public interface UserRepository {

    void create(User user);
    void delete(Long id);
    User getUserByEmail(String email);
    boolean     update(User user,Long id);
    User getUserById(Long id);
    List<User> getAll ();



}
