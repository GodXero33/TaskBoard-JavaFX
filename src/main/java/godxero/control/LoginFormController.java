package godxero.control;

import godxero.control.user.UserController;
import godxero.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoginFormController {
	public TextField loginUserNameTextField;
	public PasswordField loginPasswordField;

	private User importedUserData;

	private void validateUserDetails (String password, Stage currentStage) {
		if (this.importedUserData == null) {
			new Alert(Alert.AlertType.WARNING, "User not found.").show();
			return;
		}

		if (!this.importedUserData.getPassword().equals(password)) {
			new Alert(Alert.AlertType.WARNING, "Wrong password.").show();
			return;
		}

		try {
			final Stage stage = new Stage();
			final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../../view/main_view.fxml"));

			stage.setScene(new Scene(loader.load()));
			stage.setTitle("TaskBoard - " + this.importedUserData.getUserName());
			stage.setResizable(false);
			stage.show();

			final MainFormController controller = loader.getController();

			if (controller != null) controller.setUser(this.importedUserData);

			currentStage.close();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * First this checks both username and password fields are not empty.
	 * Then collect user data from database and store in 'importedUserData' field only if 'importedUserData' field is null.
	 *
	 * @param actionEvent :(
	 */
	public void loginButtonOnAction (ActionEvent actionEvent) {
		final String userName = this.loginUserNameTextField.getText();
		final String password = this.loginPasswordField.getText();

		if (userName.isEmpty()) {
			new Alert(Alert.AlertType.WARNING, "Username can't be empty.").show();
			return;
		}

		if (password.isEmpty()) {
			new Alert(Alert.AlertType.WARNING, "Password can't be empty.").show();
			return;
		}

		// If imported user data is null then import user data.
		if (this.importedUserData == null) this.importedUserData = UserController.getInstance().getUser(userName.toLowerCase());

		/*
		  Validate user data.
		  There are two casting in 'currentStage' parameter.
		  First cast, cast Object type to javafx.scene.control.Button.
		  Second cast, cast javafx.stage.Window to javafx.stage.Stage.
		  Both casting together returns current stage.
		 */
		this.validateUserDetails(password, (Stage) ((Button) actionEvent.getSource()).getScene().getWindow());
	}

	// If user try to edit text in username text field, erase 'importedUserData' field.
	public void loginUserNameTextFieldOnKeyPressed (KeyEvent keyEvent) {
		this.importedUserData = null;
	}

	public void signUpButtonOnAction (ActionEvent actionEvent) {
		try {
			final Stage stage = new Stage();

			stage.setScene(new Scene(new FXMLLoader(this.getClass().getResource("../../view/signup_view.fxml")).load()));
			stage.setTitle("TaskBoard - Sign Up");
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());
			stage.showAndWait();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
}
