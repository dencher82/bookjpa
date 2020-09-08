package telran.ashkelon2020.book.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.book.dao.AuthorRepository;
import telran.ashkelon2020.book.dao.BookRepository;
import telran.ashkelon2020.book.dao.PublisherRepository;
import telran.ashkelon2020.book.dto.AuthorDto;
import telran.ashkelon2020.book.dto.BookDto;
import telran.ashkelon2020.book.dto.exceptions.EntityNotFoundException;
import telran.ashkelon2020.book.model.Author;
import telran.ashkelon2020.book.model.Book;
import telran.ashkelon2020.book.model.Publisher;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired BookRepository bookRepository;
	
	@Autowired AuthorRepository authorRepository;
	
	@Autowired PublisherRepository publisherRepository;
	
	@Autowired ModelMapper modelMapper;
	
	@Override
	public boolean addBook(BookDto bookDto) {
		if (bookRepository.existsById(bookDto.getIsbn())) {
			return false;
		}
		// Publisher
		String publisherName = bookDto.getPublisherName();
		Publisher publisher = publisherRepository.findById(publisherName)
				.orElse(publisherRepository.save(new Publisher(publisherName)));
		// Authors
		Set<Author> authors = bookDto.getAuthors().stream()
				.map(a -> authorRepository.findById(a.getName()).orElse(authorRepository.save(new Author(a.getName(), a.getBirthDate()))))
				.collect(Collectors.toSet());
		// Book
		Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
		bookRepository.save(book);
		return true;
	}

	@Override
	public BookDto findBookByIsbn(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional // atomic
	public BookDto removeBook(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(() -> new EntityNotFoundException());
		bookRepository.delete(book);
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional // auto commit in DB (not need bookRepository.save(..))
	public BookDto updateBook(String isbn, String title) {
		Book book = bookRepository.findById(isbn).orElseThrow(() -> new EntityNotFoundException());
		if (title != null) {
			book.setTitle(title);
		}
		return modelMapper.map(book, BookDto.class);
	}

	@Override
	@Transactional
	public Iterable<BookDto> findBooksByAuthor(String authorName) {
		return bookRepository.findBooksByAuthorsName(authorName)
				.map(b -> modelMapper.map(b, BookDto.class))
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public Iterable<BookDto> findBooksByPublisher(String publisherName) {
		return bookRepository.findBooksByPublisherPublisherName(publisherName)
				.map(b -> modelMapper.map(b, BookDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<AuthorDto> findBookAuthors(String isbn) {
		Book book = bookRepository.findById(isbn).orElseThrow(() -> new EntityNotFoundException());
		return book.getAuthors()
				.stream().map(a -> modelMapper.map(a, AuthorDto.class))
				.collect(Collectors.toSet());
	}

	@Override
	@Transactional
	public Iterable<String> findPublishersByAuthor(String authorName) {
		authorRepository.findById(authorName).orElseThrow(() -> new EntityNotFoundException());
		return bookRepository.findPublishersByAuthor(authorName)
				.collect(Collectors.toSet());
	}

	@Override
	@Transactional
	public AuthorDto removeAuthor(String authorName) {
		Author author = authorRepository.findById(authorName).orElseThrow(() -> new EntityNotFoundException());
		bookRepository.findBooksByAuthorsName(authorName)
				.forEach(b -> bookRepository.delete(b));
		authorRepository.delete(author);
		return modelMapper.map(author, AuthorDto.class);
	}

}
