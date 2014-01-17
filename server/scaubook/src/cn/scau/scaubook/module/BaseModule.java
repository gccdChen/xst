package cn.scau.scaubook.module;

import java.util.HashMap;
import java.util.Map;

import cn.scau.scaubook.base.BaseMessage;

public class BaseModule {
    protected BaseMessage msg(int code,String des){
        return new BaseMessage(code, des);
    }
    protected BaseMessage msg(int code,String des,Map<String, Object> map){
        return new BaseMessage(code, des,map);
    }
    protected BaseMessage msg(int code,String des,String name,Object data){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(name, data);
        return new BaseMessage(code, des,map);
    }
    /**
     * 成功的msg
     * @param des 描述
     * @param map 内容
     * @return
     */
    protected BaseMessage m(String des,Map<String, Object> map){
        return new BaseMessage(BaseMessage.CodeType.SUCCESS_CODE, des,map);
    }
    /**
     * 成功的msg
     * @param map 内容
     * @return
     */
    protected BaseMessage m(Map<String, Object> map){
        return new BaseMessage(BaseMessage.CodeType.SUCCESS_CODE, "",map);
    }
    /**
     * 成功的msg
     * @param des
     * @param name
     * @param data
     * @return
     */
    protected BaseMessage m(String des,String name,Object data){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(name, data);
        return new BaseMessage(BaseMessage.CodeType.SUCCESS_CODE, des,map);
    }
    /**
     * 失败的msg
     * @param des
     * @return
     */
    protected BaseMessage mf(String des,String name,Object data){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(name, data);
        return new BaseMessage(BaseMessage.CodeType.FAIL_CODE, des,map);
    }
    /**
     * 失败的msg
     * @param des
     * @param name
     * @param data
     * @return
     */
    protected BaseMessage mf(String des){
        return new BaseMessage(BaseMessage.CodeType.FAIL_CODE, des,null);
    }
    /**
     * 失败的msg
     * @param map 内容
     * @return
     */
    protected BaseMessage mf(Map<String, Object> map){
        return new BaseMessage(BaseMessage.CodeType.FAIL_CODE, "",map);
    }
}
