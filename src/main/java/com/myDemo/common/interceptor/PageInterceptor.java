package com.myDemo.common.interceptor;

import com.myDemo.common.bean.PaginatorInfo;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

/**
 * http://blog.csdn.net/ABCD898989/article/details/51261163 （可以）
 * http://blog.csdn.net/hupanfeng/article/details/9265341
 * 注意：包引入问题，和拦截器注解问题。
 *
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={java.sql.Connection.class})})
public class PageInterceptor implements Interceptor {

    /* (non-Javadoc)
     * 拦截器要执行的方法
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("嗨，这是个mybatis 拦截器。。。。。");
        //先取出StatementHandler
        //关于StatementHandler其有两个实现，【见上文】，默认情况下，程序会执行BaseStatementHandler下的内容
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        /**
         * 【注意】其在BaseStatementHandler中是protected的，我们在没有继承等关系的条件下，是无法直接取出来的。
         因此，我们就需要引入一个Mybatis已经实现了的对象：MetaObject。关于这个对象，可以先暂时的理解为帮助我们获取或设置该对象的原本不可访问的属性。
         我们先只用到了public static MetaObject forObject(.....) ，其他内容有兴趣的读者可以自行学习。
         于是，我们操作的对象就由StatementHandler的实例，变为MetaObject的实例
         */
        MetaObject metaObject = MetaObject.forObject(statementHandler
                , SystemMetaObject.DEFAULT_OBJECT_FACTORY
                , SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY );

        // begin :分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环 可以分离出最原始的的目标类) ---(可注释)
        while (metaObject.hasGetter("h")) {
            Object object = metaObject.getValue("h");
            metaObject = MetaObject.forObject(object, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                    SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaObject.hasGetter("target")) {
            Object object = metaObject.getValue("target");
            metaObject = MetaObject.forObject(object, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                    SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        //end
        //获取查询接口映射的相关信息 ，获取的方法为参数名，OGNL表达式：
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String id = mappedStatement.getId();
        //id中（即mapper文件中sql语句的唯一id）以ByPage字符串结尾的，我们就认为该sql语句按照分页插叙来执行
        if(id.matches(".+ByMyPage$")){
            BoundSql boundSql = statementHandler.getBoundSql();
            Map<String,Object> params = (Map<String,Object>)boundSql.getParameterObject();
            PaginatorInfo page = (PaginatorInfo)params.get("page");

            String sql = boundSql.getSql();

            String countSql = "select count(*)from ("+sql+")a";
            Connection connection = (Connection) invocation.getArgs()[0];
            PreparedStatement countStatement = connection.prepareStatement(countSql);
            //带有条件的分页查询，下面的这句话就是获取查询条件的参数
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            //经过set方法，就可以正确的执行sql语句
            parameterHandler.setParameters(countStatement);
            ResultSet rs = countStatement.executeQuery();
            if(rs.next()){
                page.setTotalCount(rs.getInt(1));
            }
            String pageSql = sql+" limit "+page.getCurrentResult()+","+page.getLimit();
            //MetaObject对象，刚才，我们说它提供给我们获取或设置该对象的原本不可访问的属性。因此，就来利用它实现替换sql语句的功能
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        return invocation.proceed();
    }

    /* (non-Javadoc)
     * 拦截器需要拦截的对象 （注意：要返回一个对象）
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /* (non-Javadoc)
     * 设置初始化的属性值
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
