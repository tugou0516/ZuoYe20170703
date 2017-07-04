import org.forten.si.bo.StudentBo;
import org.forten.si.dto.RoWithPages;
import org.forten.si.dto.Student4Query;
import org.forten.si.dto.StudentQo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by student1 on 2017/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/**/app-*.xml"})
public class Test4QueryStudent {
    @Resource
    private StudentBo bo;

    @Test
    public void testQueryBy(){
        StudentQo qo = new StudentQo();
        qo.setName("王");
        RoWithPages<Student4Query> ro = bo.queryBy(qo);
        System.out.println(ro.getList().toString());
        System.out.println(ro.getListSize());
        System.out.println(ro.getPage());
    }
}
