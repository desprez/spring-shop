package fr.training.samples.spring.shop.importjob;

public class ItemDto {

	private String id;

	private String description;

	private Integer price;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(final Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("ItemDto [id=").append(id) //
		.append(", description=").append(description) //
		.append(", price=").append(price) //
		.append("]");
		return builder.toString();
	}

}
