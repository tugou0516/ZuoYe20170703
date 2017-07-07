package org.forten.si.action;

import org.forten.si.bo.StudentBo;
import org.forten.si.dto.*;
import org.forten.utils.common.DateUtil;
import org.forten.utils.system.PropertiesFileReader;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by student1 on 2017/7/6.
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"logined","admin"})
public class UserAction {
    @Resource
    private StudentBo bo;

    @RequestMapping("user/userInfo")
    public @ResponseBody Student4User showUserInfo(HttpSession session){
        LoginedUser stuLogined = (LoginedUser)session.getAttribute("logined");
        int id = stuLogined.getId();
        try{
            Student4User stu = bo.showUserInfo(id);
            return stu;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("user/changePwd")
    public @ResponseBody Message changePwd(String oldPwd, String newPwd){
        //TODO
        int id = 1;
        return bo.doChangePwd(id,oldPwd,newPwd);
    }

    @RequestMapping("user/update")
    public @ResponseBody Message update(@RequestBody Student4Update stu){
        //TODO
        int id = 1;
        return bo.doUpdate(id,stu);
    }

    @RequestMapping("login")
    public String login(String name, String password, Model model){
        if(name.equals("admin") && password.equals(PropertiesFileReader.getValue("system/admin","ADMIN_PWD"))){
            LoginedUser stu = new LoginedUser(0,"admin");
            model.addAttribute("logined", stu);
            model.addAttribute("admin",true);
            return "redirect:admin/adminIndex.html";
        }else {
            LoginedUser stu = bo.login(name, password);
            if (stu == null) {
                return "redirect:index.html";
            } else {
                model.addAttribute("logined", stu);
                model.addAttribute("admin",false);
                return "redirect:user/userIndex.html";
            }
        }
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        //TODO
        return "";
    }

    @RequestMapping("forgetPwd")
    public @ResponseBody Message forgetPwd(String name){
        return bo.forgetPwd(name);
    }

    @RequestMapping("regist")
    public @ResponseBody Message regist(String name, String gender, String idCardNum, String email, String tel, String address, String birthday, String eduBg){
        Date birthdayDate = DateUtil.convertStringToDate(birthday,"yyyy-MM-dd");
        Student4Save stu4S = new Student4Save(name,gender,idCardNum,email,tel,address,birthdayDate,eduBg);
        try {
            bo.doSave(stu4S);
            return new Message("注册成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new Message("注册失败！");
        }
    }

    @RequestMapping("checkEmail")
    public boolean checkEmail(@RequestBody String email){
        boolean result = bo.doCheckEmail(email);
        if(result){
            return false;
        }else {
            return true;
        }
    }

}
