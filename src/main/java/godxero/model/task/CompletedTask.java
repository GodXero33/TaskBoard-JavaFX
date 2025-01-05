package godxero.model.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CompletedTask implements Task {
	private Integer taskID;
	private Integer userID;
	private String description;
	private String title;
	private TaskCategory category;
	private String startTime;
	private String updateTime;
	private String scheduleTime;
	private TaskPriority priority;
	private TaskFrequency frequency;
	private String completedTime;
}
