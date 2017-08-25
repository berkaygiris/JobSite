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
public class AdminPages {
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
    


	@RequestMapping(value = "/ilanlar", method = RequestMethod.GET)
	public String ilanlar(Locale locale, ModelMap model) {
		
		List<Advert> advertList = advertService.listAll();
		
		model.addAttribute("advertList",advertList);
		
		return "ilanlar";
	}
	
	@RequestMapping(value = "/people", method = RequestMethod.GET)
	public String people(Locale locale, ModelMap model) {
		
		List<LnkdnProfile> profileList = profileService.listAll();
		
		model.addAttribute("profileList",profileList);
		
		return "people";
	}
	
	@RequestMapping(value = "/karaliste", method = RequestMethod.GET)
	public String karaliste(Locale locale, ModelMap model) {
		
		BlackList blacklist = blackListRepository.findOneByFakeid("blacklist");
		model.addAttribute("blacklist", blacklist);
		
		List<LnkdnProfile> profileList = new ArrayList<LnkdnProfile>();
		for (Map.Entry<String, String> app : blacklist.getList().entrySet()) {
		    String key = app.getKey();
		    profileList.add(profileService.getById(key));
		}
		model.addAttribute("profileList",profileList);
		
		return "blacklist";
	}	

	@RequestMapping(value = "/ilanver", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("ilanver", "advert", new Advert());
    }
	
	
	
	@RequestMapping(value = "/ilan/{advertId}", method = RequestMethod.GET )
	public String ilanshow(@PathVariable("advertId") String advertId, ModelMap model){
		
		Advert advert = advertService.getById(advertId);
		if( advert == null) {
			return "error";
		}
	    model.addAttribute("advert",advert);
	  
	    List<String> applicantIds = advert.getApplicantList();
	    List<LnkdnProfile> applicants = new ArrayList<LnkdnProfile>();
	    
	    for(int i=0;i<applicantIds.size();i++) {
	    	applicants.add(profileService.getById( applicantIds.get(i)));
	    }
	    model.addAttribute("applicants",applicants);
	    
	    
	    return "ilan";
	}
	
	@RequestMapping(value = "/adminProfile/{profileId}", method = RequestMethod.GET )
	public String profileshow(@PathVariable("profileId") String profileId, ModelMap model){
		
		LnkdnProfile profile = profileService.getById(profileId);
		if( profile == null) {
			return "error";
		}
	    model.addAttribute("person",profile);
	    
	    return "adminProfile";
	}
	
	@RequestMapping(value = "/addAdvert", method = RequestMethod.POST)
    public String addAdvert(@Valid @ModelAttribute("advert")Advert advert, 
      BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        
        advertService.saveOrUpdate(advert);

        return "home";
    }	
	
	@RequestMapping(value = "/addBlackList", method = RequestMethod.POST)
    public String addBlackList( @RequestParam (value="profileId") String profileId,
					    		@RequestParam (value="reason") String reason
    							, Locale locale, ModelMap model) {
		
		LnkdnProfile profile = profileRepository.findOne(profileId);
		
		profile.setBlacklisted(true);
		
		//deny all applications
		for (Map.Entry<String, String> app : profile.getApplications().entrySet()) {
		    String key = app.getKey();
		    profile.setStatusNo(key);
		}
		BlackList blackList = blackListRepository.findOneByFakeid("blacklist");
		if(blackList.getList() == null) {
			blackList.setList(new HashMap<String, String>());
		}
		blackList.add(profileId, reason);
		
		profileService.saveOrUpdate(profile);
		blackListService.saveOrUpdate(blackList);

        return "redirect:/adminProfile/"+ profileId;
    }
	
	@RequestMapping(value = "/accept/{profileId}/{advertId}", method = RequestMethod.GET )
	public String accept(@PathVariable("profileId") String profileId, @PathVariable("advertId") String advertId, ModelMap model){
		
		LnkdnProfile profile = profileService.getById(profileId);
		
		if( profile == null) {
			return "error";
		}
		
		profile.setStatusYes(advertId);
		
		profileService.saveOrUpdate(profile);
	    
	    return "redirect:/ilan/" + advertId;
	}	
	
	@RequestMapping(value = "/deny/{profileId}/{advertId}", method = RequestMethod.GET )
	public String deny(@PathVariable("profileId") String profileId, @PathVariable("advertId") String advertId, ModelMap model){
		
		LnkdnProfile profile = profileService.getById(profileId);
		
		if( profile == null) {
			return "error";
		}
		
		profile.setStatusNo(advertId);
		
		profileService.saveOrUpdate(profile);
	    
	    return "redirect:/ilan/" + advertId;
	}	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, ModelMap model) {
		
		LdapUserDetailsImpl ldapDetails = (LdapUserDetailsImpl) SecurityContextHolder
	            .getContext().getAuthentication().getPrincipal();
		String dn = ldapDetails.getDn();
		int beginIndex = dn.indexOf("cn=") + 3;
		int endIndex = dn.indexOf(",");
		String username = dn.substring(beginIndex, endIndex);
		String uid = SecurityContextHolder.getContext().getAuthentication().getName();
		LdapProfile user = new LdapProfile(username, uid);
		ldapService.saveOrUpdate(user);
		
		
		model.put("ldapProfile", ldapRepository.findOneByUid(uid));
		model.put("currentLdap", uid);
		
		return "redirect:/ilanlar";
	}
	
	
}
