<h1 align = "center">⊹ ࣪ ˖ 📚 UniLib 📚 ⊹ ࣪ ˖<h1/>

## 📖 OVERVIEW

UniLib is a Java-based, console-driven library management system designed to help librarians manage books, theses, laptops, tablets and students with MySQL database integration.

It showcases strong fundamentals in:

- Object-oriented Programming (OOP) principles

- Exception handling

- DAO (Data Access Object) pattern

- Service-layer abstraction

- Database CRUD operations

Users can:

- 📚 Add new library items (book, laptop, tablet, thesis)
- 🎓 Register students
- 📑 Borrow and return items
- 🔍 View all items, view availability and view all active borrowed items
<br/>

_______________

## 🚀 FEATURES

1) List items — Display all items with availability status.
2) Check out (by call number) — Students may check out/borrow available items.
3) Check in (by call number) — Updates loan status and item availability by returning the item.
4) View all borrowed items — Shows all the active borrowed items.
5) Add item — Register books, laptops, tablets, or theses.
6) Remove item — Removes any books, laptops, tablets, or theses.
7) Update item — Update any existing books, laptops, tablets, or theses.
8) Add student — Add a student record to the system.
9) Remove student — Remove a student record in the system.
0) Exit — Exit UniLib.<br/>

_______________

## 🦋OOP Concepts Applied
#### 🔹 Encapsulation

Classes such as `Item`, `Book`, `Laptop`, and `Student` have private fields with public getters/setters, ensuring controlled access to data.

#### 🔹 Inheritance

Common attributes `(e.g., ID, name, contact info)` are placed in base classes, which are extended by more specific classes such as `Student, Faculty, etc.`

#### 🔹 Polymorphism

Key operations `(e.g., displaying information, validating records)` are overridden across subclasses to allow dynamic behavior.

#### 🔹 Abstraction

Core functionalities `(e.g., item registration, borrowing process)` are placed in abstract or general classes to hide implementation details from users.

_______________


## 🎀Program Structure
```
src/
 └── library
      ├── dao
      │    ├── DatabaseConnector.java
      │    ├── ItemDAO.java
      │    ├── LoanDAO.java
      │    └── StudentDAO.java
      │
      ├── exceptions
      │    ├── AlreadyBorrowedException.java
      │    ├── InvalidInputException.java
      │    └── ItemNotFoundException.java
      │
      ├── model
      │    ├── Item.java (abstract)
      │    ├── Book.java
      │    ├── Borrowable.java
      │    ├── Thesis.java
      │    ├── Laptop.java
      │    └── Tablet.java
      │
      ├── service
      │    └── LibraryService.java
      │
      └── ui
           └── Main.java

```

## 🗄️MySQL Database Schema

`items` Table
```
CREATE TABLE items (
    call_number VARCHAR(20) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    type ENUM('BOOK','THESIS','LAPTOP','TABLET') NOT NULL,
    author VARCHAR(255),
    publisher VARCHAR(255)
);
```

`students` Table
```
CREATE TABLE students (
    student_code VARCHAR(20) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
```

`loans` Table
```
CREATE TABLE loans (
    id INT AUTO_INCREMENT PRIMARY KEY,
    call_number VARCHAR(20),
    student_code VARCHAR(20),
    borrowed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    returned_at TIMESTAMP NULL,
    FOREIGN KEY (call_number) REFERENCES items(call_number),
    FOREIGN KEY (student_code) REFERENCES students(student_code)
);
```


### Main Components:

**Book 📚** – represents items in the library

**Member 🪪** – base class for library users

**Student / Faculty 👥** – subclasses with specific rules

**Transaction 🖥️** – handles borrowing + returning

**Database 📊** – mock or real DB storage

**MainMenu 💻** – entry point and user interface

**Relationships (simplified):**
Member
 ├── Student
 └── Faculty
*Book  ←→  Transaction  ←→  Member*

_______________

## 🧋How to Run the Program
✔ Compile 🤖
-       javac MainMenu.java
✔ Run 🤖
-       java MainMenu

If the project uses packages, run:
```
javac -d . */*.java
java ui.MainMenu
```
If using a database, ensure the DB file or connection settings are correct before running.
_______________


### ✨Sample Output
```
=============================
       UniLib System
=============================
[1] Add Book
[2] Register Member
[3] Borrow Book
[4] Return Book
[5] View Records
[0] Exit
Choose an option: 1

Another example:
Book borrowed successfully!
Due date: 2025-01-14
```
_______________

### 🎆Author and Acknowledgment


Albo, Lex Randal B.

Ballesteros, Zyra

Quijaro, Earl Leobert

_______________

#### Acknowledgments:

With sincere appreciation, We thank God for His unwavering guidance, for the strength He has given me during challenging moments, and for the inspiration that allowed me to continue progressing. His blessings have enabled me to learn, grow, and complete this project successfully. I am truly grateful for His presence throughout this journey.

We would like to express my sincere gratitude to my CS 211 instructors Ms. Fatima Marie P. Agdon for their guidance, support, and dedication throughout this course. Their lessons greatly helped me complete this project.


Our sincere appreciation goes to our friends for their guidance, ideas, and constant encouragement during the development of this project.

_______________
#### 📗References

*Java Documentation*
*CS 211 Lecture Notes*
*Online Java Tutorials (W3Schools, GeeksForGeeks, etc.)*
