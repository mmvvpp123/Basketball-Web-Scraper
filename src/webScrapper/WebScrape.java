package webScrapper;

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

}
