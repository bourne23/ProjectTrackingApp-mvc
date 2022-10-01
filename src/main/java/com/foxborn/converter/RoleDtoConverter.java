package com.foxborn.converter;

import com.foxborn.dto.RoleDTO;
import com.foxborn.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@ConfigurationPropertiesBinding
public class RoleDtoConverter implements Converter<String, RoleDTO> {

    RoleService roleService;

    //injection
    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public RoleDTO convert(String source) {
        return roleService.findById(Long.parseLong(source));
    }
}
