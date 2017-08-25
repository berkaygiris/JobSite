package controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import domain.Advert;
import domain.BlackList;
import domain.LdapProfile;
import domain.LnkdnProfile;
import initializer.Adverts;
import initializer.People;
import repositories.AdvertRepository;
import repositories.BlackListRepository;
import repositories.LdapRepository;
import repositories.ProfileRepository;
import services.AdvertService;
import services.BlackListService;
import services.LdapService;
import services.ProfileService;


@Controller
@SessionAttributes({"ldapProfile", "currentLdap", "profile"})
public class ResetDatabase {
	private LdapService ldapService;
	private AdvertService advertService;
	private ProfileService profileService;
	private BlackListService blackListService;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private LdapRepository ldapRepository;
	
	@Autowired
	private AdvertRepository advertRepository;
	
	@Autowired
	private BlackListRepository blackListRepository;
	
	@Autowired
	public void setBlackListService(BlackListService blackListService) {
		this.blackListService = blackListService;
	}
	
	@Autowired
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Autowired
    public void setAdvertService(AdvertService advertService) {
        this.advertService = advertService;
    }
	
	
    @Autowired
    public void setProfileService(LdapService ldapService) {
        this.ldapService = ldapService;
    }
	
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String reset(Locale locale, ModelMap model) throws ParseException {
		Advert tempAd;
		LnkdnProfile tempPrf;
		
		String uid = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(uid.equals("girisb")) {
			mongoTemplate.getDb().dropDatabase();
			People people = new People();
			List<LnkdnProfile> profileList =  people.getProfileList();
			for (LnkdnProfile profile : profileList) {
				profileService.saveOrUpdate(profile);
			}
			Adverts ads = new Adverts();
			List<Advert> adsList = ads.getAds();
			
			for(Advert advert : adsList) {
				advertService.saveOrUpdate(advert);
			}

			applianceRequest("Software Developer", "Courtney Dobson");
			applianceRequest("Software Developer", "Kate Brin");
			applianceRequest("Software Developer", "Mark Christensen");
			applianceRequest("Software Developer", "Gudh Guy");
			applianceRequest("Software Developer", "Sun Sand");
			applianceRequest("Software Developer", "Jonathan Doe");
			acceptRequest("Software Developer", "Mark Christensen");	
			
			applianceRequest("Stajyer Mühendis", "Orhun Black");
			applianceRequest("Stajyer Mühendis", "Stuart Parker");
			applianceRequest("Stajyer Mühendis", "Jonathan Doe");
			applianceRequest("Stajyer Mühendis", "Mark Christensen");
			applianceRequest("Stajyer Mühendis", "Kate Brin");
			applianceRequest("Stajyer Mühendis", "Tom Rich");
			acceptRequest("Stajyer Mühendis", "Tom Rich");
			acceptRequest("Stajyer Mühendis", "Orhun Black");
			denyRequest("Stajyer Mühendis","Stuart Parker");
			denyRequest("Stajyer Mühendis","Jonathan Doe");
			denyRequest("Stajyer Mühendis","Kate Brin");

			applianceRequest("Uzun Dönem Stajyer", "Kate Brin");
			applianceRequest("Uzun Dönem Stajyer", "Mark Christensen");
			applianceRequest("Uzun Dönem Stajyer", "Jane Doe");
			applianceRequest("Uzun Dönem Stajyer", "Courtney Dobson");
			applianceRequest("Uzun Dönem Stajyer", "Gudh Guy");
			applianceRequest("Uzun Dönem Stajyer", "Sun Sand");
			applianceRequest("Uzun Dönem Stajyer", "Tom Rich");
			applianceRequest("Uzun Dönem Stajyer", "Jonathan Doe");
			applianceRequest("Uzun Dönem Stajyer", "Stuart Parker");
			
			
			BlackList blackList = new BlackList("blacklist");
			blackListService.saveOrUpdate(blackList);
		}
		
		return "redirect:/ilanlar";
	}
	public void denyRequest(String title, String fullname) {
		Advert tempAd;
		LnkdnProfile tempPrf;
		tempAd = advertRepository.findOneByTitle(title);
		tempPrf = profileRepository.findOneByFullname(fullname);
		tempPrf.setStatusNo(tempAd.getId());
		profileService.saveOrUpdate(tempPrf);
	}
	
	public void acceptRequest(String title, String fullname) {
		Advert tempAd;
		LnkdnProfile tempPrf;
		tempAd = advertRepository.findOneByTitle(title);
		tempPrf = profileRepository.findOneByFullname(fullname);
		tempPrf.setStatusYes(tempAd.getId());
		profileService.saveOrUpdate(tempPrf);
	}
	
	public void applianceRequest(String title, String fullname) {
		Advert tempAd;
		LnkdnProfile tempPrf;
		tempAd = advertRepository.findOneByTitle(title);
		tempPrf = profileRepository.findOneByFullname(fullname);
		tempAd.addApplicant(tempPrf.getId());
		tempPrf.addApplication(tempAd.getId(), tempAd.getTitle());
		tempPrf.setStatusPending(tempAd.getId());
		tempAd.setCount(tempAd.getCount()+1);
		advertService.saveOrUpdate(tempAd);
		profileService.saveOrUpdate(tempPrf);
	}
	
}
