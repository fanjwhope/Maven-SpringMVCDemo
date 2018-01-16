# Maven-SpringMVCDemo

1. 创建一个maven 项目 并用git 管理 只需要将src文件夹，pom.xml ,以及.gitignore 文件上传到github 即可。这3个文件组成一个简单的maven项目。
    如何针对一个项目添加 .gitignore文件呢？
    
    在创建的 .gitignore 文件中 配置如下：（默认没有target，在此处添加）
        # IntelliJ project files
        .idea
        *.iml
        out
        gen
        target
2.一般用maven构建的项目会生成一个target文件夹。【out文件是用idea构建项目时会自动生成。两者2选1即可】

3.springMVC项目中的controller返回响应时，如果存在问题。并且配置无误的情况下，可能是解析json包未引用。
  原因可能是springMVC的@responseBody注解将对象准换为json 的时候需要外部引入json包。
4.配置全局的setting : File-->others settings-->default setting / default Project Structure.
  配置指定项目的setting： File-->Settings/Project Structure.
 
5.将一个已经存在的项目上传至github或者码云。
    github: VCS-->import into version control -->Share Project on GitHub
    码云： 有点问题。

6.maven 项目的 setting.xml 如何配置？
   公司使用 D:\WorkSpace\Maven_jar\settings.xml
   不是公司的项目可以使用 D:\WorkSpace\Maven_jar\setting_my.xml

7.在测试类中写测试方法 添加注解@Test 是报错。。可能是 Junit 包版本问题。

8.CXF配置问题：
  application.xml 中 只需要配置  <import resource="classpath:META-INF/cxf/cxf.xml" />
  备注：cxf3以后，只需要引入这个配置文件即可，其他两个废弃掉了
    <import resource="classpath:META-INF/cxf/cxf.xml" />
    可以cxf核心包下，看一下，也只有cxf.xml这一个配置文件了，如里配置原来的3个，启服务时，会报错  
   
  给一个新项目添加cxf功能时候，启动报错： 
  org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'XXX': Invocation of init method failed; nested exception is java.lang.NoSuchMethodError: org.springframework.aop.support.AopUtils.isCglibProxyClass(Ljava/lang/Class;)Z
  上网查找相关资料发现，是由spring4.2.0和cxf2.7.14的版本不兼容导致的。 
  （在Spring 4.x以上，废弃了setFactoryBean方法，而CXF 2.*采用的是调用Spring的这个方法进行Bean的设置。）

9. mybatis-generator自动生成。
  网址：http://blog.csdn.net/liudongdong0909/article/details/51534735
       https://www.cnblogs.com/yuanmiemie/p/6736347.html
   问题：1. mybatis-generator-config.xml 文件中  http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd 报错（没有注册）
   将其改为 http://www.mybatis.org/dtd/mybatis-generator-config_1_0.dtd.
   问题：1. mybatis-generator的配置文件 mybatis-generator-config.xml 中 
        <properties resource="dbconfig.properties"/> 属性必须 放在generatorConfiguration节点下的第一个子节点位置。
        2.自动生成的表在没有 生成帮助类（Exmaples）的情况下，如果没有增删改的 操作的话，可能是数据表中没有【主键】的原因。
        注意：是主键而非索引。
10.安装mysql的步骤（zip 方式） :http://www.jb51.net/article/89224.htm 

11.配置拦截器。进行验证登录。
  在编写验证登录的时候，会有报错信息，但是暂时不影响程序的运行。 
    网上百度是少包的原因。hibernate-validator 

12.页面发送请求到后台时 如果是json 格式。 controller方法的参数前加上 @RequestBody。会将json 格式的数据转换为对象。

    

    


后台工程：
1.springMVC+Mybatis 项目 ： 基础的增删查改。（restful）
2.配置Mybatis自动生成策略。 
3.webservice 发布。客户端测试
4.配置自动记录或者输出sql语句。==目前是全部都有输出
5.编一的异常处理，（网上查资料）写统
6.拦截处理（配置切面之类。。。）。    （重要）
7.spring安全认证可以弄么？
8.文件上传下载功能。导出等功能。
9.Mybatis分页功能。 mybatis分页插件 怎么使用？  以及事物处理。
  <!--mybatis分页插件-->
    <dependency>
        <groupId>com.github.pagehelper</groupId>
        <artifactId>pagehelper-spring-boot-starter</artifactId>
        <version>${pagehelper.version}</version>
    </dependency>
    
    分页：1.外部插件 ，代码实现。
    
 10. 通用mapper 是做什么的？
 <!-- 通用mapper -->
         <dependency>
             <groupId>tk.mybatis</groupId>
             <artifactId>mapper-spring-boot-starter</artifactId>
             <version>${mapper.version}</version>
         </dependency>
 
 
 11.静态资源的访问控制  网址： http://blog.csdn.net/u012730299/article/details/51872704 
    当web.xml 中拦截器配置的 <url-pattern>/</url-pattern> 而不是 *.do 是会存在静态资源拦截的问题：
    方案1： 采用的是在web.xml中配置defaultServlet来处理静态文件。 
     <servlet-mapping>
           <servlet-name>default</servlet-name>
           <url-pattern>*.js</url-pattern>
           <url-pattern>*.css</url-pattern>
           <url-pattern>*.ico</url-pattern>
           <url-pattern>/statics/*</url-pattern>
          <!-- <url-pattern>/images/*</url-pattern>-->
     </servlet-mapping>
     **要写在DispatcherServlet的前面， 让defaultServlet先拦截，这个就不会进入Spring了，我想性能是最好的吧。**
     Tomcat, Jetty, JBoss, and GlassFish  默认 Servlet的名字 -- "default"
     Google App Engine 默认 Servlet的名字 -- "_ah_default"
     Resin 默认 Servlet的名字 -- "resin-file"
     WebLogic 默认 Servlet的名字  -- "FileServlet"
     WebSphere  默认 Servlet的名字 -- "SimpleFileServlet"
     方案2：在spring3.0.4以后版本提供了mvc:resources
       在Spring-MVC.xml 中 修改2个地方。
        <!--1. 静态资源映射 ,静态资源最好不要放在web-INF下。放在webAPP 根目录下。-->
        <mvc:resources mapping="/statics/**" location="/statics/"/>
        <!--2. 拦截器 配置拦截那些映射URL。 -->
          <mvc:interceptors>
              <!-- 多个拦截器，顺序执行-->
              <mvc:interceptor>
                  <mvc:mapping path="/**"/>
                  <mvc:exclude-mapping path="/**/*login*"></mvc:exclude-mapping>
                  <mvc:exclude-mapping path="/**/*register*"></mvc:exclude-mapping>
                  <mvc:exclude-mapping path="/statics/**"></mvc:exclude-mapping>  <!--方案2-->
                  <bean class="com.myDemo.common.interceptor.LoginInterceptor"></bean>
              </mvc:interceptor>
          </mvc:interceptors>
          
     方案3：使用<mvc:default-servlet-handler/> 同时也需要在拦截器中配置 那些映射路径不需要拦截。
     <mvc:default-servlet-handler/>会把 "/**" url,注册到 SimpleUrlHandlerMapping 的 urlMap 中,
     把对静态资源的访问由 HandlerMapping 转到 org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler 处理并返回.
     DefaultServletHttpRequestHandler 使用就是各个Servlet容器自己的默认 Servlet.
     补充说明：多个HandlerMapping的执行顺序问题：
     DefaultAnnotationHandlerMapping 的 order 属性值是：0
     <mvc:resources/ >自动注册的 SimpleUrlHandlerMapping 的 order 属性值是： 2147483646
     <mvc:default-servlet-handler/>自动注册的 SimpleUrlHandlerMapping 的 order 属性值是：2147483647
     spring 会先执行 order 值比较小的。当访问一个 a.jpg 图片文件时，先通过 DefaultAnnotationHandlerMapping 来找处理器，一定是找不到的
     ，我们没有叫 a.jpg 的 Action。再按 order 值升序找，由于最后一个 SimpleUrlHandlerMapping 是匹配 "/**" 的，所以一定会匹配上，再响应图片。
   **最后再说明一下，如何你的 DispatcherServlet 拦截 *.do 这样的 URL，就不存上述问题了。**
    注意： 将静态文件放在webApp根文件夹下，不要放在 web-inf 里面，如果放在  web-inf 里面需要走后台请求。。      
     WEB-INF目录作用：
        WEB-INF是Java的WEB应用的安全目录。所谓安全就是客户端无法访问，只有服务端可以访问的目录。
        如果想在页面中直接访问其中的文件，必须通过web.xml文件对要访问的文件进行相应映射才能访问。
        当然，你非要放在WEB-INF中，则必须修改resources映射，如：
       <mvc:resources mapping="/js/**" location="/WEB-INF/js/" />   
       
 12.自定义拦截器： 对mybatis3.4 一下版本的注解和mybatis3.2 的有所不同
 对于相同的类名字 引用是需要注意：最好找到方法的源码出，看看引用的是那个包下的。否则会报错。
 
 13.添加统一异常处理。(在dao 抛出异常，在Service 捕捉处理)
    
 
 
 
前端工程：
1.vuejs 框架+ element 元素
2.bootstrap


案例： 弄个OA或者blog管理，爬虫。。。
数据库的设计。。
OA是一个怎样的管理系统。。
blog是怎么样的。。有人要发表文章，有人要评论文章。
注册之后才可以评论还是都可以评论。不需要注册？
评论的数据时候需要管理员仅进行管理。（客户评论之后由管理员审批，审批通过的显示。审批不通过的不显示）--不是实时评论了感觉。。


看OA部门的数据库设计。以及权限管理系统是怎么弄的？然后模拟的做一个。。

页面：
1.注册时，在用户是去焦点的时候是否可以有个提示？
2.用户查询管理页面。分页。







   
  

