package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.BaseMajorEntity;
import com.alienlab.niit.qm.service.BaseMajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/4/9.
 */
@Api(value="/qm-api/major",description = "专业API")
@RestController
@RequestMapping("/qm-api")
public class BaseMajorController {
    @Autowired
    private BaseMajorService baseMajorService;

    //根据专业代码查专业名称
    @ApiOperation(value = "根据专业代码查专业名称")
    @GetMapping(value = "/major")
    public ResponseEntity findMajorByMajorNo(@RequestParam String majorNo){
        BaseMajorEntity baseMajorEntity = baseMajorService.getMajorBymajorNo(majorNo);
        if (majorNo!=null){
            return ResponseEntity.ok().body(baseMajorEntity);
        }else {
            ExecResult er = new ExecResult(false, "未获取专业信息");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
    @ApiOperation(value="查询所有部门")
    @GetMapping(value = "/major/getMajor")
    public ResponseEntity findAllMajor(HttpServletRequest request){
        List<BaseMajorEntity> majorEntities = baseMajorService.findAllMajor();
        request.getSession().setAttribute("majorEntities",majorEntities);
        return ResponseEntity.ok(majorEntities);
    }
}
