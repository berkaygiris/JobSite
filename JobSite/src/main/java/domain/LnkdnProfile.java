package domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LnkdnProfile {
	@Id
    public String id;

	private String linkedinid;
	private String name;
	private String surname;
	private String fullname;
	private String headline;
	private String picture;
	private String link;
	private String email;
	private Boolean blacklisted;
	private String blacklistid;
	private List<String> skills;
	private Map<String, String> applications;
	private Map<String, String> status;
	
	public void setStatus(Map<String, String> status) {
		this.status = status;
	}
	public void sortSkills() {
		Collections.sort(this.skills);
	}
	
	public Map<String, String> getStatus() {
		return status;
	}

	public void setStatusYes(String key) {
		this.status.put(key, "Kabul Edildi");
	}
	public void setStatusNo(String key) {
		this.status.put(key, "Reddedildi");
	}
	public void setStatusPending(String key) {
		this.status.put(key, "Beklemede");
	}

	public void addSkill(String skl) {
		if(!skills.contains(skl)) {
			this.skills.add(skl);
		}
	}
	
	public void addApplication(String Id, String Title) {
		this.applications.put(Id,Title);
	}
	
	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public Map<String, String> getApplications() {
		return applications;
	}

	public void setApplications(Map<String, String> applications) {
		this.applications = applications;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getBlacklisted() {
		return blacklisted;
	}

	public void setBlacklisted(Boolean blacklisted) {
		this.blacklisted = blacklisted;
	}

	public String getBlacklistid() {
		return blacklistid;
	}

	public void setBlacklistid(String blacklistid) {
		this.blacklistid = blacklistid;
	}

	public String getLinkedinid() {
		return linkedinid;
	}

	public void setLinkedinid(String linkedinid) {
		this.linkedinid = linkedinid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}


    public LnkdnProfile() {
    	this.applications = new HashMap<String, String>();
		this.status = new HashMap<String, String>();
		this.blacklisted = false;
    }

    public LnkdnProfile(String linkedinid, String name, String surname, String fullname, String headline, String picture, String link, String email) {
    	this.linkedinid = linkedinid;
		this.name = name;
		this.surname = surname;
		this.headline =headline;
		this.link = link;
		this.fullname = fullname;
		this.picture = picture;
		this.email = email;
		this.blacklisted = false;
		this.blacklistid = null;
		this.skills = new ArrayList();
		this.applications = new HashMap<String, String>();
		this.status = new HashMap<String, String>();
    }
    public LnkdnProfile(String name, String surname, String headline, String picture) {
    	this.linkedinid = "0"+name+"0"+surname;
		this.name = name;
		this.surname = surname;
		this.headline =headline;
		this.link = "www.berkaygiris.com";
		this.fullname = name+" "+surname;
		this.picture = picture;
		this.email = name+surname+"@gmail.com";
		this.blacklisted = false;
		this.blacklistid = null;
		this.skills = new ArrayList();
		this.applications = new HashMap<String, String>();
		this.status = new HashMap<String, String>();
    }

    @Override
    public String toString() {
        return String.format(
                "LnkdnProfile[id=%s, linkedinid='%s', name='%s',surname='%s', fullname='%s', headline='%s', picture='%s', link='%s']",
                id, linkedinid, name, surname, fullname, headline, picture, link);
    }

}
