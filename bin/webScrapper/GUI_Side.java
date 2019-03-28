package webScrapper;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class GUI_Side extends Application{
	
	WebScrape scraper = new WebScrape();
	ArrayList <String> hello;
	Stage window;
	Scene scene, scene2;
	Button submit;
	
	TextField month, day, year;

	public static void main(String[] args) {
		
		launch(args);
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		scraper.leadingScorer("https://www.basketball-reference.com/boxscores/");
		window = primaryStage;
		
		DatePicker date = new DatePicker();

		
		submit = new Button("Submit");
		
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(date, submit);
		scene = new Scene(layout1, 250,500);
		
		
		
		submit.setOnAction(e -> generate(date.getValue()));
		
		
		window.setScene(scene);
		window.setTitle("Get Scores");
		window.show();
		
	}
	
	public void generate(LocalDate date) {
				
		int year = date.getYear();
		int day = date.getDayOfMonth();
		int month = date.getMonthValue();
		
		VBox layout2 = new VBox(20);
		hello = scraper.getScores("https://www.basketball-reference.com/boxscores/?month="+month+"&day="+day+"&year="+year);
		
		int counter = 1;

		String lab = "";
		for(int i = 0; i < hello.size(); i++) {
			
			lab += hello.get(i);
			lab += "\n";
			if(counter %  2 == 0) {
				lab += "\n";
			}
			counter++;
		}
		
		Label scores = new Label(lab);
		
		layout2.getChildren().addAll(scores);
		scene2 = new Scene(layout2, 250,500);
		window.setScene(scene2);
		
	}
	
	

	

}
