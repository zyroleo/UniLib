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

### 📁 REPOSITORY STUCTURES
```
schema.sql<br/>
seed_data.sql<br/>
triggers.sql<br/>
views.sql<br/>
README.md<br/>
```
_________________

### 🛠️ GETTING STARTED<br/>

Clone the repository
```
git clone https://github.com/zyroleo/UniLib.git
cd UniLib/library-system-DB
```

Create (or choose) a database for the library system, e.g. library_db.

Run the SQL schema file(s) to create needed tables and relationships.
For example (if using MySQL):
```
mysql -u <username> -p <library_db> < schema.sql
```

(Optional) If seed/sample data is provided, import it to populate initial books, members, etc.
```
mysql -u <username> -p <library_db> < seed_data.sql
```

Configure your application (if any) to connect to the database library_db using correct credentials and host information.<br/>

Once connected, test basic queries to verify tables are created and accessible.<br/>

_________________

### 🧰 INTENDED USAGE

This database module is designed to be used in tandem with an application layer (web, desktop, API) that implements library logic — e.g.:

- Adding new books and members

- Searching catalog / members

- Borrowing/issuing books to a member

- Returning books and updating availability

- Tracking loan history, due dates, fines (if implemented)

You may build your own UI or integrate with existing front-end/back-end frameworks, as long as they connect to the schema provided here.

_________________

### 📌 WHY THIS REPOSITORY EXIST

Maintaining a clean, well-defined database schema for a library system helps in:

- Ensuring data consistency (via constraints, relationships)

- Simplifying integration with apps (clear table definitions)

``````````````````````- Supporting scalability and future maintenance

- Allowing reuse across different front-ends or projects

_________________


👥 CREATED BY:
Albo, Lex Randal B.
Ballesteros, Zyra
Quijaro, Earl Leobert

