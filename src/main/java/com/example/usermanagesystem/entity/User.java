package com.example.usermanagesystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("users")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 5, message = "用户名至少得5个字符")
    private String username;

    private String password;

    @Email(message = "邮箱格式不正确")
    private String email;

    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // Getters and Setters
} 