package com.myDemo.model.admin;
import lombok.*;

import java.util.Date;

/**
 * 注册的用户信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserModel {
    private Integer userId;
    private String userName ;
    private String password;
    private String realName; //真实姓名
    private String sex;
    private String phone;
    private Date registerDate; //注册日期
    private Date lastModifiedDate;//修改日期

}
