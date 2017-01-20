package com.main;

import com.constant.Commons;
import com.users.dao.User;
import com.users.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by jose.medina on 1/19/2017.
 */
public class Application {
    static final String MENU = "=== Simple ABC With Spring ===\n" +
            "0 - Exit\n" +
            "1 - Insert User\n" +
            "2 - Modify User\n" +
            "3 - Search User By Id\n" +
            "4 - See all Users\n" +
            "5 - Delete User\n" +
            "> ";
    static final String SUCCESS_MESSAGE = "Success!";
    static final String ERROR_MESSAGE = "ERROR :C";

    static final Logger LOG = Logger.getLogger(Application.class.getName());
    @Autowired
    private UserDao userDao;

    public void displayMenu() {
        LOG.info(MENU);
    }

    public void start() {
        int option = -1;
        while(option != 0) {
            displayMenu();
            option = new Scanner(System.in).nextInt();
            switch (option) {
                case 0:
                    break;

                case 1:
                    this.insertUser();
                    break;
                case 2:
                    this.updateUser();
                    break;
                case 3:
                    this.findUser();
                    break;
                case 4:
                    StringBuilder builder = new StringBuilder("ID\tUSERNAME\tPASSWORD\n");
                    for(User user : userDao.findAllUsers())
                        builder.append(user.getId()+"\t"+user.getUsername()+"\t"+user.getPassword()+"\n");
                    LOG.info(builder.toString());
                    break;
                case 5:
                    this.deleteUser();
                    break;
                default:
                    LOG.info("Undefined option...\n");
                    break;
            }
        }
    }
    public void insertUser() {
        LOG.info("Insert user name > ");
        String username = new Scanner(System.in).nextLine();
        LOG.info("Insert password > ");
        String password = new Scanner(System.in).nextLine();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if(userDao.insert(user) == Commons.SUCCESS_INSERT) {
            LOG.info(SUCCESS_MESSAGE);
        } else {
            LOG.info(ERROR_MESSAGE);
        }
    }

    public void updateUser() {
        LOG.info("Insert id to modify > ");
        int id = new Scanner(System.in).nextInt();
        LOG.info("Leave in blank the fields which you don't want to modify.\n");
        LOG.info("Insert new username > ");
        String username = new Scanner(System.in).nextLine();
        LOG.info("Insert new password > ");
        String password = new Scanner(System.in).nextLine();
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        if(userDao.update(user) == Commons.SUCCESS_INSERT) {
            LOG.info(SUCCESS_MESSAGE);
        } else {
            LOG.info(ERROR_MESSAGE);
        }
    }

    public void findUser() {
        LOG.info("Insert id to seach > ");
        int id = new Scanner(System.in).nextInt();
        User userFound = userDao.findUser(id);
        LOG.info(
            userFound == null?"User Not Found..." : userFound.toString()
        );
    }

    public void deleteUser() {
        LOG.info("Insert id to delete > ");
        int id = new Scanner(System.in).nextInt();
        if(userDao.delete(id) == Commons.SUCCESS_INSERT) {
            LOG.info(SUCCESS_MESSAGE);
        } else {
            LOG.info(ERROR_MESSAGE);
        }
    }
}
