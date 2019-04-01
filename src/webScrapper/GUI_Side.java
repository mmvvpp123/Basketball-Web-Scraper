package webScrapper;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI_Side extends Application {

	WebScrape scraper = new WebScrape();
	ArrayList<String> hello;
	ArrayList<String> playerStats;
	Stage window;
	Scene scene, scene2;
	Button submit;

	TextField month, day, year;

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setResizable(false);
		submit = new Button("Submit");
		
		Label pickDateText = new Label("Pick a date: ");
		DatePicker date = new DatePicker();
		VBox layout1 = new VBox(20, pickDateText, date, submit);

		layout1.setPadding(new Insets(10));

		scene = new Scene(layout1, 250, 500);

		submit.setOnAction(e -> generate(date.getValue()));

		window.setScene(scene);
		window.setTitle("Get Scores");
		window.show();

	}

	public void generate(LocalDate date) {

		int year = date.getYear();
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();

		String url = "https://www.basketball-reference.com/boxscores/?month=" + month + "&day=" + day + "&year=" + year;

		VBox layout2 = new VBox(20);
		layout2.setPadding(new Insets(5));

		hello = scraper.getScores(url);
		playerStats = scraper.leadingScorer(url);

		String labelText = "";
		String playerBoxScore = "";
		int playerCounter = 0;
		for (int i = 0; i < hello.size(); i++) {
			labelText += hello.get(i);
			if (playerCounter % 2 == 0) {
				playerBoxScore += "\tPTS: ";
			} else
				playerBoxScore += "\tTRB: ";
			playerBoxScore += playerStats.get(i) + "\n";
			labelText += "\n";
			playerCounter++;

			if (playerCounter % 2 == 0) {
				labelText += playerBoxScore;
				labelText += "\n";
				playerBoxScore = "";
			}
		}

		Label scores = new Label(labelText);
		
		if(labelText.length() < 1) {
			scores = new Label("No Games On This Day");
		}
		
		Label dateOfScores = new Label(month + "/" + day +"/" + year);
		dateOfScores.setFont(new Font("Arial", 20));

		Button back = new Button("Go back");
		back.setOnAction(e -> window.setScene(scene));

		layout2.getChildren().addAll(dateOfScores, scores, back);
		ScrollPane pane = new ScrollPane(layout2);
		scene2 = new Scene(pane, 250, 500);
		window.setScene(scene2);
	}
}