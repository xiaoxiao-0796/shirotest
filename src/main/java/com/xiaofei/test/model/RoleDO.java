package com.xiaofei.test.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class RoleDO {

    private Long id;

    private String roleName;

    private String roleDesc;
}
