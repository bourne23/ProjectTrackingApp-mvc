package com.foxborn.converter;

import com.foxborn.dto.RoleDTO;
import com.foxborn.service.RoleService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@ConfigurationPropertiesBinding   // this annotation automatically converts to object
//@ConfigurationProperties is for external  binding
public class RoleDtoConverter implements Converter<String, RoleDTO> {
                  // use Converter<String, RoleDTO> to convert string value from UI to object name
    RoleService roleService;

    //injection
    public RoleDtoConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override               // String value = "1"    to role
    public RoleDTO convert(String source) {
        return roleService.findById(Long.parseLong(source));
    }
}
