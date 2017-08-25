package domain;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Advert {
	@Id
    public String id;

	private String title;
	private String text;
	private Integer count;
	private boolean active;
	private Date start;
	private Date end;
	private List<String> skills;
	private List<String> applicantList;

    public Advert(String title, String text, Date start, Date end, String active) {
    	this.title = title;
    	this.text = text;
    	this.start = start;
    	this.end = end;
    	if(active.equals("1"))
    		this.active = true;
    	else
    		this.active = false;
    	this.count = 0;
    	this.applicantList = new ArrayList<String>();
    	this.skills = new ArrayList<String>();
    }
    
    
    public void addApplicant(String profileId) {
		this.applicantList.add(profileId);
	}
    
    public List<String> getApplicantList() {
		return applicantList;
	}


	public void setApplicantList(List<String> applicantIds) {
		this.applicantList = applicantIds;
	}

	public List<String> getSkills() {
		return skills;
	}


	public void addSkill(String skill) {
		this.skills.add(skill);
	}


	public void setSkills(List<String> skills) {
		this.skills = skills;
	}





	public void setActive(boolean active) {
		this.active = active;
	}


	public Advert() {
    	this.count = 0;
    	applicantList = new ArrayList<String>();
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	public void setActive(String active) {
		if (active.equals("false"))
			this.active = false;
		else
			this.active = true;
		
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}


	public String getId() {
		return id;
	}
	
	public String stringStart() {
		Format formatter = new SimpleDateFormat("dd.MM.yyyy");
		String s = formatter.format(this.start);
		return s;
	}
	public String stringEnd() {
		Format formatter = new SimpleDateFormat("dd.MM.yyyy");
		String s = formatter.format(this.end);
		return s;
	}

}
