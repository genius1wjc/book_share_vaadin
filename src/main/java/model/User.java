package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long mId;

	@Column(name = "email", nullable = false)
	public String mEmail;

	@Column(name = "name", nullable = false)
	public String mName;

	@Column(name = "username", nullable = false)
	public String mUsername;

	@Column(name = "password", nullable = false)
	public String mPassword;

}
