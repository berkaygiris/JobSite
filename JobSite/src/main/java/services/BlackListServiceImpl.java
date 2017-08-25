package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.BlackList;
import repositories.BlackListRepository;

@Service
public class BlackListServiceImpl implements BlackListService {
	
	@Autowired
	private BlackListRepository blackListRepository;
	
	@Autowired
	public BlackListServiceImpl(BlackListRepository blackListRepository) {
		this.blackListRepository = blackListRepository;
	}

	@Override
	public List<BlackList> listAll() {
		List<BlackList> blackList = new ArrayList<BlackList>();
		return blackList;
	}

	@Override
	public BlackList getById(String id) {
		return blackListRepository.findOne(id);
	}

	@Override
	public BlackList saveOrUpdate(BlackList blacklist) {
		blackListRepository.save(blacklist);
		return blacklist;
	}

	@Override
	public void delete(String id) {
		blackListRepository.delete(id);
		
	}
	
}
