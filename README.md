# BankSystem

A small Java Project about banking systems. 
### Topics:
 - Overview
 - Requirements
 - Execution

# Overview
 
This reposiry contains the implementation of basic functionalities for a banking system in ***Java*** .
This documentatios helps you run the program.
# Requirements

 -  **Java 8+ (JDK)**
# Execution

1. Clone the repository on your machine
2. Open on your IDE 
3. Run from Program.java

# Class Diagram
``` mermaid
---
config:
  layout: elk
  look: neo
  theme: redux
  class:
    hideEmptyMembersBox: false
---
classDiagram
direction LR
    class Menu {
	    -accountService: AccountService
	    +start()
	    +showMenu()
    }

    class AccountService {
	    -exportService: ExportService
	    -transactionService: TransactionService
	    -accountRepository: AccountRepository
	    -userRepository: UserRepository
	    +deposit()
	    +withdraw()
	    +transfer()
    }

    class ExportService {
	    +exportCSV()
    }

    class TransactionService {
	    -transactions : List~Transaction~
	    +register()
	    +list()
    }

    class Transaction {
	    -dateTime: LocalDateTime
	    -type: String
	    -amount: Double
	    -originAccount: String
	    -destinationAccount: String
    }

    class AccountRepository {
	    -accounts: Map~String, BankAccount~
	    +addAccount()
	    +findAccount()
	    +listAccounts()
    }

    class UserRepository {
	    -users: Map~String, User~
	    +addUser()
	    +findUser()
    }

    class User {
	    -name: String
	    -cpf: String
	    -accounts: List~BankAccount~
    }

    class BankAccount {
	    #accountNumber: String
	    #user: User
	    #balance: Double
	    +deposit(amount: Double)
	    +withdraw(amount: Double)
    }

    class SavingsAccount {
	    +deposit(amount: Double)
	    +withdraw(amount: Double)
    }

    class CheckingAccount {
	    -overdraft: Double
	    +deposit(amount: Double)
	    +withdraw(amount: Double)
    }

    class Program {
	    ~menu: Menu
	    +main()
    }

	<<abstract>> BankAccount

    BankAccount <|-- SavingsAccount
    BankAccount <|-- CheckingAccount
    Program --> Menu : start
    Menu --> AccountService : operate
    User "1" --> "0..*" BankAccount : owns account
    AccountRepository --> BankAccount : stores
    UserRepository --> User : stores
    TransactionService --> Transaction : uses data
    AccountService --> AccountRepository
    AccountService --> TransactionService
    AccountService --> BankAccount
    AccountService --> ExportService : requests
    ExportService --> TransactionService : uses data from Transaction
    AccountService -- UserRepository
```

# Class Sequence
``` mermaid
sequenceDiagram
    participant User as User
    participant Menu as Menu
    participant AccountService as AccountService
    participant AccountRepository as AccountRepository
    participant BankAccount as BankAccount
    participant UserRepository as UserRepository
    participant P1 as exportService
    participant P2 as transacaoService
    participant P3 as transaction

    User->>Menu: Select "Create Account"
    Menu->>BankAccount: Requests Account Creation
    BankAccount->>User: Requests User info (Name, CPF, etc.)
    User-->>BankAccount: Sends information
    BankAccount->>UserRepository: Verify User
    UserRepository->>BankAccount: Creates account based on Account Type
    BankAccount->>AccountRepository: Saves Account
    AccountRepository-->>Menu: Confirms Account creation
    Menu->>UserRepository: Saves User info
    Menu-->>User: Returns Success
    User->>Menu: Select Operation
    Menu->>AccountService: Performs the selected operation
    AccountService->>P2: Saves Operation Info
    AccountService-->>Menu: Returns Success
    User->>Menu: Select "List Accounts"
    AccountRepository->>User: Requests Account info
    User-->>AccountRepository: Sends Account information
    AccountRepository-->>Menu: Returns listed accounts
    User->>Menu: Select "Check Balance
    Menu->>AccountService: Requests Verification
    AccountService->>User: Requests Account Number
    User-->>AccountService: Sends Account Number
    AccountService->>AccountRepository: Verify info
    AccountRepository->>Menu: Returns balance
    User->>Menu: Select "Export CSV"
    Menu->>P1: Requests info
    P1->>P2: Requests info
    P2->>P3: Requests info
    P3->>P2: Returns info
    P2-->>P1: Returns transaction history
    P1-->>User: Saves file on user machine
```
