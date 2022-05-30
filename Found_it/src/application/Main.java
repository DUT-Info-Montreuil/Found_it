package application;
 
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
 
public class Main extends Application {
 
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("vue/vue1.fxml"));
			Scene scene = new Scene(root,960,544);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(true);
			primaryStage.show();
			root.requestFocus();
		} catch(Exception e) {
		e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
