package am.smartCode.lesson1.Repository.jdbcImpl;


import am.smartCode.lesson1.Model.User;
import am.smartCode.lesson1.Repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final Connection connection;

    public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
        String query = "create table if not exists users (id bigserial primary key, first_name varchar(255),last_name varchar(255),email  varchar(255) ,password  varchar(255), age int, amount int)";
        try {
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(User user) {
        String createQuery = String.format("insert into users (first_name,last_name,email,password,age,amount) values ('%s','%s','%s','%s','%d','%d')",
                user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword(), user.getAge(), user.getAmount());
        try {
            connection.createStatement().executeUpdate(createQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = String.format("delete from users where id = %d", id);
        try {
            connection.createStatement().executeUpdate(deleteQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(User user, Long id) {
        String update = "Update users set first_name = ?,last_name = ?,email = ?,password = ?,age = ?,amount = ? where id = ?";
        if (getUserById(id) == null) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getAge());
            preparedStatement.setInt(6, user.getAmount());
            preparedStatement.setLong(7, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(Long id) {
        String getQuery = "select * from users where id = ?";
        User user;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = getUserFromResult(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        String getAllQuery = "Select * from users";
        List<User> users;
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(getAllQuery);
            users = getUserFromResult(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User getUserByEmail(String email) {
        User user;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where email =  ?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = getUserFromResult(resultSet).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private List<User> getUserFromResult(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setAge(resultSet.getInt("age"));
            user.setAmount(resultSet.getInt("amount"));
            users.add(user);
        }
        return users;
    }


}
