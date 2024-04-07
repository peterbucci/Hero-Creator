package com;

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

  public MonsterCreationPanel() {
    super();
  }

  public ScrollPane createMonsterLayout() {
    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20, 20, 20, 20));

    // ComboBox for selecting monster type
    Label monsterTypeLabel = new Label("Race:");
    ComboBox<String> monsterType = createComboBox(
      new String[] { "Ogre", "Troll" },
      "Select Race"
    );
    monsterType.setStyle("-fx-cursor: hand;");

    // Name input field
    Label nameLabel = new Label("Name:");
    TextField nameInput = createTextField("Name");

    // Habitat input field
    Label habitatLabel = new Label("Habitat:");
    TextField habitatInput = createTextField("Habitat");

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
     * Weight input field and label for Ogre monster type.
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
    statBox.getChildren().addAll(chartPane, statControls);

    Button createButton = createButton(
      "Create",
      e -> {
        // Get the selected monster type
        String selectedType = monsterType.getValue();
        if (selectedType == null) {
          showAlert("Error", "Please select a monster type");
          return;
        }
        String saveData = "";
        // Get the name from the input field
        String name = nameInput.getText();
        // Get the habitat from the input field
        String habitat = habitatInput.getText();

        switch (selectedType) {
          case "Ogre":
            Ogre ogre = (Ogre) entity;
            try {
              boolean hasScales = hasScale.getState();
              ogre.setName(name);
              ogre.setHabitat(habitat);
              ogre.setHasScales(hasScales);
              saveData = ogre.toString();
            } catch (IllegalArgumentException ex) {
              showAlert("Invalid input", ex.getMessage());
            }

            break;
          case "Troll":
            Troll troll = (Troll) entity;
            try {
              int height = Integer.parseInt(heightInput.getText());
              troll.setName(name);
              troll.setHabitat(habitat);
              troll.setHeight(height);
              saveData = troll.toString();
            } catch (NumberFormatException ex) {
              showAlert("Invalid input", "Height must be a number");
            } catch (IllegalArgumentException ex) {
              showAlert("Invalid input", ex.getMessage());
            }
            break;
        }

        if (!saveData.isEmpty()) {
          try {
            // Save the monster entity to the database
            saveEntity(saveData);
          } catch (Exception ex) {
            showAlert("Error", "Failed to save monster to database.");
          }
        }
      }
    );

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

    monsterType.setOnAction(e -> {
      String selectedType = monsterType.getValue();

      // Reset visibility for all elements first
      setConditionalVisibility(false, heightInput, heightLabel, scaleBox);

      // Show the relevant elements based on the selected monster type
      switch (selectedType) {
        case "Ogre":
          entity = new Ogre();
          setConditionalVisibility(true, scaleBox);
          break;
        case "Troll":
          entity = new Troll();
          setConditionalVisibility(true, heightInput, heightLabel);
          break;
      }

      // Update UI based on the selected hero
      updateUIComponents();
    });

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(layout);
    scrollPane.setFitToWidth(true); // To ensure the scroll pane uses the width of the VBox
    return scrollPane;
  }
}
