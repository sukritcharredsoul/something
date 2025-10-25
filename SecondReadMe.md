# MQ Planner

## **What problems does your application solve?**

MQ Planner is designed to help students stay organized and manage their study time effectively. Often, students struggle with keeping track of multiple subjects, deadlines, and study sessions. This application solves these problems by:

- Allowing you to **store all your subjects** with details like study duration and prerequisites.
- **Logging study sessions** with timestamps and descriptions, so you can track how much time you’ve invested in each subject.
- **Managing tasks** with priority levels, due dates, and reminders to make sure nothing gets missed.
- **Persisting all your data** in CSV files so it’s safe, shareable, and easy to analyze in Excel or Google Sheets.

Essentially, it makes studying more structured, helps you stay on top of deadlines, and encourages consistent study habits.

---

## **Structure of the program**

The application is organized in a modular way for maintainability and clarity:

- **app/** – The main application entry point and CLI interface where users interact with the system.
- **models/** – Classes that define the core entities: `Subject`, `Task`, and `StudySession`.
- **services/** – Contains the business logic for managing tasks, subjects, and study sessions.
- **utils/** – Helper classes like `FileStorage` for saving and loading data to csv files and implemented self defined LinkedList task.
- **test/** – Unit tests using JUnit-5 to ensure that methods behave correctly.

Data is stored in a `/data` folder as CSV files. Each row represents a subject, task, or session. The modular setup ensures that adding new features or maintaining the code is simple and intuitive.

---

## **How to run the application**

1. Make sure you have **Java 23** installed on your system.
2. Navigate to the **app/** folder in your terminal.
3. Compile the Java files:
   ```bash
   javac app/Main.java
