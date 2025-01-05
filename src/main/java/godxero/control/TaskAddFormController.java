package godxero.control;

import godxero.control.task.ActiveTaskController;
import godxero.model.User;
import godxero.model.task.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class TaskAddFormController {
	public TextField titleTextField;
	public TextArea descriptionTextArea;
	public TextField scheduleTimeTextField;
	public ComboBox<String> categoryComboBox;
	public ComboBox<String> priorityComboBox;
	public ComboBox<String> frequencyComboBox;

	private User user;

	public void setUser (User user) {
		this.user = user;
		final List<String> catgoriesList = new ArrayList<>();
		final List<String> frequenciesList = new ArrayList<>();
		final List<String> priorityiesList = new ArrayList<>();

		catgoriesList.add("WORK");
		catgoriesList.add("PERSONAL");
		catgoriesList.add("HEALTH");
		catgoriesList.add("EDUCATION");
		catgoriesList.add("SHOPPING");
		catgoriesList.add("FINANCE");
		catgoriesList.add("HOUSEHOLD");
		catgoriesList.add("SOCIAL");
		catgoriesList.add("TRAVEL");
		catgoriesList.add("MISCELLANEOUS");

		frequenciesList.add("DAILY");
		frequenciesList.add("WEEKLY");
		frequenciesList.add("MONTHLY");
		frequenciesList.add("ONCE");

		priorityiesList.add("LOW");
		priorityiesList.add("MEDIUM");
		priorityiesList.add("HIGH");

		this.categoryComboBox.setItems(FXCollections.observableArrayList(catgoriesList));
		this.frequencyComboBox.setItems(FXCollections.observableArrayList(frequenciesList));
		this.priorityComboBox.setItems(FXCollections.observableArrayList(priorityiesList));

		this.categoryComboBox.setValue("WORK");
		this.frequencyComboBox.setValue("DAILY");
		this.priorityComboBox.setValue("LOW");
	}

	public void addButtonOnAction (ActionEvent actionEvent) {
		if (!this.scheduleTimeTextField.getText().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
			new Alert(Alert.AlertType.WARNING, "Invalid Date and Time. (YYYY-MM-DD HH:MM:SS)").show();
			return;
		}

		final Task task = new ActiveTask(
			null,
			this.user.getId(),
			this.descriptionTextArea.getText(),
			this.titleTextField.getText(),
			TaskCategory.fromString(this.categoryComboBox.getValue()),
			Task.getCurrentDateTime(),
			null,
			this.scheduleTimeTextField.getText(),
			TaskPriority.fromString(this.priorityComboBox.getValue()),
			TaskFrequency.fromString(this.frequencyComboBox.getValue())
		);

		if (ActiveTaskController.getInstance().addTask(task) == 1) {
			((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
		} else {
			new Alert(Alert.AlertType.WARNING, "Task not added.").show();
		}
	}
}
