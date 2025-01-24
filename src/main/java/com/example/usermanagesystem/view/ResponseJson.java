package com.example.usermanagesystem.view;

import com.example.usermanagesystem.utils.MessageSourceHolder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * @author hj01857655
 * @create 2025:01:25 20:50
 */
@Setter
@Getter
@Data
public class ResponseJson<T> {
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAILURE = 400;
    public static final int CODE_NOAUTH = 401;
    public static final int CODE_ERROR = 500;

    private int code;
    private String msg;
    private Object data;
    private long total = 1L;

    public static <T> ResponseJson<T> success(String msg, T data) {
        ResponseJson<T> ins = new ResponseJson<>();
        ins.setCode(CODE_SUCCESS);
        ins.setMsg(msg);
        ins.setData(data);
        return ins;
    }

    public static <T> ResponseJson<T> success(T t) {
        return ResponseJson.success(MessageSourceHolder.getMessage("msg.success"), t);
    }

    public static <T> ResponseJson<T> success(Collection<T> list, long total) {
        ResponseJson<T> ins = new ResponseJson<>();
        ins.setCode(CODE_SUCCESS);
        ins.setMsg(MessageSourceHolder.getMessage("msg.success"));
        ins.setData(Objects.requireNonNullElse(list, Collections.emptyList()));
        ins.setTotal(total);
        return ins;
    }

    public static <T> ResponseJson<T> success(Collection<T> list) {
        return success(list, list == null ? 0 : list.size());
    }

    public static <T> ResponseJson<T> success() {
        return ResponseJson.success(MessageSourceHolder.getMessage("msg.success"), null);
    }

    public static <T> ResponseJson<T> failure(String msg) {
        ResponseJson<T> ins = new ResponseJson<>();
        ins.setCode(CODE_FAILURE);
        ins.setMsg(msg);
        return ins;
    }

    public static <T> ResponseJson<T> response(int code, String msg) {
        ResponseJson<T> ins = new ResponseJson<>();
        ins.setCode(code);
        ins.setMsg(msg);
        return ins;
    }

    public static <T> ResponseJson<T> error(String msg) {
        ResponseJson<T> ins = new ResponseJson<>();
        ins.setCode(CODE_ERROR);
        ins.setMsg(msg);
        return ins;
    }

    public long getCount() {
        return total;
    }
}
