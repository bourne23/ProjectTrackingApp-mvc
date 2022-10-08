package com.foxborn.service;

import com.foxborn.dto.UserDTO;

import java.util.List;

public interface UserService extends CrudService<UserDTO,String> {
    List<UserDTO> findManagers();
}
