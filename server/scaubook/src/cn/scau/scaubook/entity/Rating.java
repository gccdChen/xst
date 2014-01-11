package cn.scau.scaubook.entity;

import org.nutz.json.Json;

public class Rating{
   	private String average;
   	private Number max;
   	private Number min;
   	private Number numRaters;

 	public String getAverage(){
		return this.average;
	}
	public void setAverage(String average){
		this.average = average;
	}
 	public Number getMax(){
		return this.max;
	}
	public void setMax(Number max){
		this.max = max;
	}
 	public Number getMin(){
		return this.min;
	}
	public void setMin(Number min){
		this.min = min;
	}
 	public Number getNumRaters(){
		return this.numRaters;
	}
	public void setNumRaters(Number numRaters){
		this.numRaters = numRaters;
	}
    @Override
    public String toString() {
        return Json.toJson(this);
    }
}
