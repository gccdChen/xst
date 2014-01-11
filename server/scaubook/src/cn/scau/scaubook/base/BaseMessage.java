package cn.scau.scaubook.base;

import org.nutz.json.Json;

public class BaseMessage {
    /**
     * 返回码
     */
    private int code;
    /**
     * 描述
     */
    private String des;
    /**
     * 数据
     */
    private Object data = null;
    public BaseMessage() {
        // TODO Auto-generated constructor stub
        this.code = CodeType.SUCCESS_CODE;
    }
    
    public BaseMessage(int code) {
        super();
        this.code = code;
    }

    public BaseMessage(int code, String des) {
        super();
        this.code = code;
        this.des = des;
    }

    public BaseMessage(int code, String des, Object data) {
        super();
        this.code = code;
        this.des = des;
        this.data = data;
    }

    
    public int getCode() {
        return code;
    }



    public void setCode(int code) {
        this.code = code;
    }



    public String getDes() {
        return des;
    }



    public void setDes(String des) {
        this.des = des;
    }



    public Object getData() {
        return data;
    }



    public void setData(Object data) {
        this.data = data;
    }



    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return Json.toJson(this);
    }
    public class CodeType{
        public static final int SUCCESS_CODE = 10;
        public static final int FAIL_CODE = 101;
    }
}

