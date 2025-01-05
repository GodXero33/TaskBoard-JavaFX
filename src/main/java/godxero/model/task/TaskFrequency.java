package godxero.model.task;

public enum TaskFrequency {
	DAILY, WEEKLY, MONTHLY, ONCE;

	public static TaskFrequency fromString (String frequency) {
		if (frequency == null) return null;

		return switch (frequency.toUpperCase()) {
			case "DAILY" -> DAILY;
			case "WEEKLY" -> WEEKLY;
			case "MONTHLY" -> MONTHLY;
			case "ONCE" -> ONCE;
			default -> null;
		};
	}
}
