# RailwayReservationTest

This project automates the testing of a railway reservation web application using Selenium WebDriver and Java. It includes features for robust reporting, Excel integration, and enhanced test visibility.

## Features
- **Automatic Screenshot on Failure:** Only failed test cases generate screenshots, saved with timestamps for traceability.
- **Test Summary Report:** At the end of each run, a summary (total, passed, failed, total time) is printed and saved to `src/test_summary.txt`.
- **Timestamped Screenshots:** Screenshots are saved in `src/screenshots/` with a timestamp in the filename.
- **Input Field Highlighting:** Input fields are visually highlighted before data entry for clarity in demos and screenshots.
- **Execution Time Logging:** Each test and the total suite execution time are logged.
- **Excel Result Color Coding:** Test results are written back to the Excel file, with green for pass and red for fail (using Apache POI).

## Project Structure
- `RailwayReservationTest/src/`
  - `LoginTest.java` — Main test runner
  - `ExcelReader.java` — Excel utility for reading/writing test data and results
  - `screenshots/` — (auto-created) Stores screenshots of failed tests
  - `test_summary.txt` — Test summary output
  - `sourceanddest.xlsx` — Test data Excel file (update as needed)
- `poi-lib/` — Apache POI libraries
- `selenium-java-4.32.0/` — Selenium Java libraries

## Prerequisites
- Java 11 or later
- Chrome browser
- ChromeDriver (already included in `chromedriver-mac-arm64/`)
- All required JARs (see classpath in run instructions)

## How to Run
1. **Compile:**
   ```zsh
   javac -cp ".:../poi-lib/poi-bin-5.2.3/*:../selenium-java-4.32.0/*:../log4j-core-2.20.0.jar:../commons-compress-1.23.0.jar:../xmlbeans-5.1.1.jar" *.java
   ```
   (Run from `RailwayReservationTest/src/`)

2. **Run:**
   ```zsh
   java -cp ".:../poi-lib/poi-bin-5.2.3/*:../selenium-java-4.32.0/*:../log4j-core-2.20.0.jar:../commons-compress-1.23.0.jar:../xmlbeans-5.1.1.jar" LoginTest
   ```

3. **Check Results:**
   - Screenshots: `src/screenshots/`
   - Test summary: `src/test_summary.txt`
   - Excel results: `src/sourceanddest.xlsx`

## Notes
- Update the path to ChromeDriver in `LoginTest.java` if your environment differs.
- The Excel file should have 'from' and 'to' station columns at the correct indexes (see code for details).
- Only non-header, non-empty rows are processed.

## Contributing
- Please add new features or bugfixes via pull requests.

## License
MIT License
