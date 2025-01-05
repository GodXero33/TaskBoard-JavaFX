package godxero.control;

import godxero.model.task.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class TaskEditFormController {
	public TextField titleTextField;
	public TextField scheduleTimeTextField;
	public TextArea descriptionTextArea;
	public ComboBox<String> categoryComboBox;
	public ComboBox<String> priorityComboBox;
	public ComboBox<String> frequencyComboBox;

	@Getter
	private ActiveTask task;

	public void setTask (Task task) {
		this.task = (ActiveTask) task;

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

		this.categoryComboBox.setValue(task.getCategory().toString());
		this.frequencyComboBox.setValue(task.getFrequency().toString());
		this.priorityComboBox.setValue(task.getPriority().toString());
		this.titleTextField.setText(task.getTitle());
		this.descriptionTextArea.setText(task.getDescription());
		this.scheduleTimeTextField.setText(task.getScheduleTime());
	}

	public void editButtonOnAction (ActionEvent actionEvent) {
		if (!this.scheduleTimeTextField.getText().matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
			new Alert(Alert.AlertType.WARNING, "Invalid Date and Time. (YYYY-MM-DD HH:MM:SS)").show();
			return;
		}

		this.task.setTitle(this.titleTextField.getText());
		this.task.setDescription(this.descriptionTextArea.getText());
		this.task.setScheduleTime(this.scheduleTimeTextField.getText());
		this.task.setUpdateTime(Task.getCurrentDateTime());
		this.task.setCategory(TaskCategory.fromString(this.categoryComboBox.getValue()));
		this.task.setPriority(TaskPriority.fromString(this.priorityComboBox.getValue()));
		this.task.setFrequency(TaskFrequency.fromString(this.frequencyComboBox.getValue()));

		((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
	}
}
