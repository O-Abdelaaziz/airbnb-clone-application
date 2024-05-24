package com.airbnb.clone.server.user.mapper;

import com.airbnb.clone.server.user.application.dto.ReadUserDTO;
import com.airbnb.clone.server.user.domain.Authority;
import com.airbnb.clone.server.user.domain.User;
import org.mapstruct.Mapper;

/**
 * @Created 5/17/2024 - 4:01 PM on (Friday)
 * @Package com.airbnb.clone.server.user.mapper
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
@Mapper(componentModel = "spring")
public interface UserMapper {
    ReadUserDTO readUserDTOToUser(User user);

    default String mapAuthoritiesToString(Authority authority) {
        return authority.getName();
    }
}
