package com.example.usermanagesystem.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson2.JSONObject;
import com.example.usermanagesystem.entity.User;
import com.example.usermanagesystem.service.UserService;
import com.example.usermanagesystem.utils.RandomUtils;
import com.example.usermanagesystem.utils.TokenUtils;
import com.example.usermanagesystem.view.ResponseJson;
import com.example.usermanagesystem.view.UserView;
import jakarta.validation.Valid;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {
    //版本号，解决缓存问题
    private static final String version = DateFormatUtils.format(System.currentTimeMillis(), "yyyyMMddHHmm");
    private static final String keyOfCaptcha = RandomUtils.generateUUID();
    @Autowired
    private UserService userService;

    /**
     *
     */
    @GetMapping("/")
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        // 版本号，解决缓存问题
        model.addAttribute("version", version);
        return "index";
    }

    @GetMapping("/captchaImage")
    @ResponseBody
    public ResponseJson<JSONObject> captchaImage() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        JSONObject json = new JSONObject();
        json.put("image", lineCaptcha.getImageBase64());
        json.put("uuid", DigestUtils.md5Hex(keyOfCaptcha + lineCaptcha.getCode()));

        return ResponseJson.success(json);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseJson<JSONObject> login(@RequestBody
                                          UserView param) {
        if (StringUtils.isBlank(param.getUsername())) {
            return ResponseJson.failure("用户名");
        }

        if (StringUtils.isBlank(param.getPassword())) {
            return ResponseJson.failure("密码不能为空");
        }

        //uncomment these code
//        User user = userService
//                .getOne(new QueryWrapper<User>().eq("PHONE_NO", param.getPhoneNo()).eq("USER_TYPE", User.TYPE_ADMIN));
//        if (user == null || user.getStatus() != User.STATUS_ACTIVE) {
//            return ResponseJson.failure("用户不存在或已禁用");
//        }
//
//        if (!user.getPassword().equals(DigestUtils.md5Hex(param.getPassword() + "#" + user.getSecureKey()))) {
//            return ResponseJson.failure("用户名或密码错误");
//        }
//
        String token = TokenUtils.createToken(param, 240);
        if (StringUtils.isBlank(token)) {
            return ResponseJson.failure("登录失败");
        }

        JSONObject json = new JSONObject();
//        json.put("token", token);
//        json.put("name", user.getName());
        json.put("token", token);
        json.put("name", "Jack");
        return ResponseJson.success(json);
    }

    @GetMapping("/user/list")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }


    @PostMapping("/user/add")
    public String addUser(@Valid @ModelAttribute User user, BindingResult result) {
        if (result.hasErrors()) {
            // 处理验证错误
            return "error"; // 假设有一个错误页面
        }
        boolean isAdded = userService.addUser(user);
        return isAdded ? "redirect:/user/list" : "error";
    }


    @PostMapping("/user/edit")
    public String editUser(@ModelAttribute User user) {
        boolean isUpdated = userService.updateUser(user);
        if (isUpdated) {
            return "redirect:/user/list";
        } else {
            // 处理更新失败的情况
            return "error"; // 假设有一个错误页面
        }
    }

    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
} 