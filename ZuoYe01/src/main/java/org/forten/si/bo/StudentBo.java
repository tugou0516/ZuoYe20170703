package org.forten.si.bo;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.forten.si.dao.HibernateDao;
import org.forten.si.dto.*;
import org.forten.si.entity.Student;
import org.forten.utils.common.NumberUtil;
import org.forten.utils.common.StringUtil;
import org.forten.utils.security.SHA1Util;
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

    @Transactional(readOnly = true)
    public Student4User showUserInfo(int id){
        Student stu = dao.getById(id,Student.class);
        Student4User s4u = new Student4User();
        try {
            BeanPropertyUtil.copy(s4u,stu);
            return s4u;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public Message doChangePwd(int id, String oldPwd, String newPwd){
        String hql = "SELECT FROM Student WHERE id=:id AND password=:oldPassword ";
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("oldPassword",oldPwd);
        long count = dao.findOneBy(hql,map);
        if(count==0){
            return new Message("用户名或密码输入有误");
        }
        hql = "UPDATE Student SET password=:newPassword WHERE id=:id ";
        map.clear();
        map.put("id",id);
        map.put("newPassord",newPwd);
        try{
            dao.executeUpdate(hql,map);
            return new Message("密码修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Message("密码修改失败");
        }
    }

    @Transactional
    public Message doUpdate(int id, Student4Update s4up){
        Student stu = dao.getById(id,Student.class);
        BeanPropertyUtil.copy(stu,s4up);
        try{
            dao.update(stu);
            return new Message("信息修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Message("信息修改失败");
        }
    }

    @Transactional(readOnly = true)
    public LoginedUser login(String name, String password){
        String hql = "SELECT new org.forten.si.dto.LoginedUser(id,name) FROM Student WHERE name=:name AND password=:pwd ";
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("pwd",password);
        return dao.findOneBy(hql,map);
    }

    @Transactional
    public Message forgetPwd(String name){
        String hql = "SELECT new org.forten.si.dto.Student4User(id,name,gender,idCardNum,email,tel,address,birthday,eduBg,status,registTime) FROM Student WHERE name=:name ";
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        Student4User stu4U = dao.findOneBy(hql,map);
        if(stu4U == null){
            return new Message("用户名不存在！");
        }
        String pwdCode = getRandomStr();
        String shaPwdCode = SHA1Util.encryptSHA(pwdCode);
        hql = "UPDATE Student SET password=:newp WHERE id=:id ";
        map.clear();
        map.put("newp",shaPwdCode);
        map.put("id",stu4U.getId());
        dao.executeUpdate(hql,map);

        new Thread(()->{
            try {
                HtmlEmail email = new HtmlEmail();
                email.setHostName("smtp.126.com");
                email.setCharset("UTF-8");
                email.setSmtpPort(465);
                email.setAuthenticator(new DefaultAuthenticator("hihi05160696", "a123456"));
                email.setSSLOnConnect(true);
                email.addTo(stu4U.getEmail(),stu4U.getName());
                email.setFrom("hihi05160696@126.com", "Zuoye_System");
                email.setSubject("重置密码");
                email.setHtmlMsg("<p>系统已经为你重置密码，新密码为：<strong>"+pwdCode+"</strong></p><p>请尽快<a href='http://localhost:8081/login.html'>登录</a>系统修改密码！</p>");
                email.send();
            }catch(Exception e){
                e.printStackTrace();
            }
        }).run();
        return new Message("系统已向"+stu4U.getEmail()+"发送新密码，请尽快查收并修改密码");
    }

    private String getRandomStr(){
        String s = "";
        for(int i = 0;i<6;i++){
            s = s+ (char) NumberUtil.getRandomInteger(97,(97+25));
        }
        return s;
    }
}
