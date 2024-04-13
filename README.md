<p align="center">
  <img src="https://cdn-icons-png.flaticon.com/512/6295/6295417.png" width="100" />
</p>
<p align="center">
    <h1 align="center">BDD_FRAMEWORK_PAGE_OBJECT_MODEL</h1>
</p>

<p align="center">
	<img src="https://img.shields.io/github/last-commit/Gopi-Appapuram/BDD_Framework_Page_Object_Model?style=flat&logo=git&logoColor=white&color=0080ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/Gopi-Appapuram/BDD_Framework_Page_Object_Model?style=flat&color=0080ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/Gopi-Appapuram/BDD_Framework_Page_Object_Model?style=flat&color=0080ff" alt="repo-language-count">
<p>
<p align="center">
		<em>Developed with the software and tools below.</em>
</p>
<p align="center">
    <img src="//upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Selenium_logo.svg/120px-Selenium_logo.svg.png" decoding="async" loading="lazy" width="90" height="20" data-file-width="512" data-file-height="125" element-id="118">
	<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white" alt="java">
	<img src="https://img.shields.io/badge/JavaScript-F7DF1E.svg?style=flat&logo=JavaScript&logoColor=black" alt="JavaScript">
	<img src="https://img.shields.io/badge/HTML5-E34F26.svg?style=flat&logo=HTML5&logoColor=white" alt="HTML5">
</p><hr>

##  Quick Links

> - [ Overview](#-overview)
> - [ Features](#-features)
> - [ Repository Structure](#-repository-structure)
> - [ Getting Started](#-getting-started)
>   - [ Installation](#-installation)
>   - [ Running BDD_Framework_Page_Object_Model](#-running-BDD_Framework_Page_Object_Model)

---

##  Overview

This project is a Behavior-Driven Development (BDD) framework implemented using the Page Object Model (POM) for automating tests on an e-commerce website. The framework is built using Java and utilizes Cucumber for writing feature files and Selenium for automating web interactions.


---

##  Features

The features included in this project are:

User Login: Allows registered users to log into the website.
Searching for Products: Enables users to search for products on the website.
Adding Items to Cart: Facilitates adding items to the shopping cart.
Deleting Items in Cart: Provides functionality to remove items from the shopping cart.
Wishlist: Allows users to add items to their wishlist for later purchase.

---

##  Repository Structure

```sh
└── BDD_Framework_Page_Object_Model/
    ├── pom.xml
    ├── src
    │   └── test
    │       ├── java
    │       │   ├── PageObjects
    │       │   │   ├── CartPage.java
    │       │   │   ├── CheckoutPage.java
    │       │   │   ├── HomePage.java
    │       │   │   ├── LoginPage.java
    │       │   │   ├── MyAccountPage.java
    │       │   │   ├── OrderDetailsPage.java
    │       │   │   ├── OrdersPage.java
    │       │   │   ├── ProductPage.java
    │       │   │   ├── SearchPage.java
    │       │   │   ├── UserProfilePage.java
    │       │   │   └── WishlistPage.java
    │       │   ├── StepDefinations
    │       │   │   ├── AddToCartSteps.java
    │       │   │   ├── CheckoutSteps.java
    │       │   │   ├── DeleteItemsFromCart.java
    │       │   │   ├── LoginSteps.java
    │       │   │   ├── ManageOrdersSteps.java
    │       │   │   ├── SearchProductSteps.java
    │       │   │   ├── UserProfileSteps.java
    │       │   │   └── WishlistSteps.java
    │       │   ├── TestRunner
    │       │   │   └── amazontestrunner.java
    │       │   └── UtilityClasses
    │       │       ├── ExcelUtility.java
    │       │       ├── ScreenshotUtility.java
    │       │       ├── ScrollUtility.java
    │       │       ├── SeleniumHighlighterUtility.java
    │       │       ├── WebDriverManager.java
    │       │       └── WindowHandles.java
    │       └── resources
    │           ├── Features
    │           │   ├── 1-User Login.feature
    │           │   ├── 2-Searching for Products.feature
    │           │   ├── 3-Adding Items to Cart.feature
    │           │   ├── 4-Delete Items In Cart.feature
    │           │   ├── 5-Wishlist.feature
    │           │   ├── Checking Out.feature
    │           │   ├── Managing Orders.feature
    │           │   └── User Profile.feature
    │           └── TestData
    │               └── AmazonData.xlsx
    └── test-output
        └── CucumberReports
            └── htmlreport.html
```

---

##  Getting Started

***Requirements***

Ensure you have the following dependencies installed on your system:

* **Java**: `version 11`

###  Installation

1. Clone the BDD_Framework_Page_Object_Model repository:

```sh
git clone https://github.com/Gopi-Appapuram/BDD_Framework_Page_Object_Model
```

2. Change to the project directory:

```sh
cd BDD_Framework_Page_Object_Model
```

3. Install the dependencies:

```sh
mvn clean install
```

###  Running BDD_Framework_Page_Object_Model

Use the following command to run BDD_Framework_Page_Object_Model:

```sh
mvn clean test -Dtest= amazontestrunner.java
```



---

[**Return**](#-quick-links)

---
