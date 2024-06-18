package com.airbnb.clone.server.user.application;

/**
 * @Created 5/24/2024 - 8:54 PM on (Friday)
 * @Package com.airbnb.clone.server.user.application
 * @Project airbnb-clone-server
 * @User mrabdelaaziz
 * @Author Abdelaaziz Ouakala
 **/
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
