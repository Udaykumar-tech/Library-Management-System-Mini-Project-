package com.library.dao;

import com.library.model.Book;
import com.library.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, total_copies, available_copies) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getTotalCopies());
            ps.setInt(4, book.getAvailableCopies());

            ps.executeUpdate();
            System.out.println("Book added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                );
                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
    public void decreaseAvailableCopies(int bookId, Connection conn) throws Exception {
        String sql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ps.executeUpdate();
        }
    }
    public void increaseAvailableCopies(int bookId, Connection conn) throws Exception {
        String sql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            ps.executeUpdate();
        }
    }

}
