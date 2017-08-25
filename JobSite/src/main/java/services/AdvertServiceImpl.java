package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Advert;
import repositories.AdvertRepository;

@Service
public class AdvertServiceImpl implements AdvertService {
	
	@Autowired
	private AdvertRepository advertRepository;

	@Autowired
    public AdvertServiceImpl(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }
	
	
	@Override
	public List<Advert> listAll() {
		List<Advert> adverts = new ArrayList<Advert>();
		advertRepository.findAll().forEach(adverts::add);
        return adverts;
	}

	@Override
	public Advert getById(String id) {
		return advertRepository.findOne(id);
	}

	@Override
	public Advert saveOrUpdate(Advert advert) {
		advertRepository.save(advert);
		return advert;
	}

	@Override
	public void delete(String id) {
		advertRepository.delete(id);
	}
		
}

