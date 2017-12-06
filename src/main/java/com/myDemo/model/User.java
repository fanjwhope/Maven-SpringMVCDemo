package com.myDemo.model;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private  String id;
    private String name ;
    private String sex;
}
