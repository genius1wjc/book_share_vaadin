package model;

import java.util.List;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "borrower")
public class Borrower extends User {

	private static final long serialVersionUID = 1L;
	
//	@Column(name = "books_borrowed", nullable = false)
//	public List<Book> mBooksBorrowed;
	
	public Borrower() {
		// Default constructor required by hibernate
	}
	
	public Borrower(long id, @Nonnull String email, @Nonnull String name, @Nonnull String username,
			@Nonnull String password) {
		mId = id;
		mEmail = email;
		mName = name;
		mUsername = username;
		mPassword = password;
	}
	
	@Override
	public String toString() {
		return "Borrower [id=" + mId + ", email=" + mEmail + ", name=" + mName + ", username=" + mUsername
				+ ", password=" + mPassword + "]";
	}

}
