package com.xzq.commonUtils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
/**
 * @Des 统一返回类
 * @Param
 * @Return
 * @Author xuzhiqiang
 * @Date 2021/1/20 14:08
 */
@Data
public class ResultMessage {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private ResultMessage(){}
    /**
     * @Description: 成功静态方法
     * @Author xuzhiqiang
     * @Date 2021/1/20 14:09
     */
    public static ResultMessage ok(){
        ResultMessage r = new ResultMessage();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    /**
     * @Description: 失败静态方法
     * @Author xuzhiqiang
     * @Date 2021/1/20 14:10
     */
    public static ResultMessage error(){
        ResultMessage r = new ResultMessage();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }
    public ResultMessage success(Boolean success){
        this.setSuccess(success);
        return this;
    }
    public ResultMessage message(String message){
        this.setMessage(message);
        return this;
    }
    public ResultMessage code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResultMessage data(String key, Object value){
        this.data.put(key, value);

        return this;
    }
    public ResultMessage data(Map<String, Object> map){

        this.setData(map);

        return this;
    }

}
