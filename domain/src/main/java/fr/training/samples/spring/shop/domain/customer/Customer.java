package fr.training.samples.spring.shop.domain.customer;

import javax.persistence.Entity;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;

@Entity
public class Customer extends AbstractBaseEntity {

	private String name;

	private String password;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

}
