package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	public String mEmail;

	@Column(name = "name", nullable = false)
	public String mName;

	@Column(name = "password", nullable = false)
	public String mPassword;

}
