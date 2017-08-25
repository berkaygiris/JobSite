package services;

import java.util.List;

import domain.BlackList;

public interface BlackListService {
	List<BlackList> listAll();
	
	BlackList getById(String id);
	
	BlackList saveOrUpdate(BlackList blacklist);
	
	void delete(String id);
}
