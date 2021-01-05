package fr.training.samples.spring.shop.exportjob;

public class CustomerDto {

	private String id;

	private String name;

	private String password;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("CustomerDto [id=").append(id) //
		.append(", name=").append(name) //
		.append(", password=").append(password) //
		.append("]");
		return builder.toString();
	}

}
