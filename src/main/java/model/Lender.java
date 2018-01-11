package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lender")
public class Lender extends User {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "books_owned", nullable = false)
	public List<Book> mBooksOwned;

}
