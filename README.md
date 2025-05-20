# RailwayReservationTest

Automated regression testing for a railway reservation web application using Selenium WebDriver and Java, with Excel-driven test data and advanced reporting features.

## Features
- **Automatic Screenshot on Failure:** Only failed test cases generate screenshots, saved with timestamps for traceability in `src/screenshots/`.
- **Test Summary Report:** At the end of each run, a summary (total, passed, failed, total time) is printed and saved to `src/test_summary.txt`.
- **Timestamped Screenshots:** Screenshots are saved with a timestamp in the filename.
- **Input Field Highlighting:** Input fields are visually highlighted before data entry for clarity in demos and screenshots.
- **Execution Time Logging:** Each test and the total suite execution time are logged.
- **Excel Result Color Coding:** Test results are written back to the Excel file, with green for pass and red for fail (using Apache POI).

## Project Structure
- `RailwayReservationTest/src/`
  - `LoginTest.java` — Main test runner
  - `ExcelReader.java` — Excel utility for reading/writing test data and results
  - `screenshots/` — (auto-created) Stores screenshots of failed tests
  - `test_summary.txt` — Test summary output
  - `sourceanddest.xlsx` — Test data Excel file (see below for format)
- `poi-lib/` — Apache POI libraries
- `selenium-java-4.32.0/` — Selenium Java libraries
- `chromedriver-mac-arm64/` — ChromeDriver binary (macOS ARM64)

## Prerequisites
- Java 11 or later
- Google Chrome browser
- ChromeDriver (already included)
- All required JARs (see classpath in run instructions)

## Excel Test Data Format
Your `sourceanddest.xlsx` should have the following columns (example):

| Test Case ID | Test Desc | Pre-conditions | Test Steps | Test Data |  |  | Expected Result | Post-conditions | Actual Result | Status (Pass/Fail) |
|--------------|-----------|----------------|------------|-----------|----|----|----------------|-----------------|---------------|--------------------|
|              |           |                |            | from      | to |    |                |                 |               |                    |
| TS_01        | ...       | ...            | ...        | NDLS      | BCT|    | ...            | ...             | ...           | Pass               |

- The script reads the `from` and `to` values from columns 4 and 5 (zero-based index) starting from the second data row (skipping headers).

## How to Run

1. **Unzip POI libraries (if not already unzipped):**
   ```zsh
   unzip poi-bin-5.2.3-20220909.zip -d poi-lib
   ```

2. **Compile:**
   ```zsh
   cd RailwayReservationTest/src
   javac -cp ".:../poi-lib/poi-bin-5.2.3/*:../selenium-java-4.32.0/*:../log4j-core-2.20.0.jar:../commons-compress-1.23.0.jar:../xmlbeans-5.1.1.jar" *.java
   ```

3. **Run:**
   ```zsh
   java -cp ".:../poi-lib/poi-bin-5.2.3/*:../selenium-java-4.32.0/*:../log4j-core-2.20.0.jar:../commons-compress-1.23.0.jar:../xmlbeans-5.1.1.jar" LoginTest
   ```

4. **If you get a security popup for chromedriver:**
   ```zsh
   xattr -d com.apple.quarantine ../chromedriver-mac-arm64/chromedriver
   chmod +x ../chromedriver-mac-arm64/chromedriver
   ```

5. **Check Results:**
   - Screenshots: `src/screenshots/`
   - Test summary: `src/test_summary.txt`
   - Excel results: `src/sourceanddest.xlsx`

## Quick Start for New Contributors

If you're new to this repo, follow these steps to get up and running:

1. **Clone the repository:**
   ```zsh
   git clone <repo-url>
   cd <repo-folder>
   ```

2. **Install Java (if not already installed):**
   Ensure you have Java 11 or later:
   ```zsh
   java -version
   ```

3. **Unzip POI libraries (if not already unzipped):**
   ```zsh
   unzip poi-bin-5.2.3-20220909.zip -d poi-lib
   ```

4. **Check/prepare your test data:**
   - Edit or add `RailwayReservationTest/src/sourceanddest.xlsx` as per the format described above.

5. **Compile the project:**
   ```zsh
   cd RailwayReservationTest/src
   javac -cp ".:../poi-lib/poi-bin-5.2.3/*:../selenium-java-4.32.0/*:../log4j-core-2.20.0.jar:../commons-compress-1.23.0.jar:../xmlbeans-5.1.1.jar" *.java
   ```

6. **Run the tests:**
   ```zsh
   java -cp ".:../poi-lib/poi-bin-5.2.3/*:../selenium-java-4.32.0/*:../log4j-core-2.20.0.jar:../commons-compress-1.23.0.jar:../xmlbeans-5.1.1.jar" LoginTest
   ```

7. **(macOS only) If you get a security popup for chromedriver:**
   ```zsh
   xattr -d com.apple.quarantine ../chromedriver-mac-arm64/chromedriver
   chmod +x ../chromedriver-mac-arm64/chromedriver
   ```

8. **Check your results:**
   - Screenshots: `RailwayReservationTest/src/screenshots/`
   - Test summary: `RailwayReservationTest/src/test_summary.txt`
   - Excel results: `RailwayReservationTest/src/sourceanddest.xlsx`

---

## Notes
- Update the path to ChromeDriver in `LoginTest.java` if your environment differs.
- Only non-header, non-empty rows are processed from Excel.
- All dependencies are included in the repo.

## .gitignore
The following are already gitignored:
- `RailwayReservationTest/src/screenshots/`
- `RailwayReservationTest/src/test_summary.txt`
- `RailwayReservationTest/src/sourceanddest.xlsx` (optional, for privacy)
- `*.class`, `.DS_Store`

