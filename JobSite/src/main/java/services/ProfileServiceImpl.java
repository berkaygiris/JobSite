package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.LnkdnProfile;
import repositories.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }
	
	
	@Override
	public List<LnkdnProfile> listAll() {
		List<LnkdnProfile> profiles = new ArrayList<>();
		profileRepository.findAll().forEach(profiles::add);
        return profiles;
	}

	@Override
	public LnkdnProfile getById(String id) {
		return profileRepository.findOne(id);
	}

	@Override
	public LnkdnProfile saveOrUpdate(LnkdnProfile profile) {
		profileRepository.save(profile);
		return null;
	}

	@Override
	public void delete(String id) {
		profileRepository.delete(id);
	}
		
}
