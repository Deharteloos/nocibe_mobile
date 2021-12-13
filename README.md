<div id="top"></div>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
<!--[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]-->



<!-- PROJECT LOGO -->
<br />
<div align="center">

<h3 align="center">Appium Framework</h3>

  <p align="center">
    This is an appium project for the automation of mobile app
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Running the tests</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

### Built With

* [Appium](https://appium.io/)
* [Maven](https://maven.apache.org/)
* [Cucumber](https://cucumber.io/)
* [Gherkin](https://cucumber.io/docs/gherkin/reference/)
* [TestNG](https://testng.org/doc/)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

These instructions will help you set up the project locally and proceed with some tests.

### Prerequisites

* [JDK 8](https://www.oracle.com/java/technologies/downloads/#java8-windows)
* [Android Studio](https://developer.android.com/studio)
* [Node.js](https://nodejs.org/en/download/)
* [Appium](https://appium.io/docs/en/about-appium/getting-started/?lang=en)
* [Maven 3](https://maven.apache.org/install.html)
* [Git](https://git-scm.com/downloads)

### Installation

* Clone the repo
   ```sh
   git clone https://github.com/Deharteloos/appium-testng-cucumber-framework.git
   ```
* Rename the file `config.properties.template` to `config.properties` and update the property `app` according to the location of the app you want to test. Or add the necessary desired capabilities.

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Running the tests

To run all the tests just type `mvn test`

### Override default parameters

You can override the default parameters by specifying yours. Just add some of the followings after `mvn test` command.  
The default parameters are in bold.
* -DonBrowser: if it is a test of a web applications (**true**, false )
* -DplatformName: the platform we will run the test on (iOS, **Android**)
* -DplatformVersion: the version of the platform (**9.0**)
* -DdeviceName: the name of the device on which the test will be run (**Android Emulator**)

```  
mvn test -DonBrowser=false -DdeviceName='Galaxy S7'  
```  

### Running some specifics tests
You can easily run a subset of your tests by using the `-Dcucumber.filter.tags` argument.  
There, you can filter the scenarios by their tags.  
For example, if you want to run a subset of scenarios tagged with `@smoke`, use the following line command :
```  
mvn test -Dcucumber.filter.tags="@smoke"  
```  
You can also combine multiple tags.  
The following will run the tests tagged with both smoke and manual.
```  
mvn test -Dcucumber.filter.tags="@smoke and @manual"  
```  

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- ROADMAP -->
## Roadmap

- [ ] Fully implement test for iOS platform

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/Deharteloos/appium-testng-cucumber-framework.svg?style=for-the-badge
[contributors-url]: https://github.com/Deharteloos/appium-testng-cucumber-framework/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/Deharteloos/appium-testng-cucumber-framework.svg?style=for-the-badge
[forks-url]: https://github.com/Deharteloos/appium-testng-cucumber-framework/network/members
[stars-shield]: https://img.shields.io/github/stars/Deharteloos/appium-testng-cucumber-framework.svg?style=for-the-badge
[stars-url]: https://github.com/Deharteloos/appium-testng-cucumber-framework/stargazers
[issues-shield]: https://img.shields.io/github/issues/Deharteloos/appium-testng-cucumber-framework.svg?style=for-the-badge
[issues-url]: https://github.com/Deharteloos/appium-testng-cucumber-framework/issues
[license-shield]: https://img.shields.io/github/license/Deharteloos/appium-testng-cucumber-framework.svg?style=for-the-badge
[license-url]: https://github.com/Deharteloos/appium-testng-cucumber-framework/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
