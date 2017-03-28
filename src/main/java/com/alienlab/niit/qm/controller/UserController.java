package com.alienlab.niit.qm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alienlab.niit.qm.common.Azdg;
import com.alienlab.niit.qm.common.Md5Azdg;
import com.alienlab.niit.qm.controller.util.ExecResult;
import com.alienlab.niit.qm.entity.TbUserEntity;
import com.alienlab.niit.qm.repository.UserRepository;
import com.alienlab.niit.qm.service.UserService;
import io.swagger.annotations.*;
import io.swagger.models.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by 橘 on 2017/3/14.
 */
@Api(value="/qm-api/user",description = "用户API")
@RestController
@RequestMapping("/qm-api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @ApiOperation(value="获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="keyword",value="查询关键字",paramType = "query"),
            @ApiImplicitParam(name="index",value="分页位置",paramType = "query"),
            @ApiImplicitParam(name="length",value="分页长度",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "", response = TbUserEntity.class),
            @ApiResponse(code = 500, message = "", response = ExecResult.class)
    })
    @GetMapping(value="/user")
    public ResponseEntity listUser(@RequestParam String keyword,@RequestParam int index,@RequestParam int length){
        try {
            Page<TbUserEntity> users=userService.listUser(keyword,new PageRequest(index,length));
            return ResponseEntity.ok().body(users);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    @GetMapping(value="/user/{userid}")
    public ResponseEntity listUser(@PathVariable long userid,@RequestParam String loginname){
        try {
            return ResponseEntity.ok().body(null);
        } catch (Exception e) {
            e.printStackTrace();
            ExecResult er=new ExecResult(false,e.getMessage());
            //发生错误返回500状态
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }

    //用户登录
    @ApiOperation(value="用户登录")
    @PostMapping(value = "/dologin")
    public ResponseEntity doLogin(@RequestParam String loginname, @RequestParam String password,HttpServletRequest request){
        try{
            Azdg a=new Azdg();
            String pwd= a.encrypt(loginname, password);
            TbUserEntity user=userService.findUserByloginname(loginname);
            if(user==null){
                ExecResult er= new ExecResult(false,"登录用户不存在");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);

            }else{
                if(user.getUserPwd().equals(password)){//登录成功
                    request.getSession().setAttribute("user",user);//当前用户进入session
                    return ResponseEntity.ok().body(user);
                }else{
                    ExecResult er=  new ExecResult(false,"用户名或密码错误");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            ExecResult er= new ExecResult(false,"登录发生异常");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
        }
    }



}
