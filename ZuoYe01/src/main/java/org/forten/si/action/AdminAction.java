package org.forten.si.action;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.forten.si.bo.StudentBo;
import org.forten.si.dto.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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

    @RequestMapping("export")
    public void exportInfoToExcel(HttpServletResponse response){
        try (OutputStream out = response.getOutputStream();Workbook wb = bo.doExportAll()) {
            response.setContentType("application/x-msexcel");
            response.setHeader("Content-Disposition","attachment;filename=students.xls");
            wb.write(out);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @RequestMapping("import")
    public String importFile(MultipartFile excelFile, HttpServletRequest request) throws IOException {
        String fileName = excelFile.getOriginalFilename();
        String uploadPathStr = request.getServletContext().getRealPath("/upload");
        File uploadPath = new File(uploadPathStr);
        if(!uploadPath.exists()){
            uploadPath.mkdir();
        }
        FileCopyUtils.copy(excelFile.getBytes(),new File(uploadPath+"/"+fileName));
        return "adminIndex.html";
    }

    //@RequestMapping("import")
    public @ResponseBody Message importData(MultipartFile excelFile) throws IOException, InvalidFormatException {
        try(Workbook wb = WorkbookFactory.create(excelFile.getInputStream())){
            bo.doImport(wb);
            return new Message("导入成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Message("导入失败");
        }
    }

}
