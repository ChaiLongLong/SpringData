package com.tz.service.impl;


import com.tz.dao.UserDao;
import com.tz.entity.User;
import com.tz.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.List;

//import org.springframework.cglib.core.Predicate;

/**
 * Created by Jerry on 2015/9/25.
 */
@Component
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findSome(final String name) {

        Specification<User>  spec = new Specification<User>() {
                public Predicate toPredicate(Root<User> root,
                                             CriteriaQuery<?> query, CriteriaBuilder cb) {
                    //User um=new User();
                    Predicate p1 = cb.like(root.get("name").as(String.class), "%"+name+"%");
                    //Predicate p2 = cb.equal(root.get("uuid").as(Integer.class), user.getId());
                    Predicate p3 = cb.equal(root.get("password").as(String.class),"123456");
                    //把Predicate应用到CriteriaQuery中去,因为还可以给CriteriaQuery添加其他的功能，比如排序、分组啥的
                    //query.where(cb.and(p3,cb.or(p1,p2)));
                    query.where(cb.and(p1,p3));
                    //query.where(p1);
                    //添加排序的功能
                    query.orderBy(cb.desc(root.get("id").as(Integer.class)));

                    return query.getRestriction();
                }
        };

        return userDao.findAll(spec);

    }


    @Override
    public Page<User> pageTest(final String name,int pageNumber, int pageSize,String sortType) {
        Page<User> user =userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                //List<Expression<Boolean>> expressions=predicate.getExpressions();
                //Path<String> studentNamePath = root.get("User").get("name");
                if(StringUtils.isNotBlank(name)){
                    predicate=cb.and(predicate,cb.like(root.<String>get("name"), "%" + name + "%"));
                }
                return predicate;
            }
        },buildPageRequest(pageNumber,pageSize,sortType));

        //Page requestedPage=userDao.findAll(spec,constructPageSpecification(page, size));

        //return requestedPage.getContent();
        return user;
    }

    private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
        Sort sort = null;
            sort = new Sort(Sort.Direction.DESC, "id");
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }


}


