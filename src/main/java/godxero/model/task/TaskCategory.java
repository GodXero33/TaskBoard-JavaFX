package godxero.model.task;

public enum TaskCategory {
	WORK, PERSONAL, HEALTH, EDUCATION, SHOPPING, FINANCE, HOUSEHOLD, SOCIAL, TRAVEL, MISCELLANEOUS;

	public static TaskCategory fromString (String category) {
		if (category == null) return null;

		return switch (category.toUpperCase()) {
			case "WORK" -> WORK;
			case "PERSONAL" -> PERSONAL;
			case "HEALTH" -> HEALTH;
			case "EDUCATION" -> EDUCATION;
			case "SHOPPING" -> SHOPPING;
			case "FINANCE" -> FINANCE;
			case "HOUSEHOLD" -> HOUSEHOLD;
			case "SOCIAL" -> SOCIAL;
			case "TRAVEL" -> TRAVEL;
			case "MISCELLANEOUS" -> MISCELLANEOUS;
			default -> null;
		};
	}
}
