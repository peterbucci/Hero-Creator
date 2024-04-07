package com.ui;

import com.entities.heroes.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HeroCreationPanel extends CreationPanel {

  /**
   * Constructor for the HeroCreationPanel class.
   */
  public HeroCreationPanel() {
    // Call the parent class constructor
    super();
  }

  /**
   * Create the layout for the hero creation panel.
   * @return ScrollPane The scroll pane containing the hero creation layout
   */
  public ScrollPane createHeroLayout() {
    // VBox to hold all components with 10px spacing between elements
    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20, 20, 20, 20)); // Padding around the layout

    // ComboBox for selecting hero type
    Label heroTypeLabel = new Label("Class:");
    ComboBox<String> heroType = createComboBox(
      new String[] { "Mage", "Warrior", "Faerie" },
      "Select Class"
    );
    heroType.setStyle("-fx-cursor: hand;"); // Change cursor to hand when hovering over the ComboBox

    // Name input field
    Label nameLabel = new Label("Name:");
    TextField nameInput = createTextField("Name");

    // setManaged is used to prevent the elements from taking up space when they are hidden.

    /*
     * Age input field and label for Mage hero type.
     * Initially hidden and only shown when Mage is selected.
     */
    Label ageLabel = new Label("Age:");
    TextField ageInput = createTextField("Age");
    ageInput.setVisible(false);
    ageInput.setManaged(false);
    ageLabel.setVisible(false);
    ageLabel.setManaged(false);

    /*
     * Height input field and label for Faerie hero type.
     * Initially hidden and only shown when Faerie is selected.
     */
    Label heightLabel = new Label("Height:");
    TextField heightInput = createTextField("Height");
    heightInput.setVisible(false);
    heightInput.setManaged(false);
    heightLabel.setVisible(false);
    heightLabel.setManaged(false);

    /**
     * Switch to indicate if the Warrior hero has a mount.
     * Initially hidden and only shown when Warrior is selected.
     */
    VBox transportBox = new VBox(5); // VBox to hold the transport switch and label with 5px spacing
    Label hasTransportLabel = new Label("Mounted:");
    SwitchButton hasTransport = new SwitchButton();
    transportBox.getChildren().addAll(hasTransportLabel, hasTransport);
    transportBox.fillWidthProperty().setValue(false); // To prevent the switch from taking up the full width
    transportBox.setVisible(false);
    transportBox.setManaged(false);

    StackPane chartPane = radarChart.getPane(); // Pane to hold the radar chart

    // HBox to hold the radar chart and stat controls with 5px spacing between elements
    HBox statBox = new HBox(5);
    statBox.getChildren().addAll(chartPane, statControls);

    /*
     * Button to create the hero entity.
     * On click, the hero entity is created and saved to the database.
     */
    Button createButton = createButton(
      "Create",
      e -> {
        // Get the selected hero type
        String selectedType = heroType.getValue();
        if (selectedType == null) { // If no hero type is selected
          // Show an alert to the user and return early
          showAlert("Error", "Please select a hero type.");
          return;
        }
        // String to hold the data to be saved to the database
        String saveData = "";
        // Get the name from the input field
        String name = nameInput.getText();

        /*
         * Switch statement to handle the creation of the hero entity based on the selected type.
         * The entity is created and the relevant fields are set based on the selected hero type.
         * The entity is then saved to the database. If an exception is thrown, an alert is shown to the user.
         */
        switch (selectedType) {
          case "Mage":
            Mage mage = (Mage) entity; // Cast the entity to a Mage object
            try {
              // Parse the age input as an integer
              int age = Integer.parseInt(ageInput.getText());
              // Set the name and age for the mage
              mage.setName(name);
              mage.setAge(age);
              // saveData is set to the mage object's string representation
              saveData = mage.toString();
            } catch (NumberFormatException ex) { // Catch exception if age is not a number
              // Show an alert to the user
              showAlert("Invalid Input", "Age must be a number.");
            } catch (IllegalArgumentException ex) { // Catch any other exceptions
              // Show an alert to the user
              showAlert("Invalid Input", ex.getMessage());
            }
            break;
          case "Warrior":
            Warrior warrior = (Warrior) entity; // Cast the entity to a Warrior object
            try {
              // Set the name and hasTransport fields for the warrior
              warrior.setName(name);
              warrior.setHasTransport(hasTransport.getState());
              // saveData is set to the warrior object's string representation
              saveData = warrior.toString();
            } catch (IllegalArgumentException ex) { // Catch any exceptions thrown
              // Show an alert to the user
              showAlert("Invalid Input", ex.getMessage());
            }
            break;
          case "Faerie":
            Faerie faerie = (Faerie) entity; // Cast the entity to a Faerie object
            try {
              // Parse the height input as an integer
              int height = Integer.parseInt(heightInput.getText());
              // Set the name and height for the faerie
              faerie.setName(name);
              faerie.setHeight(height);
              // saveData is set to the faerie object's string representation
              saveData = faerie.toString();
            } catch (NumberFormatException ex) { // Catch exception if height is not a number
              // Show an alert to the user
              showAlert("Invalid Input", "Height must be a number.");
            } catch (IllegalArgumentException ex) { // Catch any other exceptions
              // Show an alert to the user
              showAlert("Invalid Input", ex.getMessage());
            }
            break;
        }

        // If saveData is not empty
        if (!saveData.isEmpty()) {
          try {
            // Save the hero entity to the database
            saveEntity(saveData);
          } catch (Exception ex) { // Catch any exceptions thrown
            // Show an alert to the user
            showAlert("Error", "Failed to save hero to database.");
          }
        }
      }
    );

    // Add all components to the layout
    layout
      .getChildren()
      .addAll(
        heroTypeLabel,
        heroType,
        nameLabel,
        nameInput,
        ageLabel,
        ageInput,
        heightLabel,
        heightInput,
        transportBox,
        statBox,
        createButton
      );

    /*
     * Event listener for the heroType ComboBox. When a hero type is selected,
     * the relevant elements are shown or hidden based on the selected monster type.
     */
    heroType.setOnAction(e -> {
      String selectedType = heroType.getValue(); // Get the selected hero type

      // Reset visibility for all elements first
      setConditionalVisibility(
        false,
        ageInput,
        ageLabel,
        heightInput,
        heightLabel,
        transportBox
      );

      switch (selectedType) {
        case "Mage":
          // If the selected hero type is "Mage", show the age input field and label.
          entity = new Mage();
          setConditionalVisibility(true, ageInput, ageLabel);
          break;
        case "Warrior":
          // If the selected hero type is "Warrior", show the transport switch.
          entity = new Warrior();
          setConditionalVisibility(true, transportBox);
          break;
        case "Faerie":
          // If the selected hero type is "Faerie", show the height input field and label.
          entity = new Faerie();
          setConditionalVisibility(true, heightInput, heightLabel);
          break;
        // Add more cases as needed
      }

      // Update UI based on the selected hero
      updateUIComponents();
    });

    // Create a scroll pane to hold the layout and return it
    return createScrollPane(layout);
  }
}
