package com.github.hygoni.dormitory.service;

import com.github.hygoni.dormitory.model.CommonResult;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    public enum CommonResponse{
        SUCCESS(0, "성공");

        int code;
        String msg;

        CommonResponse(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode(){
            return code;
        }

        public String getMsg(){
            return msg;
        }
    }

    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    public CommonResult getFailResult (int code, String msg) {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    private void setSuccessResult(CommonResult result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}
