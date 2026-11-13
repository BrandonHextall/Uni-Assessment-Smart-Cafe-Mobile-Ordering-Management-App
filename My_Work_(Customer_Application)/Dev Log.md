# Smart Café – Development Log

**Module:** CTEC3703 – Mobile Application Development & Big Data  
**Project:** Smart Café – Mobile Ordering and Management Application  
**Development Role:** Customer Application  
**Developer:** Brandon Hextall
**Development Start Date:** 29 October 2026
**Submission Date:** 16 November 2026

---

# 1. Development Environment and Technologies

| Technology | Purpose |
|---|---|
| Android Studio | Android development environment |
| Kotlin | Application programming language |
| Jetpack Compose | User interface development |
| Firebase Authentication | Customer authentication |
| Cloud Firestore | Cloud database |
| GitHub | Version control and project documentation |
| Data Analytics API | Product analysis and recommendations |

---

# 2. Development Timeline and Progress

Record development activities chronologically.

## Development Session 1 – 4 November 2026

**Objectives:**
- Implement Jetpack Compose
- Implement Firebase
- Implement Cloud Firestore
- Configure project 

**Work completed:**
- Implemented Jetpack Compose
- Created a simple test method to ensure its compatibility

**Issues encountered:**
- Virtual machine was dysfunctional

**Outcome:**
- Jetpack Compose implemented and compatible
- Disregarded Android Studio virtual machine for physical android phone

**GitHub commits:**
- 1cb896a955b2cb946ac9c93c1b66363dbe236cb0

## Development Session 2 – 4 November 2026

**Objectives:**
- Get accustomed to using Jetpack Compose

**Work completed:**
- Created a simple menu for logging in
- Created a simple home page
- Implemented page navigation

**Issues encountered:**
- None

**Outcome:**
- Basic design and navigation

**GitHub commits:**
- ba9948b85402ecc377e3ba158945e47e1f2301f8

## Development Session 3 – 5 November 2026

**Objectives:**
- Further develop app design

**Work completed:**
- Created registration page

**Issues encountered:**
- App kept crashing on device

**Outcome:**
- Further navigation implemented

**GitHub commits:**
- ff35d12b6500e49f22ea8bc678975a3a6576f80f

## Development Session 4 – 9 November 2026

**Objectives:**
- Re-format to MVC
- Implement Firebase Authentication

**Work completed:**
- Formatted current work to use MVC format
- Added simple search and navigation bars to home page
- Implemented simple validation to login and registration pages
- Implemented Firebase Auth for user login

**Issues encountered:**
- Access to Firebase denied: Insufficient permissions

**Outcome:**
- Implemented basic Firebase functionality
- Semi-functional login page

**GitHub commits:**
- 516d1d60a9f722ba47894f06a0a69c363ac44708

## Development Session 5 – 9 November 2026

**Objectives:**
- Fully implement login and registration
- Implement some form of error handling

**Work completed:**
- Functional login
- Functional registration
- Redirect user upon successful login/registration
- Relevant error handling for login and registration
- Registration saves data to Firebase

**Issues encountered:**
- None

**Outcome:**
- Functional login and registration
- Functional and relevant error handling

**GitHub commits:**
- 5691f5faa15f37c7909492f91ab4a8d968644b0f

## Development Session 6 – 9 November 2026

**Objectives:**
- Develop authorisation checks

**Work completed:**
- Developed a basic dropdown menu
- Contents of dropdown vary based on whether user is logged in or not

**Issues encountered:**
- Navbars not reusable across pages

**Outcome:**
- Improved menu navigation
- Authorisation checks

**GitHub commits:**
- 85907ea838a3bfc7190e3c2948c8300c151e04bc

## Development Session 7 – 13 November 2026

**Objectives:**
- Further develop login and registration

**Work completed:**
- Implemented page switching between login and registration pages

**Issues encountered:**
- NavHost functionality became unreliable

**Outcome:**
- Improved login/registration UI

**GitHub commits:**
- 3df4909cdbd0019d1ac08441d1a2c4a1ac73b0fd

## Development Session 8 – 13 November 2026

**Objectives:**
- Modfy files to fit MVVM format

**Work completed:**
- All files modified to accomodate MVVM format

**Issues encountered:**
- None

**Outcome:**
- MVVM achieved

**GitHub commits:**
- 6528d21fcc215ffd4055f02077dd176ca94e6cfe

## Development Session 9 – 13 November 2026

**Objectives:**
- Create custom Theme for application
- Improve navigation

**Work completed:**
- Created custom theme
- Removed start page for menu screen on startup
- Removed top navbar
- Moved profile button to bottom navbar
- Profile button clickable: shows dropdown when logged in, redirects to login page if not
- Sign Out button reloads page
- Added "no account" button to login page

**Issues encountered:**
- Dropdown continuously placed with a space between the navbar

**Outcome:**
- Cleaner UI
- More intuitive navigation

**GitHub commits:**
- 91e3be7c28d776676b2699d4e9ff31101c7ad1f3

## Development Session 10 – 13 November 2026

**Objectives:**
- Allow data retrieval from Products table

**Work completed:**
- ProductRepository file now retrieves data from Firestore
- HomeViewModel manages products
- HomeScreen displays retrieved data

**Issues encountered:**
- None

**Outcome:**
- Successful data retrieval from Firestore

**GitHub commits:**
- link

## Development Session 11 – [Date]

**Objectives:**
- Obj

**Work completed:**
- Complete

**Issues encountered:**
- None

**Outcome:**
- outcome

**GitHub commits:**
- link

## Development Session 12 – [Date]

**Objectives:**
- Obj

**Work completed:**
- Complete

**Issues encountered:**
- None

**Outcome:**
- outcome

**GitHub commits:**
- link

## Development Session 13 – [Date]

**Objectives:**
- Obj

**Work completed:**
- Complete

**Issues encountered:**
- None

**Outcome:**
- outcome

**GitHub commits:**
- link

## Development Session 14 – [Date]

**Objectives:**
- Obj

**Work completed:**
- Complete

**Issues encountered:**
- None

**Outcome:**
- outcome

**GitHub commits:**
- link

## Development Session 15 – [Date]

**Objectives:**
- Obj

**Work completed:**
- Complete

**Issues encountered:**
- None

**Outcome:**
- outcome

**GitHub commits:**
- link

## Development Session 16 – [Date]

**Objectives:**
- Obj

**Work completed:**
- Complete

**Issues encountered:**
- None

**Outcome:**
- outcome

**GitHub commits:**
- link

## Development Session 17 – [Date]

**Objectives:**
- Obj

**Work completed:**
- Complete

**Issues encountered:**
- None

**Outcome:**
- outcome

**GitHub commits:**
- link

## Development Session Template – [Date]

**Objectives:**
- Obj

**Work completed:**
- Complete

**Issues encountered:**
- None

**Outcome:**
- outcome

**GitHub commits:**
- link

---

# 3. Feature Implementation Log

Record the implementation of individual application features.

| Feature ID | Feature | Implementation Details | Status |
|---|---|---|---|
| SCA-01 | Customer registration | [Details] | [Status] |
| SCA-02 | Customer login | [Details] | [Status] |
| SCA-03 | Browse menu | [Details] | [Status] |
| SCA-04 | Search menu | [Details] | [Status] |
| SCA-05 | Shopping cart | [Details] | [Status] |
| SCA-06 | Place order | [Details] | [Status] |
| SCA-07 | Order history | [Details] | [Status] |
| SCA-08 | Customer feedback | [Details] | [Status] |

---

# 4. Issue and Bug Log

Record significant development errors, bugs and technical problems.

| Issue ID | Date | Description | Cause | Resolution | Status |
|---|---|---|---|---|---|
| ISS-001 | [Date] | [Description] | [Cause] | [Resolution] | [Status] |

For particularly significant issues, include additional details:

### ISS-001 – [Issue Title]

**Description:**  
[Explain what happened.]

**Error message:**  
[Include the relevant error message.]

**Investigation:**  
[Explain what you investigated and what you discovered.]

**Resolution:**  
[Explain how you resolved the issue or what workaround you used.]

**Outcome:**  
[Explain whether the issue was resolved and whether functionality was affected.]

**Evidence:**  
[Link to screenshot, GitHub issue or relevant commit.]

---

# 5. Change Request Log

Record changes to the original requirements or requests received from the client.

| Change ID | Date | Original Requirement | Requested Change | Reason | Status |
|---|---|---|---|---|---|
| CR-001 | [Date] | [Original] | [Change] | [Reason] | [Status] |

---

# 6. Testing and Debugging Log

Record important testing activities and results.

| Test ID | Feature | Expected Result | Actual Result | Status |
|---|---|---|---|---|
| TEST-001 | Registration | Account successfully created | [Result] | [Pass/Fail] |
| TEST-002 | Login | Valid credentials authenticate user | [Result] | [Pass/Fail] |
| TEST-003 | Database | Correct data retrieved | [Result] | [Pass/Fail] |

Include links to the full test plan and supporting evidence where appropriate.

---

# 7. Client Meetings and Feedback

Record discussions with the lab tutor.

| Date | Features Demonstrated | Feedback Received | Agreed Actions | Status |
|---|---|---|---|---|
| [Date] | [Features] | [Feedback] | [Actions] | [Status] |