package com.myDemo.pojo;

import com.myDemo.model.User;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserPojo {
    private List<User> userList;
    private User userBo;
}
