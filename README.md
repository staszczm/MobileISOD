<div align="center">
  <img src="https://www.ee.pw.edu.pl/wp-content/uploads/2016/11/WE-znak.png" />
</div>

<h1 align="center">MobileISOD</h1>

**MobileIsod** is an Android application developed in Kotlin and Java, using the Gradle build system. The application is designed to provide notifications from the [ISOD portal](https://isod.ee.pw.edu.pl) of the Warsaw University of Technology Electrical Engineering faculty.

## Preview
<div display=flex">
  <img src="screenshots/login.svg />
  <img src="screenshots/main.svg />
  <img src="screenshots/schedule.svg />
</div>
## Features

- User authentication with username and API key
- Navigation between different fragments
- Fetching and displaying data from the ISOD portal

## Demo

TODO

## Setup

1. Set the <code>JAVA_HOME</code> and <code>ANDROID_HOME</code> environment variables to point to the JDK and Android SDK directories respectively.
2. Open a terminal or command prompt.
3. Clone the repository
```bash
$ git clone https://github.com/staszczm/MobileISOD
```
4. Navigate into the cloned repository:
```bash
$ cd MobileISOD
```
5. Build the project using Gradle:
```bash
./gradlew assembleDebug
```
The above command will create an APK file in the <code>app/build/outputs/apk/debug</code> directory. You can install this APK file on an Android device or emulator to run the application.

## Requirements

* [**Java SDK** (preferably 17)](https://www.oracle.com/pl/java/technologies/downloads/#java17)
- **Gradle**
- **Android SDK**


## Roadmap

**LoginScreen**
- [x] UI/UX login screen
- [x] Back-end for login screen
    - [x] Read API key
**MainScreen**
- [x] News and announcements
- [ ] 3 upcoming classes

**SettingsScreen**
- [ ] Change to dark/light theme
- [ ] Choose which type of announcements you want to receive

**Back-end**
- [ ] Sending notifications
- [ ] Make a system that validates Your API key

## License

This project is licensed under the MIT License.
