package com.tz.dao;

import com.tz.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//import org.jboss.logging.annotations.Param;

/**
 * Created by Jerry on 2015/9/24.
 */

public interface UserDao extends CrudRepository<User,Integer>,JpaSpecificationExecutor<User> {


   /* @RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
    public interface UserDao { …… }*/
    //1.通过解析方法名创建查询
    //(1)先判断 userAddressZip （根据 POJO 规范，首字母变为小写，下同）是否为 User
    // 的一个属性，如果是，则表示根据该属性进行查询；如果没有该属性，继续第二步；
    //(2)从右往左截取第一个大写字母开头的字符串（此处为 Zip），然后检查剩下的字符串
    // 是否为 User 的一个属性，如果是，则表示根据该属性进行查询；
    // 如果没有该属性，则重复第二步，继续从右往左截取；
    // 最后假设 user 为 User 的一个属性；
    //(3)接着处理剩下部分（ AddressZip ），先判断 user 所对应的类型是否有 addressZip
    // 属性，如果有，则表示该方法最终是根据 "User.user.addressZip" 的取值进行
    // 查询；否则继续按照步骤 2 的规则从右往左截取，最终表示根据
    // "AccountInfo.user.address.zip" 的值进行查询。
   public User findByNameAndPassword(String name, String pwd);

    //2.使用 @Query 创建查询
    @Query("select u from User u where u.id > ?1")
    public List<User> findByUserId(Integer id);

    @Query("select u from User u where u.id > :id")
    public List<User> findByUsersId(@Param("id")Integer id);

    @Modifying
    @Query(value="update User u set u.password = ?1 where u.id = ?2")
    public int updatePassword(String after, Integer before);

}
