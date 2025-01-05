package godxero.control;

import godxero.control.task.ActiveTaskController;
import godxero.model.User;
import godxero.model.task.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainFormController {
	public Label userNameLabel;
	public Label userFullNameLabel;
	public ScrollPane scrollPane;
	public AnchorPane scrollContent;
	public Button taskEditButton;
	public Button taskDeleteButton;
	public Button taskDoneButton;

	private User user;
	private List<Task> activeTasks;
	private List<Object[]> selectedTasksList;

	private void init () {
		this.activeTasks = ActiveTaskController.getInstance().getAllTasks(this.user.getId());
		this.selectedTasksList = new ArrayList<>();
		final int len = this.activeTasks.size();

		for (int a = 0; a < len; a++) {
			Pane listItem = createListItem(this.activeTasks.get(a));
			listItem.setLayoutY(a * 85);
			scrollContent.getChildren().add(listItem);
		}

		scrollContent.setPrefHeight(85 * 10);
	}

	public void setUser (User user) {
		if (user == null) return;

		this.user = user;

		this.userNameLabel.setText(user.getUserName());
		this.userFullNameLabel.setText(user.getFirstName() + " " + user.getLastName());
		this.init();
	}

	private void checkBoxOnAction (Task task, boolean isSelected, Pane pane, Label label) {
		if (isSelected) {
			if (!this.selectedTasksList.contains(task)) this.selectedTasksList.add(new Object[] { task, pane, label });
		} else {
			this.selectedTasksList.remove(task);
		}

		if (this.selectedTasksList.isEmpty()) {
			this.taskDeleteButton.setDisable(true);
			this.taskEditButton.setDisable(true);
			this.taskDoneButton.setDisable(true);
		} else if (this.selectedTasksList.size() == 1) {
			this.taskDeleteButton.setDisable(false);
			this.taskEditButton.setDisable(false);
			this.taskDoneButton.setDisable(false);
		} else {
			this.taskDeleteButton.setDisable(false);
			this.taskEditButton.setDisable(true);
			this.taskDoneButton.setDisable(false);
		}
	}

	private Pane createListItem (Task task) {
		final Pane pane = new Pane();
		final Label label = new Label(task.getTitle());
		final CheckBox checkBox = new CheckBox();

		pane.setPrefHeight(75.0);
		pane.setPrefWidth(400.0);
		pane.getStyleClass().add("list-label");

		label.setLayoutX(13.0);
		label.setLayoutY(14.0);
		label.setPrefHeight(49.0);
		label.setPrefWidth(331.0);
		label.getStyleClass().add("list-label");

		checkBox.setLayoutX(357.0);
		checkBox.setLayoutY(24.0);
		checkBox.setPrefHeight(22.0);
		checkBox.setPrefWidth(29.0);
		checkBox.setMnemonicParsing(false);

		pane.getChildren().addAll(label, checkBox);

		checkBox.setOnAction(event -> this.checkBoxOnAction(task, checkBox.isSelected(), pane, label));

		return pane;
	}

	public void taskEditButtonOnAction (ActionEvent actionEvent) {
		try {
			final Stage stage = new Stage();
			final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../../view/task_edit_view.fxml"));

			stage.setScene(new Scene(loader.load()));
			stage.setTitle("TaskBoard - Edit task");
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());

			final TaskEditFormController controller = loader.getController();

			controller.setTask((Task) this.selectedTasksList.getFirst()[0]);

			stage.showAndWait();

			final Object[] firstTaskRecord = this.selectedTasksList.getFirst();

			ActiveTaskController.getInstance().updateTask((Task) firstTaskRecord[0]);
			((Label) firstTaskRecord[2]).setText(((Task) firstTaskRecord[0]).getTitle());
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public void taskDeleteButtonOnAction (ActionEvent actionEvent) {
		for (final Object[] taskRecord : this.selectedTasksList) if (ActiveTaskController.getInstance().deleteTask(((Task) taskRecord[0]).getTaskID()) == 1) this.scrollContent.getChildren().remove((Pane) taskRecord[1]);

		this.selectedTasksList.clear();
		this.taskDeleteButton.setDisable(true);
		this.taskEditButton.setDisable(true);
		this.taskDoneButton.setDisable(true);
	}

	public void taskDoneButtonOnAction (ActionEvent actionEvent) {
		for (final Object[] taskRecord : this.selectedTasksList) if (ActiveTaskController.getInstance().completeTask(((Task) taskRecord[0]).getTaskID()) == 1) this.scrollContent.getChildren().remove((Pane) taskRecord[1]);

		this.selectedTasksList.clear();
		this.taskDeleteButton.setDisable(true);
		this.taskEditButton.setDisable(true);
		this.taskDoneButton.setDisable(true);
	}

	public void taskHistoryButtonOnAction (ActionEvent actionEvent) {
		try {
			final Stage stage = new Stage();
			final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../../view/history_view.fxml"));

			stage.setScene(new Scene(loader.load()));
			stage.setTitle("TaskBoard - History");
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());

			final HistoryFormController controller = loader.getController();

			controller.setUser(this.user);

			stage.showAndWait();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}

	public void taskAddButtonOnAction (ActionEvent actionEvent) {
		try {
			final Stage stage = new Stage();
			final FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../../view/task_add_view.fxml"));

			stage.setScene(new Scene(loader.load()));
			stage.setTitle("TaskBoard - Add task");
			stage.setResizable(false);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Button) actionEvent.getSource()).getScene().getWindow());

			final TaskAddFormController controller = loader.getController();

			controller.setUser(this.user);

			stage.showAndWait();
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
}
