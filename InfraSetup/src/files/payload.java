package files;

import java.time.LocalDate;

public class payload {
	
	
	private static String findPrevDay(LocalDate localdate)
	  {
	    return localdate.minusDays(1).toString();
	  }
	
	private static LocalDate findNextDay(LocalDate localdate, int days)
	  {
	    return localdate.plusDays(days);
	  }


	public static String createMMatch(String contentId, String matchId, String SeriedId, String matchTitle)

	{
		
		LocalDate todayDate = LocalDate.now();

		return "{\r\n"
				+ "    \"match_id\": \""+matchId+"\",\r\n"
				
				+ "    \"content_ids\": [\r\n"
				+ "        {\r\n"
				+ "            \"content_id\": \""+contentId+"\",\r\n"
				+ "            \"type\": \"primary\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"series_id\": "+SeriedId+",\r\n"
				+ "    \"title\": \""+matchTitle+"\",\r\n"
				+ "    \"type\": \"sports\",\r\n"
				+ "    \"status\": \"Ongoing\",\r\n"
				+ "    \"sub_type\": \"cricket\",\r\n"
				+ "    \"slug\": \""+matchTitle+"\",\r\n"
				+ "    \"child_config\": \"[{\\\"country\\\":\\\"Default\\\",\\\"concurrency\\\":true,\\\"sports_tab\\\":true,\\\"scorecard\\\":true,\\\"key_moments\\\":false,\\\"branded\\\":false,\\\"branded_icon_url\\\":\\\"\\\",\\\"live_feed\\\":false,\\\"portrait_tab_order\\\":[\\\"live_feed\\\",\\\"key_moments\\\",\\\"scorecard\\\",\\\"branded\\\"],\\\"landscape_tab_order\\\":[\\\"live_feed\\\",\\\"key_moments\\\",\\\"scorecard\\\",\\\"branded\\\"]}]\",\r\n"
				+ "    \"config\": \"[{\\\"country\\\":\\\"Default\\\",\\\"concurrency\\\":true,\\\"portrait\\\":true,\\\"video_off\\\":true,\\\"sports_tab\\\":true,\\\"scorecard\\\":true,\\\"key_moments\\\":true,\\\"branded\\\":true,\\\"branded_icon_url\\\":\\\"\\\",\\\"live_feed\\\":true,\\\"commentary\\\":true,\\\"editorial_card\\\":true,\\\"portrait_tab_order\\\":[\\\"live_feed\\\",\\\"key_moments\\\",\\\"scorecard\\\",\\\"branded\\\"],\\\"landscape_tab_order\\\":[\\\"key_moments\\\"]}]\",\r\n"
				+ "    \"start_time\": \""+findPrevDay(todayDate)+"T11:30:00+08:00"+"\",\r\n"
				+ "    \"end_time\": \""+findNextDay(todayDate, 5)+"T23:59:00+08:00"+"\"\r\n"
				+ "}";

	}
	
	public static String createEditorialCard(String brandedMoment)

	{

		return "{\r\n"
				+ "    \"title\": \"AdsMaker\",\r\n"
				+ "    \"sub_title\": \"AdsMaker\",\r\n"
				+ "    \"description\": \"AdsMaker\",\r\n"
				+ "    \"image_link\": \"AdsMaker\",\r\n"
				+ "    \"avatar_link\": \"AdsMaker\",\r\n"
				+ "    \"deep_link\": \"AdsMaker\",\r\n"
				+ "    \"type\": \"AdsMaker\",\r\n"
				+ "    \"trigger_type\": \"Immediate\",\r\n"
				+ "    \"cta_text\": \"\",\r\n"
				+ "    \"badge_text\": \"\",\r\n"
				+ "    \"trackers\": [\r\n"
				+ "        {\r\n"
				+ "            \"tracker_type\": \"click\",\r\n"
				+ "            \"tracker_urls\": []\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"tracker_type\": \"impressions\",\r\n"
				+ "            \"tracker_urls\": []\r\n"
				+ "        },\r\n"
				+ "        {\r\n"
				+ "            \"tracker_type\": \"interaction\",\r\n"
				+ "            \"tracker_urls\": []\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"identifier\": \""+brandedMoment+"\",\r\n"
				+ "    \"label\": \"test bm\"\r\n"
				+ "}";

	}

}
