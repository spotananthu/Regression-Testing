# Regression Testing - Railway Reservation System

This project demonstrates regression testing automation for an online railway reservation system using Selenium WebDriver and Apache POI (for Excel integration) in Java.

## Features
- Automated login testing using Selenium
- Test data management via Excel (Apache POI)
- Supports regression and boundary value analysis

## Prerequisites
- Java 8 or above
- Google Chrome browser
- Compatible ChromeDriver (already included)
- All dependencies (Selenium, Apache POI, etc.) are included in the repo

## How to Run

1. **Unzip POI libraries (if not already unzipped):**
   ```zsh
   unzip poi-bin-5.2.3-20220909.zip -d poi-lib
   ```

2. **Compile:**
   ```zsh
   javac -cp ".:selenium-java-4.32.0/*:commons-compress-1.23.0.jar:xmlbeans-5.1.1.jar:poi-lib/poi-bin-5.2.3/poi-5.2.3.jar:poi-lib/poi-bin-5.2.3/poi-ooxml-5.2.3.jar:poi-lib/poi-bin-5.2.3/poi-ooxml-full-5.2.3.jar:poi-lib/poi-bin-5.2.3/lib/*" RailwayReservationTest/src/ExcelReader.java RailwayReservationTest/src/LoginTest.java
   ```

3. **Run:**
   ```zsh
   java -cp ".:log4j-core-2.20.0.jar:selenium-java-4.32.0/*:commons-compress-1.23.0.jar:xmlbeans-5.1.1.jar:poi-lib/poi-bin-5.2.3/poi-5.2.3.jar:poi-lib/poi-bin-5.2.3/poi-ooxml-5.2.3.jar:poi-lib/poi-bin-5.2.3/poi-ooxml-full-5.2.3.jar:poi-lib/poi-bin-5.2.3/lib/*:RailwayReservationTest/src" LoginTest
   ```

4. **Make chromedriver executable and allow it if blocked:**
   ```zsh
   xattr -d com.apple.quarantine RailwayReservationTest/chromedriver-mac-arm64/chromedriver
   chmod +x RailwayReservationTest/chromedriver-mac-arm64/chromedriver
   ```
   If you get a security popup, allow it in System Settings > Privacy & Security.

5. **Prepare your test data:**
   - Place `credentials.xlsx` in `RailwayReservationTest/src/`.
   - Format:
     | username | password |
     |----------|----------|
     | user1    | pass1    |
     | user2    | pass2    |

## Project Structure
- `RailwayReservationTest/src/` - Java source files
- `ExcelReader/src/` - Excel reading utility
- `poi-lib/` - Apache POI libraries
- `selenium-java-4.32.0/` - Selenium libraries
- `chromedriver-mac-arm64/` - ChromeDriver binary

## License
MIT
