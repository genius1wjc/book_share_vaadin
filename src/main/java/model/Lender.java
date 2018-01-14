package model;

import java.util.List;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lender")
public class Lender extends User {
	
	private static final long serialVersionUID = 1L;
	
//	@Column(name = "books_owned", nullable = false)
//	public List<Book> mBooksOwned;
	
	public Lender() {
		// Default constructor required by hibernate
	}
	
	public Lender(long id, @Nonnull String email, @Nonnull String name, @Nonnull String username,
			@Nonnull String password) {
		mId = id;
		mEmail = email;
		mName = name;
		mUsername = username;
		mPassword = password;
	}

}
