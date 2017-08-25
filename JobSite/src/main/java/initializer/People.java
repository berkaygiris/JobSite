package initializer;

import java.util.ArrayList;
import java.util.List;


import domain.LnkdnProfile;

public class People {
	
	private List<LnkdnProfile> profileList;
	
	public People() {	//Profile constructor inputs  name,  surname,  headline,  picture,  
		profileList = new ArrayList<LnkdnProfile>();
		LnkdnProfile temp;
		temp = new LnkdnProfile(
				"Kate",
				"Brin",
				"ODTÜ Aşçılık Öğrencisi",
				"http://www.aspph.org/app/uploads/2015/11/KatelynnDodd1-e1446654853302-200x200.jpg"
				);
		temp.addSkill("C++");
		profileList.add(temp);	
		temp = new LnkdnProfile(
				"Mark",
				"Christensen",
				"Süper Bilişim'de Software Developer",
				"http://about.library.ubc.ca/files/2014/09/Mark_Christensen.jpeg"
				);
		temp.addSkill("HTML");
		temp.addSkill("C++");
		temp.addSkill("Networking");
		profileList.add(temp);
		temp = new LnkdnProfile(
				"Jane",
				"Doe",
				"Bilim Kadını",
				"https://media.licdn.com/mpr/mpr/shrink_200_200/p/6/005/014/0f0/243bd88.jpg"
				);
		temp.addSkill("AutoCAD");
		temp.addSkill("CSS");
		temp.addSkill("Collaboration");
		temp.addSkill("Hardware");
		profileList.add(temp);
		temp = new LnkdnProfile(
				"Courtney",
				"Dobson",
				"WTT'de Business Anaylist",
				"http://www.roofingcontractor.com/ext/resources/images/authors/Courtney-Dobson-Headshot-square.jpg?1449241605"
				);
		temp.addSkill("Network Architecture");
		temp.addSkill("Ruby");
		temp.addSkill("SQL");
		temp.addSkill("PHP");
		temp.addSkill("HTML");
		profileList.add(temp);
		temp = new LnkdnProfile(
				"Gudh",
				"Guy",
				"İTÜ Bilgisayar Mühendisliği Mezunu",
				"https://media.licdn.com/mpr/mpr/shrinknp_200_200/AAEAAQAAAAAAAAKrAAAAJDVjODZiZDgwLWIwMjEtNGYyYS1iZjQ0LTZhMTVhZDM1NmMyNA.jpg"
				);
		temp.addSkill("Java");
		temp.addSkill("Javascript");
		temp.addSkill("Linux");
		temp.addSkill("C++");
		temp.addSkill("Python");
		profileList.add(temp);
		temp = new LnkdnProfile(
				"Sun",
				"Sand",
				"İTÜ Bilgisayar Mühendisliği Mezunu",
				"https://s-media-cache-ak0.pinimg.com/736x/27/73/8b/27738b3e152b6ba412535ed24efffd3f--pic-maker-picture-poses.jpg"
				);	
		temp.addSkill("Java");
		temp.addSkill("Javascript");
		temp.addSkill("Linux");
		temp.addSkill("C++");
		temp.addSkill("Python");
		temp.addSkill("Search Engine Optimization (SEO)");
		temp.addSkill("Matlab");
		profileList.add(temp);
		temp = new LnkdnProfile(
				"Tom",
				"Rich",
				"İşadamı",
				"https://media.licdn.com/mpr/mpr/shrink_200_200/AAEAAQAAAAAAAANaAAAAJDI1M2NmYmIzLWRkNzItNDU4Yi05MThjLTExMjE3OGMyOGMwNA.jpg"
				);	
		temp.addSkill("Database Management");
		temp.addSkill("Data Mining");
		temp.addSkill("Data Analytics");
		profileList.add(temp);
		temp = new LnkdnProfile(
				"Jonathan",
				"Doe",
				"Kendi işinin yöneticisi",
				"https://pbs.twimg.com/profile_images/867126110248677377/b1QO4rtV_200x200.jpg"
				);	
		temp.addSkill("Team Building");
		profileList.add(temp);
		temp = new LnkdnProfile(
				"Stuart",
				"Parker",
				"IWATA'da Business Analyst",
				"https://thegreenprogram.com/uploads/attachments/ciyonhq9702wb84i5n804w2r6-stuart.0.0.200.200.full.jpg"
				);
		temp.addSkill("Google Analytics");
		temp.addSkill("Documentation");
		temp.addSkill("Diagnostics");
		profileList.add(temp);
		temp = new LnkdnProfile(
				"Orhun",
				"Black",
				"Google'da etnik kadro danışmanı",
				"http://www.ncl.ac.uk/media/wwwnclacuk/newcastleuniversitybusinessschool/images/phd-ade.jpg"
				);	
		temp.addSkill("Customer Support");
		temp.addSkill("Computing");
		temp.addSkill("AutoCAD");
		temp.addSkill("Matlab");
		profileList.add(temp);
	}
	

	public List<LnkdnProfile> getProfileList() {
		return profileList;
	}
	
}
