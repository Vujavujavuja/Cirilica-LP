# 🇷🇸 Lexer-Parser for a Custom Cyrillic Language

## Project Overview

This project implements the foundational components—a **Lexer** and a **Parser**—required to process and validate a custom programming language defined using the Cyrillic alphabet. It is a fundamental demonstration of compiler design principles applied to create a robust front-end for a new language.

The primary goal is to provide a complete language processing pipeline for syntax analysis and error checking.

***

## 🛠️ Components & Technologies

| Component | Language | Role |
| :--- | :--- | :--- |
| **Lexer** | **Java** | Tokenizes the input source code, scanning characters and classifying them into tokens for the parser. |
| **Parser** | **Python** | Consumes the token stream from the Lexer, checks the source code for syntactic correctness, and constructs an abstract parse tree. |

***

## 👤 Credits

| Role | Name |
| :--- | :--- |
| **Author** | Nemanja Vujic |
| **Supervisor** | Mihajlo Stojanovic |

***

## 🚀 Getting Started

### Installation & Execution

The project provides two execution paths, depending on whether you want to run the components independently or as a complete pipeline.

1.  **Lexer Only (Java):**
    * Compile and run `Main.java` to test the tokenization stage.

2.  **Lexer & Parser Pipeline (Python):**
    * Run `codes.py` to execute the full process, integrating both the Java Lexer output and the Python Parser.

The source code files for the custom language are located in `javaLexer/ProgramskiPrevodioci1/src/codes/` and can be edited to test different language constructs.
