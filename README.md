
Allure report

After successful test execution:

Generate Allure Report: 
$allure generate target/allure-results --clean

Serve Allure Report:
$allure serve target/allure-results

**alternative**
Generate and Serve Allure Report:

$mvn allure:report
$mvn allure:serve




********************************************************

**Upgrade appium** >> npm i -g appium@latest

**Upgrade uiautomator2** >> appium driver update uiautomator2

example: driver uiautomator2 updated: 3.5.2 => 3.7.9
         driver flutter updated: 2.6.0 => 2.9.2

Installation Guide:

set environment variables and set path
[
Android Sdk!
java Sdk!
maven!
appium inspector
bundletool (optional) etc
]

run this in terminal to check if emulator device is setup or not
>>%ANDROID_HOME%\platform-tools\adb devices
(in this pc "C:\Users\TalentPro\AppData\Local\Android\Sdk\platform-tools\adb devices")

check driver command
>>appium driver doctor uiautomator2

my machine NodeExePath
>>C:\Program Files\nodejs\

my machine NodeMainJsPath [ make sure it is the the one "\appium\build\lib"]
>>C:\Users\TalentPro\AppData\Roaming\npm\node_modules\appium\build\lib

$ appium driver list
✔ Listing available drivers
- uiautomator2@2.42.1 [installed (npm)]
- xcuitest [not installed]
- mac2 [not installed]
- espresso [not installed]
- safari [not installed]
- gecko [not installed]
- chromium [not installed]

drivers update command
>>appium driver list
>>appium driver list --updates

appium-doctor
>>npm install -g appium-doctor
>>appium-doctor --version

download & install Appium Inspector
>>https://github.com/appium/appium-inspector/releases
>>https://inspector.appiumpro.com/

Env setup for Android
note: AppData is hidden folder

Directly add into path
>>C:\Users\TalentPro\AppData\Local\Android\Sdk\build-tools
>>C:\Users\TalentPro\AppData\Local\Android\Sdk\platform-tools
>>C:\Users\TalentPro\AppData\Local\Android\Sdk\tools
>>C:\Users\TalentPro\AppData\Local\Android\Sdk\platforms

To check setup for Android
>>adb

make shortcut for Android-studio windows-batch-files
>>C:\Program Files\Android\Android Studio\bin

example:
Created device 1
>>Pixel 5 API 33 (6 inch)
>>Android 13.0x86_64

Created device 2
>>Pixel 2 API 27
>>Android 8.1 x86

appPackage/appAcƟvity:
>>adb shell dumpsys window windows | grep mFocusedApp

appium:appPackage
>>build.gradle

appium:appActivity
>>app/src/main/AndroidManifest.xml

driverUpdate:
appium driver update uiautomator2


💥Appium Flutter Driver using Appium Java Client💦

[medium]
🔗guidelink: https://medium.com/@amaralisa321/automating-flutter-apps-with-appium-flutter-driver-using-appium-java-client-5af5e2de7cba

Prerequisites:
👉Have to
✅install Appium versions > 2

Step 1🏹
👉Have to
✅install Appium Flutter Driver
🔗link: https://github.com/appium/appium-flutter-driver
🏃‍♀️run> $ appium driver install --source=npm appium-flutter-driver
🏃‍♀️run> $ appium driver list
🏃‍♀️run> $ appium driver update
**check the example section in the link for useage**

Step 2🏹
👉How to Generate the Jar for Flutter Finder Library
✅install Gradle Build Tool (prerequisite to Flutter Finder Library)
🔗link: https://gradle.org/next-steps/?version=7.5.1&format=bin

click >> releases page
check gradle version for finder-kotlin
🔗link: https://github.com/appium-userland/finder-kotlin/blob/main/gradle/wrapper/gradle-wrapper.properties
select the required gradle (ex: 7.5.1)
click >> binary-only

>--$--$--{🚸OPTIONAL}--$--$--<
while file being downloaded
click >> Verify SHA-256 checksum
download (ex:gradle-7.6.4-bin.zip.sha256)
🏃‍♀️run> $ type gradle-7.6.4-bin.zip.sha256 (** your sha file to generate chechsum **)
[reply example>> 'f6b8596b10cce501591e92f229816aa4046424f3b24d771751b06779d58c8ec4']
local check Sum
🏃‍♀️run> $ certutil -hashfile gradle-7.6.4-bin.zip.sha256
[reply example>> '983a193e4b66b753a98dc8af90fef1b5c1347001']
⚠ check if both value is the same to Verify file integrity
>--$--$--{🚸THE END}--$--$--<

Extract All to a folder (ex: C:\Tools\gradle-7.5.1)
☑ Add bin Directory to the system path (ex: C:\Tools\gradle-7.5.1\bin)
🏃‍♀️run> $ gradle -v
(output should be like "Welcome to Gradle 7.5.1!")

✅install Flutter Finder Library (Inspector)
🔗link: https://github.com/appium-userland/finder-kotlin

clone this repo in a dir folder
🏃‍♀️run> $ git clone https://github.com/appium-userland/finder-kotlin

Open the dir folder with Android Studio
wait for the project to get initialized
🏃‍♀️run> $ gradle clean build
(output should be like "BUILD SUCCESSFUL in 22s... 6 actionable tasks: 5 executed, 1 up-to-date!")
📂Once the build is over. find the JAR file
inside the build directory of the project, i.e., “finder-kotlin\build\libs\appium-flutter-finder-0.0.6.jar”
You can add this jar to your automation project.

Step 3🏹
👉How to Inspect Elements in the Flutter App
Before trying to identify/inspect elements
🔗link: https://docs.flutter.dev/ui#basic-widgets
to get an idea of how the UI is basically built out of Widgets & RenderObject class
Clone this Repo for flutter sample Apps
🔗link: https://github.com/flutter/samples.git
i.e 🔗link: https://github.com/flutter/samples/tree/main/provider_shopper is being used to test

🔒Pre-Requisites:
🔗link: https://www.browserstack.com/docs/app-automate/appium/test-hybrid-apps/test-flutter-apps#preprocess-flutter-app-for-testing

1️⃣Open your Flutter app project.
2️⃣Add the following dev_dependencies in the pubspec.yaml file of your Flutter project:
pubspec.yaml
dev_dependencies:
test: any
flutter_test:
sdk: flutter
flutter_driver:
sdk: flutter

ℹ The flutter_test and flutter_driver libraries provide functions and APIs respectively to write tests for Flutter apps.

3️⃣Run the following command in the command-line/terminal of your Flutter project to install the dev_dependencies added in the previous step:
Command-line
🏃‍♀️run> $ flutter pub get

4️⃣Add the following code statement in the main.dart file of your Flutter project to import the flutter_driver_extension library:
main.dart
👻code> $
import 'package:flutter_driver/driver_extension.dart';

5️⃣Add the enableFlutterDriverExtension() method before the runApp method in main.dart file as shown in the following code snippet:
👻code> $
main.dart
void main() {
enableFlutterDriverExtension();
runApp(const MyApp());
}

ℹ The enableFlutterDriverExtension() method enables the flutter_driver_extension library imported in the previous step. Check out the enableFlutterDriverExtension function section to learn more
Build commands:

iOS	debug	    🏃‍♀️run>  $flutter build ios --debug
profile	    🏃‍♀️run>  $flutter build ios --profile

Android	debug   🏃‍♀️run>  $flutter build apk --debug
profile	    🏃‍♀️run>  $flutter build apk --profile

Open IDE:
1️⃣ IntelliJ IDEA
2️⃣ Create new project
3️⃣ language : java; Build System : Maven; JDK : OpenJDK v 17.0.10
4️⃣ Connect Mobile Device : Android Emulator OR Real Device
5️⃣ added the following dependencies in my pom.xml file
🔑Appium java client: 9.1.0
🔑TestNG: 7.9.0
🔑Kotlin standard library for JVM: 1.3.40
🔑Kotlin multiplatform serialization runtime library: 0.14.0
🔑Selenium-java 4.17.0
6️⃣ And do not forget to add the flutter finder jar to your project -ℹ appium-flutter-finder-0.0.6.jar

Use Appium Inspector?
ℹ We cannot launch the flutter app in Appium inspector with the automation name: “Flutter,”

automation name value= $ “UIAutomator2”     //for android
automation name value= $ “XCUITest”         //for iOS

ℹ Only native elements can be found in the inspector; we will not be able to find flutter-based elements

Allure report*****************

How to use
To install plugin

appium plugin install --source=npm appium-reporter-plugin

Start appium server with plugin

appium --use-plugins=appium-reporter-plugin

After successful test execution:

Generate Allure Report:
$allure generate target/allure-results --clean

Serve Allure Report:
$allure serve target/allure-results

**alternative**
Generate and Serve Allure Report:

$mvn allure:report
$mvn allure:serve

*** Add Sementic Id for Flutter ***

Semantics(
identifier: 'red-box',
label: 'A square red box',
child: InkWell(
onTap: () => launchUrl('https://youtu.be/YlUKcNNmywk?t=147'),
child: Container(
width: 100,
height: 100,
color: Colors.red,
),
),
)

🐸🐸🐸 example:

Semantics(
identifier: '${title} show all',
)


### Environment

- Operating system:

- Appium server version (output of `appium --2.11.3`):

- Appium driver(s) and their version(s) (`appium driver list`):
    - driver uiautomator2 updated: 3.5.2 => 3.7.9
    - driver flutter updated: 2.6.0 => 2.9.2

- Appium plugin(s) and their version(s) (`appium plugin list`):
    - appium-reporter-plugin@1.1.0-beta.06

- Node.js version (output of `node --v20.17.0`):
- `npm` version (output of `npm --10.8.2`):
- Last component(s) version which did _not_ exhibit the problem: - same pc same specs same versions
- Platform and version under test: Windows 11
- Real device or emulator/simulator: Xiaomi Note 10 (2gb)

    RUN APPIUM CMD>               
appium --log-level debug