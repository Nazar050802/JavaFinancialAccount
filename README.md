# Financial Accounting System

![Java](https://img.shields.io/badge/Java-21+-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.8.5-brightgreen.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

The **Financial Accounting System** is a robust Java-based application designed to manage and analyze your financial records effortlessly. Whether you're tracking personal expenses or managing business finances, this system provides comprehensive features to help you stay organized and make informed financial decisions.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
  - [User Login](#user-login)
  - [Main Menu](#main-menu)
  - [Add Record](#add-record)
  - [Show Records](#show-records)
  - [Show Statistics](#show-statistics)
  - [Settings](#settings)
- [Architecture](#architecture)
- [Documentation](#documentation)
  - [Developer Documentation](#developer-documentation)
  - [User Documentation](#user-documentation)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Record Management:** Add, view, and delete financial records with ease.
- **Categorization:** Organize records into categories like Food, Utilities, Entertainment, and more.
- **Statistical Analysis:** Generate yearly, monthly, and daily statistics to visualize your financial health.
- **User Authentication:** Secure login system to protect your financial data.
- **Data Persistence:** Automatically saves data to ensure your records are always up-to-date.
- **Console-Based Interface:** Intuitive Text User Interface (TUI) for seamless navigation and interaction.

## Getting Started

Follow these instructions to set up and run the Financial Accounting System on your local machine.

### Prerequisites

- **Java Development Kit (JDK) 21 or higher:** Ensure Java is installed by running:
  ```bash
  java -version
  ```
  You should see output indicating Java version 21 or above.

- **Maven:** Ensure Maven is installed for building and managing the project.

### Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/Nazar050802/JavaFinAccount.git
   ```

2. **Navigate to the Project Directory:**
   ```bash
   cd JavaFinAccount/Program/JavaFinAccount
   ```

3. **Compile the Code with Maven:**
   ```bash
   mvn clean compile
   ```

### Running the Application

Start the application using Maven:

```bash
mvn exec:java
```

Upon successful launch, you'll be greeted with the login screen to access the main features of the system.

## Usage

### User Login

Access your account or register a new user to manage your financial records.

1. **Login Prompt:**
   - Enter your **username** and **password**.
   - If you're a new user, follow the prompts to register.

2. **Successful Login:**
   - Access the main menu with options to manage records, categories, and view statistics.

3. **Failed Login:**
   - Retry entering your credentials if authentication fails.

### Main Menu

Once logged in, navigate through the main menu to perform various actions:

- **Add Records:** Enter new financial data.
- **Show Records:** View and manage existing records.
- **Show Statistics:** Visualize your financial data.
- **Settings:** Update your account settings.
- **Exit:** Close the application.

### Add Record

Add financial records either individually or in bulk with flexible input methods.

1. **Choose Record Type:**
   - **Multiple Records:** Add several records at once.
   - **Single Record:** Add one record at a time.
   - **Return to Main Menu:** Go back to the main menu.

2. **Select Input Method:**
   - **In One Line:** Enter all details in a single formatted line.
   - **With Help Messages:** Input each detail step-by-step with guidance.

**Example (One Line):**
```
100.00,Lunch,12 03 2024 12 00,Food,Expense
```

### Show Records

View and manage your financial records with various filtering options.

1. **Options:**
   - **Show All Records Sorted by Date**
   - **Show Records by Specific Date**
   - **Show Records by Category**
   - **Delete a Record by ID**
   - **Return to Main Menu**

2. **Pagination Commands:**
   - **n:** Next page
   - **p:** Previous page
   - **b:** Back to the records menu

### Show Statistics

Generate and view financial statistics to understand your financial trends.

1. **Options:**
   - **Yearly Statistics**
   - **Monthly Statistics**
   - **Daily Statistics**
   - **Return to Main Menu**

2. **Visualization:**
   - Graphical representations of income, expenses, and profitability.

### Settings

Manage your account settings for enhanced security and personalization.

1. **Options:**
   - **Change Password:** Update your account password.
   - **Return to Main Menu**

2. **Change Password Process:**
   - Enter current password for verification.
   - Set a new password following security guidelines.

## Architecture

The Financial Accounting System is built using the **Onion Architecture**, ensuring scalability and maintainability. The architecture is divided into four main layers:

1. **Domain Layer:**
   - **Entities:** `Record`, `Category`, `User`, `DateAndTime`
   - **Value Objects:** `StatisticField`

2. **Application Services Layer:**
   - **Services:** `RecordService`, `UserService`, `CategoryService`, `StatisticsService`
   - Mediates between domain and infrastructure, enforcing business rules.

3. **Infrastructure Layer:**
   - **Repositories:** `InMemoryRecordRepository`, `InMemoryCategoryRepository`, `InMemoryUserRepository`
   - **Serialization:** `RecordSerializer`
   - Handles data persistence and storage.

4. **User Interface Layer:**
   - **Text User Interface (TUI):** Controllers for user interaction
   - Provides a console-based interface for seamless navigation.

## Documentation

Comprehensive documentation is available to help both developers and users make the most of the Financial Accounting System.

### Developer Documentation

Access the detailed developer documentation generated with Javadoc:

[Developer Documentation](/Documentation/javadoc/index.html)

### User Documentation

For an in-depth guide on using the application, refer to the user manual:

[User Documentation](/Documentation/User%20Documentation.pdf)

## Contributing

Contributions are welcome! Please follow these steps to contribute:

1. **Fork the Repository**
2. **Create a Feature Branch:**
   ```bash
   git checkout -b feature/YourFeature
   ```
3. **Commit Your Changes:**
   ```bash
   git commit -m "Add your message"
   ```
4. **Push to the Branch:**
   ```bash
   git push origin feature/YourFeature
   ```
5. **Open a Pull Request**

Please ensure your code follows the project's coding standards and includes appropriate tests.

## License

This project is licensed under the [MIT License](LICENSE).

---

*© 2024 Financial Accounting System. All rights reserved.*
