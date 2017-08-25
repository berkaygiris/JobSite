package controllers;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.xml.sax.SAXException;

import domain.LnkdnProfile;
//import guru.springframework.services.ProductService;
import linkedin.LinkedinAuth;
import repositories.ProfileRepository;
import services.ProfileService;
 
/**
 * Handles and retrieves the login or denied page depending on the URI template
 */
@Controller
@RequestMapping("/auth")
@SessionAttributes("profile")
public class LoginLogoutController {
	
	@Autowired
	private ProfileService profileService;
	@Autowired
	private ProfileRepository profileRepository;
	/*
	@Autowired
    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }*/
	
 protected static Logger logger = Logger.getLogger("controller");
 
 /**
  * Handles and retrieves the login JSP page
  * 
  * @return the name of the JSP page
 * @throws SAXException 
 * @throws JSONException 
 * @throws ParserConfigurationException 
 * @throws IOException 
  */
 
 @RequestMapping(value = "/linkedin", method = {RequestMethod.POST, RequestMethod.GET})
	public String linkedin(@RequestParam("code") String strCode, @RequestParam("state") String strState, ModelMap model) throws JSONException, ParserConfigurationException, SAXException{
	 	
	 	
	 	LnkdnProfile  profile = LinkedinAuth.getData(strCode, strState);
	 	LnkdnProfile newProfile = profileRepository.findOneByLinkedinid(profile.getLinkedinid());
	 	if(newProfile != null) {
	 		profile.setId(newProfile.getId());
	 		profile.setApplications(newProfile.getApplications());
	 		profile.setSkills(newProfile.getSkills());
	 		profile.setStatus(newProfile.getStatus());
	 		profile.setBlacklisted(newProfile.getBlacklisted());
	 	}
	 	profileService.saveOrUpdate(profile);
	 	

	 	model.put("profile", profile);
	 	
		return "redirect:/profile";
	 	
	 	
	}
 @RequestMapping(value = "/login", method = RequestMethod.GET)
 public String getLoginPage(@RequestParam(value="error", required=false) boolean error, 
   ModelMap model) {
	 
  logger.debug("Received request to show login page");
 
 
  if (error == true) {
   // Assign an error message
   model.put("error", "You have entered an invalid username or password!");
  } else {
   model.put("error", "");
  }
   
  // This will resolve to /WEB-INF/jsp/loginpage.jsp
  return "login";
 }
  
 /**
  * Handles and retrieves the denied JSP page. This is shown whenever a regular user
  * tries to access an admin only page.
  * 
  * @return the name of the JSP page
  */
 @RequestMapping(value = "/denied", method = RequestMethod.GET)
  public String getDeniedPage() {
  logger.debug("Received request to show denied page");
  return "deniedpage";
 }
}
