package godxero.model.task;

public enum TaskPriority {
	LOW, MEDIUM, HIGH;

	public static TaskPriority fromString (String priority) {
		if (priority == null) return null;

		return switch (priority.toUpperCase()) {
			case "LOW" -> LOW;
			case "MEDIUM" -> MEDIUM;
			case "HIGH" -> HIGH;
			default -> null;
		};
	}
}
