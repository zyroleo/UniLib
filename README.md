# UniLib

A clean, modular SQL database schema for a Library Management System.

<p align="left"> <img src="https://img.shields.io/badge/Status-Active-brightgreen" /> <img src="https://img.shields.io/badge/Database-SQL-blue" /> <img src="https://img.shields.io/badge/Maintainer-zyroleo-orange" /> <img src="https://img.shields.io/badge/Project-UniLib-6A5ACD" /> </p>
📘 Overview

The UniLib Library System Database defines the SQL schema, relationships, constraints, and optional seed data for a complete Library Management System.

This module handles only the database layer — keeping the project modular and allowing the schema to be integrated into any backend or front-end, such as:

Web apps

Mobile apps

Desktop tools

University/School library systems

🚀 Features
🔹 Core Database Structure

Books catalog (titles, authors, categories, availability)

Members / users table

Loans system for borrowing & returning books

Staff/admin support (optional depending on schema)

Clean foreign-key relationships

🔹 Integrity & Safety

Primary & foreign key constraints

Cascading deletes/updates (depending on schema)

Data validation conditions

🔹 Developer-Friendly

Easy to migrate

Can be plugged into any system

Supports sample or seed data

📁 Repository Structure
library-system-DB/
│
├── schema.sql          # Main table-creation file
├── seed_data.sql       # Optional sample records (books, members, etc.)
├── triggers.sql        # Optional logic (if included)
├── views.sql           # Optional database views
└── README.md           # Documentation


Note: If your repo contains slightly different file names, adjust them here.

🛠️ Installation & Setup
1. Clone the repository
git clone https://github.com/zyroleo/UniLib.git
cd UniLib/library-system-DB

2. Create a database

Example for MySQL:

CREATE DATABASE library_db;

3. Import the schema
mysql -u <user> -p library_db < schema.sql

4. (Optional) Import sample data
mysql -u <user> -p library_db < seed_data.sql

5. Connect your app

Configure your backend to connect to the database:

Host

Username

Password

Database name (library_db)

🧩 ER Diagram (Conceptual Overview)

A typical structure looks like:

+----------+      +-----------+      +-----------+
|  Books   |----->|  Loans    |<-----| Members   |
+----------+      +-----------+      +-----------+
   | 1..*              | *..1             | 1..*
   |
   +--> Categories (optional)

Example Entities:

Books

id

title

author

category_id

available_copies

Members

id

name

email

joined_on

Loans

id

book_id

member_id

borrow_date

return_date

📝 Example Queries
✔️ Add a new book
INSERT INTO books (title, author, category_id, available_copies)
VALUES ('To Kill a Mockingbird', 'Harper Lee', 1, 4);

✔️ Get a list of available books
SELECT title, author
FROM books
WHERE available_copies > 0;

✔️ Borrow a book
INSERT INTO loans (book_id, member_id, borrow_date)
VALUES (12, 4, NOW());

UPDATE books
SET available_copies = available_copies - 1
WHERE id = 12;

✔️ Return a book
UPDATE loans
SET return_date = NOW()
WHERE id = 33;

UPDATE books
SET available_copies = available_copies + 1
WHERE id = 12;

🛠️ Technologies Used

SQL / MySQL (primary)

Works with MariaDB, PostgreSQL, or SQLite with small changes

Git for version control

📌 Roadmap

Future improvements may include:

Stored procedures for automatic loan handling

Fines/penalty system for overdue books

Full ER diagram image

Advanced search queries

User roles (Admin, Librarian, Member)

🤝 Contributing

Contributions are welcome!

Fork the repository

Create a feature branch

Commit your changes

Open a pull request

When contributing to schema changes, include:

Updated ER diagram (if any)

Explanation of new tables or fields

Migration scripts if needed

📄 License

This project currently has no license.
You may add one (MIT, Apache, etc.) if desired.

⭐ Support

If you like the project, please ⭐ the repo to support development!

If you'd like, I can also generate:

✅ a matching README for the frontend
✅ a README for the backend
✅ an ER diagram image
✅ SQL optimization guidance
