## 📖 Project Overview

# 📚 Library Management System

A full-featured backend-only Library Management System built with **Java**, **Spring Boot**, and a **relational database**. The system supports user roles, secure authentication, borrowing transactions, and rich book metadata management.

---

## 🚀 Features

- 🛡️ **Role-based Access Control**: ADMIN, LIBRARIAN, STAFF, MEMBER
- 🔐 **JWT Authentication** with secure password storage
- 📧 **Email Verification** using MailDev
- 📚 **Book Management** with authors, categories, publishers
- 📖 **Borrowing & Returning Books** with status tracking
- 🗂️ Hierarchical category support
- 🛠️ Admin tools for role assignment and user management
- 📝 SQL sample data and schema
- 📩 Activity logging and token-based auth

---

## 📑 Roles & Permissions

| Role       | Permissions                                                                 |
|------------|------------------------------------------------------------------------------|
| `ADMIN`    | Full access: manage users, roles, books, authors, categories, publishers     |
| `LIBRARIAN`| Manage books, authors, categories, publishers, and borrowing transactions     |
| `STAFF`    | View & return any book, view all transactions                                 |
| `MEMBER`   | View books, borrow/return his books                   |

---

## 📘 Book Borrowing Logic

- A member can **borrow a book** only if it's not already borrowed.
- A transaction has statuses: `BORROWED`, `RETURNED`, `OVERDUE`.
- Only `ADMIN`, `LIBRARIAN`, or `STAFF` can return books on behalf of others.
- Members can return only **their own** borrowed books.

---

## 🧾 Authentication & Account Verification

### ✅ JWT Authentication

- Secure login via `/api/v1/auth/authenticate` issues a **JWT token**
- Protected endpoints require:

---

### 📧 Email Verification** using MailDev

- New users must verify their email via a **token link** before being able to log in.
- After registration:
- A token is sent via email to `/api/v1/auth/activate-account?token=...`
- The account becomes active once the user clicks the verification link.
  ![image](https://github.com/user-attachments/assets/938a90ae-9cec-4ae4-8035-30c026c763db)
   Start MailDev with:
 ```bash
 npx maildev
```
Access the MailDev UI at http://localhost:1080.

---


## 🔁 Borrowing & Returning Logic

- A book **can only be borrowed if not already borrowed**.
- Borrow duration: **2 weeks**
- Book status tracked using:
  - `BORROWED`
  - `RETURNED`
  - `OVERDUE` (calculated based on due date)
- Only the **borrower**, **staff**, **librarian**, or **admin** can return a book.
- Borrowing transactions are saved with:
  - `borrowDate`
  - `dueDate`
  - `returnDate` (if returned)
  - `status`

---

## 🗂 ERD Diagram Overview

![ERD Diagram](![Screenshot 2025-07-02 222614](https://github.com/user-attachments/assets/3fa4c6b0-4f3b-4991-b041-f3bbbec90bb2)
)

### 🔑 Key Entities

- **Book**
  - Related to one **Publisher**
  - Belongs to one or more **Categories**
  - Linked to one or more **Authors**

- **User**
  - Has one or more **Roles**
  - Can be a **borrower** in `BorrowingTransaction`

- **BorrowingTransaction**
  - Links a **User** and a **Book**
  - Tracks borrowing, returning, and status

- **Role**
  - Assigned to system users to define permissions

- **Author / Category / Publisher**
  - Manageable metadata associated with books

---

### Note 

📌 Default Admin Credentials:
- Email: admin@library.com
- Password: passwordadmin  (⚠️ change after first login)
- For SQL Script with sample data check sqlscript.txt
- For Postman Collection check test collection.json




