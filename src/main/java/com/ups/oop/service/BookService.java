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

    public List<BookDTO> getBook() {
        Iterable<Book> bookIterable = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (Book book : bookIterable) {
            String authorFullName = book.getAuthor() != null ?
                    book.getAuthor().getName() + " " + book.getAuthor().getLastname() :
                    "N/A";

            BookDTO bookDTO = new BookDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getEditorial(),
                    authorFullName
            );
            bookDTOList.add(bookDTO);
        }

        return bookDTOList;
    }
}
