
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

import  Components.*;

public class Flow1 {
	
	public static void main(String[] args) throws Exception {
		
		String contentId="1260055945";
		HashMap<String,String> map=new HashMap<String,String>();  
		map = Espncricinfo.getLiveMatch();
		
		if(!map.isEmpty()) {
			System.out.println("*****Match Details*****");
			System.out.println("matchSlug: " + map.get("matchSlug"));
			System.out.println("matchId: " + map.get("matchId"));
			System.out.println("seriesId: " + map.get("seriesId"));
			
			int eventId = Consumption.createMatch(contentId, map.get("matchId"), map.get("seriesId"), map.get("matchSlug"));
			Consumption.addEditorialCard(eventId, "test - 2");
			
			System.out.println("Deeplink: hotstar://"+contentId+"/watch");
			
		}
		else
		{
			System.out.println("No match is LIVE currently. Please try again later.");
		}
		
		
	}
}
