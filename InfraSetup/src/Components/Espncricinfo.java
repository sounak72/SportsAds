package Components;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.util.*;  

public class Espncricinfo {
	
	public static HashMap getLiveMatch() {
		
		HashMap<String,String> map=new HashMap<String,String>();  
		
		RestAssured.baseURI= "https://hs-consumer-api.espncricinfo.com";
		
		
		System.out.println("*****Checking the matches that are LIVE currently*****");
		String response=given().relaxedHTTPSValidation().header("lang", "en").queryParam("latest", "true").when().get("/v1/pages/matches/current")
				.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		int count=	js.getInt("matches.size()");
		for(int i=0;i<count;i++)
		  {
			 String matchSlug=js.get("matches["+i+"].slug");
			 String matchStage=js.get("matches["+i+"].stage").toString().trim();
			 String matchState=js.get("matches["+i+"].state").toString().trim();
			 String matchId = js.get("matches["+i+"].scribeId").toString().trim();
			 String seriesId = js.get("matches["+i+"].series.objectId").toString().trim();
			 String status = js.get("matches["+i+"].status").toString().trim();
			 boolean isLive0 = js.get("matches["+i+"].teams[0].isLive");
			 boolean isLive1 = js.get("matches["+i+"].teams[1].isLive");
			 
			 if(matchStage.equalsIgnoreCase("RUNNING") && matchState.equals("LIVE") && (isLive0==true || isLive1==true) && status.equalsIgnoreCase("LIVE") ) {	
				 map.put("matchSlug", matchSlug);
				 map.put("matchId", matchId);
				 map.put("seriesId", seriesId);
				 
				 System.out.println("Match Live: " + matchSlug);
//				 break;
			 }
		  }
		
		return map;
	}
	
}
