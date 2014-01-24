package cn.scau.zzzd.xst.entity;

import org.gccd.json.Json;
public class Tags{
   	private Number count;
   	private String name;
   	private String title;
   	
    public Number getCount(){
		return this.count;
	}
	public void setCount(Number count){
		this.count = count;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
	@Override
    public String toString() {
        return Json.toJson(this);
    }
}
