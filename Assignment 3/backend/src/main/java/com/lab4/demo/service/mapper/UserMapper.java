package com.lab4.demo.service.mapper;

import com.lab4.demo.dto.UserListDTO;
import com.lab4.demo.dto.UserMinimalDTO;
import com.lab4.demo.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "name", source = "user.username")
    })
    UserMinimalDTO userMinimalFromUser(User user);

    @Mappings({
            @Mapping(target = "name", source = "user.username"),
            @Mapping(target = "roles", ignore = true)
    })
    UserListDTO userListDtoFromUser(User user);

    @AfterMapping
    default void populateRoles(User user, @MappingTarget UserListDTO userListDTO) {
        userListDTO.setRoles(user.getRoles());
    }
    //.stream().map(role -> role.getName().name()).collect(Collectors.toSet())

    UserListDTO toDTO(User user);

    User fromDTO(UserListDTO user);
}
