package com.baizhi;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserTest {
    @Resource
    private UserService userService;
    @Test
    public void insert(){
        User user = new User("dif", "dfs", "dfsa", "dfs", "sdfa", "dsfa", "dfsa", "dfsa", "dfsa", "sdaf", new Date(), new Date(), "dsaf", "dsa", "dsfa");
        userService.insert(user);
    }
}
