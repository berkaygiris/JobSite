package repositories;


import org.springframework.data.repository.CrudRepository;

import domain.Advert;

public interface AdvertRepository extends CrudRepository<Advert, String> {
	
	Advert findOneById(String id);
	Advert findOneByTitle(String title);
}