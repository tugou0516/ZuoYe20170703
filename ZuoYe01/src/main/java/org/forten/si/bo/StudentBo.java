package org.forten.si.bo;

import com.fasterxml.jackson.databind.BeanProperty;
import org.forten.si.dao.HibernateDao;
import org.forten.si.entity.Student;
import org.forten.si.org.forten.si.dto.Student4Save;
import org.forten.utils.system.BeanPropertyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by student1 on 2017/7/3.
 */
@Service("StudentBo")
public class StudentBo {
    @Resource
    private HibernateDao dao;

    @Transactional
    public void doSave(Student4Save sdto){
        Student stu = new Student();
        BeanPropertyUtil.copy(stu,sdto);
        dao.save(stu);
    }
}
