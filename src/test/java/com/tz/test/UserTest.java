package com.tz.test;

import com.tz.AppConfig;
import com.tz.dao.UserDao;
import com.tz.entity.User;
import com.tz.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AppConfig.class)
@ContextConfiguration(classes = AppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class UserTest {

    @Resource
    private UserDao userDao;

    @Autowired
    private UserService userService;

    private User   user;

    @Before
    public void initCustomer(){
        user = new User();
        user.setName("user");
        user.setPassword("231312");
    }

   /* @Test
    public void test1findOne() {
        userDao.save(user);
        User temp = userDao.findOne(user.getId());
        Assert.assertEquals(user, temp);
    }*/

    //Returns all instances of the type.
    @Test
    public void test2findAll(){
        userDao.save(user);
        List<User> list = (List<User>) userDao.findAll();
        Assert.assertEquals(true, list.size()>0);
    }

    @Test
    public void test3findAllByIds(){
        Set set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        List<User> list = (List<User>) userDao.findAll(set);
        Assert.assertNotNull(list);//always true
    }

    @Test
    public void test4update(){
        userDao.save(user);
        User temp = userDao.findOne(user.getId());
        temp.setName("name");
        temp.setPassword("124141");
        userDao.save(temp);
    }

    @Test
    public void test5delete(){
        userDao.save(user);
        userDao.delete(user.getId());
    }

    @Test
    public void test6Select(){
        user=userDao.findByNameAndPassword("user","231312");
        if(user!=null){
            System.out.println("findByNameAndPassword is ok");
        }
    }

    @Test
    public void test7Select(){
        List<User> users=userDao.findByUserId(1);
        if(user!=null){
            System.out.println("Query is ok");
            for (User u: users){
                System.out.println(u.getName());
            }
        }
    }

    @Test
    public void test8Select(){
        List<User> users=userDao.findByUsersId(1);
        if(user!=null){
            System.out.println("Query is ok");
            for (User u: users){
                System.out.println(u.getName());
            }
        }
    }

    @Test
    public void test9Select(){
        //运行结果为int,表示影响了多少行数据
        int a=userDao.updatePassword("654321", 3);
        System.out.println(a);
    }



    @Test
    public void test11Select(){

        List<User> users= userService.findSome("tom");
        for(User u:users){
            System.out.println(u.getName());
        }
        //System.out.println();

    }


    @Test
    public void test10Select(){
        Page<User> users=userService.pageTest("tom",1,5,"id");
        for (User u:users){
            System.out.println(u.getName());
        }


    }


}
