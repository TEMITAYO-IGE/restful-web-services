package com.osagie.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    static List<User> userList = new ArrayList<>();
    static {
        userList.add(new User( 0,new Date("02/08,1985"),"Osagie Erhabor"));
        userList.add(new User( 1,new Date("02/08,1985"),"Blessing Erhabor"));
        userList.add(new User( 2,new Date("02/08,1985"),"Isaac Erhabor"));
        userList.add(new User( 3,new Date("02/08,1985"),"Favour Erhabor"));
    }

    List<User> findAll(){
        return userList;
    }

    public User save(User user){
        if (user.getId() == null)user.setId( userList.size());
        userList.add(user);
        return user;
    }

    public User findById(int id){
        for (User user: userList){
            if (user.getId() == id)return user;
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()){
            User user=iterator.next();
            if (user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
