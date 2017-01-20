package test;

import com.users.dao.User;
import com.users.dao.UserDao;
import com.users.impl.UserDaoImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by jose.medina on 1/19/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:databaseConfiguration.xml"})
public class testDatabase {

    @Autowired
    private UserDao userDao;

    @Test
    public void testDB() {
        User u = new User();
        u.setUsername("test");
        u.setPassword("test");
        int result = userDao.insert(u);
        System.out.println("Res: " + result);
        assertNotEquals(0,result);
    }
}
