package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "borrower")
public class Borrower extends User {

	private static final long serialVersionUID = 1L;
	
//	@Column(name = "books_borrowed", nullable = false)
//	public List<Book> mBooksBorrowed;
	
	@Override
	public String toString() {
		return "Borrower [id=" + mId + ", email=" + mEmail + ", name=" + mName + ", username=" + mUsername
				+ ", password=" + mPassword + "]";
	}

}
