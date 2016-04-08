package ru.ancienttree.main;

import java.util.Locale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AncientTreeMain extends Application {

	Scene scene;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("AncientTree.fxml"));
			scene = new Scene(parent, 800, 650);

			primaryStage.setOpacity(1);

			primaryStage.getIcons().add(new Image(AncientTreeMain.class.getResourceAsStream("icon256.png")));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ancient Tree v.1.0.1 | Created by bu1b0b © 2016");
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		initLocale();
		launch(args);
	}

	private static void initLocale() {
		Locale.setDefault(Locale.forLanguageTag("ru-RU"));
	}

} // class end
