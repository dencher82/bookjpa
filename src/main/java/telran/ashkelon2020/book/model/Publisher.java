package telran.ashkelon2020.book.model;

import java.io.Serializable;

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
@EqualsAndHashCode(of = {"publisherName"})
@Entity
@Table(name = "publishers")
public class Publisher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8968852048450070513L;
	@Id
	String publisherName;
	
}
