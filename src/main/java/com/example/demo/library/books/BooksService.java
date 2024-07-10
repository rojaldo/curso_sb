package com.example.demo.library.books;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksService {

    @Autowired
    BooksRespository booksRespository;

    public Optional<BookDto> createBook(BookDto book) {
        // search isbn book in the database
        Optional<BookEntity> be = this.booksRespository.findByIsbn(book.getIsbn());
        if (be.isPresent()) {
            return Optional.empty();
        }
        BookEntity bookEntity = BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .pages(book.getPages())
                .build();
        this.booksRespository.save(bookEntity);
        return Optional.of(BookDto.builder()
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .description(bookEntity.getDescription())
                .pages(bookEntity.getPages())
                .build());
    }

    public BookDto getBookById(long id) {
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return null;
        }
        return BookDto.builder()
                .isbn(be.getIsbn())
                .title(be.getTitle())
                .author(be.getAuthor())
                .description(be.getDescription())
                .pages(be.getPages())
                .build();
    }

    public BookEntity getBookEntityById(long id) {
        return this.booksRespository.findById(id);
    }

    public Iterable<BookDto> getAllBooks(BookDto book) {
        Iterable<BookEntity> bookEntities = this.booksRespository.findAll();
        if (book.getTitle() == null && book.getAuthor() == null) {
            
        }else if (book.getTitle() != null && book.getAuthor() == null) {
            bookEntities = this.booksRespository.findByTitleContainingIgnoreCase(book.getTitle());
        }else if (book.getTitle() == null && book.getAuthor() != null) {
            bookEntities = this.booksRespository.findByAuthorContainingIgnoreCase(book.getAuthor());
        }
        return this.convertBookEntitiesToBookDtos(bookEntities);
    }

    public BookDto deleteBookById (long id) {
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return null;
        }else {
            this.booksRespository.deleteById(id);
            return BookDto.builder()
                    .isbn(be.getIsbn())
                    .title(be.getTitle())
                    .author(be.getAuthor())
                    .description(be.getDescription())
                    .pages(be.getPages())
                    .build();
        }
    }

    public BookDto deleteBookByIsbn (String isbn) {
        Optional<BookEntity> be = this.booksRespository.findByIsbn(isbn);
        if (be == null) {
            return null;
        }else {
            this.booksRespository.delete(be.get());
            return BookDto.builder()
                    .isbn(be.get().getIsbn())
                    .title(be.get().getTitle())
                    .author(be.get().getAuthor())
                    .description(be.get().getDescription())
                    .pages(be.get().getPages())
                    .build();
        }
    }

    public Optional<BookDto> modifyBook (BookDto book, long id){
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return Optional.empty();
        }else {
            be.setIsbn(book.getIsbn());
            be.setTitle(book.getTitle());
            be.setAuthor(book.getAuthor());
            be.setDescription(book.getDescription());
            be.setPages(book.getPages());
            this.booksRespository.save(be);
            return Optional.of(BookDto.builder()
                    .isbn(be.getIsbn())
                    .title(be.getTitle())
                    .author(be.getAuthor())
                    .description(be.getDescription())
                    .pages(be.getPages())
                    .build());
        }
    }

    public Optional<BookDto> modifyBook(BookDto book, String isbn) {
        Optional<BookEntity> be = this.booksRespository.findByIsbn(isbn);
        if (be.isEmpty()) {
            return Optional.empty();
        }
        BookEntity bookEntity = be.get();
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setDescription(book.getDescription());
        bookEntity.setPages(book.getPages());
        this.booksRespository.save(bookEntity);
        return Optional.of(BookDto.builder()
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .description(bookEntity.getDescription())
                .pages(bookEntity.getPages())
                .build());
    }

    public Optional<BookDto> updateBook (BookDto book, long id){
        BookEntity be = this.booksRespository.findById(id);
        if (be == null) {
            return Optional.empty();
        }else {
            if (book.getIsbn() != null) {
                be.setIsbn(book.getIsbn());
            }
            if (book.getTitle() != null) {
                be.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null) {
                be.setAuthor(book.getAuthor());
            }
            if (book.getDescription() != null) {
                be.setDescription(book.getDescription());
            }
            if (book.getPages() != 0) {
                be.setPages(book.getPages());
            }
            this.booksRespository.save(be);
            return Optional.of(BookDto.builder()
                    .isbn(be.getIsbn())
                    .title(be.getTitle())
                    .author(be.getAuthor())
                    .description(be.getDescription())
                    .pages(be.getPages())
                    .build());
        }
    }

    public Optional<BookDto> updateBook(BookDto book, String isbn) {
        Optional<BookEntity> be = this.booksRespository.findByIsbn(isbn);
        if (be.isEmpty()) {
            return Optional.empty();
        }
        BookEntity bookEntity = be.get();
        if (book.getTitle() != null) {
            bookEntity.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null) {
            bookEntity.setAuthor(book.getAuthor());
        }
        if (book.getDescription() != null) {
            bookEntity.setDescription(book.getDescription());
        }
        if (book.getPages() != 0) {
            bookEntity.setPages(book.getPages());
        }
        this.booksRespository.save(bookEntity);
        return Optional.of(BookDto.builder()
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .description(bookEntity.getDescription())
                .pages(bookEntity.getPages())
                .build());
    }

    private Iterable<BookDto> convertBookEntitiesToBookDtos(Iterable<BookEntity> bookEntities) {
        return StreamSupport.stream(bookEntities.spliterator(), false)
                .map(be -> BookDto.builder()
                        .isbn(be.getIsbn())
                        .title(be.getTitle())
                        .author(be.getAuthor())
                        .description(be.getDescription())
                        .pages(be.getPages())
                        .build())
                .collect(Collectors.toList());
    }
    
}
