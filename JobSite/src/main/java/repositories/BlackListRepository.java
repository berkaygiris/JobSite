package repositories;

import org.springframework.data.repository.CrudRepository;

import domain.BlackList;

public interface BlackListRepository extends CrudRepository<BlackList, String> {
	
	BlackList findOneById(String id);
	BlackList findOneByFakeid(String fakeid);
}
