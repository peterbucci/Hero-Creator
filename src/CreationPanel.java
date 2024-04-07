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

  protected Entity entity;
  protected RadarChart radarChart;
  protected VBox statControls;
  private CloseHandler closeHandler;

  public CreationPanel() {
    radarChart = new RadarChart(StatValue.DEFAULT_VALUES);
    statControls = addStatControls();
  }

  protected TextField createTextField(String prompt) {
    TextField textField = new TextField();
    textField.setPromptText(prompt);
    return textField;
  }

  protected ComboBox<String> createComboBox(String[] items, String prompt) {
    ComboBox<String> comboBox = new ComboBox<>();
    comboBox.getItems().addAll(items);
    comboBox.setPromptText(prompt);
    return comboBox;
  }

  protected Button createButton(String text, EventHandler<ActionEvent> action) {
    Button button = new Button(text);
    button.setOnAction(action);
    return button;
  }

  protected VBox addStatControls() {
    StatValue[] values = entity != null
      ? entity.getStats()
      : StatValue.DEFAULT_VALUES;

    VBox statsBox = new VBox(5);
    statsBox.setAlignment(Pos.CENTER_LEFT);
    // add a drop shadow to statsBox
    statsBox.setStyle(
      "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0.0, 0.0, 2); -fx-background-color: #7e917e; -fx-background-radius: 20; -fx-padding: 10; -fx-border-radius: 20; -fx-border-color: #000;"
    );

    for (int i = 0; i < values.length; i++) {
      HBox statBox = createStatControl(i, values[i]);
      statsBox.getChildren().add(statBox);
    }

    return statsBox;
  }

  private HBox createStatControl(int index, StatValue statValue) {
    String statName = statValue.getName().substring(0, 3).toUpperCase();

    HBox statBox = new HBox();
    HBox.setHgrow(statBox, Priority.ALWAYS); // Ensure the HBox uses all available horizontal space

    Label statLabel = createStyledLabel(statName + "", "-fx-text-fill: white;");
    Label statField = createStyledLabel(
      String.valueOf(statValue.getValue()),
      "-fx-text-fill: white;"
    );

    HBox statText = new HBox(5, statLabel, statField);
    statText.setAlignment(Pos.CENTER_LEFT);
    statText.setMinWidth(50);

    Button increaseButton = createButton(
      "+",
      e -> {
        HBox statControl = (HBox) statControls.getChildren().get(index);
        HBox text = (HBox) statControl.getChildren().get(0);
        Label valueLabel = (Label) text.getChildren().get(1);
        StatValue stat = entity.getStats()[index];
        try {
          stat.increase();
          if (stat.getName() == "Constitution") entity.updateHealth();
          valueLabel.setText(
            String.valueOf(entity.getStats()[index].getValue())
          );
          radarChart.drawChart();
        } catch (IllegalArgumentException ex) {
          showAlert("Value Error", ex.getMessage());
        }
      }
    );

    Button decreaseButton = createButton(
      "-",
      e -> {
        HBox statControl = (HBox) statControls.getChildren().get(index);
        HBox text = (HBox) statControl.getChildren().get(0);
        Label valueLabel = (Label) text.getChildren().get(1);
        StatValue stat = entity.getStats()[index];
        try {
          stat.decrease();
          if (stat.getName() == "Constitution") entity.updateHealth();
          valueLabel.setText(
            String.valueOf(entity.getStats()[index].getValue())
          );
          radarChart.drawChart();
        } catch (IllegalArgumentException ex) {
          showAlert("Value Error", ex.getMessage());
        }
      }
    );

    applyButtonStyle(increaseButton);
    increaseButton.setDisable(statValue.getMaxValue() == 0);
    applyButtonStyle(decreaseButton);
    decreaseButton.setDisable(statValue.getMaxValue() == 0);

    // Use a spacer to push the buttons to the right
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS); // This makes the spacer take up all available space

    HBox statControl = new HBox(5, spacer, increaseButton, decreaseButton); // Add spacer before buttons

    statBox.getChildren().addAll(statText, statControl);
    HBox.setHgrow(statControl, Priority.ALWAYS); // Ensure statControl takes up necessary space

    return statBox;
  }

  protected ScrollPane createScrollPane(VBox content) {
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(content);
    scrollPane.setFitToWidth(true); // To ensure the scroll pane uses the width of the VBox
    return scrollPane;
  }

  protected void setConditionalVisibility(boolean visiblity, Node... nodes) {
    for (Node node : nodes) {
      node.setVisible(visiblity); // used to show/hide components.
      node.setManaged(visiblity); // used to ensure the hidden components do not take up space in the layout.
    }
  }

  protected void updateUIComponents() {
    if (entity == null) return;

    StatValue[] values = entity.getStats();
    radarChart.setValues(values);
    radarChart.drawChart();

    // Update stat controls
    for (int i = 0; i < values.length; i++) {
      // Assuming you have a method or a way to update each stat control
      // For example, if you're storing references to the stat control components:
      HBox statControl = (HBox) statControls.getChildren().get(i);
      updateStatControl(statControl, values[i]);
    }
  }

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
    HBox statText = (HBox) statControl.getChildren().get(0);
    Label valueLabel = (Label) statText.getChildren().get(1);
    valueLabel.setText(String.valueOf(value.getValue()));

    HBox statButtons = (HBox) statControl.getChildren().get(1);
    Button increaseButton = (Button) statButtons.getChildren().get(1);
    Button decreaseButton = (Button) statButtons.getChildren().get(2);
    increaseButton.setDisable(value.getMaxValue() == 0);
    decreaseButton.setDisable(value.getMaxValue() == 0);
  }

  // Method to show alerts for value errors
  protected void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  private void applyButtonStyle(Button button) {
    button.setStyle("-fx-cursor: hand;");
  }

  private Label createStyledLabel(String text, String style) {
    Label label = new Label(text);
    label.setStyle(style);
    return label;
  }

  protected void saveEntity(String data) {
    try {
      File file = new File("peterbucci.txt");
      FileWriter writer = new FileWriter(file, true);
      String uniqueID = UUID.randomUUID().toString();
      String dataWithId = "id=" + uniqueID + ", " + data;
      writer.write(dataWithId + "\n");
      writer.close();
      onClose();
    } catch (Exception e) {
      throw new RuntimeException("Failed to save entity!", e);
    }
  }

  public void setCloseHandler(CloseHandler closeHandler) {
    this.closeHandler = closeHandler;
  }

  protected void onClose() {
    if (closeHandler != null) {
      closeHandler.handleClose();
    }
  }
}
