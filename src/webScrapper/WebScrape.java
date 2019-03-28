package webScrapper;

import java.time.LocalDate;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebScrape {

	public ArrayList<String> getScores(String url) {

		ArrayList <String> teamsAndScores = new ArrayList<String> ();
		
		final String output = url;

		try {
			final Document document = Jsoup.connect(url).get();
			
			String teams = "td:nth-of-type(1)";
			String scores = "td:nth-of-type(2)";

			for (Element row : document.select("table.teams tr")) {
				teamsAndScores.add((row.select(teams).text() + ": ") + (row.select(scores).text()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return teamsAndScores;

	}
	
	public ArrayList<String> leadingScorer(String url) {
		
		ArrayList<String> leadingStats = new ArrayList<String> ();
		
		String ticker = "";
		
		try {
			final Document document = Jsoup.connect(url).get();
			
			int counter = 0;
			
			String player = "td:nth-of-type(2)";
			String points = "td:nth-of-type(3)";
			
			for(Element row : document.select("table.stats td")) {
				if(!(row.select(points).text().equals("")) || !(row.select(player).text().equals(""))) {
					ticker += " " + row.select(points).text() + row.select(player).text();
					counter++;
					}
				if(counter % 2 == 0) {
					if(!ticker.equals(""))
					leadingStats.add(ticker);
					ticker = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leadingStats;
	}
	

}
