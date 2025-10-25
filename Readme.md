# *MQ Planner*

## **Project Specification & Overview**

--- 

MQPlanner is an application created while keeping in mind the things an effective student productivity application
should be having.One can have a separate CLI Application which is having the functionalities of storing Subjects ,
Sessions Created which we create while we study to log in time and finally a Task Storage functionality which keeps
Tasks in check.

---

## **Functional Requirements**

### **1. Subject Storage**

- **Comprehensive Subject Representation**
    - Stores essential subject details including **Subject ID**, **Name**, **Study Duration**, and **Prerequisites**.
    - Maintains a list of tasks linked to each subject (initially empty or `null`).

- **User-Friendly Functionalities**
    - Provides well-defined methods to interact with subject data efficiently.
    - Enhances user experience by offering smart handling of subjects, prerequisites, and associated tasks.


### **2. Study Sessions**

- **Complete Record Tracking**
    - Keeps detailed records of study activities, including **Subject References**, **Study Duration (in minutes)**, **Descriptions**, and **Session Dates**.
    - Ensures long-term tracking of effort, helping users monitor their consistency and progress.

- **Structured and Informative Data Storage**
    - Each session acts as a timestamped record of learning, providing insight into how much time was invested per subject or task.
    - Supports future analytics on productivity and study patterns.


### **3. Task Service**

- **Priority and Status Enabled Service**
    - Stores detailed descriptions about data, reminders, due dates, subject affiliations, and priority/status values.

- **Rich Methods for Easy User Experience**
    - Practical methods defined so that users may filter tasks according to reminders, time, due dates, and subjects.
    - Includes a **Due Reminder Function** that alerts users about pending or overdue tasks.


### **4. File Storage**

- **Persistence Enabled**
  - Persistence is enabled via FileWriter Utility and we are storing data into .csv files to have easy access to share data to excel or any other 3rd party Application
  - Utility Class File Storage saves the data to file stored inside the data folder in the src and fetches from the same.

### **5. Unit Testing**

- **Junit For Testing**
  - Junit has been used to test several of the functions defined in the Service Class which makes our application reliable and Tolerant
  - All the Tests have been covered inside the test folder , with defined **Java Doc** comments to showcase which method is for what Purpose.
  
---

## **Non Functional Requirements**

### **1. Persistence & Extensions**
- All functions present store the data into .csv files defined under the data folder.
- This ensures the compatibility with various application like Mircosoft's Excel and Google sheets.
- All file storage methods enable the user to basically do CRUD Operations.

### **2. Performance and Scaling**
- The application as it's a small workload application functions without any latency and Thread Issues.
- The dependencies used are compatible and have no Vulnerability at this point of this time.
- The application can be scaled easily as it's .jar files can be easily be used on other computers.

### **3. Maintainability and Modularity**
- The code base is maintainable with define java doc comments which makes it easier to work on the project even after a long time.
- Modularity has been kept in mind while working on the project with defined classes like app , models , services , test & Utils

### **4.Error Handling and Validation**
- **Input Validation:**
    - The system checks for **valid input values**.
    - Provides **suggestions and alerts** when invalid entries are detected.
    - Informative **error messages** guide users to correct input mistakes without delay.

### **5. Documentation and Usability**
- Includes **developer documentation** detailing application usage and code structure.
- Although no GUI is required, the application ensures **clear and intuitive command-line interactions**.
- **Clear Readme.md file** for Project Overview and Explanation


---

## **Technical Specification**

### **1. Programming Language and Environment**

- **Language**
  - Developed using **Java-23 Valhalla**

- **Development Tools**
  - Utilizes Intellij IDE for overall development and dependency handling.
  - **JUnit** framework employed for testing purposes.

### **2. Data Structures**

- **User Defined Data Structure**
  - Wide range of Data Structures has been used in the Application ranging from ArrayList to Recursive Data Structures
  - Objects of defined Classes are also used as a Data Structure such as **StudySessions,Tasks and Subjects**.

- **Collections Class**
  - Internal Data Structures such as ArrayList or List has been used in the Application 


### **3. Testing and QA ( Quality Assurance )**

- **Unit Testing Using JUnit**
  - Extensive Unit testing has been done via JUnit-5 for few of the services
  - Proper usage of Annotations and javadoc comments

- **Integration Testing**
  - Successful Integration of various components and testing via rigorous usage.
  - File Management & CSV Storage has been checked too.

### **4. Documentation**

- **Code Documentation**
  - Application Code has been documented with each method having **Java Doc comments** keeping maintainability and clarity in mind.
  - All the code base has been **kept under defined packages** which are being imported wherever there's a need.

- **Developer Guide**
  -Developers working on the project can **follow up with the Readme.md** to understand the working of the project.


---


## **Project Milestone**

### **Milestone 1 - Core Code and Requirement Analysis**
- **Duration 1 Week**
  - Did a requirement analysis for the assignment.
  - Worked on core components like the Task , session and subjects file

### **Milestone 2 - CLI and Folder Structure**
- **Duration 1 Week**
  - After creating core functionality divided the whole code base into separate Classes and Packages
  - Created the CLI Classes for all the core entities.

### **Milestone 3 - Utilary Classes**
- **Duration 1 Week**
  - Utilary classes are the ones which help in the overall functioning of the application
  - Created Utilary class named file storage to implement persistence via '.csv' files.
  
### **Milestone 4 - Unit Testing & Documentation**
- **Duration 1 Week**
  - Implement testing via Junit-5 for major methods in the services package
  - Created a readme.md file for clear documentation
  - Learnt markup using online articles such as "https://www.markdownguide.org/cheat-sheet/".


---


## Sample CSV


### **Subjects:**

Mathematics
Physics
Chemistry
Computer Science

```csv
Type,ID,Subject,Task Name,Session Date,Duration (Minutes),Priority,Status,Due Date,Reminder,Description
Subject,101,Mathematics,,,,,,,"Study of numbers, formulas, and problem solving"
Subject,102,Physics,,,,,,,"Understanding laws of motion, energy, and matter"
Subject,103,Chemistry,,,,,,,"Exploration of chemical reactions and bonding"
Subject,104,Computer Science,,,,,,,"Algorithms, data structures, and software systems"
Task,201,Mathematics,Complete Algebra Assignment,,,HIGH,PENDING,2025-10-20,true,"Assignment covering Algebraic equations"
Task,202,Physics,Revise Kinematics Notes,,,MEDIUM,COMPLETED,2025-10-22,false,"Summarize formulas and motion equations"
Task,203,Chemistry,Prepare for Organic Chemistry Quiz,,,HIGH,PENDING,2025-10-23,true,"Revision for organic compounds and reactions"
Task,204,Computer Science,Finish Data Structures Lab,,,HIGH,PENDING,2025-10-25,true,"Implement LinkedList and test CRUD operations"
Task,205,Mathematics,Practice Integration Problems,,,LOW,PENDING,2025-10-27,false,"Daily integration problem-solving session"
Session,,Mathematics,,2025-10-10,90,,,,,"Practiced Algebra problems and revised formulas"
Session,,Physics,,2025-10-11,60,,,,,"Revised Newton's laws and solved related problems"
Session,,Chemistry,,2025-10-12,75,,,,,"Reviewed Organic Chemistry notes"
Session,,Computer Science,,2025-10-13,120,,,,,"Implemented LinkedList in Java project"
Session,,Mathematics,,2025-10-14,45,,,,,"Attempted integration questions"
Session,,Computer Science,,2025-10-15,90,,,,,"Debugged file storage class and tested persistence"
```

---

### **Explanation of Data**

- **Mathematics:**
- Tasks:
  - Complete Algebra Assignment — due 2025-10-20 (High Priority). 
  - Practice Integration Problems — due 2025-10-27 (Low Priority). 
-Sessions:
  - Studied on 2025-10-10 for 90 minutes — revised Algebra.
  - Studied on 2025-10-14 for 45 minutes — focused on Integration.

- **Physics:**
- **Tasks:**
  - Revise Kinematics Notes — completed, due 2025-10-22.

- **Sessions:**
  - Studied on 2025-10-11 for 60 minutes — revised Newton’s laws.


--- 

- **Chemistry:**

- **Tasks:**
  - Prepare for Organic Chemistry Quiz — due 2025-10-23.

- **Sessions:**
  - Studied on 2025-10-12 for 75 minutes — reviewed Organic Chemistry.

--- 

**Computer Science:**

- **Tasks:**
  - Finish Data Structures Lab — due 2025-10-25.

**Sessions:**
  - Studied on 2025-10-13 for 120 minutes — implemented LinkedList.
  - Studied on 2025-10-15 for 90 minutes — debugged FileStorage persistence.

---

### Usage of Sample CSV

- **Importing into Spreadsheet Applications:**

  - The CSV file can be directly imported into tools like Microsoft Excel or Google Sheets for analysis or presentation.
  - Each row represents either a Subject, Task, or Session.

- **Personalization:**

  - Students can adjust task deadlines, session durations, or add new subjects as they progress through their semester.
  - The Study Planner will automatically update and persist this data in the /data directory.

- **Progress Tracking:**

  - Enables users to see how much time they are dedicating to each subject and which tasks are still pending.
  - Useful for generating insights like total hours studied per subject or completion rate of tasks.

- **Time Management:**
  - Provides a clear and organized representation of academic workload, making it easier for students to maintain consistent study habits and deadlines.

---


## **Conclusion**

MQPlanner is a tool for enhancing productivity amongst students and Can have multiple use-case. It's designed to be robust
and modular making it easier for anyone working later on easier and explainable. With better tracking of time and schedules
and your Tasks not only one can prepare for academia in an efficient way but also learn the art of time management.

---


## **Next Steps**

- **Implementing Report Generator**
  - To ensure record generation after every time period a User Report can be generated which will be stored in archive.
  - Report contains all the data stored about Subjects , the tasks compleeted and the sessions one has completed.
  
- **Implementing Multi-User and Multi-Facet System**
  - Currently, it's tied only to Academia , in future the application should move forward towards personalised use-case.
  - When Multi-User application is built , issues regarding storage and performance might arise so we can get hands on that too.
  
- **Turning towards GUI**
  - As web based application provides a lot more UI/UX elements and it's more easily accessed and has cross-platform compatibility.
  - We can also look forward to using JavaFX for desktop GUI elements.

---