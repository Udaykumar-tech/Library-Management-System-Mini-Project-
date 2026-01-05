# 📚 Library Management System

A robust Java-based Library Management System built with JDBC and MySQL to streamline library operations. This application implements the MVC architecture pattern and provides comprehensive functionality for managing books, users, and transactions with data integrity and reliability.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-007396?style=for-the-badge&logo=java&logoColor=white)

---

## 🎯 Features

### Book Management
- Add new books to the library inventory
- View all available books with detailed information
- Update book details and availability status
- Remove books from the system

### User Management
- Register new library members
- View user profiles and borrowing history
- Update user information
- Track active borrowers

### Transaction Management
- Issue books to registered users
- Process book returns efficiently
- Automatic fine calculation for overdue books
- Complete transaction history tracking
- Real-time availability updates

### Data Integrity
- Transaction handling with ACID properties
- Foreign key constraints for referential integrity
- Input validation and error handling
- Concurrent access management

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| **Java** | Core application logic and OOP implementation |
| **MySQL** | Relational database management |
| **JDBC** | Database connectivity and operations |
| **SQL** | Database queries and schema design |

---

## 🏗️ Architecture

The project follows the **MVC (Model-View-Controller)** architecture pattern for clean separation of concerns:
```
Library-Management-System/
│
├── src/
│   ├── model/
│   │   ├── Book.java
│   │   ├── User.java
│   │   └── Transaction.java
│   │
│   ├── dao/
│   │   ├── BookDAO.java
│   │   ├── UserDAO.java
│   │   └── TransactionDAO.java
│   │
│   ├── service/
│   │   ├── BookService.java
│   │   ├── UserService.java
│   │   └── TransactionService.java
│   │
│   ├── controller/
│   │   └── LibraryController.java
│   │
│   └── util/
│       └── DatabaseConnection.java
│
└── database/
    └── schema.sql
```

### Layer Responsibilities:

- **Model Layer** – POJO classes representing data entities (Book, User, Transaction)
- **DAO Layer** – Database operations using JDBC with prepared statements
- **Service Layer** – Business logic and validation
- **Controller Layer** – User interaction and application flow control
- **Util Layer** – Database connection management and helper utilities

---

## 🗄️ Database Schema

### Users Table
```sql
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    registration_date DATE DEFAULT CURRENT_DATE
);
```

### Books Table
```sql
CREATE TABLE books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    total_copies INT DEFAULT 1,
    available_copies INT DEFAULT 1,
    category VARCHAR(50)
);
```

### Transactions Table
```sql
CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    book_id INT,
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    fine_amount DECIMAL(10,2) DEFAULT 0.00,
    status ENUM('ISSUED', 'RETURNED') DEFAULT 'ISSUED',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);
```

**Key Features:**
- Foreign keys ensure referential integrity
- Indexes on frequently queried columns
- Cascading operations for data consistency
- Date tracking for fine calculations

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- MySQL Server 5.7 or higher
- MySQL Connector/J (JDBC Driver)
- IDE (Eclipse, IntelliJ IDEA, or VS Code)

### Installation Steps

1. **Clone the repository**
```bash
   git clone https://github.com/your-username/Library-Management-System.git
   cd Library-Management-System
```

2. **Set up the database**
```bash
   mysql -u root -p
```
```sql
   CREATE DATABASE library_db;
   USE library_db;
   SOURCE database/schema.sql;
```

3. **Configure database connection**
   
   Update the database credentials in `src/util/DatabaseConnection.java`:
```java
   private static final String URL = "jdbc:mysql://localhost:3306/library_db";
   private static final String USERNAME = "your_username";
   private static final String PASSWORD = "your_password";
```

4. **Add MySQL Connector to classpath**
   
   Download [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) and add to your project build path.

5. **Compile and run**
```bash
   javac -d bin src/**/*.java
   java -cp bin:mysql-connector-java.jar controller.LibraryController
```

---

## 💻 Usage Examples

### Adding a Book
```java
Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565", 5);
bookService.addBook(book);
```

### Registering a User
```java
User user = new User("John Doe", "john@example.com", "1234567890");
userService.registerUser(user);
```

### Issuing a Book
```java
transactionService.issueBook(userId, bookId);
```

### Returning a Book
```java
transactionService.returnBook(transactionId);
```

---

## 🔧 Configuration

You can customize the following settings in `application.properties`:
```properties
# Database Configuration
db.url=jdbc:mysql://localhost:3306/library_db
db.username=root
db.password=your_password

# Transaction Settings
book.issue.days=14
fine.per.day=5.00
max.books.per.user=3
```

---

## 📊 Key Functionalities

### Fine Calculation Logic
- Books must be returned within 14 days
- Fine: ₹5 per day for overdue books
- Automatic calculation on return date

### Transaction Flow
1. Check book availability
2. Verify user eligibility (max 3 books)
3. Create transaction record
4. Update book availability
5. Set due date (14 days from issue)

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👤 Author

**Ramanaboina Venkata Uday Kumar**

- LinkedIn: [Venkata Uday Kumar Ramana Boina](https://www.linkedin.com/in/venkata-uday-kumar-ramana-boina-b11615255/)
- Email: udaykumar.ramanaboina@gmail.com

---

## 🙏 Acknowledgments

- Inspired by real-world library management systems
- Built as a learning project for JDBC and database design
- Thanks to the open-source community for guidance

---

## 📞 Support

If you encounter any issues or have questions, please [open an issue](https://github.com/your-username/Library-Management-System/issues) on GitHub.

---

<div align="center">

### ⭐ Star this repository if you find it helpful!

Made with ❤️ and Java

</div>
