package domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LdapProfile {
	@Id
    public String id;

	private String name;
	private String uid;

    public LdapProfile() {}

    public LdapProfile(String name, String uid) {
    	this.name = name;
		this.uid = uid;
    }

    @Override
    public String toString() {
        return String.format(
                "LdapProfile[id=%s, name='%s', uid='%s]",
                id, name, uid);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getId() {
		return id;
	}

}
