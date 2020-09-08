package telran.ashkelon2020.book.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.book.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
	
	Stream<Book> findBooksByAuthorsName(String name);
	
	Stream<Book> findBooksByPublisherPublisherName(String publisherName);
	
	@Query("select b.publisher.publisherName from Book b, in (b.authors)authors where authors.name=?1")
	Stream<String> findPublishersByAuthor(String name);
	
}
