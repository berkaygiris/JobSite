package repositories;


import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import domain.LnkdnProfile;


public interface ProfileRepository extends CrudRepository<LnkdnProfile, String> {
	LnkdnProfile findOneByLinkedinid(String id);
	LnkdnProfile findOneByFullname(String fullname);
	
}