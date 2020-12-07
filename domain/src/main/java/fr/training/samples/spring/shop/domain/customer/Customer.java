package fr.training.samples.spring.shop.domain.customer;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;

import fr.training.samples.spring.shop.domain.common.entity.AbstractBaseEntity;

@Entity
public class Customer extends AbstractBaseEntity {

	private String name;

	private String password;

	private final Set<RoleTypeEnum> roles = new HashSet<>();

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

	public void addRole(final RoleTypeEnum role) {
		roles.add(role);
	}

	public Set<RoleTypeEnum> getRoles() {
		return roles;
	}

}
