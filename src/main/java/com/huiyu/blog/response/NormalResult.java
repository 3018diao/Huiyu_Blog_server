package com.huiyu.blog.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalResult<T> {
    private String errCode = "";
    private String status; // success / fail
    private String message = "";
    private T data;

    public static <T> NormalResult<T> success(T data) {
        NormalResult<T> result = new NormalResult<>();
        result.setData(data);
        result.setStatus("success");
        return result;
    }

    public static <T> NormalResult<T> fail(String errCode, String message) {
        NormalResult<T> result = new NormalResult<>();
        result.setStatus("failure");
        result.setErrCode(errCode);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
