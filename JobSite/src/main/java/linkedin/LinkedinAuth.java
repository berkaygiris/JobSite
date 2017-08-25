package linkedin;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import domain.LnkdnProfile;

public class LinkedinAuth {
	public static LnkdnProfile getData(String strCode, String strState) throws JSONException, ParserConfigurationException, SAXException {
		String data = "";
	    
		//CSRF Security
	 	if(strState.equals("25EDUaG2Si")) {
	 		String Token = "";
	 		try {
		 		String url = "https://www.linkedin.com/oauth/v2/accessToken";
	
		 		HttpClient client = HttpClientBuilder.create().build();
		 		HttpPost post = new HttpPost(url);
		 		post.setHeader("Host", "www.linkedin.com");
		 		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		 		
		 		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		 		urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
		 		urlParameters.add(new BasicNameValuePair("code", strCode));
		 		urlParameters.add(new BasicNameValuePair("redirect_uri", "http://localhost:8181/auth/linkedin"));
		 		urlParameters.add(new BasicNameValuePair("client_id", "8657sei31n7odt"));
		 		urlParameters.add(new BasicNameValuePair("client_secret", "3PJVRe5Wgt6XwDnF"));
		 		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		 		
		 		HttpResponse response = client.execute(post);
		 		//System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
		 		String x = EntityUtils.toString(response.getEntity());
		 		
		 		JSONObject obj = new JSONObject(x);
		 		Token = obj.getString("access_token");
		 		
	 		} catch (IOException | ParseException ex) {
	            // handle exception
	        }
	 		
	 		try {
	            HttpClient httpclient = HttpClientBuilder.create().build();  // the http-client, that will send the request
	            HttpGet httpGet = new HttpGet("https://api.linkedin.com/v1/people/~:(id,email-address,first-name,last-name,formatted-name,picture-url,headline,public-profile-url)");   // the http GET request
	            httpGet.addHeader("Host", "api.linkedin.com");
	            httpGet.addHeader("Connection", "Keep-Alive");
	            httpGet.addHeader("Authorization", "Bearer "+ Token); // add the authorization header to the request
	            HttpResponse response1 = httpclient.execute(httpGet); // the client executes the request and gets a response
	            int responseCode = response1.getStatusLine().getStatusCode();  // check the response code
	            switch (responseCode) {
	                case 200: { 
	                    // everything is fine, handle the response
	                    data = EntityUtils.toString(response1.getEntity());
	                    LnkdnProfile profile = getPerson(data);
	                   
	                    return profile;
	                }
	                case 500: {
	                    // server problems ?
	                    break;
	                }
	                case 403: {
	                    // you have no authorization to access that resource
	                    break;
	                }
	            }
	        } catch (IOException | ParseException ex) {
	            // handle exception
	        }
	 		
	 		
	 		
	 		
	 		
			return null;
	 	}
	 	else {
	 		
	 		System.out.println(strState); //DEBUG
	 		return null;
	 	}
		}
	
	private static LnkdnProfile getPerson(String data) throws ParserConfigurationException, SAXException, IOException {
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         DocumentBuilder builder = factory.newDocumentBuilder();
         org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(data)));
         String id = document.getElementsByTagName("id").item(0).getTextContent();
         String name = document.getElementsByTagName("first-name").item(0).getTextContent();
         String surname = document.getElementsByTagName("last-name").item(0).getTextContent();
         String headline = document.getElementsByTagName("headline").item(0).getTextContent();
         String fullname = document.getElementsByTagName("formatted-name").item(0).getTextContent();
         String picture = document.getElementsByTagName("picture-url").item(0).getTextContent();
         String link = document.getElementsByTagName("public-profile-url").item(0).getTextContent();
         String email = document.getElementsByTagName("email-address").item(0).getTextContent();
         
         										//linkedinid, String name, String surname, String fullname, String headline, String picture, String link
         LnkdnProfile profile = new LnkdnProfile(id,name,surname,fullname,headline,picture,link,email);
		return profile;
		
	}
}
