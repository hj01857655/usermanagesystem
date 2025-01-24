package com.example.usermanagesystem.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author hj01857655
 * @create 2025:01:25 20:51
 */
@Component

public class MessageSourceHolder {
    private static MessageSourceHolder holder;

    @Autowired
    private MessageSource messageSource;

    public static String getMessage(String key) {
        return holder.getMessage0(key);
    }

    public static String getMessage(String key, Object[] args) {
        return holder.getMessage0(key, args);
    }

    @PostConstruct
    public void init() {
        holder = this;
    }

    private String getMessage0(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    private String getMessage0(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }
}
