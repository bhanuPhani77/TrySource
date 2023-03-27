package com.trysol.entity;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class EmployeeDetails extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String firstName;
	private final String lastName;
	private final String email;

	private EmployeeDetails(Builder builder) {
		super(builder.username, builder.password,builder.roles);
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		EmployeeDetails that = (EmployeeDetails) o;
		return firstName.equals(that.firstName) && lastName.equals(that.lastName) && email.equals(that.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), firstName, lastName, email);
	}
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Builder {
		private String firstName;
		private String lastName;
		private String email;
		private String username;
		private String password;
		private Collection<? extends GrantedAuthority> roles;

		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withAuthorities(Collection<? extends GrantedAuthority> roles) {
			this.roles = roles;
			return this;
		}

		public EmployeeDetails build() {
			return new EmployeeDetails(this);
		}
	}
}
