# XMLForge 🚀  
*A simple and powerful XML formatter & attribute reordering tool for Android.*

<div align="center">
  <img src="assets/banner.png" width="100%" alt="Banner">
</div>

## 📌 Features  
- ✔️ Formats XML with proper indentation  
- ✔️ Reorders attributes in a structured way  
- ✔️ Supports Android XML files  
- ✔️ One-click copy to clipboard  
- ✔️ Simple and intuitive UI  

## 🛠️ How It Works  
1. Paste or load your XML content.  
2. Tap the **Format** button to clean up the structure.  
3. View the formatted output with attributes ordered correctly.  
4. Copy the formatted XML with a single tap.  

## 📜 Example Input & Output  
### 🔹 Input XML:  
```xml
<LinearLayout android:layout_height= "match_parent" 
android:layout_width="match_parent"
xmlns:android="http://schemas.android.com/apk/res/android"
android:orientation= "vertical">

    <TextView android:id="@+id/title"
      android:text = "Hello World" 
      android:layout_width = "wrap_content" android:layout_height="wrap_content" />

<Button
android:layout_width="wrap_content"
android:id="@+id/button"
    android:layout_height= "wrap_content"
android:text = "Click Me"
      />

    <EditText android:id="@+id/input" android:layout_width="match_parent" 
      android:layout_height= "wrap_content"
        android:hint="Enter text"/>

  </LinearLayout>
```

### 🔹Output XML:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World" />

    <Button
        android:id="@+id/button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Click Me" />

    <EditText
        android:id="@+id/input"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Enter text" />

</LinearLayout>
```
---

<div align="center">
  <a href="https://github.com/DP-Hridayan/aShellYou"><img alt="Repo Size" src="https://img.shields.io/github/repo-size/DP-Hridayan/XMLForge?style=for-the-badge"></a>
  <a href="https://github.com/DP-Hridayan/aShellYou/releases"><img src="https://img.shields.io/github/downloads/DP-Hridayan/XMLForge/total?color=%233DDC84&logo=android&logoColor=%23fff&style=for-the-badge" alt="Downloads"></a>
</div>

---

## 🔧 Tech Stack  
- **Language:** Java  
- **Parsing:** DOM Parser  

## ❔ Why this app?
- This app is for people like me who likes to code a lot on their phone.
