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
    public interface UserDao { ���� }*/
    //1.ͨ������������������ѯ
    //(1)���ж� userAddressZip ������ POJO �淶������ĸ��ΪСд����ͬ���Ƿ�Ϊ User
    // ��һ�����ԣ�����ǣ����ʾ���ݸ����Խ��в�ѯ�����û�и����ԣ������ڶ�����
    //(2)���������ȡ��һ����д��ĸ��ͷ���ַ������˴�Ϊ Zip����Ȼ����ʣ�µ��ַ���
    // �Ƿ�Ϊ User ��һ�����ԣ�����ǣ����ʾ���ݸ����Խ��в�ѯ��
    // ���û�и����ԣ����ظ��ڶ������������������ȡ��
    // ������ user Ϊ User ��һ�����ԣ�
    //(3)���Ŵ���ʣ�²��֣� AddressZip �������ж� user ����Ӧ�������Ƿ��� addressZip
    // ���ԣ�����У����ʾ�÷��������Ǹ��� "User.user.addressZip" ��ȡֵ����
    // ��ѯ������������ղ��� 2 �Ĺ�����������ȡ�����ձ�ʾ����
    // "AccountInfo.user.address.zip" ��ֵ���в�ѯ��
   public User findByNameAndPassword(String name, String pwd);

    //2.ʹ�� @Query ������ѯ
    @Query("select u from User u where u.id > ?1")
    public List<User> findByUserId(Integer id);

    @Query("select u from User u where u.id > :id")
    public List<User> findByUsersId(@Param("id")Integer id);

    @Modifying
    @Query(value="update User u set u.password = ?1 where u.id = ?2")
    public int updatePassword(String after, Integer before);

}
