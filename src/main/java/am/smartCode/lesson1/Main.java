package am.smartCode.lesson1;


import am.smartCode.lesson1.model.User;
import am.smartCode.lesson1.repository.UserRepository;

import am.smartCode.lesson1.repository.jdbcImpl.UserRepositoryJdbcImpl;
import am.smartCode.lesson1.repository.jpaImpl.UserRepositoryJpaImpl;
import am.smartCode.lesson1.repository.springImpl.UserRepositorySpringImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        var context = new ClassPathXmlApplicationContext("application.xml");
        var userRepositorySpring = (UserRepository)context.getBean("userRepositorySpringImpl");

        var user = User.builder()
                .firstName("Karen")
                .age(20)
                .email("Kgfdsfsdfsdsrfen")
                .amount(200)
                .build();
        userRepositorySpring.create(user);
    }
}
