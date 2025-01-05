package godxero.control;

import godxero.control.user.UserController;
import godxero.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupFormController {
	public TextField firstNameTextField;
	public TextField lastNameTextField;
	public TextField userNameTextField;
	public TextField emailTextField;
	public PasswordField passwordField;

	public void signUpButtonOnAction(ActionEvent actionEvent) {
		final String firstname = this.firstNameTextField.getText().toLowerCase();
		final String lastName = this.lastNameTextField.getText().toLowerCase();
		final String userName = this.userNameTextField.getText().toLowerCase();
		final String email = this.emailTextField.getText().toLowerCase();
		final String password = this.passwordField.getText();

		if (firstname.isEmpty()) {
			new Alert(Alert.AlertType.WARNING, "First name is empty.").show();
			this.firstNameTextField.requestFocus();
			return;
		}

		if (lastName.isEmpty()) {
			new Alert(Alert.AlertType.WARNING, "Last name is empty.").show();
			this.lastNameTextField.requestFocus();
			return;
		}

		if (userName.isEmpty()) {
			new Alert(Alert.AlertType.WARNING, "Username is empty.").show();
			this.userNameTextField.requestFocus();
			return;
		}

		if (email.isEmpty()) {
			new Alert(Alert.AlertType.WARNING, "Email is empty.").show();
			this.emailTextField.requestFocus();
			return;
		}

		if (password.isEmpty()) {
			new Alert(Alert.AlertType.WARNING, "Password is empty.").show();
			this.passwordField.requestFocus();
			return;
		}

		if (UserController.getInstance().isUsernameAvailable(userName)) {
			new Alert(Alert.AlertType.WARNING, "User name has already taken.").show();
			this.userNameTextField.requestFocus();
			return;
		}

		final User user = new User(null, firstname, lastName, userName, password, email);

		if (UserController.getInstance().addUser(user) == 1) {
			new Alert(Alert.AlertType.INFORMATION, "Account created. Login again.").show();
			((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
		} else {
			new Alert(Alert.AlertType.INFORMATION, "Failed to create account. Try again.").show();
		}
	}
}
