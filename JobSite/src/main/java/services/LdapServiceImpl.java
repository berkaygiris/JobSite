package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.LdapProfile;
import repositories.LdapRepository;

@Service
public class LdapServiceImpl implements LdapService {
	
	@Autowired
	private LdapRepository LdapRepository;

	@Autowired
    public LdapServiceImpl(LdapRepository LdapRepository) {
        this.LdapRepository = LdapRepository;
    }
	
	
	@Override
	public List<LdapProfile> listAll() {
		List<LdapProfile> profiles = new ArrayList<>();
		LdapRepository.findAll().forEach(profiles::add);
        return profiles;
	}

	@Override
	public LdapProfile getById(String id) {
		return LdapRepository.findOne(id);
	}

	@Override
	public LdapProfile saveOrUpdate(LdapProfile profile) {
		LdapRepository.save(profile);
		return null;
	}

	@Override
	public void delete(String id) {
		LdapRepository.delete(id);
	}
		
}
