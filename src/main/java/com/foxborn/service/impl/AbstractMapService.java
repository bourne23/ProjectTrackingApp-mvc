package com.foxborn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This abstract class has implementation methods for Crud Services, and a map acting as DB
 * class has generic methods for operations used by both services: RoleService and UserService
 * @param <T>
 * @param <ID>
 */
public abstract class AbstractMapService<T,ID> {

// this is database
    protected Map<ID,T> map = new HashMap<>();


    T save(ID id,T object){
        map.put(id,object);    //  put - add new or update
        return object;
    }

    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void update(ID id, T object){
        map.put(id, object);
    }

}
