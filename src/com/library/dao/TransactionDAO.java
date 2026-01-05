package com.library.dao;

import com.library.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;

public class TransactionDAO {

    public void issueBook(int userId, int bookId) {

        String checkSql = "SELECT available_copies FROM books WHERE book_id = ?";
        String issueSql = "INSERT INTO transactions(user_id, book_id, issue_date, due_date, fine) VALUES (?, ?, ?, ?, 0)";
        String updateBookSql = "UPDATE books SET available_copies = available_copies - 1 WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false); // IMPORTANT

            PreparedStatement checkPs = conn.prepareStatement(checkSql);
            checkPs.setInt(1, bookId);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {

                PreparedStatement issuePs = conn.prepareStatement(issueSql);
                issuePs.setInt(1, userId);
                issuePs.setInt(2, bookId);
                issuePs.setDate(3, new Date(System.currentTimeMillis()));
                issuePs.setDate(4, new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000));
                issuePs.executeUpdate();

                PreparedStatement updatePs = conn.prepareStatement(updateBookSql);
                updatePs.setInt(1, bookId);
                updatePs.executeUpdate();

                conn.commit();
                System.out.println("Book issued successfully");

            } else {
                System.out.println("Book not available");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int txnId) {

        String returnSql = "UPDATE transactions SET return_date = ?, fine = ? WHERE txn_id = ? AND return_date IS NULL";
        String updateBookSql = "UPDATE books SET available_copies = available_copies + 1 WHERE book_id = (SELECT book_id FROM transactions WHERE txn_id = ?)";

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);

            int fine = 0;
            PreparedStatement ps1 = conn.prepareStatement(
                    "SELECT due_date FROM transactions WHERE txn_id = ? AND return_date IS NULL"
            );
            ps1.setInt(1, txnId);
            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                System.out.println("Invalid transaction ID or book already returned");
                return;
            }

            Date due = rs.getDate(1);
            long daysLate = (System.currentTimeMillis() - due.getTime()) / (1000 * 60 * 60 * 24);
            if (daysLate > 0) fine = (int) daysLate * 5;

            PreparedStatement returnPs = conn.prepareStatement(returnSql);
            returnPs.setDate(1, new Date(System.currentTimeMillis()));
            returnPs.setInt(2, fine);
            returnPs.setInt(3, txnId);

            int rows = returnPs.executeUpdate();

            if (rows == 0) {
                System.out.println("Return failed. Invalid transaction.");
                conn.rollback();
                return;
            }

            PreparedStatement updatePs = conn.prepareStatement(updateBookSql);
            updatePs.setInt(1, txnId);
            updatePs.executeUpdate();

            conn.commit();
            System.out.println("Book returned successfully. Fine: ₹" + fine);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
