These are my projects for the [Java track](https://hyperskill.org/tracks/1) at [Jetbrains Academy](https://www.jetbrains.com/academy). The list is sorted chronologically.


### Coffee Machine
A simple terminal app which simulates a coffee machine. You can order a hot beverage, inspect and fill up the stored ingredients, and collect the money.

### Tic-Tac-Toe with AI
A tic-tac-toe app for the terminal. It lets you play either against another human or the computer. It is also possible to watch the computer play against itself. The computer has three difficulty levels. The easiest makes random moves, medium looks one move ahead, and the hardest uses the minimax algorithm.

### Sorting Tool
This application sorts your inputs in different ways. Supported data types are "word", "long", and "line". They are sorted naturally or by count. It can read the values from the command line or a file and output the result either to the command line or a file.

### Smart Calculator
A terminal calculator. It supports addition, subtraction, multiplication, integer division, and exponentiation. You can assign variables and group operations using parentheses.

### Text Editor
This is a simple text editor with a Swing gui. It lets you load and save your text to files. It also has a text search function with an optional regex mode. The search runs in a separate thread to avoid interrupting the main app.

### Game of Life
Conway's Game of Life cellular automaton. With a Swing gui that enables the user to start and stop the simulation and step through it. The game and the gui run in separate threads.

### Guess the Animal
This is a little game where the computer tries to guess which animal you are thinking of via a series of yes/no questions. If it fails to give a correct answer it learns a new animal and a fact to differentiate it from others. The data is stored in a binary tree. Between runs this tree is serialized to either json, xml or yaml. In this project I also practiced internationalization by providing an english and an esperanto locale.

### Simple Banking System
A simple terminal app that lets you open bank accounts and deposit or transfer money. Card numbers are generated with the Luhn algorithm. The data is stored in an SQLite database.

### Web Quiz Engine
A REST API built with Spring Boot. It can register and authenticate users. Users can submit quizzes, solve them, and delete them. It is possible to get paginated lists of all stored quizzes and quiz completions per user. The data is stored in a database. There is no user interface so you have to use the terminal or e.g. Postman to interact with the api.

### Code Sharing Platform
In this project I am using Spring Boot to learn how to combine the backend and frontend by building a platform where users can share code snippets. You can access it in the browser to create new code snippets, see the latest codes and access specific codes by uuid. Codes are optionally access restricted by number of views or with a time limit.
