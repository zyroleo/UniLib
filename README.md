# UniLib

##📘 OVERVIEW

UniLib’s library-system-DB module provides the database schema and scripts for a Library Management System.
It is intended to support key library operations storing book and member records, tracking borrow/return transactions, and managing library data in a relational database.

This module focuses solely on database design and data management (tables, relationships, data constraints) rather than UI or application logic.


###🚀 FEATURES

###🔹 Core Database Features

####📘 Book catalog (title, author, availability, category)

####🧑‍🤝‍🧑 Member records

####🔄 Loan management (borrow/return)

####🗂 Categories & metadata

###🔹 Technical Features

####🔐 Foreign keys & constraints

####🧱 Normalized and clean schema

####⚙ Optional triggers & views

####📦 Easy export/import


###📁 REPOSITORY STUCTURES

schema.sql
seed_data.sql
triggers.sql
views.sql
README.md

###🛠️ GETTING STARTED

Clone the repository

git clone https://github.com/zyroleo/UniLib.git
cd UniLib/library-system-DB


Create (or choose) a database for the library system, e.g. library_db.

Run the SQL schema file(s) to create needed tables and relationships.
For example (if using MySQL):

mysql -u <username> -p <library_db> < schema.sql


(Optional) If seed/sample data is provided, import it to populate initial books, members, etc.

mysql -u <username> -p <library_db> < seed_data.sql


Configure your application (if any) to connect to the database library_db using correct credentials and host information.

Once connected, test basic queries to verify tables are created and accessible.

###🧰 INTENDED USAGE

This database module is designed to be used in tandem with an application layer (web, desktop, API) that implements library logic — e.g.:

Adding new books and members

Searching catalog / members

Borrowing/issuing books to a member

Returning books and updating availability

Tracking loan history, due dates, fines (if implemented)

You may build your own UI or integrate with existing front-end/back-end frameworks, as long as they connect to the schema provided here.

###📌 WHY THIS REPOSITORY EXIST

Maintaining a clean, well-defined database schema for a library system helps in:

Ensuring data consistency (via constraints, relationships)

Simplifying integration with apps (clear table definitions)

Supporting scalability and future maintenance

Allowing reuse across different front-ends or projects

###🔍 FUTURE IMPROVEMENTS (POSSIBLE ENHANCEMENT)

Add migration scripts for schema updates over time.

Provide sample queries for common operations (e.g. search books, list borrowed items, overdue items).

Include stored procedures or triggers for automating tasks (e.g. on return, update availability).

Add documentation for database ER diagram and table descriptions.

Provide seed data sample files (books, members) to ease testing.


CREATED BY:
Albo, Lex Randal B.
Ballesteros, Zyra
Quijaro, Earl Leobert
