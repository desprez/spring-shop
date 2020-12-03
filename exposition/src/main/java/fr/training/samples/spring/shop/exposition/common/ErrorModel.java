package fr.training.samples.spring.shop.exposition.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Generic error report structure
 */
@ApiModel(description = "Generic error report structure")
@Validated
public class ErrorModel {

	@JsonProperty("code")
	private String code;

	@JsonProperty("message")
	private String message;

	@JsonProperty("description")
	private String description;

	/**
	 * error code
	 *
	 * @return code
	 **/
	@ApiModelProperty(required = true, value = "error code")
	@NotNull
	public String getCode() {
		return code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * HTTP textual reason phrase
	 *
	 * @return message
	 **/
	@ApiModelProperty(required = true, value = "HTTP textual reason phrase")
	@NotNull
	@Size(max = 80)
	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * Description longue
	 *
	 * @return description
	 **/
	@ApiModelProperty(value = "Description longue")
	@Size(max = 230)
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	private ErrorModel(final Builder builder) {
		code = builder.code;
		message = builder.message;
		description = builder.description;
	}

	/**
	 * Builder pattern
	 */
	public static class Builder {
		private String code;
		private String message;
		private String description;

		public Builder code(final String code) {
			this.code = code;
			return this;
		}

		public Builder message(final String message) {
			this.message = message;
			return this;
		}

		public Builder description(final String description) {
			this.description = description;
			return this;
		}

		public ErrorModel build() {
			return new ErrorModel(this);
		}
	}

	/**
	 * Builder static assessor
	 */
	public static Builder builder() {
		return new Builder();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (code == null ? 0 : code.hashCode());
		result = prime * result + (description == null ? 0 : description.hashCode());
		result = prime * result + (message == null ? 0 : message.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ErrorModel other = (ErrorModel) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!message.equals(other.message)) {
			return false;
		}
		return true;
	}

}
