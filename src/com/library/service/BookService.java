package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;

import java.util.List;

public class BookService {

    private BookDAO bookDAO = new BookDAO();

    public void addBook(String title, String author, int copies) {
        Book book = new Book(0, title, author, copies, copies);
        bookDAO.addBook(book);
    }

    public List<Book> viewBooks() {
        return bookDAO.getAllBooks();
    }
}
