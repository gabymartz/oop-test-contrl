package com.ups.oop.service;

import com.ups.oop.dto.BookDTO;
import com.ups.oop.entity.Book;
import com.ups.oop.entity.Editorial;
import com.ups.oop.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<BookDTO> getAllBooks() {
        Iterable<Book> bookIterable = bookRepository.findAll();
        List<BookDTO> bookList = new ArrayList<>();
        for(Book book : bookIterable) {
            List<String> editorials = new ArrayList<>();
            for(Editorial editorial : book.getEditorials()) {
                editorials.add(editorial.getName());
            }
            BookDTO b = new BookDTO(
                    book.getTitle(),
                    book.getAuthor().getName() + "-" + book.getAuthor().getLastname(),
                    editorials
            );
            bookList.add(b);
        }
        return bookList;
    }
}