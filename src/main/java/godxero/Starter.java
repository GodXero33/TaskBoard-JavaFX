package godxero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
	public static void main (String[] args) {
		Application.launch(args);
	}

	@Override
	public void start (Stage stage) throws IOException {
		stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/login_view.fxml"))));
		stage.setTitle("TaskBoard - Login");
		stage.setResizable(false);
		stage.show();
	}
}
