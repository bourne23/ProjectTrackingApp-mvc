package com.foxborn.service;

import java.util.List;

/**
 * Interface with all defined services. These services fit to be used by both Role and User service. Hence defined here...
 * @param <T>
 * @param <ID>
 */
public interface CrudService<T,ID> {

     T save(T object);
     List<T> findAll();
     T findById(ID id);
//     void delete(T object);
     void deleteById(ID id);
     void update(T object);


}
