package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
public class HomeController {
	private LdapService ldapService;
	private AdvertService advertService;
	private ProfileService profileService;
	private BlackListService blackListService;
	
	@Autowired
	private BlackListRepository blackListRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private LdapRepository ldapRepository;
	
	@Autowired
	private AdvertRepository advertRepository;
	
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
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Locale locale, ModelMap model) {
		if(model.get("profile") == null) {
			return "redirect:/";
		}
		LnkdnProfile oldprofile = (LnkdnProfile) model.get("profile");
		LnkdnProfile newProfile =	profileRepository.findOne(oldprofile.getId());
		model.put("profile", newProfile);
		return "profile";
	}
	
	
	@RequestMapping(value = "/ilanlarKullanici", method = RequestMethod.GET)
	public String ilanlarKullanici(Locale locale, ModelMap model) {
		
		if(model.get("profile") == null) {
			return "redirect:/loginWithLinkedIn";
		}
		List<Advert> advertList = advertService.listAll();
		
		model.addAttribute("advertList",advertList);
		
		return "ilanlarKullanici";
	}
	
	@RequestMapping(value = "/loginWithLinkedIn", method = RequestMethod.GET)
	public String loginWithLinkedIn(Locale locale, ModelMap model) {
		
		return "loginWithLinkedIn";
	}
	
	@RequestMapping(value = "/logoutwithLinkedIn", method = RequestMethod.GET)
	public String logoutwithLinkedIn(Locale locale, ModelMap model) {
		if(model.get("profile") == null) {
			return "redirect:/";
		}
		else {
			model.remove("profile");
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/ilanKullanici/{advertId}", method = RequestMethod.GET)
	public String ilanKullanici(@PathVariable("advertId") String advertId, ModelMap model){
		if(model.get("profile") == null) {
			return "redirect:/loginWithLinkedIn";
		}
		Advert advert = advertService.getById(advertId);
		if( advert == null) {
			return "error";
		}
		model.addAttribute("advert",advert);
		
	    return "ilanKullanici";
	}
	
	@RequestMapping(value = "/addApplicant", method = RequestMethod.POST)
    public String addApplicant( @RequestParam (value="profileId") String profileId,
					    		@RequestParam (value="advertId") String advertId
    							, Locale locale, ModelMap model) throws JSONException {
		
		LnkdnProfile newProfile = profileService.getById(profileId);
		if(newProfile.getBlacklisted() == true) {
			return "redirect:/youAreBlackListed";
		}
		if(newProfile.getApplications().containsKey(advertId)) {
			return "redirect:/alreadyApplied";
		}
		
        Advert newAdvert = advertRepository.findOneById(advertId);
        newAdvert.addApplicant(profileId);
        newProfile.addApplication(advertId, newAdvert.getTitle());
        newProfile.setStatusPending(advertId);
        newAdvert.setCount(newAdvert.getCount()+1);
        
        model.put("profile", newProfile);
        profileService.saveOrUpdate(newProfile);
        advertService.saveOrUpdate(newAdvert);

        return "redirect:/profile";
    }	
	
	@RequestMapping(value = "/addSkills", method = RequestMethod.POST)
    public String addApplicant( @RequestParam (value="profileId") String profileId,
					    		@RequestParam (value="skills") List<String> skills
    							, Locale locale, ModelMap model) {
		
		LnkdnProfile newProfile = profileService.getById(profileId);
		for(int i=0;i<skills.size();i++) {
			newProfile.addSkill(skills.get(i));
		}
        newProfile.sortSkills();
		model.put("profile", newProfile);
        profileService.saveOrUpdate(newProfile);

        return "redirect:/profile";
    }	
		
	
}
