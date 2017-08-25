package services;

import java.util.List;

import domain.Advert;

public interface AdvertService {
	List<Advert> listAll();
	Advert getById(String id);

	Advert saveOrUpdate(Advert profile);

	void delete(String id);
}