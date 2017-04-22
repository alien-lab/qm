package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseTeacherEntity;
import com.alienlab.niit.qm.service.BaseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/9.
 */
@Api(value="/qm-api/teacher",description = "教师API")
@RestController
@RequestMapping("/qm-api")
public class BaseTeacherController {
    @Autowired
    private BaseTeacherService baseTeacherService;

    @ApiOperation(value = "新增教师")
    @PostMapping(value = "/teacher")
    public ResponseEntity addTeacher(@RequestParam String teacherNo,@RequestParam String teacherName,
                                     @RequestParam String depNo,@RequestParam String teacherTitle,@RequestParam String teacherType){
        BaseTeacherEntity teacherEntity = new BaseTeacherEntity();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        teacherEntity.setTeacherNo(teacherNo);
        teacherEntity.setTeacherName(teacherName);
        teacherEntity.setDepNo(depNo);
        teacherEntity.setTeacherTitle(teacherTitle);
        teacherEntity.setTeacherType(teacherType);
        teacherEntity.setTeacherStatus("1");
        teacherEntity.setDataTime(Timestamp.valueOf(df.format(new Date())));
        BaseTeacherEntity teacherEntity1 = baseTeacherService.saveTeacher(teacherEntity);
        if (teacherEntity1!=null){
            return ResponseEntity.ok().body(teacherEntity1);
        }else {
            ExecResult er=  new ExecResult(false,"新增教师失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据教师编号查教师名称")
    @GetMapping(value = "/teacher")
    public ResponseEntity findTeacherByTeacherNo(@RequestParam String teacherNo){
        BaseTeacherEntity baseTeacherEntity = baseTeacherService.getTeacherByteacherNo(teacherNo);
        if (teacherNo!=null){
            return ResponseEntity.ok().body(baseTeacherEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "根据部门和类型查找教师")
    @GetMapping(value = "/teacher/findTeacherByDepNoAndType")
    public ResponseEntity findTeacherBydepNoAndType(@RequestParam String depNo,@RequestParam String teacherType,@RequestParam int index,@RequestParam int length){
        if (depNo!=null&&teacherType!=null){
            Page<BaseTeacherEntity> baseTeacherEntity = baseTeacherService.getTeacherByTypeAndDepNo(depNo,teacherType,new PageRequest(index,length));
            return ResponseEntity.ok().body(baseTeacherEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="获取单个教师内容")
    @GetMapping(value = "/teacher/findOneTeacher")
    public ResponseEntity updateDepartment(@RequestParam String teacherNo,HttpServletRequest request) throws IOException {
       /* String jsonBody=IOUtils.toString(request.getInputStream(),"UTF-8");
        JSONObject form=JSONObject.parseObject(jsonBody);
        Long depSort = form.getLong("depSort");*/
        BaseTeacherEntity teacherEntity =baseTeacherService.getTeacherByteacherNo(teacherNo);
        if(teacherEntity != null){
            return ResponseEntity.ok().body(teacherEntity);
        }else {
            ExecResult er=  new ExecResult(false,"获取教师列表失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);

        }
    }


    @ApiOperation(value = "根据部门和类型查找教师")
    @GetMapping(value = "/teacher/findTeacher")
    public ResponseEntity findAllTeacher(@RequestParam int index,@RequestParam int length){
            Page<BaseTeacherEntity> baseTeacherEntity = baseTeacherService.getAllTeacher(new PageRequest(index,length));
            return ResponseEntity.ok().body(baseTeacherEntity);
    }

    @ApiOperation(value = "根据部门和类型查找教师")
    @GetMapping(value = "/teacher/findTeacherByDepNoAndTypeAndKey")
    public ResponseEntity findTeacherBydepNoAndTypeAndKey(@RequestParam String depNo,@RequestParam String teacherType,@RequestParam String teacherKey,@RequestParam int index,@RequestParam int length){
        if (depNo!=null&&teacherType!=null&&teacherKey!=null){
            Page<BaseTeacherEntity> baseTeacherEntity = baseTeacherService.getteacherByDepNoAndTypeAndKey(depNo,teacherType,teacherKey,new PageRequest(index,length));
            return ResponseEntity.ok().body(baseTeacherEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="修改教师内容")
    @GetMapping(value = "/teacher/updateTeacher")
    public ResponseEntity saveDepartment(@RequestParam String teacherNo,@RequestParam String teacherName,
                                         @RequestParam String teacherTitle,@RequestParam String teacherType){
        BaseTeacherEntity teacherEntity =baseTeacherService.getTeacherByteacherNo(teacherNo);
        if(teacherEntity != null){
            teacherEntity.setTeacherNo(teacherNo);
            teacherEntity.setTeacherName(teacherName);
            teacherEntity.setTeacherType(teacherType);
            teacherEntity.setTeacherTitle(teacherTitle);
            BaseTeacherEntity result = baseTeacherService.saveTeacher(teacherEntity);
            return ResponseEntity.ok().body(result);
        }else {
            ExecResult er=  new ExecResult(false,"保存部门信息失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="菜单switch开关")
    @PutMapping(value = "/teacher/switch")
    public ResponseEntity switchMenu( @RequestParam String teacherNo)  {
        BaseTeacherEntity baseTeacherEntity = null;
        try {
            baseTeacherEntity = baseTeacherService.getTeacherByteacherNo(teacherNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (baseTeacherEntity != null){
            String teacherStatu = baseTeacherEntity.getTeacherStatus();
            System.out.print(teacherStatu);
            if (teacherStatu.equals("1")){
                baseTeacherEntity.setTeacherStatus("0");
            }else {
                baseTeacherEntity.setTeacherStatus("1");
            }
            try {
                BaseTeacherEntity baseTeacherEntity1 =  baseTeacherService.saveTeacher(baseTeacherEntity);
                if (baseTeacherEntity1 != null)
                {
                    ExecResult right=  new ExecResult(true,"修改教师switch成功！");
                    return ResponseEntity.ok().body(right);
                }else {
                    ExecResult er=  new ExecResult(false,"修改教师switch保存失败！请重试");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            } catch (Exception e) {
                ExecResult er=  new ExecResult(false,"修改教师switch失败，系统异常！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
        }else {
            ExecResult er=  new ExecResult(false,"系统获取需要修改的教师switch失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value = "分页根据教师ID查询教师信息")
    @GetMapping(value = "/teacher/findTeacherpage")
    public ResponseEntity pageGetTeacher(@RequestParam String keyword, @RequestParam int index,@RequestParam int length){
        Page<BaseTeacherEntity> baseTeacherEntities = baseTeacherService.findTeacherByKeywords(keyword,new PageRequest(index,length));
        if (baseTeacherEntities!=null){
            return ResponseEntity.ok().body(baseTeacherEntities);
        }else {
            ExecResult er= new ExecResult(false,"用户不存在");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }

    }

}
