package org.forten.si.action;

import com.mysql.cj.api.x.io.MessageWriter;
import org.forten.si.bo.StudentBo;
import org.forten.si.dto.LoginedUser;
import org.forten.si.dto.Message;
import org.forten.si.dto.Student4Update;
import org.forten.si.dto.Student4User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by student1 on 2017/7/6.
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"logined"})
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
        LoginedUser stu = bo.login(name,password);
        if(stu == null){
            return "redirect:index.html";
        }else {
            model.addAttribute("logined",stu);
            return "redirect:user/userIndex.html";
        }
    }

    @RequestMapping("forgetPwd")
    public @ResponseBody Message forgetPwd(String name){
        return bo.forgetPwd(name);
    }

}
