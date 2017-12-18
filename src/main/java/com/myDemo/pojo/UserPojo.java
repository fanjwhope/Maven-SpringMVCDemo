package com.myDemo.pojo;

import com.myDemo.model.admin.UserModel;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserPojo {
    private List<UserModel> userList;
    private UserModel userBo;
}
