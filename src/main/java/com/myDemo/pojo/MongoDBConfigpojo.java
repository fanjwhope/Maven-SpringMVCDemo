package com.myDemo.pojo;

import lombok.*;

import java.util.Map;

/**
 * {
 "_id" : ObjectId("5a2e125689f7cc1828434af3"),
 "_class" : "java.util.LinkedHashMap",
 "classname" : "OptRegister",
 "keys" : {
     "GHLB" : {
         "1" : "普通号",
         "2" : "专家号"
     }
 }
 }
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MongoDBConfigpojo {
    private String _id;
    private String _class;
    private String classname;
    private Map<String,Object> keys;

    @Override
    public String toString() {
        return "ConfigBO{" +
                "_class='" + _class + '\'' +
                ", classname='" + classname + '\'' +
                ", keys=" + (keys!=null?keys.toString():"") +
                '}';
    }
}
