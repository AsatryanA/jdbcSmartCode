package am.smartCode.lesson1;

import am.smartCode.lesson1.Model.User;
import am.smartCode.lesson1.Repository.UserRepository;
import am.smartCode.lesson1.Repository.jpaImpl.UserRepositoryJpaImpl;
import am.smartCode.lesson1.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryJpaImpl(HibernateUtil.getSessionFactory());

    User user = new User();
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setEmail("email@gmail.com");
        user.setPassword("password123");
        user.setAge(20);
        user.setAmount(1000);
        userRepository.create(user);

        System.out.println(userRepository.getAll());
    }
}
