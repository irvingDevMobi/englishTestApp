# English Test App

Basic KMP app that uses OpenAi Api to get a ser a questions to assign you an English Level.

# How to Test Android App
- On an Android device or Android Emulator, download /releases/composeApp-debug_alpha.apk file
- Open file and allow Unknown sources installation permission for the app.
- After install the apk, open the English Test App.
- In the input field, paste a valid API KEY

# Requirements to run the project

- Android Studio
- Create a Android Emulator with Android Studio or use real device
- Open AI API KEY
- Xcode in case you want to run the iOS version

## How to run the project

- Import the project with Android Studio
- Run the project



This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


