package com.users.impl;

import com.users.dao.User;
import com.users.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by jose.medina on 1/19/2017.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private static final String INSERT_USER_SQL = "INSERT INTO users VALUES (NULL, ?, ?);";
    private static final String UPDATE_USER_SQL = "UPDATE users SET username = ? , password = ? WHERE id = ? ;";
    private static final String UPDATE_USER_USERNAME_SQL = "UPDATE users SET username = ? WHERE id = ? ;";
    private static final String UPDATE_USER_PASS_SQL = "UPDATE users SET password = ? WHERE id = ? ;";
    private static final String SELECT_USER_SQL = "SELECT * FROM users WHERE id = ? ;";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM users;";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ? ;";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int insert(User user) {
        return jdbcTemplate.update(INSERT_USER_SQL,user.getUsername(),user.getPassword());
    }


    public int update(User user) {
        if(!StringUtils.isEmpty(user.getPassword()) && !StringUtils.isEmpty(user.getUsername()))
            return jdbcTemplate.update(UPDATE_USER_SQL,
                    user.getUsername(),
                    user.getPassword(),
                    user.getId()
                    );
        else if(!StringUtils.isEmpty(user.getUsername()))
            return this.updateUsername(user);
        else
            return this.updatePassword(user);
    }

    private int updateUsername(User user) {
        return jdbcTemplate.update(UPDATE_USER_USERNAME_SQL,
                user.getUsername(),
                user.getId()
        );
    }

    private int updatePassword(User user) {
        return jdbcTemplate.update(UPDATE_USER_PASS_SQL,
                user.getPassword(),
                user.getId()
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update(DELETE_USER,id);
    }

    public User findUser(int id) {
        return (jdbcTemplate.query(SELECT_USER_SQL, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        },id)).get(0);
    }

    public List<User> findAllUsers() {
        return jdbcTemplate.query(SELECT_ALL_USERS_SQL, new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
            }
        });
    }
}
