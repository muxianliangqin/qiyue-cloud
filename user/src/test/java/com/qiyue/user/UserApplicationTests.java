package com.qiyue.user;

import com.qiyue.user.controller.UserController;
import com.qiyue.user.node.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {
    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() {
        String menuTree = userController.getMenuNode(1);
        System.out.println(menuTree);
    }
}
