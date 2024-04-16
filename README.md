# Hero-Creator

This JavaFX application allows users to create and customize hero characters with different attributes and weapons. It was originally created as a school project, but I have plans to expand on it in the future.

## Features

- **Graphical User Interface**: Built with JavaFX, providing a smooth and interactive user experience.
- **Customizable Heroes**: Assign different attributes and choose from a variety of weapons listed in `weapons.txt`.

## Project Structure

- **src/**: Contains all the JavaFX source code necessary to run the application.
- **weapons.txt**: A list of weapons that can be assigned to heroes, enhancing their capabilities.

## Getting Started

To get started with the Hero-Creator, ensure you have Java and JavaFX set up on your machine. Follow these steps to run the application:

1. **Clone the Repository**
   ```bash
   git clone https://github.com/peterbucci/Hero-Creator.git
   cd Hero-Creator
   ```
2. **Include JavaFX Library**

   Make sure to include the path to your JavaFX lib folder if running from the command line.

   ```bash
   java --module-path /path/to/javafx-sdk-XX/lib --add-modules javafx.controls,javafx.fxml -cp bin Main
   ```
