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

## 👨‍💻 Example Stored Students
You can insert predefined students:
```
INSERT INTO students (student_code, name) VALUES
('24-00500', 'ABELLA, JARELL M.'),
('24-03039', 'ACUZAR, RAYMOND F.'),
('24-04817', 'AGUILA, AL JOHN M.'),
('24-09818', 'BACAY, KOTNIE EDRAE L.'),
('24-03087', 'BALLESTEROS, ZYRA M.'),
('24-02528', 'BAY, CLAIRE NICOLE V.'),
('24-05821', 'CANTOS, ALODIVINNO RICCO L.'),
('23-05201', 'CARAIG, HANS GADIEL P.'),
('24-01594', 'CASANOVA, MICKHAEL D.'),
('24-02431', 'DADAP, AMBER LOVEINE R.'),
('24-07852', 'DICHOSO, COLEEN B.'),
('23-00205', 'ESTRADA, AUBREY NICOLE P.'),
('24-04891', 'LARGA, ERIKA YSOBELLE U.'),
('24-05254', 'MANALO, JOHN DANVER Z.'),
('24-06747', 'MENDOZA, GOLDWYN DAINE KIERZENE D.'),
('24-04698', 'MERCADO, AARON DANIEL L.'),
('24-00627', 'PAALA, LUKE ANDRE V.'),
('24-02154', 'PLAZA, GEEVOI A.'),
('23-64749', 'QUIJARO, EARL LEOBERT L.'),
('24-07030', 'RAMOS, SHIN-MIE'),
('24-03592', 'RICOHERMOSO, LORDY MILES J.'),
('24-05612', 'TORRIRIT, SHAWN JANXENT'),
('24-03493', 'TOSINO, MYK ANGELO D.'),
('24-05989', 'VILLEGAS, LEMUEL L.');
```


_______________

## 🔧 Setup Instructions 🤖
1. Install Requirements

- Java 17+

- MySQL Server

- VS Code 

- MySQL Connector/J (JDBC driver)

2. Import the Project

Place source files under:
```
/src/library/...
```

3. Update Database Credentials

In `DatabaseConnector.java`:
```
private static final String URL = "jdbc:mysql://localhost:3306/librarydb";
private static final String USER = "root";
private static final String PASSWORD = "yourpassword";
```

4. Run the Program

Compile and run:
```
javac -d bin src/library/ui/Main.java
java -cp bin;lib/mysql-connector-j-9.5.0.jar library.ui.Main
```
_______________


### ✨Sample Output
```
=== Welcome to UniLib ===

Menu:
1) List items
2) Check out (by call number)
3) Check in (by call number) 
4) View all borrowed items   
5) Add item
6) Remove item
7) Update item
8) Add student
9) Remove student
0) Exit
Choice: 1

=== BOOKS ===
 - Data Structures (call#: B002) (AVAILABLE) -> Book: Data Structures by Jane Roe (2018) - CS House
 - Introduction to Java (call#: B001) (AVAILABLE) -> Book: Introduction to Java by John Doe (2019) - TechPub

=== THESES ===
 - Effects of Processed Foods (call#: T100) (AVAILABLE) -> Thesis: Effects of Processed Foods by Alice Santos (2024) - BSFE

=== LAPTOPS ===
 - Dell Inspiron (call#: L500) (AVAILABLE) -> Laptop: Dell Inspiron

=== TABLETS ===
 - Galaxy Tab (call#: TB900) (AVAILABLE) -> Tablet: Galaxy Tab

Menu:
1) List items
2) Check out (by call number)
3) Check in (by call number)
4) View all borrowed items
5) Add item
6) Remove item
7) Update item
8) Add student
9) Remove student
0) Exit
Choice: 2
Student code (e.g., 24-00101): 23-64749
Student: QUIJARO, EARL LEOBERT L.
Call number to check out: T100
Checked out successfully.

Menu:
1) List items
2) Check out (by call number)
3) Check in (by call number)
4) View all borrowed items
5) Add item
6) Remove item
7) Update item
8) Add student
9) Remove student
0) Exit
Choice: 4

Active Borrowed Items:
ID | Call# | StudentCode | StudentName | Title (Type) | BorrowedAt
12 | T100 | 23-64749 | QUIJARO, EARL LEOBERT L. | Effects of Processed Foods (THESIS) | 2025-12-03 01:57:54.0

Menu:
1) List items
2) Check out (by call number)
3) Check in (by call number)
4) View all borrowed items
5) Add item
6) Remove item
7) Update item
8) Add student
9) Remove student
0) Exit
Choice: 1

=== BOOKS ===
 - Data Structures (call#: B002) (AVAILABLE) -> Book: Data Structures by Jane Roe (2018) - CS House
 - Introduction to Java (call#: B001) (AVAILABLE) -> Book: Introduction to Java by John Doe (2019) - TechPub

=== THESES ===
 - Effects of Processed Foods (call#: T100) (BORROWED) -> Thesis: Effects of Processed Foods by Alice Santos (2024) - BSFE

=== LAPTOPS ===
 - Dell Inspiron (call#: L500) (AVAILABLE) -> Laptop: Dell Inspiron

=== TABLETS ===
 - Galaxy Tab (call#: TB900) (AVAILABLE) -> Tablet: Galaxy Tab

Menu:
1) List items
2) Check out (by call number)
3) Check in (by call number)
4) View all borrowed items
5) Add item
6) Remove item
7) Update item
8) Add student
9) Remove student
0) Exit
Choice: 3
Call number to check in: T100
Checked in successfully.

Menu:
1) List items
2) Check out (by call number)
3) Check in (by call number)
4) View all borrowed items
5) Add item
6) Remove item
7) Update item
8) Add student
9) Remove student
0) Exit
Choice: 4

Active Borrowed Items:
ID | Call# | StudentCode | StudentName | Title (Type) | BorrowedAt

Menu:
1) List items
2) Check out (by call number)
3) Check in (by call number)
4) View all borrowed items
5) Add item
6) Remove item
7) Update item
8) Add student
9) Remove student
0) Exit
Choice:
```
_______________

### 🎆Contributors


Albo, Lex Randal B.

Ballesteros, Zyra M.

Quijaro, Earl Leobert L.

_______________

#### Acknowledgments:

With sincere appreciation, We thank God for His unwavering guidance, for the strength He has given us during challenging moments, and for the inspiration that allowed us to continue progressing. His blessings have enabled me to learn, grow, and complete this project successfully. We are truly grateful for His presence throughout this journey.

We would like to express our sincere gratitude to our CS 211 instructors Ms. Fatima Marie P. Agdon for their guidance, support, and dedication throughout this course. Their lessons greatly helped me complete this project.


Our sincere appreciation goes to our friends for their guidance, ideas, and constant encouragement during the development of this project.

_______________

