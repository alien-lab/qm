package com.alienlab.niit.qm.controller;

import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.QmStuPjEntity;
import com.alienlab.niit.qm.repository.QmStuPjRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/14.
 */
@Api(value="/qm-api/",description = "评教课程API")
@RestController
@RequestMapping("/qm-api")
public class QmStuPjController {
    @Autowired
    private QmStuPjRepository qmStuPjRepository;

    @ApiOperation(value="修改评教内容")
    @GetMapping(value = "/qmstupj/updatestupj")
    public ResponseEntity updateStuPj(@RequestParam(required = false) String jxjy, @RequestParam(required = false) String jxpj,
                                      @RequestParam(required = false) Integer per11, @RequestParam(required = false) Integer per12,
                                      @RequestParam(required = false) Integer per13, @RequestParam(required = false) Integer per14,
                                      @RequestParam(required = false) Integer per15, @RequestParam String stuNo,
                                      @RequestParam long taskNo){
        QmStuPjEntity qmStuPjEntity = qmStuPjRepository.findByStuNoAndTaskNo(stuNo,taskNo);
        if(qmStuPjEntity != null){
            qmStuPjEntity.setJxpj(jxpj);
            qmStuPjEntity.setJxjy(jxjy);
            qmStuPjEntity.setPer11(per11);
            qmStuPjEntity.setPer12(per12);
            qmStuPjEntity.setPer13(per13);
            qmStuPjEntity.setPer14(per14);
            qmStuPjEntity.setPer15(per15);
            qmStuPjEntity.setTotal(per11+per12+per13+per14+per15);
            QmStuPjEntity qmStuPjEntity1 = qmStuPjRepository.save(qmStuPjEntity);
            return ResponseEntity.ok().body(qmStuPjEntity1);
        }else {
            ExecResult er=  new ExecResult(false,"修改评教信息失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }
}
