package am.smartCode.lesson1.repository.jpaImpl;

import am.smartCode.lesson1.model.User;
import am.smartCode.lesson1.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

public class UserRepositoryJpaImpl implements UserRepository {

    private  SessionFactory sessionFactory;

    public UserRepositoryJpaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(User user) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();


        session.save(user);
        transaction.commit();
        session.close();

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public User getUserByEmail(String email) {
        var session = sessionFactory.openSession();
        var query = session.createQuery("Select * from users where email = " + email, User.class);
        return query.getSingleResult();
    }

    @Override
    public boolean update(User user, Long id) {
        var session = sessionFactory.openSession();
        session.update(user);
        session.close();
        return true;
    }

    @Override
    public User getUserById(Long id) {
        var session = sessionFactory.openSession();
        User user = session.get(User.class, id);

        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        var session = sessionFactory.openSession();
        var query = session.createQuery("FROM User", User.class);
        return query.getResultList();
    }
}
