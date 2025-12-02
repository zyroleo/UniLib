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

All class fields are protected/private and accessed through getters and setters to preserve data integrity.

#### 🔹 Inheritance

Common attributes `(e.g., ID, name, contact info)` are placed in base classes, which are extended by more specific classes such as `Student, Faculty, etc.`

#### 🔹 Polymorphism

Key operations `(e.g., displaying information, validating records)` are overridden across subclasses to allow dynamic behavior.

#### 🔹 Abstraction

Core functionalities `(e.g., item registration, borrowing process)` are placed in abstract or general classes to hide implementation details from users.

_______________


## 🎀Program Structure
```
/library-system-DB
│
├── Database.java           # Handles database connections + queries
├── models/
│   ├── Book.java
│   ├── Member.java
│   ├── Student.java
│   ├── Faculty.java
│   ├── Transaction.java
│
├── ui/
│   └── MainMenu.java       # Console-based UI
│
└── utils/
    └── Validator.java      # Input checking utilities
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
