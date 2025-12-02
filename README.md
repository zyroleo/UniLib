<h1 align = "center">⊹ ࣪ ˖ 📚 UniLib 📚 ⊹ ࣪ ˖<h1/>

### 📖 OVERVIEW

UniLib’s library-system-DB module provides the database schema and scripts for a Library Management System.
It is intended to support key library operations storing book and member records, tracking borrow/return transactions, and managing library data in a relational database.

This module focuses solely on database design and data management (tables, relationships, data constraints) rather than UI or application logic.<br/>

_________________

### 🚀 FEATURES

### 🔹 Core Database Features

📘 Book catalog (title, author, availability, category)<br/>

🧑‍🤝‍🧑 Member records<br/>

🔄 Loan management (borrow/return)<br/>

🗂 Categories & metadata<br/>

### 🔹 Technical Features

🔐 Foreign keys & constraints<br/>

🧱 Normalized and clean schema<br/>

⚙ Optional triggers & views<br/>

📦 Easy export/import<br/>

_________________

### 🦋OOP Concepts Applied
🔹 Encapsulation

All class fields are protected/private and accessed through getters and setters to preserve data integrity.

🔹 Inheritance

Common attributes (e.g., ID, name, contact info) are placed in base classes, which are extended by more specific classes such as Student, Faculty, etc.

🔹 Polymorphism

Key operations (e.g., displaying information, validating records) are overridden across subclasses to allow dynamic behavior.

🔹 Abstraction

Core functionalities (e.g., item registration, borrowing process) are placed in abstract or general classes to hide implementation details from users.

_________________


### 🎀Program Structure
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

#### Main Components:

Book – represents items in the library

Member – base class for library users

Student / Faculty – subclasses with specific rules

Transaction – handles borrowing + returning

Database – mock or real DB storage

MainMenu – entry point and user interface

Relationships (simplified):
```
Member
 ├── Student
 └── Faculty
Book  ←→  Transaction  ←→  Member
```

_________________


### 🧋How to Run the Program
✔ Compile🤖
```
javac MainMenu.java
```
✔ Run🤖
```
java MainMenu
```

If the project uses packages, run:
```
javac -d . */*.java
java ui.MainMenu
```

If using a database, ensure the DB file or connection settings are correct before running.

_________________


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
```

Another example:
```
Book borrowed successfully!
Due date: 2025-01-14
```

Screenshots may be added if you prefer.

_________________


### 🎆Author and Acknowledgment

#### Author:

Your Name / Team Name

#### Acknowledgments:

-With sincere appreciation, We thank God for His unwavering guidance, for the strength He has given me during challenging moments, and for the inspiration that allowed me to continue progressing. His blessings have enabled me to learn, grow, and complete this project successfully. I am truly grateful for His presence throughout this journey.

-We would like to express my sincere gratitude to my CS 211 instructors Ms. Fatima Marie P. Agdon for their guidance, support, and dedication throughout this course. Their lessons greatly helped me complete this project.


-Our sincere appreciation goes to our friends for their guidance, ideas, and constant encouragement during the development of this project.


_________________


### 🎨Other Sections
#### Future Enhancements

-Implement GUI (JavaFX or Swing)

-Add login system (Admin vs User)

-Add fine-calculation system

-Integrate fully with SQL or Firebase

-Add export/import (CSV/JSON)

_________________


## 📗References

Java Documentation

CS 211 Lecture Notes

Online Java Tutorials (W3Schools, GeeksForGeeks, etc.)
