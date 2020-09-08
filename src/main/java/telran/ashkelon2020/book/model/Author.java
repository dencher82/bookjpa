package telran.ashkelon2020.book.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"name"})
@Entity
@Table(name = "authors")
public class Author implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4191233982819804639L;
	@Id
	String name;
	LocalDate birthDate;
	
}
