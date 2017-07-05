package org.forten.si.bo;

import org.forten.si.dao.HibernateDao;
import org.forten.si.dto.*;
import org.forten.si.entity.Student;
import org.forten.utils.common.StringUtil;
import org.forten.utils.system.BeanPropertyUtil;
import org.forten.utils.system.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Transactional
    public RoWithPages<Student4Query> queryBy(StudentQo qo){
        String countHql = "SELECT count(id) FROM Student WHERE 1=1 ";
        String hql = "SELECT new org.forten.si.dto.Student4Query(id, name, gender, idCardNum, email, tel, address, birthday, eduBg, status, registTime) FROM Student WHERE 1=1 ";
        Map<String,Object> map = new HashMap<>();

        if(StringUtil.hasText(qo.getName())){
            countHql = countHql + "AND name LIKE :name ";
            hql = hql + "AND name LIKE :name ";
            map.put("name","%"+qo.getName()+"%");
        }

        hql = hql + "ORDER BY registTime DESC ";
        long count = dao.findOneBy(countHql,map);
        if(count == 0){
            return RoWithPages.EMPTY_RO;
        }
        PageInfo page = PageInfo.getInstance(qo.getPageNo(),qo.getPageSize(),count);
        List<Student4Query> list = dao.findBy(hql, map, (int)page.getFirstResultNum(), page.getPageSize());
        return new RoWithPages<>(list,page);
    }

    @Transactional
    public Message doDelete(Integer... ids){
        String hql = "DELETE FROM Student WHERE id IN (:ids) ";
        Map<String,Object> map = new HashMap<>();
        map.put("ids", Arrays.asList(ids));
        try{
            int n = dao.executeUpdate(hql,map);
            return new Message("成功删除"+n+"条数据");
        }catch (Exception e){
            e.printStackTrace();
            return new Message("删除操作失败");
        }
    }

    @Transactional
    public Message changeStatus(int id, String status){
        String hql = "UPDATE Student SET status= :status WHERE id= :id ";
        Map<String,Object> map = new HashMap<>();
        map.put("status",status);
        map.put("id",id);
        try{
            dao.executeUpdate(hql,map);
            return new Message("状态修改为："+status);
        }catch (Exception e){
            e.printStackTrace();
            return new Message("状态修改失败");
        }
    }

    @Transactional
    public Student4User showUserInfo(int id){
        return dao.getById(id,Student4User.class);
    }
}
