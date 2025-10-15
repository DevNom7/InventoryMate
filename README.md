# 💼 InventoryMate  
_SNHU CS360 – Mobile Architect & Programming_   October 15, 2025
> Simple, Offline Inventory Tracking for Everyone
<p align="center">
<img width="300" height="600" alt="Screenshot_20251015_172458" src="https://github.com/user-attachments/assets/858d9cc2-1c77-43d9-a6b7-0c7ff1043d50" />
<img width="300" height="600" alt="Screenshot_20251015_172540" src="https://github.com/user-attachments/assets/36c9dd27-b814-45d2-a363-539b3676e8d3" />
</p>

## 🪞 Reflection (CS360 Module Eight Journal)

This project, **InventoryMate**, was developed for *SNHU’s CS360 Mobile Architect & Programming* course to demonstrate the full lifecycle of mobile app creation-from UI design to functional deployment.  
The goal was to build a simple, user-centered Android application that allows users to securely log in, manage inventory items, and send SMS alerts when needed. Designed for small business owners, field technicians, and students, the app meets real-world needs for quick, offline inventory tracking without relying on internet access.  

The app features three primary screens: **Login**, **Inventory**, and **SMS**. Each screen was intentionally kept clean and intuitive, with large buttons, clear text fields, and minimal distractions to ensure accessibility for all users. During development, I followed modular coding practices, consistent naming conventions, and maintained a strong separation between UI and logic through the `DatabaseHelper` class. This approach improved maintainability, organization, and made debugging much easier.  

Testing was performed using both the **Android Emulator** and a physical Android device that I have laying aorund, a One Plus McLaren to be exact 😅. Each feature was tested individually—login creation, database persistence, and SMS permission handling—to confirm that the app behaved correctly in all scenarios. One of the key challenges I encountered was ensuring stability when a user denied SMS permissions. I solved this by refining control flow and adding condition checks to prevent crashes. Another challenge was **changing the global color theme** across all activities, since I initially forgot how to properly modify color resources and themes in Android Studio. Relearning how to edit `colors.xml` and apply styles throughout the app taught me the importance of centralized theming for scalability and UI consistency.  

Implementing **SQLite** was another challenge that required careful setup. Understanding how to structure the database, handle table creation, and perform CRUD operations inside the `DatabaseHelper` class gave me valuable experience with persistent local storage. After some trial and error, I successfully integrated the login and inventory features to work seamlessly with SQLite while keeping data available even after closing the app.  

The component I’m most proud of is the **integration between the LoginActivity and SQLite database**, which successfully validates users and stores data locally without the need for an external API. This process strengthened my understanding of data persistence, user authentication, and activity lifecycle management in Android.  

Overall, *InventoryMate* represents a complete, functional MVP and a solid demonstration of mobile development skills. The project taught me how to plan, design, and execute a real application from concept to completion while prioritizing user experience. Looking forward, I plan to expand InventoryMate by integrating **Firebase Firestore** for cloud backup, adding **dark mode and custom themes**, and eventually rebuilding it in **Flutter** for cross-platform deployment. This app marks an important milestone in my journey toward becoming a professional mobile developer.

--


**InventoryMate** is a lightweight Android app that helps small business owners, field technicians, and students track inventory easily and reliably.  
Built with **Java** and **SQLite**, it allows users to log in, add or view inventory items, and send SMS alerts — even offline.

[![Android](https://img.shields.io/badge/Platform-Android-green)]()
[![Language](https://img.shields.io/badge/Language-Java-blue)]()
[![Database](https://img.shields.io/badge/Database-SQLite-lightgrey)]()
[![Status](https://img.shields.io/badge/Version-1.0%20(MVP)-purple)]()

---

## 🧩 Overview
InventoryMate was developed as part of SNHU’s CS360 course to demonstrate a complete mobile app lifecycle — from UI design and database integration to runtime permissions and testing.  
The app emphasizes usability, clear layout, and stability. Data is stored locally using SQLite, ensuring users can manage their inventory anywhere without needing an internet connection.

---

## ✨ Features
- 🔐 **User Login & Account Creation** — Secure, persistent login system with SQLite.
- 📦 **Inventory Management (CRUD)** — Add and view stored items; persistent local database.
- ✉️ **SMS Notifications** — Optional permission-based text message alerts.
- 🧭 **Multi-Screen Navigation** — Login, Inventory, and SMS activities connected seamlessly.
- 🌙 **Future Plans (v2)** — Firestore sync, dark mode, smart alerts, and Flutter cross-platform version.

---

## 🛠️ Tech Stack
| Component | Details |
|------------|----------|
| **Language** | Java |
| **IDE** | Android Studio |
| **Database** | SQLite |
| **Architecture** | Multi-Activity, Offline-First |
| **Permissions** | SEND_SMS (optional feature) |
| **Min SDK** | Android 9 (Pie) |

---

## 🚀 How to Run
1. Clone this repo:
   ```bash
   git clone https://github.com/devnom7/InventoryMate.git
--
📱 Screens
Login Screen: Create or log in to an account.
Inventory Screen: Add and display inventory items saved to SQLite.
SMS Screen: Check or request permission and send a test text message.

--
💡 Future Development
☁️ Add Firebase/Firestore sync and authentication
🌙 Introduce dark mode + user color themes
🔔 Smart notifications for low inventory
🧩 Rebuild in Flutter for Android, iOS, and web

   
