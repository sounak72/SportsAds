package Components;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

import files.payload;  

import java.time.LocalDate;

public class Consumption {
	
	private static String findPrevDay(LocalDate localdate)
	  {
	    return localdate.minusDays(1).toString();
	  }
	
	
	public static int createMatch(String contentId, String matchId, String SeriedId, String matchTitle) throws Exception {
		
		RestAssured.baseURI= "https://origin-consumption-manager-pp.mum.hotstar-labs.com";
		
		
		try {
//			System.out.println(payload.createMMatch(contentId, matchId,SeriedId, matchTitle));
			System.out.println("*****Creationg an entry in the consumption portal for contentId:" + contentId + " matchtitle:" + matchTitle +" matchId:" + matchId + " SeriesId:" + SeriedId + "*****");
			String response=given().relaxedHTTPSValidation().header("Content-Type","application/json")
			.header("X-HS-IAuth", "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MjQ1ODY1NjAsImp0aSI6ImVlNGQxMjVhLTQxMjAtNDIzOS1iOGNmLTg3NDllYjU4NzQ2ZSIsImlhdCI6MTY2NTM4NjU1OCwibmJmIjoxNjY1Mzg2NTU4LCJzdWJqZWN0X2lkIjoic2h1by5saW5AaG90c3Rhci5jb20ifQ._eveJXqIu5DxN1L3FE2DJi9-NMSUvZ2ctDxSbxksFkAJ8mEUJ_vMBmWH6araBhrp05OrlIlztHrkj_SN1ee0KA")
			.header("X-HS-IAuth-UserID", "shuo.lin@hotstar.com")
			.body(payload.createMMatch(contentId, matchId,SeriedId, matchTitle)).when().post("/v1/events/matches")
			.then().assertThat().statusCode(200).extract().response().asString();
			JsonPath js=new JsonPath(response); //for parsing Json	
			
			int eventId = js.getInt("event_id");
			System.out.println("EventId" + eventId);
			
			return eventId;
			
		}
		catch(AssertionError e)
		{
			System.out.println("Exception " + e.toString() + " encountered while chreating match in consumption portal ");
			String eventId = Consumption.retrieveEventId(contentId);
			Consumption.deleteMatch(Integer.parseInt(eventId));
			
			RestAssured.baseURI= "https://origin-consumption-manager-pp.mum.hotstar-labs.com";
			
			System.out.println("Creationg an entry in the consumption portal for contentId:" + contentId + " matchtitle:" + matchTitle +" matchId:" + matchId + " SeriesId:" + SeriedId);
			String response=given().relaxedHTTPSValidation().header("Content-Type","application/json")
					.header("X-HS-IAuth", "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MjQ1ODY1NjAsImp0aSI6ImVlNGQxMjVhLTQxMjAtNDIzOS1iOGNmLTg3NDllYjU4NzQ2ZSIsImlhdCI6MTY2NTM4NjU1OCwibmJmIjoxNjY1Mzg2NTU4LCJzdWJqZWN0X2lkIjoic2h1by5saW5AaG90c3Rhci5jb20ifQ._eveJXqIu5DxN1L3FE2DJi9-NMSUvZ2ctDxSbxksFkAJ8mEUJ_vMBmWH6araBhrp05OrlIlztHrkj_SN1ee0KA")
					.header("X-HS-IAuth-UserID", "shuo.lin@hotstar.com")
					.body(payload.createMMatch(contentId, matchId,SeriedId, matchTitle)).when().post("/v1/events/matches")
					.then().assertThat().statusCode(200).extract().response().asString();
			
			JsonPath js=new JsonPath(response);	
			
			int eventId1 = js.getInt("event_id");
			System.out.println("EventId: "+ eventId1);
			
			return eventId1;
		}
		
		
	}
	
	public static String retrieveEventId(String contentId) throws Exception {
		
		RestAssured.baseURI= "https://origin-consumption-portal-pp.mum.hotstar-labs.com";
		
		Map<String,Object> headerMap = new HashMap<String,Object>();
		headerMap.put("X-HS-IAuth", "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MjQ1ODY1NjAsImp0aSI6ImVlNGQxMjVhLTQxMjAtNDIzOS1iOGNmLTg3NDllYjU4NzQ2ZSIsImlhdCI6MTY2NTM4NjU1OCwibmJmIjoxNjY1Mzg2NTU4LCJzdWJqZWN0X2lkIjoic2h1by5saW5AaG90c3Rhci5jb20ifQ._eveJXqIu5DxN1L3FE2DJi9-NMSUvZ2ctDxSbxksFkAJ8mEUJ_vMBmWH6araBhrp05OrlIlztHrkj_SN1ee0KA");
		headerMap.put("X-HS-IAuth-UserID", "shuo.lin@hotstar.com");
		
		System.out.println("*****Fetching eventId foor content:" +  contentId + "*****");
		
		String response=given().relaxedHTTPSValidation().header("Content-Type","application/json")
				.queryParam("status", "Ongoing")
				.queryParam("limit", 1000)
				.headers(headerMap)
//				.header("X-HS-IAuth", "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MjQ1ODY1NjAsImp0aSI6ImVlNGQxMjVhLTQxMjAtNDIzOS1iOGNmLTg3NDllYjU4NzQ2ZSIsImlhdCI6MTY2NTM4NjU1OCwibmJmIjoxNjY1Mzg2NTU4LCJzdWJqZWN0X2lkIjoic2h1by5saW5AaG90c3Rhci5jb20ifQ._eveJXqIu5DxN1L3FE2DJi9-NMSUvZ2ctDxSbxksFkAJ8mEUJ_vMBmWH6araBhrp05OrlIlztHrkj_SN1ee0KA")
//				.header("X-HS-IAuth-UserID", "shuo.lin@hotstar.com")
				.when().get("/sports/v1/events/matches")
				.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response.toString());
		JsonPath js=new JsonPath(response); 
		
		
		int count=	js.getList("$").size();

		
		Map mMap = new HashMap();
		List<Map> list = new ArrayList();
		
		list = js.getList("$");
		
		 for(Map obj:list)  {

			  List<Map> myList = new ArrayList<Map>();
			  myList = (List<Map>) obj.get("content_ids");
			  int flag=0;
			  for(Map map:myList)    {
				  String contentId1 = map.get("content_id").toString();
				    if (contentId1.equalsIgnoreCase(contentId))
				    {
				    	String eventId =obj.get("event_id").toString();
				    	System.out.println("Event Id: " + eventId);
				    	return eventId;
				    }
				  
				 }  

			  
			}  
		  
		System.out.println("Failed to fetch eventId");
		throw new Exception("Failed to fetch eventId");
//		return "";
	}
	
	public static void deleteMatch(int eventId)
	{
		RestAssured.baseURI= "https://origin-consumption-portal-pp.mum.hotstar-labs.com";
		
		System.out.println("*****Deleting content with eventId:"+ eventId + "*****");
		String response=given().relaxedHTTPSValidation().header("Content-Type","application/json")
				.header("X-HS-IAuth", "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MjQ1ODY1NjAsImp0aSI6ImVlNGQxMjVhLTQxMjAtNDIzOS1iOGNmLTg3NDllYjU4NzQ2ZSIsImlhdCI6MTY2NTM4NjU1OCwibmJmIjoxNjY1Mzg2NTU4LCJzdWJqZWN0X2lkIjoic2h1by5saW5AaG90c3Rhci5jb20ifQ._eveJXqIu5DxN1L3FE2DJi9-NMSUvZ2ctDxSbxksFkAJ8mEUJ_vMBmWH6araBhrp05OrlIlztHrkj_SN1ee0KA")
				.header("X-HS-IAuth-UserID", "shuo.lin@hotstar.com")
				.when()
				.delete("/sports/v1/events/matches/"+eventId)
				.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Event id:" +eventId + " deleted successfully"  );
		
	}
	
	public static void addEditorialCard(int eventId, String editorialCard )
	{
		RestAssured.baseURI= "https://origin-consumption-portal-pp.mum.hotstar-labs.com";
		
		System.out.println("*****Adding editorial card content with eventId:"+ eventId + "*****");
		String response=given().relaxedHTTPSValidation().header("Content-Type","application/json")
				.header("X-HS-IAuth", "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MjQ1ODY1NjAsImp0aSI6ImVlNGQxMjVhLTQxMjAtNDIzOS1iOGNmLTg3NDllYjU4NzQ2ZSIsImlhdCI6MTY2NTM4NjU1OCwibmJmIjoxNjY1Mzg2NTU4LCJzdWJqZWN0X2lkIjoic2h1by5saW5AaG90c3Rhci5jb20ifQ._eveJXqIu5DxN1L3FE2DJi9-NMSUvZ2ctDxSbxksFkAJ8mEUJ_vMBmWH6araBhrp05OrlIlztHrkj_SN1ee0KA")
				.header("X-HS-IAuth-UserID", "shuo.lin@hotstar.com")
				.body(payload.createEditorialCard(editorialCard))
				.when()
				.post("sports/v1/events/matches/"+eventId+"/editorial_card")
				.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Editorial card:" +editorialCard + " added successfully"  );
		
	}
	
	public static void uploadFile(String matchSlug) {
		
		RestAssured.baseURI= "https://origin-consumption-portal-pp.mum.hotstar-labs.com";
		
		given().log().all()
		.queryParam("random_name", true)
		.header("X-HS-IAuth", "eyJhbGciOiJFUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE5MjQ1ODY1NjAsImp0aSI6ImVlNGQxMjVhLTQxMjAtNDIzOS1iOGNmLTg3NDllYjU4NzQ2ZSIsImlhdCI6MTY2NTM4NjU1OCwibmJmIjoxNjY1Mzg2NTU4LCJzdWJqZWN0X2lkIjoic2h1by5saW5AaG90c3Rhci5jb20ifQ._eveJXqIu5DxN1L3FE2DJi9-NMSUvZ2ctDxSbxksFkAJ8mEUJ_vMBmWH6araBhrp05OrlIlztHrkj_SN1ee0KA")
		.header("X-HS-IAuth-UserID", "shuo.lin@hotstar.com")

//		.header("Content-Type","multipart/form-data")

		.multiPart("file", new File("/Users/sounaksaha/Downloads/logo.jpeg"), "multipart/form-data").when().

		post("/sports/v1/sports-service/resources/image/branded/"+matchSlug+"_0").then().log().all();
	}

}
