package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseDepartmentEntity;
import com.alienlab.niit.qm.service.BaseDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */
@Api(value="/qm-api/department",description = "部门API")
@RestController
@RequestMapping("/qm-api")
public class BaseDepartmentController {

    @Autowired
    private BaseDepartmentService baseDepartmentService;
    //查询所有部门
    @ApiOperation(value="查询所有部门")
    @GetMapping(value = "/findAllDepartment")
    public ResponseEntity findAllDepartment(HttpServletRequest request){
        List<BaseDepartmentEntity> department = baseDepartmentService.getDepartment();
        request.getSession().setAttribute("department",department);
        return ResponseEntity.ok(department);
    }

    @ApiOperation(value="获取单个内容")
    @GetMapping(value = "/updateDepartment")
    public ResponseEntity updateDepartment(@RequestParam long id,HttpServletRequest request) throws IOException {
       /* String jsonBody=IOUtils.toString(request.getInputStream(),"UTF-8");
        JSONObject form=JSONObject.parseObject(jsonBody);
        Long depSort = form.getLong("depSort");*/
        BaseDepartmentEntity departmentEntity =baseDepartmentService.getBaseDepartmentById(id);
        if(departmentEntity != null){
            return ResponseEntity.ok().body(departmentEntity);
        }else {
            ExecResult er=  new ExecResult(false,"获取部门列表失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);

        }
    }

    @ApiOperation(value="保存部门内容")
    @PostMapping(value = "/saveDepartment")
    public ResponseEntity saveDepartment(@RequestParam Long depNo,@RequestParam String depName,@RequestParam String depCddwNo,
                                         @RequestParam String depType,@RequestParam Integer depSort,@RequestParam String depAbbreviation){
        BaseDepartmentEntity departmentEntity =baseDepartmentService.getBaseDepartmentById(depNo);
        if(departmentEntity != null){
            departmentEntity.setDepNo(depNo);
            departmentEntity.setDepName(depName);
            departmentEntity.setDepCddwNo(depCddwNo);
            departmentEntity.setDepType(depType);
            departmentEntity.setDepSort(depSort);
            departmentEntity.setDepAbbreviation(depAbbreviation);
            BaseDepartmentEntity result = baseDepartmentService.saveDepartment(departmentEntity);
            ExecResult right=  new ExecResult(true,"保存部门信息成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"保存部门信息失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @ApiOperation(value="增加部门内容")
    @PostMapping(value = "/addDepartment")
    public ResponseEntity addDepartment(@RequestParam Long depNo,@RequestParam String depName,@RequestParam String depCddwNo,
                                         @RequestParam String depType,@RequestParam Integer depSort,@RequestParam String depAbbreviation){
            BaseDepartmentEntity departmentEntity = new BaseDepartmentEntity();
            departmentEntity.setDepNo(depNo);
            departmentEntity.setDepName(depName);
            departmentEntity.setDepCddwNo(depCddwNo);
            departmentEntity.setDepType(depType);
            departmentEntity.setDepSort(depSort);
            departmentEntity.setDepAbbreviation(depAbbreviation);
            BaseDepartmentEntity departmentEntity1 = baseDepartmentService.saveDepartment(departmentEntity);
            if (departmentEntity1!=null){
                return ResponseEntity.ok().body(departmentEntity1);
            }else {
                ExecResult er=  new ExecResult(false,"新增菜单失败！请重试");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
            }
    }

    @ApiOperation(value="删除部门内容")
    @DeleteMapping(value = "/deleteDepartment")
    public ResponseEntity deleteDepartment(@RequestParam Long dep_no){
        boolean flag= false;
        flag = baseDepartmentService.deleteDepartment(dep_no);
        if (flag==true){
            ExecResult right=  new ExecResult(true,"删除部门成功！");
            return ResponseEntity.ok().body(right);
        }else {
            ExecResult er=  new ExecResult(false,"删除部门失败！请重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

}
