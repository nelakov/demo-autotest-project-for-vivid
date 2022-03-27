# Demonstration test automation project for Vivid Money

## :page_with_curl:    Content

> :heavy_check_mark: [Covered functionality](#earth_africa-covered-functionality)
>
> :heavy_check_mark: [Technology stack](#classical_building-technology-stack)
>
> :heavy_check_mark: [Running tests from the terminal](#running-tests-from-the-terminal)
>
> :heavy_check_mark: [Running Tests in Jenkins](#robot-remote-test-running)
>
> :heavy_check_mark: [Test results report in Allure Report](#skier-main-page-of-allure-report)
>
> :heavy_check_mark: [Telegram notifications using a bot](#-telegram-notifications-using-a-bot)
>
> :heavy_check_mark: [An example of running a test in Selenoid](#-an-example-of-running-a-test-in-selenoid)

## :technologist: Covered functionality

> UI autotests developed <code>UI</code>.

### UI

- [x] Open account with random phone number
- [x] Open account with random email
- [x] Invite form should have header text
- [x] Invite form should have subtitle text
- [x] Invite button work is correct
- [x] Invite success message displayed
- [x] Switch tab phone/email work is correct
- [x] Negative tests for validation input field
- [x] No errors in page console log

## :classical_building: Technology stack

<p align="center">
<img width="6%" title="IntelliJ IDEA" src="images/logo/Intelij_IDEA.svg">
<img width="6%" title="Java" src="images/logo/Java.svg">
<img width="6%" title="Selenide" src="images/logo/Selenide.svg">
<img width="6%" title="Selenoid" src="images/logo/Selenoid.svg">
<img width="6%" title="Allure Report" src="images/logo/Allure_Report.svg">
<img width="6%" title="Gradle" src="images/logo/Gradle.svg">
<img width="6%" title="JUnit5" src="images/logo/JUnit5.svg">
<img width="6%" title="GitHub" src="images/logo/GitHub.svg">
<img width="6%" title="Jenkins" src="images/logo/Jenkins.svg">
<img width="6%" title="Telegram" src="images/logo/Telegram.svg">
</p>

In this project, autotests are written in <code>Java</code> using <code>Selenide</code> for UI tests.
>
> <code>Selenoid</code> launches browsers in <code>Docker</code> containers.
>
> <code>Allure Report</code> generates a test run report.
>
> <code>Gradle</code> is used for automated project build.
>
> <code>JUnit 5</code> is used as a unit testing library.
>
> <code>Jenkins</code> runs the tests.
>
> After the run is completed, notifications are sent using the bot to <code>Telegram</code>.

## Running tests from the terminal

### :robot: Running Tests Locally

```
gradle clean test
```

### :robot: Remote test running

## <img src="images/logo/Jenkins.svg" width="25" height="25"  alt="Jenkins"/></a> Jenkins <a target="_blank" href="https://jenkins.autotests.cloud/job/demo-project-for-vivid/"> job </a>

```
clean
test
-Dbrowser=${BROWSER}
-DbrowserVersion=${BROWSER_VERSION}
-DbrowserSize=${BROWSER_SIZE}
-DbrowserMobileView="${BROWSER_MOBILE}"
-DremoteDriverUrl=https://user1:1234@${REMOTE_DRIVER_URL}/wd/hub/
-DvideoStorage=https://${REMOTE_DRIVER_URL}/video/
-Dthreads=${THREADS}
```

### :robot: Build Options

> <code>REMOTE_URL</code> – the address of the remote server where the tests will run.
>
> <code>BROWSER</code> – the browser the tests will be run (_default - <code>chrome</code>_).
>
> <code>BROWSER_VERSION</code> – version of the browser the tests will be run (_default - <code>91.0</code>_).
>
> <code>BROWSER_SIZE</code> – the size of the browser window the tests will be run (_default - <code>1920x1080</code>_).

### :pushpin: Main page of <a target="_blank" href="https://jenkins.autotests.cloud/job/demo-project-for-vivid/8/allure/">Allure-report</a>

<p align="center">
<img title="Allure Overview" src="images/screens/allure_overview.png">
</p>

### :pushpin: Grouping tests by tested functionality

<p align="center">
<img title="Allure Behaviors" src="images/screens/allure_behaviors.png">
</p>

### :pushpin: Main dashboard

<p align="center">
<img title="Allure Overview Dashboard" src="images/screens/allure_overview_dashboard.png">
</p>

## <img width="4%" title="Telegram" src="images/logo/Telegram.svg"> Telegram notifications using a bot

> After the build is completed, a special bot created in <code>Telegram</code> automatically processes and sends a message with a run report.

<p align="center">
<img title="Telegram Notifications" src="images/screens/telegram_notifications.png">
</p>

## <img width="4%" title="Selenoid" src="images/logo/Selenoid.svg"> An example of running a test in Selenoid

> A video is attached to each test in the report. Two of these videos is shown below.

```Positive test```
<p align="center">
  <img title="Selenoid Video" src="images/gif/selenoid_video.gif">
</p>

```Negative test```
<p align="center">
  <img title="Selenoid Video" src="images/gif/selenoid_video_2.gif">
</p>
