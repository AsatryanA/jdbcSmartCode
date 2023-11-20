package am.smartCode.lesson1;

import am.smartCode.lesson1.Model.Address;
import am.smartCode.lesson1.Model.User;
import am.smartCode.lesson1.Repository.UserRepository;
import am.smartCode.lesson1.Repository.jpaImpl.UserRepositoryJpaImpl;
import am.smartCode.lesson1.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryJpaImpl(HibernateUtil.getSessionFactory());



/*        Address address = new Address();
        address.setCountry("Armenia");

        var user = new User();
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setEmail("email1@gmail.com");
        user.setPassword("password123");
        user.setAge(20);
        user.setAmount(1000);
        user.setAddresses(List.of(address));

        userRepository.create(user);*/



        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        User user = session.get(User.class, 16L);

        user.getAddresses().forEach(System.out::println);
        transaction.commit();
        session.close();
    }
}
