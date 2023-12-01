package am.smartCode.lesson1.repository.springImpl;

import am.smartCode.lesson1.model.User;
import am.smartCode.lesson1.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public class UserRepositorySpringImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean update(User user, Long id) {
        return false;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
