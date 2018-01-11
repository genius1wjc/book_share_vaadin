package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "sequenceGenerator")
    public Long id;
	
	@Column(name = "isbn", nullable = false)
	public final String mIsbn;
	
	@Column(name = "name", nullable = false)
	public final String mName;
	
	public Book(final String isbn, final String name) {
		mIsbn = isbn;
		mName = name;
	}
}
