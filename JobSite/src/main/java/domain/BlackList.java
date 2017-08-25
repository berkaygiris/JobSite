package domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BlackList {
	@Id
    public String id;

	private Map<String, String> list;
	private String fakeid;

	public String getFakeid() {
		return fakeid;
	}

	public void setFakeid(String fakeid) {
		this.fakeid = fakeid;
	}

	public BlackList() {
    	this.list = new HashMap<String, String>();
    }
	
	public BlackList(String name) {
    	this.list = new HashMap<String, String>();
    	this.fakeid = new String(name);
    }

	public Map<String, String> getList() {
		return list;
	}

	public void setList(Map<String, String> list) {
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void add(String id, String reason) {
		list.put(id, reason);
	}
	
	public String getReason(String id) {
		return list.get(id);
	}
	
	public void remove(String id) {
		list.remove(id);
	}

}
