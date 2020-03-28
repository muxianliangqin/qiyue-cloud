package com.qiyue.service.user
        ;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class User implements Serializable {

    private int id;

    private String mobile;

    private String email;

    private String username;

    private String alias;

    private String gender;

    private String openid;

    private String state;

}
