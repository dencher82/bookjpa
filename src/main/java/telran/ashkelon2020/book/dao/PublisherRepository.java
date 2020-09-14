package telran.ashkelon2020.book.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.book.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String>{
	
	// unidirectional
	@Query("select distinct p.publisherName from Book b join b.authors a join b.publisher p where a.name=?1") // distinct better than Set<>
//	Set<String> findPublisherByAuthor(String name);
	List<String> findPublisherByAuthor(String name);
	
	// bidirectional
	Stream<Publisher> findDistinctByBooksAuthorsName(String name);
	
}
