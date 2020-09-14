package telran.ashkelon2020.book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.book.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
	
	// unidirectional
//	Stream<Book> findBooksByAuthorsName(String name);
	
	// unidirectional
//	Stream<Book> findBooksByPublisherPublisherName(String publisherName);
	
	void deleteBooksByAuthorsName(String name);
	
}
