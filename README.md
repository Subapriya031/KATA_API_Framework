Hotel Booking API Automation Framework
***************************************************************************************************************                       

A Cucumber + RestAssured based test automation framework for testing the Hotel Booking REST API.  
Features include token-based authentication, JSON schema validation, reusable utilities, dynamic payload generation with POJOs, and Extent Report integration.

********************************************************************************************************
*********************************************************************************************************
## Tech Stack

- **Java 17+**
- **RestAssured** – API automation
- **Cucumber (BDD)** – Behavior-driven development
- **JUnit** – Test execution
- **Extent Reports** – HTML reporting
- **Maven** – Build & dependency management
- **Faker** – Dynamic test data generation
- **JSON Schema Validator** – Response schema validation

******************************************************************************************************
******************************************************************************************************
## Getting Started (First Time Setup)

### 1. Clone the repository

```bash
git clone https://github.com/Subapriya031/KATA_API_Framework.git

### 2. Install Java & Maven

Verify installed versions:
java -version    # Java 17+ recommended
mvn -v           # Maven 3.6+

If missing, download & install:

Java JDK,Maven
Set environment variables JAVA_HOME and MAVEN_HOME.


### 3. Open in IDE
Import as Maven project:

IntelliJ: File → Open → Select project folder


### 4. Download dependencies
Run in project root:

mvn clean install

### 5. Configure environment (optional)
Edit src/test/resources/Config.properties to update environment-specific values such as base URLs, credentials, etc.

### 6. Run tests
mvn test

### 7. View reports
HTML Report: target/cucumber-reports.html

Open in any browser after test execution.

**************************************************************************************************
**************************************************************************************************

#Project Structure
API_Testing_Kata
│
├── src/
│   └── test/
│       ├── java/
│       │   ├── hooks/           # Cucumber hooks (auth, logging)
│       │   ├── POJO/            # POJOs for request payloads
│       │   ├── stepdefinitions/ # Step definition implementations
│       │   └── utils/           # Utility classes (APIUtils, ConfigManager, etc.)
│       │
│       └── resources/
│           ├── features/        # Feature files
│           ├── Config.properties # Configuration file (URLs, credentials)
│
├── pom.xml                     # Maven dependencies
└── README.md                   # Project documentation (this file)
******************************************************************************************************
******************************************************************************************************

#Sample Scenarios Covered

✅ Create booking (valid & invalid)

✅ Retrieve booking by Booking ID / Room ID

✅ Negative test cases

✅ Request & response logging

******************************************************************************************************
******************************************************************************************************s
#Report Location

After test execution:

Cucumber HTML & JSON reports: target/
Open cucumber-reports.html in your browser to view detailed reports.

********************************************************************************************************
********************************************************************************************************
#Reusability Highlights

APIUtils: Wrapper for RestAssured HTTP methods

TokenManager: Handles authentication tokens

BookingPayload: POJO builder for request payloads

Hooks: Setup and teardown hooks (auth, logging, request specs)

TestContext: Share data between steps


Contact / Support
For questions or issues, please contact:
Subapriya

