package com;

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

  public HeroCreationPanel() {
    super();
  }

  public ScrollPane createHeroLayout() {
    // VBox to hold all components
    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20, 20, 20, 20)); // Padding around the layout

    // ComboBox for selecting hero type
    Label heroTypeLabel = new Label("Class:");
    ComboBox<String> heroType = createComboBox(
      new String[] { "Mage", "Warrior", "Faerie" },
      "Select Class"
    );
    heroType.setStyle("-fx-cursor: hand;");

    // Name input field
    Label nameLabel = new Label("Name:");
    TextField nameInput = createTextField("Name");

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

    VBox transportBox = new VBox(5);
    Label hasTransportLabel = new Label("Mounted:");
    SwitchButton hasTransport = new SwitchButton();
    transportBox.getChildren().addAll(hasTransportLabel, hasTransport);
    transportBox.fillWidthProperty().setValue(false); // To prevent the switch from taking up the full width
    transportBox.setVisible(false);
    transportBox.setManaged(false);

    StackPane chartPane = radarChart.getPane(); // Pane to hold the radar chart

    // HBox to hold the radar chart and stat controls
    HBox statBox = new HBox(5);
    statBox.getChildren().addAll(chartPane, statControls);

    // Button to create the hero
    Button createButton = createButton(
      "Create",
      e -> {
        // Get the selected hero type
        String selectedType = heroType.getValue();
        if (selectedType == null) {
          showAlert("Error", "Please select a hero type.");
          return;
        }
        String saveData = "";
        // Get the name from the input field
        String name = nameInput.getText();

        // Create the hero based on the selected type
        switch (selectedType) {
          case "Mage":
            Mage mage = (Mage) entity;
            try {
              int age = Integer.parseInt(ageInput.getText());
              mage.setName(name);
              mage.setAge(age);
              saveData = mage.toString();
            } catch (NumberFormatException ex) {
              showAlert("Invalid Input", "Age must be a number.");
            } catch (IllegalArgumentException ex) {
              showAlert("Invalid Input", ex.getMessage());
            }
            break;
          case "Warrior":
            Warrior warrior = (Warrior) entity;
            try {
              warrior.setName(name);
              warrior.setHasTransport(hasTransport.getState());
              saveData = warrior.toString();
            } catch (IllegalArgumentException ex) {
              showAlert("Invalid Input", ex.getMessage());
            }
            break;
          case "Faerie":
            Faerie faerie = (Faerie) entity;
            try {
              int height = Integer.parseInt(heightInput.getText());
              faerie.setName(name);
              faerie.setHeight(height);
              saveData = faerie.toString();
            } catch (NumberFormatException ex) {
              showAlert("Invalid Input", "Height must be a number.");
            } catch (IllegalArgumentException ex) {
              showAlert("Invalid Input", ex.getMessage());
            }
            break;
        }
        if (!saveData.isEmpty()) {
          try {
            // Save the hero entity to the database
            saveEntity(saveData);
          } catch (Exception ex) {
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
     * Add event handler to show/hide input based on hero type selected.
     */
    heroType.setOnAction(e -> {
      String selectedType = heroType.getValue();

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
          // If the selected hero type is "Faerie", show the height input field and label.
          entity = new Warrior();
          setConditionalVisibility(true, transportBox);
          break;
        case "Faerie":
          // If the selected hero type is "Warrior", hide the transport switch.
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
