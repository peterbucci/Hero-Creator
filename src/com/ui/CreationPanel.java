package com.ui;

import com.charts.RadarChart;
import com.entities.Entity;
import com.stats.StatValue;
import com.utils.CloseHandler;
import java.io.File;
import java.io.FileWriter;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public abstract class CreationPanel {

  // Instance variables
  protected Entity entity; // The entity being created
  protected RadarChart radarChart; // Radar chart for displaying entity stats
  protected VBox statControls; // VBox to hold the stat controls
  private CloseHandler closeHandler; // Close handler for the panel

  /**
   * Constructor for the CreationPanel class.
   */
  public CreationPanel() {
    // Create a new radar chart with default stat values
    radarChart = new RadarChart(StatValue.DEFAULT_VALUES);
    statControls = addStatControls(); // Add stat controls to the panel
  }

  /**
   * Create a text field with the specified prompt text.
   * @param prompt The prompt text for the text field
   * @return TextField The created text field
   */
  protected TextField createTextField(String prompt) {
    TextField textField = new TextField();
    textField.setPromptText(prompt);
    return textField;
  }

  /**
   * Create a combo box with the specified items and prompt text.
   * @param items The items to display in the combo box
   * @param prompt The prompt text for the combo box
   * @return ComboBox The created combo box
   */
  protected ComboBox<String> createComboBox(String[] items, String prompt) {
    ComboBox<String> comboBox = new ComboBox<>();
    comboBox.getItems().addAll(items);
    comboBox.setPromptText(prompt);
    return comboBox;
  }

  /**
   * Create a button with the specified text and action.
   * @param text The text to display on the button
   * @param action The action to perform when the button is clicked
   * @return Button The created button
   */
  protected Button createButton(String text, EventHandler<ActionEvent> action) {
    Button button = new Button(text);
    button.setOnAction(action);
    return button;
  }

  /**
   * Add stat controls to the panel.
   * @return VBox The VBox containing the stat controls
   */
  protected VBox addStatControls() {
    /*
     * Get the stat values from the entity, or use default values if the entity is null.
     */
    StatValue[] values = entity != null
      ? entity.getStats()
      : StatValue.DEFAULT_VALUES;

    // Create a VBox to hold the stat controls with 5px spacing between elements
    VBox statsBox = new VBox(5);
    statsBox.setAlignment(Pos.CENTER_LEFT); // Align the stat controls to the left
    // add a drop shadow to statsBox
    statsBox.setStyle(
      "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0.0, 0.0, 2); -fx-background-color: #7e917e; -fx-background-radius: 20; -fx-padding: 10; -fx-border-radius: 20; -fx-border-color: #000;"
    );

    // Create stat controls for each stat value
    for (int i = 0; i < values.length; i++) {
      HBox statBox = createStatControl(i, values[i]);
      statsBox.getChildren().add(statBox);
    }

    return statsBox;
  }

  /**
   * Create a stat control for the specified stat value.
   * @param index The index of the stat value
   * @param statValue The stat value to create the control for
   * @return The HBox containing the stat control
   */
  private HBox createStatControl(int index, StatValue statValue) {
    // Get the first three characters of the stat name in uppercase
    String statName = statValue.getName().substring(0, 3).toUpperCase();

    // Create a new HBox to hold the stat control
    HBox statBox = new HBox();
    HBox.setHgrow(statBox, Priority.ALWAYS); // Ensure the HBox uses all available horizontal space

    // Create a label for the stat name and value with white text color
    Label statLabel = createStyledLabel(statName + "", "-fx-text-fill: white;");
    Label statField = createStyledLabel(
      String.valueOf(statValue.getValue()),
      "-fx-text-fill: white;"
    );

    // Create an HBox to hold the stat name and value with 5px spacing between elements
    HBox statText = new HBox(5, statLabel, statField);
    statText.setAlignment(Pos.CENTER_LEFT); // Align the stat text to the left
    statText.setMinWidth(50); // Set a minimum width for the stat text to fix alignment issues

    /*
     * Create buttons to increase and decrease the stat value.
     * The buttons are disabled if the max value is 0.
     */
    Button increaseButton = createButton(
      "+",
      e -> {
        // Get the stat control at the specified index
        HBox statControl = (HBox) statControls.getChildren().get(index);
        // Get the HBox containing the stat name and value
        HBox text = (HBox) statControl.getChildren().get(0);
        // Get the label displaying the stat value
        Label valueLabel = (Label) text.getChildren().get(1);
        // Get the stat value at the specified index
        StatValue stat = entity.getStats()[index];

        try {
          stat.increase(); // Increase the stat value
          if (stat.getName() == "Constitution") entity.updateHealth(); // Update the health if the stat is Constitution
          // Update the value label with the new stat value
          valueLabel.setText(
            String.valueOf(entity.getStats()[index].getValue())
          );
          radarChart.drawChart(); // Redraw the radar chart
        } catch (IllegalArgumentException ex) { // Catch any exceptions thrown
          // Show an alert with the exception message
          showAlert("Value Error", ex.getMessage());
        }
      }
    );

    Button decreaseButton = createButton(
      "-",
      e -> {
        // Get the stat control at the specified index
        HBox statControl = (HBox) statControls.getChildren().get(index);
        // Get the HBox containing the stat name and value
        HBox text = (HBox) statControl.getChildren().get(0);
        // Get the label displaying the stat value
        Label valueLabel = (Label) text.getChildren().get(1);
        // Get the stat value at the specified index
        StatValue stat = entity.getStats()[index];
        try {
          stat.decrease(); // Decrease the stat value
          if (stat.getName() == "Constitution") entity.updateHealth(); // Update the health if the stat is Constitution
          // Update the value label with the new stat value
          valueLabel.setText(
            String.valueOf(entity.getStats()[index].getValue())
          );
          radarChart.drawChart(); // Redraw the radar chart
        } catch (IllegalArgumentException ex) { // Catch any exceptions thrown
          // Show an alert with the exception message
          showAlert("Value Error", ex.getMessage());
        }
      }
    );

    // Apply styles to the buttons and set them to disabled if the max value is 0
    applyButtonStyle(increaseButton);
    increaseButton.setDisable(statValue.getMaxValue() == 0);
    applyButtonStyle(decreaseButton);
    decreaseButton.setDisable(statValue.getMaxValue() == 0);

    // Use a spacer to push the buttons to the right
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS); // This makes the spacer take up all available space

    HBox statControl = new HBox(5, spacer, increaseButton, decreaseButton); // Add spacer before buttons

    // Add the stat text and buttons to the stat control HBox
    statBox.getChildren().addAll(statText, statControl);
    HBox.setHgrow(statControl, Priority.ALWAYS); // Ensure statControl takes up necessary space

    return statBox;
  }

  /**
   * Create a scroll pane with the specified content.
   * @param content The content to display in the scroll pane
   * @return ScrollPane The created scroll pane
   */
  protected ScrollPane createScrollPane(VBox content) {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(content);
    scrollPane.setFitToWidth(true); // To ensure the scroll pane uses the width of the VBox
    return scrollPane;
  }

  /**
   * Set the visibility of the specified nodes based on the specified condition.
   * @param visibility The visibility condition
   * @param nodes The nodes to set the visibility for
   */
  protected void setConditionalVisibility(boolean visiblity, Node... nodes) {
    for (Node node : nodes) {
      node.setVisible(visiblity); // used to show/hide components.
      node.setManaged(visiblity); // used to ensure the hidden components do not take up space in the layout.
    }
  }

  /**
   * Update the UI components of the panel.
   */
  protected void updateUIComponents() {
    if (entity == null) return; // Return if the entity is null

    // Update the radar chart with the entity stats
    StatValue[] values = entity.getStats();
    radarChart.setValues(values);
    radarChart.drawChart();

    /*
     * Update the stat controls with the entity stats.
     * The stat controls are updated by iterating over the stat controls VBox
     * and updating the stat value and button states.
     */
    for (int i = 0; i < values.length; i++) {
      HBox statControl = (HBox) statControls.getChildren().get(i);
      updateStatControl(statControl, values[i]);
    }
  }

  /**
   * Update the stat control with the specified stat value.
   * @param statControl The stat control to update
   * @param value The stat value to update the control with
   */
  private void updateStatControl(HBox statControl, StatValue value) {
    /*
     * The stat control layout is as follows:
     * HBox (statControl)
     * |- HBox (statText)
     * |   |- Label (statLabel)
     * |   |- Label (valueLabel)
     * |- HBox (statButtons)
     * |   |- Region (spacer)
     * |   |- Button (increaseButton)
     * |   |- Button (decreaseButton)
     */
    HBox statText = (HBox) statControl.getChildren().get(0); // Get the stat text HBox
    Label valueLabel = (Label) statText.getChildren().get(1); // Get the value label
    valueLabel.setText(String.valueOf(value.getValue())); // Update the value label with the new stat value

    HBox statButtons = (HBox) statControl.getChildren().get(1); // Get the stat buttons HBox
    Button increaseButton = (Button) statButtons.getChildren().get(1); // Get the increase button
    Button decreaseButton = (Button) statButtons.getChildren().get(2); // Get the decrease button
    // Set the buttons to disabled if the max value is 0
    increaseButton.setDisable(value.getMaxValue() == 0);
    decreaseButton.setDisable(value.getMaxValue() == 0);
  }

  /**
   * Show an alert with the specified title and content.
   * @param title The title of the alert
   * @param content The content of the alert
   */
  protected void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR); // Create a new error alert
    alert.setTitle(title); // Set the title of the alert
    alert.setHeaderText(null); // Remove the header text
    alert.setContentText(content); // Set the content text
    alert.showAndWait(); // Show the alert and wait for it to close
  }

  /**
   * Apply the button style to the specified button.
   * @param button The button to apply the style to
   */
  private void applyButtonStyle(Button button) {
    button.setStyle("-fx-cursor: hand;");
  }

  /**
   * Create a styled label with the specified text and style.
   * @param text The text to display on the label
   * @param style The style to apply to the label
   * @return Label The created label
   */
  private Label createStyledLabel(String text, String style) {
    Label label = new Label(text);
    label.setStyle(style);
    return label;
  }

  /**
   * Save the entity data to a file.
   * @param data A string representation of the entity data
   */
  protected void saveEntity(String data) {
    // Try to save the entity data to a file
    try {
      File file = new File("peterbucci.txt"); // Create a new file object
      FileWriter writer = new FileWriter(file, true); // Create a new file writer
      String uniqueID = UUID.randomUUID().toString(); // Generate a unique ID for the entity
      String dataWithId = "id=" + uniqueID + ", " + data; // Add the ID to the entity data
      writer.write(dataWithId + "\n"); // Write the entity data to the file with a newline character
      writer.close(); // Close the file writer
      onClose(); // Close the panel
    } catch (Exception e) { // Catch any exceptions thrown
      // Throw a runtime exception with the error message
      throw new RuntimeException("Failed to save entity!", e);
    }
  }

  /**
   * Set the close handler for the panel.
   * @param closeHandler The close handler to set
   */
  public void setCloseHandler(CloseHandler closeHandler) {
    this.closeHandler = closeHandler;
  }

  /**
   * Handle the close event for the panel.
   */
  protected void onClose() {
    // Call the close handler if it is not null
    if (closeHandler != null) {
      closeHandler.handleClose();
    }
  }
}
