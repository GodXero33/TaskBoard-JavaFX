package godxero.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface Task {
	Integer getTaskID();
	Integer getUserID();
	String getDescription();
	String getTitle();
	TaskCategory getCategory();
	String getStartTime();
	String getUpdateTime();
	String getScheduleTime();
	TaskPriority getPriority();
	TaskFrequency getFrequency();

	static String getCurrentDateTime () {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
