package com.tz.service;

import com.tz.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * Created by Jerry on 2015/9/25.
 */

public interface UserService {

    List<User> findSome(String name);

    Page<User> pageTest(String name,int pageNumber, int pageSize,String sortType);





}
