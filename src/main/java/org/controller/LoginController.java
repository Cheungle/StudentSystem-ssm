package org.controller;


import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.common.BaseContext;
import org.common.JWTContent;
import org.entity.VO.StudentInfoVO;
import org.entity.admin;
import org.entity.student;
import org.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    public UserAuthService userAuthService;
    @Autowired
    public JWTUtil jwtUtil;

    @RequestMapping("/login")
    public String login(@RequestParam("loginUserId") int id, @RequestParam("loginPassword") String password, RedirectAttributes ra) throws IOException {
        admin admin = userAuthService.login(id, password);
        if (admin != null) {
            log.info("用户登录成功");
            String role = admin.getRole();
            //登录成功后，生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put(JWTContent.USER_ID, id);
            claims.put(JWTContent.ROLE, role);
            String token = JWTUtil.createJWT(
                    jwtUtil.getSecretKey(),
                    jwtUtil.getTtl(),
                    claims);
            ra.addFlashAttribute("token", token);
            // 根据用户角色跳转到不同的首页
            if (Objects.equals(role, "admin")) {
                return "redirect:/adminindex";
            } else {
                return "redirect:/index";
            }
        } else {
            return "redirect:/login";
        }

    }

    @RequestMapping("/Logout")
    public String logout() {
        return "redirect:/login.jsp";
    }
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public StudentInfoVO getUserInfo(@RequestHeader(value = "token", required = false) String token) {
        Claims claims = JWTUtil.parseJWT(jwtUtil.getSecretKey(), token);
        int id = Integer.parseInt(claims.get(JWTContent.USER_ID).toString()) ;
        String role = claims.get(JWTContent.ROLE).toString();
        return userAuthService.getUserInfo(id,role);
    }
    @RequestMapping("/adminindex")
    public String adminindex() {
        return "adminindex.jsp";
    }
    @RequestMapping("/index")
    public String index() {
        return "index.jsp";
    }

}
