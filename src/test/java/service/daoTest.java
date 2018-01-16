package service;

import base.AbstractSpringContextTest;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.myDemo.common.bean.BasePageInfoList;
import com.myDemo.common.bean.PaginatorInfo;
import com.myDemo.mapper.UserinfoMapper;
import com.myDemo.mapper.UsertestMapper;
import com.myDemo.model.Userinfo;
import com.myDemo.model.Usertest;
import com.myDemo.model.UsertestExample;
import com.myDemo.service.UserInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class daoTest extends AbstractSpringContextTest {

    @Autowired
    UsertestMapper usertestMapper;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserinfoMapper userinfoMapper;

    @Test
    public void user() {
        UsertestExample usertestExample = new UsertestExample();
        usertestExample.setOrderByClause("userid");
        usertestExample.createCriteria().andUseridEqualTo(1);
        List<Usertest> usertests = usertestMapper.selectByExample(usertestExample);
        System.out.println(usertests);
    }

    /**
     * 测试 mybatis 分页插件
     */
    @Test
    public void test01() {
        //Pagination
        PageBounds page = new PageBounds(2);
        PageList<Userinfo> aa = userinfoMapper.selectPageList(null, page);
        for (Userinfo a : aa
                ) {
            System.out.println(a);
        }
    }

    /**
     * 测试分页 自己写的
     */
    @Test
    public void test03() {
        Usertest user = new Usertest();
        user.setUsername("1");
        /******************************/
        PaginatorInfo page = new PaginatorInfo();
        page.setLimit(2);
        page.setCurrentpage(1);
        System.out.println(page);
        //BasePageInfoList<Usertest> aa= usertestMapper.selectPageList1(null,page);
        BasePageInfoList<Usertest> aa = usertestMapper.selectByMyPage(user, page);
        for (Usertest a : aa) {
            System.out.println(a);
        }
        System.out.println(page);
        aa.setPaginatorInfo(page);
        System.out.println(aa.getPaginatorInfo());
    }


    //测试事物
    @Test
    //@Transactional(propagation=Propagation.REQUIRED ,rollbackFor = SQLException.class)
    public void test02() {

    }


}
