package org.forten.si.action;

import org.forten.si.bo.StudentBo;
import org.forten.si.dao.Message;
import org.forten.si.org.forten.si.dto.Student4Save;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by student1 on 2017/7/3.
 */
@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Resource
    private StudentBo bo;

    @RequestMapping("save")
    public @ResponseBody Message saveStuInfo(@RequestBody Student4Save sdto){
        try {
            bo.doSave(sdto);
            return new Message("信息保存成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new Message("信息保存失败！");
        }

    }
}
