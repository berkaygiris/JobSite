package repositories;


import org.springframework.data.repository.CrudRepository;

import domain.LdapProfile;


public interface LdapRepository extends CrudRepository<LdapProfile, String> {
	
	LdapProfile findOneByUid(String uid);
}