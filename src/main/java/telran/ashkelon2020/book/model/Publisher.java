package telran.ashkelon2020.book.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"publisherName"})
@Entity
public class Publisher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8968852048450070513L;
	@Id
	String publisherName;
	@OneToMany(mappedBy = "publisher") // bidirectional
	Set<Book> books;
	
	public Publisher(String publisherName) {
		this.publisherName = publisherName;
	}
	
}
