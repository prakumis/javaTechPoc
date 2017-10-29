package com.nyp.shopping.opensource.emailsender;

import java.util.Map;

/*
 * TODO: Try to make this class generic to support any object type 
 * by converting the class from Context to Context<T>
 * More Read: https://stackoverflow.com/questions/6715642/template-method-pattern-with-implementation-specific-parameter-type
 * 
 */
public class Context {

	private Object body;

	private Map<String, String> dataMap;

	public Context(Object obj) {
		this.body = obj;
	}

	public Context(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	public Context(Object obj, Map<String, String> dataMap) {
		this.body = obj;
		this.dataMap = dataMap;
	}

	public Object getBody() {
		return body;
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}

	public void setDataValue(String key, String value) {
		this.dataMap.put(key, value);
	}

	public String getDataValue(String key) {
		return this.dataMap.get(key);
	}
}