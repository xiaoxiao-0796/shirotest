package com.xiaofei.test.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StaffDO {

    private Long id;

    private String userName;

    private String sex;

    private String password;

    private String verifycode;

    private boolean rememberMe;
}
