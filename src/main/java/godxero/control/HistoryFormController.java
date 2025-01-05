package godxero.control;

import godxero.control.task.CompletedTaskController;
import godxero.model.User;
import godxero.model.task.CompletedTask;
import godxero.model.task.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryFormController implements Initializable {
	public TableView<CompletedTask> tasksListTable;
	public TableColumn<CompletedTask, String> titleColumn;
	public TableColumn<CompletedTask, String> startTimeColumn;
	public TableColumn<CompletedTask, String> endTimeColumn;
	public TableColumn<CompletedTask, String> editedTimeColumn;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		this.startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
		this.endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("scheduleTime"));
		this.editedTimeColumn.setCellValueFactory(new PropertyValueFactory<>("updateTime"));
	}

	public void setUser (User user) {
		final List<Task> completedTasks = CompletedTaskController.getInstance().getAllTasks(user.getId());
		final ObservableList<CompletedTask> tableList = FXCollections.observableArrayList();

		for (final Task task : completedTasks) tableList.add((CompletedTask) task);

		this.tasksListTable.setItems(tableList);
	}
}
