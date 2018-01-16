package service;

import base.AbstractSpringContextTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class mongodbTest extends AbstractSpringContextTest {
    @Autowired
    protected MongoTemplate mongoTemplate;

    /**
     * 测试mongodb 链接
     */
    @Test
    public void test01(){
        System.out.println(mongoTemplate);
        for(String name:mongoTemplate.getCollectionNames()){
            System.out.println(name);
        }
    }



}
