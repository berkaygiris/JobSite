package initializer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Advert;

public class Adverts {
	private List<Advert> ads;
	public Adverts() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		this.ads = new ArrayList<Advert>();
		
		Advert temp;
		
		temp = new Advert( //String title, String text, Date start, Date end, String active
				"Software Developer",
				"<div><strong>Minimum 3 years</strong> of experience with PHP development.<br> <ul><li>Solid understanding of OOP concepts and software design patterns. </li><li>Good knowledge of MySQL. </li><li>Good knowledge of MVC frameworks especially Laravel. </li><li>Experience with LAMP environment. </li><li>Experience with Git or other comparable version control systems. </li><li>Knowledge of Redis Server , Memcache and restfull API. </li><li>Strong analytical thinking, collobration and problem solving skills. </li><li>Experience with JavaScript especially Nodejs , AngularJS and JQuery is a plus.</li></ul>The responsibilities of this position include designing, development and maintenance of software application and database to satisfy business requirements. The job will also require collaboration with other teams & team members as well as some maintenance work on existing codes.<br> <p> </p></div>",
				sdf.parse("15/07/2017"),
				sdf.parse("05/08/2017"),
				"1"
				);
		temp.addSkill("MVC");
		temp.addSkill("PHP");
		temp.addSkill("MySQL");
		temp.addSkill("Javascript");
		temp.addSkill("AngularJS");
		ads.add(temp);
		
		
		temp = new Advert(
				"Stajyer Mühendis",
				"Farklı alanlarda hizmet veren Mühendis ya da Tasarımcıların mevcut projelerini gözlemleme ve gözlemlenen projelerin bir kısmında yer alınması. <br><ul><li>Üniversitelerin Mühendislik Fakültelerinin Makina, Elektrik, Elektrik&Elektronik, Mekatronik vb. bölümlerinde son sınıf öğrencisi veya yüksek lisans öğrencisi olan,</li><li>İyi derecede yazılı/sözlü İngilizce veya Almanca bilgisine sahip, </li><li>Haftanın en az 3 günü ve en az 3 ay boyunca programa devam edebilecek, </li><li>Sorumluluk sahibi ve gelişime açık, </li><li>Takım çalışmasına yatkın. </li></ul> ",
				sdf.parse("15/07/2017"),
				sdf.parse("05/08/2017"),
				"1"
				);
		
		temp.addSkill("C++");
		ads.add(temp);
		temp = new Advert(
				"Uzun Dönem Stajyer",
				"İşe Alım Danışmanlığı alanında bilgi ve deneyim kazanmak isteyen aşağıdaki niteliklere sahip arkadaşları<strong>“Uzun Dönemli Stajyer”</strong> olarak ekibimize katmak arzusu içerisindeyiz:</span></span></span><ul><li><span><span><span>4 yıllık üniversite mezunu</span></span></span> </li><li><span><span><span>İyi derecede İngilizce bilen</span></span></span> </li><li><span><span><span>İnsan Kaynakları fonksiyonunun özellikle İşe Alım alanına ilgi ve merak duyan, tercihen bu alanda staj ya da çalışma tecrübesine sahip</span></span></span></li><li><span><span><span>Outlook, Word, Excel ve Powerpoint kullanma tecrübesine sahip</span></span></span></li><li><span><span><span>Tercihen FileFinder(Executive Search) programını kullanmış</span></span></span></li><li><span><span><span>Azimli, hızlı adapte olabilen, analitik ve sonuç odaklı</span></span></span></li><li><span><span><span>Güçlü iletişim yeteneğine sahip</span></span></span> </li></ul>",
				sdf.parse("11/07/2017"),
				sdf.parse("29/08/2017"),
				"1"
				);
		
		temp.addSkill("Microsoft Office");
		temp.addSkill("Excel");
		ads.add(temp);
		
		
	}
	public List<Advert> getAds() {
		return ads;
	}

}
