# Online Quiz App (Mini Project)

A simple console-based quiz application that demonstrates the use of **control flow**, **collections**, and **logic** in Java. This mini project loads questions using JDBC from a MySQL database, interacts with the user using Java Swing, accepts their answers, evaluates the score, and finally stores the results back into the database.

---

## 🎯 Objective

- Build a console-based quiz system using core Java concepts.
- Apply **loops**, **logic**, and **collections**.
- Use **JDBC** for database interaction.
- Use **Swing** (GUI) for user interaction.

---

## ✨ Features

- Read quiz questions dynamically from MySQL database.
- Show questions and options through a Swing-based graphical interface.
- Accept answers and evaluate score internally using Java logic.
- Store user name and result back into database.
- Easy to extend with more questions or GUI features.

---

## 🔧 Technologies Used

| Technology | Purpose                          |
|-----------|----------------------------------|
| Java      | Core application logic and Swing |
| Swing     | User Interface                   |
| JDBC      | Database connectivity            |
| MySQL     | Storing questions and results    |           |

---

## 🗃️ Database Structure

### Database Name: `quizdb`

```sql
CREATE TABLE questions (
  id INT PRIMARY KEY,
  question VARCHAR(255),
  opt1 VARCHAR(255),
  opt2 VARCHAR(255),
  opt3 VARCHAR(255),
  opt4 VARCHAR(255),
  answerIndex INT
);

CREATE TABLE result (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50),
  score INT
);
