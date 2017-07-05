package org.forten.si.action;

import org.forten.si.bo.StudentBo;
import org.forten.si.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.awt.*;
import java.util.Arrays;

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

    @RequestMapping("list")
    public @ResponseBody RoWithPages<Student4Query> queryList(@RequestBody StudentQo qo){
        return bo.queryBy(qo);
    }

    @RequestMapping("delete")
    public @ResponseBody Message deleteStu(@RequestBody Integer... ids){
        System.out.println(Arrays.toString(ids));
        return bo.doDelete(ids);
    }

    @RequestMapping("changeStatus")
    public @ResponseBody Message changeStatus(@RequestBody Object[] map){
        //TODO
        return new Message("");
    }

    @RequestMapping("userInfo")
    public @ResponseBody
    Student4User showUserInfo(@RequestBody int id){
        return bo.showUserInfo(id);
    }
}
