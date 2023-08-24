package com.turkuazgame.btlig.response;

import com.turkuazgame.btlig.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserResponse {

    private long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String userImage;

    public UserResponse(User user) {
        this.setUserId(user.getUserId());
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setUserImage(user.getUserImage());
    }
}
