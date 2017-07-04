import org.forten.si.bo.StudentBo;
import org.forten.si.dto.Student4Save;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by student1 on 2017/7/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/**/app-*.xml"})
public class Test4SaveStudent {
    @Resource
    private StudentBo bo;

    @Test
    public void testSave(){
        List<Student4Save> list = new ArrayList<>();
        for(int i = 1;i<=50;i++){
            Student4Save stuNew = new Student4Save("李","男","549765132124722","sigengt@163.com","2165488","beijing",new Date(),"中专");
            stuNew.setName(stuNew.getName()+i);
            list.add(stuNew);
        }
        for (Student4Save stu:list
             ) {
            bo.doSave(stu);
        }
    }
}
