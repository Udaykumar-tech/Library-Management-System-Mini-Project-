package com.library.service;

import com.library.dao.TransactionDAO;

public class TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAO();

    public void issueBook(int userId, int bookId) {
        transactionDAO.issueBook(userId, bookId);
    }

    public void returnBook(int txnId) {
        transactionDAO.returnBook(txnId);
    }
}
