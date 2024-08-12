package com.ups.oop.service;


import com.ups.oop.dto.BookDTO;
import com.ups.oop.entity.Book;
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
    private List<BookDTO> bookDTOList = new ArrayList<>();

    //TemplateController

    public List<BookDTO> getBooks(){
        Iterable<Book> bookIterable = bookRepository.findAll();
        List<BookDTO> bookList = new ArrayList<>();
        for (Book b : bookIterable) {
            BookDTO book = new BookDTO(b.getAuthor().getName(), b.getEditorial(),b.getTitle(),);
            bookList.add(book);
        }
        return bookList;
    }
}
