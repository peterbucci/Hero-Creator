package com.ui;

import com.entities.monsters.Ogre;
import com.entities.monsters.Troll;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MonsterCreationPanel extends CreationPanel {

  /**
   * Constructor for the MonsterCreationPanel class.
   */
  public MonsterCreationPanel() {
    // Call the parent class constructor
    super();
  }

  /**
   * Create the layout for the monster creation panel.
   * @return ScrollPane The scroll pane containing the monster creation layout
   */
  public ScrollPane createMonsterLayout() {
    // Main layout for the monster creation panel with 10px spacing between elements
    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20, 20, 20, 20)); // 20px padding on all sides

    // ComboBox for selecting monster type
    Label monsterTypeLabel = new Label("Race:");
    ComboBox<String> monsterType = createComboBox(
      new String[] { "Ogre", "Troll" },
      "Select Race"
    );
    monsterType.setStyle("-fx-cursor: hand;"); // Change cursor to hand when hovering over the ComboBox

    // Name input field
    Label nameLabel = new Label("Name:");
    TextField nameInput = createTextField("Name");

    // Habitat input field
    Label habitatLabel = new Label("Habitat:");
    TextField habitatInput = createTextField("Habitat");

    // setManaged is used to prevent the elements from taking up space when they are hidden.

    /*
     * Height input field and label for Troll monster type.
     * Initially hidden and only shown when Troll is selected.
     */
    Label heightLabel = new Label("Height:");
    TextField heightInput = createTextField("Height");
    heightInput.setVisible(false);
    heightInput.setManaged(false);
    heightLabel.setVisible(false);
    heightLabel.setManaged(false);

    /*
     * Switch to indicate if the Ogre monster has scales.
     * Initially hidden and only shown when Ogre is selected.
     */
    VBox scaleBox = new VBox(5);
    Label hasScaleLabel = new Label("Has Scales:");
    SwitchButton hasScale = new SwitchButton();
    scaleBox.getChildren().addAll(hasScaleLabel, hasScale);
    scaleBox.fillWidthProperty().setValue(false); // To prevent the switch from taking up the full width
    scaleBox.setVisible(false);
    scaleBox.setManaged(false);

    StackPane chartPane = radarChart.getPane(); // Pane to hold the radar chart

    // HBox to hold the radar chart and stat controls
    HBox statBox = new HBox(5);
    statBox.getChildren().addAll(chartPane, statControls); // Add the radar chart and stat controls to the HBox

    /*
     * Button to create the monster entity.
     * On click, the monster entity is created and saved to the database.
     */
    Button createButton = createButton(
      "Create",
      e -> {
        // Get the selected monster type
        String selectedType = monsterType.getValue();

        if (selectedType == null) { // If no monster type is selected
          // Show an alert to the user and return early
          showAlert("Error", "Please select a monster type");
          return;
        }
        // String to hold the data to be saved to the database
        String saveData = "";
        // Get the name from the input field
        String name = nameInput.getText();
        // Get the habitat from the input field
        String habitat = habitatInput.getText();

        /*
         * Switch statement to handle the creation of the monster entity based on the selected type.
         * The entity is created and the relevant fields are set based on the selected monster type.
         * The entity is then saved to the database. If an exception is thrown, an alert is shown to the user.
         */
        switch (selectedType) {
          case "Ogre":
            Ogre ogre = (Ogre) entity; // Cast the entity to an Ogre object
            boolean hasScales = hasScale.getState(); // Get the state of the hasScale switch
            try {
              // Set the name, habitat, and hasScales fields for the Ogre object
              ogre.setName(name);
              ogre.setHabitat(habitat);
              ogre.setHasScales(hasScales);
              // saveData is set to the string representation of the Ogre object
              saveData = ogre.toString();
            } catch (IllegalArgumentException ex) { // Catch any exceptions thrown
              // Show an alert to the user with the exception message
              showAlert("Invalid input", ex.getMessage());
            }

            break;
          case "Troll":
            Troll troll = (Troll) entity; // Cast the entity to a Troll object
            try {
              // Get the height from the input field and parse it to an integer
              int height = Integer.parseInt(heightInput.getText());
              // Set the name, habitat, and height fields for the Troll object
              troll.setName(name);
              troll.setHabitat(habitat);
              troll.setHeight(height);
              // saveData is set to the string representation of the Troll object
              saveData = troll.toString();
            } catch (NumberFormatException ex) { // If the height is not a number
              // Show an alert to the user
              showAlert("Invalid input", "Height must be a number");
            } catch (IllegalArgumentException ex) { // Catch any other exceptions thrown
              // Show an alert to the user with the exception message
              showAlert("Invalid input", ex.getMessage());
            }
            break;
        }

        // If saveData is not empty
        if (!saveData.isEmpty()) {
          try {
            // Save the monster entity to the database
            saveEntity(saveData);
          } catch (Exception ex) { // Catch any exceptions thrown
            // Show an alert to the user
            showAlert("Error", "Failed to save monster to database.");
          }
        }
      }
    );

    // Add all elements to the layout
    layout
      .getChildren()
      .addAll(
        monsterTypeLabel,
        monsterType,
        nameLabel,
        nameInput,
        habitatLabel,
        habitatInput,
        heightLabel,
        heightInput,
        scaleBox,
        statBox,
        createButton
      );

    /*
     * Event listener for the monsterType ComboBox. When a monster type is selected,
     * the relevant elements are shown or hidden based on the selected monster type.
     */
    monsterType.setOnAction(e -> {
      String selectedType = monsterType.getValue(); // Get the selected monster type

      // Reset visibility for all elements first
      setConditionalVisibility(false, heightInput, heightLabel, scaleBox);

      // Show the relevant elements based on the selected monster type
      switch (selectedType) {
        case "Ogre":
          // If the selected monster type is Ogre show the hasScale switch
          entity = new Ogre(); // Create a new Ogre object
          setConditionalVisibility(true, scaleBox);
          break;
        case "Troll":
          // If the selected monster type is Troll show the height input field and label
          entity = new Troll(); // Create a new Troll object
          setConditionalVisibility(true, heightInput, heightLabel);
          break;
        // Add more cases for other monster types here
      }

      // Update UI based on the selected hero
      updateUIComponents();
    });

    // Create a ScrollPane to hold the layout
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(layout); // Set the layout as the content of the scroll pane
    scrollPane.setFitToWidth(true); // To ensure the scroll pane uses the width of the VBox
    return scrollPane;
  }
}
