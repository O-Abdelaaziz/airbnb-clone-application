package com.airbnb.clone.server.user.application.dto;

import java.util.Set;
import java.util.UUID;

/**
 * @Created 5/24/2024 - 1:16 PM on (Friday)
 * @Package com.airbnb.clone.server.user.application
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public record ReadUserDTO(UUID publicId,
                          String firstName,
                          String lastName,
                          String email,
                          String imageUrl,
                          Set<String> authorities) {
}
