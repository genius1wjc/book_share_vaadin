package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id", unique = true)
	public int id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "age", nullable = false)
    public int age;

    @Override
    public String toString() {
        return id + "\t" + name + "\t" + age;
    }
}