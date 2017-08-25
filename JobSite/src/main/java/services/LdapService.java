package services;

import java.util.List;

import domain.LdapProfile;

public interface LdapService {
	List<LdapProfile> listAll();
	LdapProfile getById(String id);

	LdapProfile saveOrUpdate(LdapProfile profile);

	void delete(String id);
	
	

}
