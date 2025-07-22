# Java TCP File Transfer System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

A TCP client-server application that enables file upload, listing, and download with integrity verification using SHA-256. Designed with Java Swing for the client interface.

## 📌 Features

- File upload and download via TCP sockets  
- Client-side graphical interface (Swing)  
- Server handles file requests and stores them  
- Hash validation using SHA-256  
- Base64 encoding for file transfer

## 🧰 Technologies

- Java 11+  
- Java Swing  
- TCP sockets  
- Base64, SHA-256  
- Gson

## 🛠 Installation

1. Clone the repo:
   ```bash
   git clone https://github.com/carlosegzm/Cliente-Servidor
   cd Cliente-Servidor
   ```

2. Run the server:
   ```
   src/server/Servidor.java
   ```

3. Run the client (Java Swing GUI):
   ```
   src/view/Tela.java
   ```

## 📁 Structure

```
src/
├── view/         # Client GUI
├── controller/   # Event handling and logic
├── model/        # File transfer objects
├── server/       # Server-side logic
└── util/         # Hashing, file encoding
```

## 📖 Educational Scope

Developed for academic study of file transfer protocols, Java I/O, and secure transmission practices.

## 📝 License

MIT License
