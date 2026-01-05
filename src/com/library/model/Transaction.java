package com.library.model;

import java.sql.Date;

public class Transaction {

    private int txnId;
    private int userId;
    private int bookId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private int fine;

    public Transaction(int userId, int bookId, Date issueDate, Date dueDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getUserId() {
        return userId;
    }

    public int getBookId() {
        return bookId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
