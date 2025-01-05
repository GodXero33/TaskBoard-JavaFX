package godxero.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
	private Integer id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
}
