package services;

import java.util.List;

import domain.LnkdnProfile;

public interface ProfileService {
	List<LnkdnProfile> listAll();
	LnkdnProfile getById(String id);

	LnkdnProfile saveOrUpdate(LnkdnProfile profile);

	void delete(String id);
	
	

}
