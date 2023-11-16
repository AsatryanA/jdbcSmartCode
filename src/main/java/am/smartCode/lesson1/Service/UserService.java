package am.smartCode.lesson1.Service;


import am.smartCode.lesson1.Model.User;
import am.smartCode.lesson1.Repository.UserRepository;
import am.smartCode.lesson1.exceptions.UserNotFoundException;
import am.smartCode.lesson1.exceptions.ValidationException;
import am.smartCode.lesson1.util.DatabaseConnection;
import am.smartCode.lesson1.util.constats.Message;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(User user) {
        registerValidation(user);
        userRepository.create(user);
        return true;
    }

    public void transferAmount(Long fromUser, Long toUser, int amount) throws InterruptedException, SQLException {
        Connection connection = DatabaseConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        User userFrom = userRepository.getUserById(fromUser);
        User userTo = userRepository.getUserById(toUser);

        userFrom.setAmount(userFrom.getAmount() - amount);
        userTo.setAmount(userTo.getAmount() + amount);

        try {
            userRepository.update(userFrom, fromUser);
            Thread.sleep(100000);
            userRepository.update(userTo, toUser);
            connection.commit();
        } catch (Throwable e) {
            connection.rollback();
        }

    }

    public User login(String email, String password) {
        loginValidation(email, password);
        User userByEmail = userRepository.getUserByEmail(email);
        if (userByEmail == null) {
            throw new UserNotFoundException(Message.USER_NOT_FOUND);
        }
        if (!Objects.equals(userByEmail.getPassword(), password)) {
            throw new UserNotFoundException("Invalid password");
        }
        return userByEmail;
    }

    private void registerValidation(User user) {
       final String EMAIL_REGEX =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException("Email must not be null");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password must not be null");
        }
        if (user.getPassword().length() < 8) {
            throw new RuntimeException("Password must be less then 8 symbols");
        }
        if (!Pattern.compile(EMAIL_REGEX).matcher(user.getEmail()).matches()) {
            throw new RuntimeException("Invalid Email");
        }
    }

    private void loginValidation(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            throw new ValidationException(Message.USERNAME_OR_PASSWORD_IS_EMPTY);
        }
    }


    public void changePassword(String username, String newPassword) {
        User userByEmail = userRepository.getUserByEmail(username);
        if (userByEmail == null) {
            throw new UserNotFoundException(Message.USER_NOT_FOUND);
        }
        userByEmail.setPassword(newPassword);
        userRepository.update(userByEmail, userByEmail.getId());
    }
}
