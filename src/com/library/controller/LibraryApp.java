package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.TransactionService;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {

        BookService bookService = new BookService();
        TransactionService transactionService = new TransactionService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter title: ");
                String title = sc.nextLine();

                System.out.print("Enter author: ");
                String author = sc.nextLine();

                System.out.print("Enter copies: ");
                int copies = sc.nextInt();
                sc.nextLine();

                bookService.addBook(title, author, copies);
            }

            else if (choice == 2) {
                List<Book> books = bookService.viewBooks();

                System.out.println("\n--- Books List ---");
                for (Book b : books) {
                    System.out.println(
                            b.getBookId() + " | " +
                                    b.getTitle() + " | " +
                                    b.getAuthor() + " | Available: " +
                                    b.getAvailableCopies()
                    );
                }
            }

            else if (choice == 3) {
                System.out.print("Enter User ID: ");
                int userId = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Book ID: ");
                int bookId = sc.nextInt();
                sc.nextLine();

                transactionService.issueBook(userId, bookId);
            }

            else if (choice == 4) {
                System.out.print("Enter Transaction ID: ");
                int txnId = sc.nextInt();
                sc.nextLine();

                transactionService.returnBook(txnId);
            }

            else if (choice == 5) {
                System.out.println("Exiting...");
                sc.close();
                break;
            }

            else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}
