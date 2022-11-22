package vttp.paf.day22.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp.paf.day22.models.User;

import static vttp.paf.day22.repositories.Queries.*;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer createUser(User user) throws Exception {

        return jdbcTemplate.update(SQL_INSERT_USER, 
            user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getDob());

    }
    
}
