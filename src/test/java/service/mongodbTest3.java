package service;
import base.AbstractSpringContextTest;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.myDemo.pojo.MongoDBConfigpojo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * 测试mongoDB
 * 配置信息
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
 * 1.查询数据库中所有的表数据
 * 2.分页查询表对应的key 数据
 * 3.新增数据
 *
 */
public class mongodbTest3 extends AbstractSpringContextTest{
    private static final String COLLECTION_NAME = "fan";
    private static final String CLASS_NAME = "classname";
    private static final String KEYS = "keys";

    @Autowired
    MongoTemplate template;
    @Test
    public void test(){
        DBObject fieldObject = new BasicDBObject();
        fieldObject.put("classname", true);
        fieldObject.put("_id", false);

        DBObject where = new BasicDBObject();
       // where.put(CLASS_NAME, "OptRegister");
        List<Map> aa=new ArrayList<>();
        aa=template.find(new BasicQuery(where,fieldObject),Map.class, COLLECTION_NAME);
        for (Map<String,Object> s: aa ) {
            for (Map.Entry<String,Object> entry : s.entrySet()) {
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            }
        }
    }
    @Test
    public void test01(){
        List<String> aa=new ArrayList<>();
        aa=template.find(new Query(where(CLASS_NAME).is("OptRegister")) ,String.class, COLLECTION_NAME);
        for (String s:aa ) {
            System.out.println(s);
        }
    }

    /**
     * 测试的时候实体对象不能放在 test 包中。 否则会报空指针异常。
     */
    @Test
    public void test02(){
        DBObject fieldObject = new BasicDBObject();
        fieldObject.put("classname", true);
        fieldObject.put("_id", false);
        fieldObject.put("keys", true);
        fieldObject.put("_class", true);

        DBObject where = new BasicDBObject();
        where.put(CLASS_NAME, "ceshi"); //OptRegister
        List<MongoDBConfigpojo> aa=new ArrayList<>();
        aa=template.find(new BasicQuery(where),MongoDBConfigpojo.class, COLLECTION_NAME);
        for (MongoDBConfigpojo s:aa ) {
            System.out.println(s);
            /*if(s.getKeys()!=null){
                for (Map.Entry<?,?> entry : s.getKeys().entrySet()) {
                    System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                }
            }*/

        }
    }

    /**
     * 新增的 insert ,更新
     */
    @Test
    public void test03 (){
        //插入
        MongoDBConfigpojo bo=new MongoDBConfigpojo();
        bo.set_class("java.util.LinkedHashMap");
        bo.setClassname("ceshi");
        Map data=new HashMap();
        data.put("F","男");
        data.put("M","nv");
        data.put("W","未知");

        Map map=new HashMap();
        map.put("XB",data);

        bo.setKeys(map);
        template.insert(bo,COLLECTION_NAME);

        //查询单条记录
        DBObject where = new BasicDBObject();
        where.put(CLASS_NAME, "ceshi"); //OptRegister
        //更新
        MongoDBConfigpojo aa=template.findOne(new BasicQuery(where),MongoDBConfigpojo.class, COLLECTION_NAME);
        Update updatebo=new Update();
        updatebo.set("keys",map);
        WriteResult up=template.upsert(new Query(where("_class").is(aa.get_class()).and("classname").is(aa.getClassname()))
                ,updatebo //, ConfigBO.class
                ,COLLECTION_NAME);
        System.out.println(up.isUpdateOfExisting()); // true
        System.out.println(up.getN()); //操作的记录数
        System.out.println(up.getUpsertedId());// ??  null

        //如果mongodb 中的value 是 数组的.可以使用pull 删除 数组里面的数据。
        /*Update update = new Update();
        update.pull("keys",new BasicDBObject("W","未知"));
        Query query = Query.query(Criteria.where("_id").is("5a30ef4bd1ba36454435e033"));
        template.updateFirst(query,update,COLLECTION_NAME);*/
        //删除 一条数据
    }


    /**
     * 新增某个节点
     */
    @Test
    public void test04(){
        Update update=new Update();
        update.set("keys.XB.M","男"); //删掉了测试这个节点 (keys.XB.M)
        WriteResult up1=template.upsert(new Query(where("_id").is("5a30ef95d1ba362dd4eef406"))
                ,update
                ,COLLECTION_NAME);
        System.out.println(up1.isUpdateOfExisting()); // true
        System.out.println(up1.getN()); //操作的记录数
        System.out.println(up1.getUpsertedId());// ??  null
    }

    /**
     * 删除某个节点
     */
    @Test
    public void test05(){
        Update delBO=new Update();
        delBO.unset("keys.XB.M"); //删掉了测试这个节点 (keys.XB.M)
        WriteResult up1=template.upsert(new Query(where("_id").is("5a30ef95d1ba362dd4eef406"))
                ,delBO
                ,COLLECTION_NAME);
        System.out.println(up1.isUpdateOfExisting()); // true
        System.out.println(up1.getN()); //操作的记录数
        System.out.println(up1.getUpsertedId());// ??  null
    }

    /**
     * 删除一条记录
     */
    @Test
    public void test06(){
        WriteResult up1=template.remove(new Query(where("_id").is("596c541ce4c769260526f435"))
                ,COLLECTION_NAME);
        System.out.println(up1.isUpdateOfExisting()); // false
        System.out.println(up1.getN()); //操作的记录数
        System.out.println(up1.getUpsertedId());// ??  null
        //查询单条记录
        DBObject where = new BasicDBObject();
        where.put("_id", "5a30ef95d1ba362dd4eef406"); //OptRegister
        //查询获取数据
        MongoDBConfigpojo aa=template.findOne(new BasicQuery(where),MongoDBConfigpojo.class, COLLECTION_NAME);
        //按对象删除数据 (对象一定要有Id属性)
        WriteResult up2=template.remove(aa,COLLECTION_NAME);
        System.out.println(up2.isUpdateOfExisting()); // false
        System.out.println(up2.getN()); //操作的记录数
        System.out.println(up2.getUpsertedId());// ??  null

    }

    /**
     * 聚合查询
     db.getCollection('intelligentGuide').aggregate([  {$unwind:'$diagnosisList'},
     {$match:{'mId':1,'partId':5}}
     ,{$group:{_id:{miniPartName:'$diagnosisList.miniPartName',diagnosisList:"$diagnosisList"}
     }}])
     *
     *
     */
    @Test
    public void test07(){
      /*  Aggregation aggregation=Aggregation.newAggregation(Aggregation.project(),Aggregation.match(Criteria.where("").is("").and("").exists(true)));
            template.aggregate(aggregation,"medical",Map.class);*/

        MatchOperation matchOperationF= Aggregation.match(Criteria.where("mId").is(1).and("partId").is(5));
        ProjectionOperation projectionOperation=Aggregation.project(Fields.fields("_id","diagnosisList"));
        //将数据进行拆分 在聚合 push
        UnwindOperation unwindOperation=Aggregation.unwind("$diagnosisList");
        MatchOperation matchOperationT=Aggregation.match(Criteria.where("diagnosisList.miniPartName").is("眼"));

        GroupOperation groupOperation=Aggregation.group("$_id").push("diagnosisList").as("diagnosisList");
        Aggregation aggregation=Aggregation.newAggregation(matchOperationF,projectionOperation
                                ,unwindOperation,matchOperationT,groupOperation);

        AggregationResults<Map> aggregationResults=template.aggregate(aggregation,"intelligentGuide",Map.class);
        List<Map> mapList = aggregationResults.getMappedResults();
        for (Map<Object,Object> map : mapList  ) {
            for (Map.Entry<Object,Object> entry : map.entrySet()) {
                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
            }
        }

    }

}





























