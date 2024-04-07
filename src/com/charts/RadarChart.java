package com.charts;

import com.stats.StatValue;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class RadarChart {

  // Constants
  private static final int MAX_VALUE = 10; // Maximum value for the stats
  private static final int DEFAULT_NUM_SIDES = 6; // Default number of sides for the chart
  private static final double DEFAULT_WIDTH = 250; // Default width of the chart
  private static final double DEFAULT_HEIGHT = 250; // Default height of the chart
  // Properties
  private StackPane stackPane; // StackPane for the chart
  private Pane pane; // Pane for the chart
  private int numSides; // Number of sides for the chart
  private StatValue[] values; // Array of StatValue objects
  private double width; // Width of the chart
  private double height; // Height of the chart

  /**
   * This constructor initializes the RadarChart object with the specified values.
   * @param values The array of StatValue objects
   * @param width The width of the chart
   * @param height The height of the chart
   */
  public RadarChart(StatValue[] values, double width, double height) {
    if (width <= 0 || height <= 0) { // Check if the width and height are greater than 0
      // Throw an exception if the width or height is less than or equal to 0
      throw new IllegalArgumentException(
        "Width and height must be greater than 0"
      );
    }

    this.width = width; // Set the width property
    this.height = height; // Set the height property
    this.stackPane = new StackPane(); // Initialize the StackPane
    this.pane = new Pane(); // Initialize the Pane
    stackPane.getChildren().add(pane); // Add the pane to the stackPane
    stackPane.setPadding(new Insets(10, 0, 10, 50)); // Set padding for the stackPane

    setValues(values); // Draw the chart with the specified values
  }

  /**
   * This constructor initializes the RadarChart object with the specified values.
   * @param values The array of StatValue objects
   */
  public RadarChart(StatValue[] values) {
    // Call the other constructor with the default width and height
    this(values, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  /**
   * This constructor initializes the RadarChart object with the specified width and height.
   * @param width The width of the chart
   * @param height The height of the chart
   */
  public RadarChart(double width, double height) {
    // Call the other constructor with an empty array of StatValue objects
    this(new StatValue[0], width, height);
  }

  /**
   * This constructor initializes the RadarChart object with the default width and height.
   */
  public RadarChart() {
    // Call the other constructor with an empty array of StatValue objects and the default width and height
    this(new StatValue[0], DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  /**
   * This method sets the values of the RadarChart object and redraws the chart.
   * @param values The array of StatValue objects
   */
  public void setValues(StatValue[] values) {
    if (values.length > 0 && values.length < 3) { // Check if the number of sides is at least 3
      // Throw an exception if the number of sides is less than 3
      throw new IllegalArgumentException("Number of sides must be at least 3");
    }
    this.values = values; // Set the values property
    // Set the number of sides to the length of the values array or the default number of sides
    this.numSides = values.length == 0 ? DEFAULT_NUM_SIDES : values.length;
    drawChart(); // Draw the chart
  }

  /**
   * This method draws the radar chart on the pane.
   */
  public void drawChart() {
    pane.getChildren().clear(); // Clear the pane
    double radius = Math.min(width, height) / 2 * 0.9; // Calculate the radius of the chart
    double centerX = width / 2; // Calculate the x-coordinate of the center
    double centerY = height / 2; // Calculate the y-coordinate of the center

    drawHexagons(centerX, centerY, radius); // Draw the hexagons
    drawLinesAndLabels(centerX, centerY, radius); // Draw the lines and labels
    plotValues(centerX, centerY, radius); // Plot the values
  }

  /**
   * This method draws the hexagons on the chart.
   * @param centerX The x-coordinate of the center
   * @param centerY The y-coordinate of the center
   * @param radius The radius of the chart
   */
  private void drawHexagons(double centerX, double centerY, double radius) {
    for (int j = 0; j < 5; j++) { // Draw 5 hexagons
      // Draw a polygon with the specified center, radius, number of sides, and fill
      drawPolygon(centerX, centerY, radius - j * radius / 5, numSides, false);
    }
  }

  /**
   * This method draws the lines and labels on the chart.
   * @param centerX The x-coordinate of the center
   * @param centerY The y-coordinate of the center
   * @param radius The radius of the chart
   */
  private void drawLinesAndLabels(
    double centerX,
    double centerY,
    double radius
  ) {
    for (int i = 0; i < numSides; i++) { // Draw lines and labels for each side
      double x = calculateCoordinate(centerX, radius, i, true); // Calculate the x-coordinate
      double y = calculateCoordinate(centerY, radius, i, false); // Calculate the y-coordinate
      drawLine(centerX, centerY, x, y); // Draw a line from the center to the point
      if (values.length >= i + 1) { // Check if the values array has a value at the current index
        String name = values[i].getName(); // Get the name of the value
        drawLabel(centerX, centerY, x, y, name); // Draw a label for the value
      }
    }
  }

  /**
   * This method calculates the x or y coordinate of a point on the chart.
   * @param center The center of the chart
   * @param radius The radius of the chart
   * @param index The index of the point
   * @param isX True if calculating the x-coordinate, false if calculating the y-coordinate
   * @return The x or y coordinate of the point
   */
  private double calculateCoordinate(
    double center,
    double radius,
    int index,
    boolean isX
  ) {
    /*
     * Center is the center of the chart and is used as the origin
     * Radius is the distance from the center to the point on the circle and is used to calculate the x and y coordinates
     * Cosine is used for the x-coordinate because cosine is the x-coordinate of a point on the unit circle
     * Sine is used for the y-coordinate because sine is the y-coordinate of a point on the unit circle
     * Pi is multiplied by 2 to get the full circle instead of half a circle
     * The index is used to calculate the angle of the point on the circle based on the number of sides
     */
    return (
      center +
      radius *
      (
        isX
          ? Math.cos(2 * Math.PI * index / numSides) // Calculate the x-coordinate
          : Math.sin(2 * Math.PI * index / numSides) // Calculate the y-coordinate
      )
    );
  }

  /**
   * This method draws a line on the chart.
   * @param startX The x-coordinate of the start of the line
   * @param startY The y-coordinate of the start of the line
   * @param endX The x-coordinate of the end of the line
   * @param endY The y-coordinate of the end of the line
   */
  private void drawLine(
    double startX,
    double startY,
    double endX,
    double endY
  ) {
    Line line = new Line(startX, startY, endX, endY); // Create a line
    pane.getChildren().add(line); // Add the line to the pane
  }

  /**
   * This method draws a label on the chart.
   * @param centerX The x-coordinate of the center
   * @param centerY The y-coordinate of the center
   * @param x The x-coordinate of the label
   * @param y The y-coordinate of the label
   * @param label The text of the label
   */
  private void drawLabel(
    double centerX,
    double centerY,
    double x,
    double y,
    String label
  ) {
    Text text = new Text(label); // Create a text object with the label
    /*
     * The 0.1 factor is used to move the text slightly away from the center.
     * It can be adjusted to move the text further or closer to the center
     */
    text.setX(x + (x - centerX) * 0.1); // Set the x-coordinate of the text
    text.setY(y + (y - centerY) * 0.1); // Set the y-coordinate of the text
    adjustTextPosition(text, centerX, centerY, x, y, label); // Adjust the position of the text
    pane.getChildren().add(text); // Add the text to the pane
  }

  /**
   * This method adjusts the position of the text based on the x and y coordinates.
   * @param text The text object
   * @param centerX The x-coordinate of the center
   * @param centerY The y-coordinate of the center
   * @param x The x-coordinate of the text
   * @param y The y-coordinate of the text
   * @param label The text of the label
   */
  private void adjustTextPosition(
    Text text,
    double centerX,
    double centerY,
    double x,
    double y,
    String label
  ) {
    if (x >= centerX) { // Check if the x-coordinate is greater than or equal to the center x-coordinate
      text.setTextOrigin(VPos.CENTER); // Set the text origin to the center
    } else { // If the x-coordinate is less than the center x-coordinate
      // If the label is "Intelligence" set the text origin to the center
      if (!"Intelligence".equals(label)) text.setTextOrigin(VPos.CENTER);
      /*
       * Set the x-coordinate of the text to the left of the label.
       * The 8 factor is used to move the text slightly away from the line.
       * It can be adjusted to move the text further or closer to the line
       */
      text.setX(x - text.getLayoutBounds().getWidth() - 8);
    }

    if (y > centerY) { // If the y-coordinate is greater than the center y-coordinate
      text.setY(text.getY() + 5); // Move the text down
    } else if (y < centerY) { // If the y-coordinate is less than the center y-coordinate
      text.setY(text.getY() - 5); // Move the text up
    }
  }

  /**
   * This method plots the values on the chart.
   * @param centerX The x-coordinate of the center
   * @param centerY The y-coordinate of the center
   * @param radius The radius of the chart
   */
  private void plotValues(double centerX, double centerY, double radius) {
    // Draw a polygon with the specified center, radius, number of sides, and fill
    drawPolygon(centerX, centerY, radius, values.length, true);
  }

  /**
   * This method draws a polygon on the chart.
   * @param centerX The x-coordinate of the center
   * @param centerY The y-coordinate of the center
   * @param radius The radius of the chart
   * @param points The number of points of the polygon
   * @param fill True if the polygon should be filled, false if it should be outlined
   */
  private void drawPolygon(
    double centerX,
    double centerY,
    double radius,
    int points,
    boolean fill
  ) {
    Polygon polygon = new Polygon(); // Create a polygon
    for (int i = 0; i < points; i++) { // Add points to the polygon
      /*
       * If fill is true, calculate the radius based on the value of the StatValue object.
       * Otherwise, use the specified radius. The polygon is only filled if plotting
       * the values, otherwise it is outlined. We use this approach to avoid code duplication.
       */
      double r = fill
        ? radius * (double) values[i].getValue() / MAX_VALUE
        : radius;
      // Calculate the x and y coordinates of the point
      double x = calculateCoordinate(centerX, r, i, true);
      double y = calculateCoordinate(centerY, r, i, false);
      polygon.getPoints().addAll(x, y); // Add the x and y coordinates to the polygon
    }
    if (fill) { // If the polygon should be filled
      polygon.setFill(Color.DARKGREEN); // Set the fill color of the polygon
      polygon.setOpacity(0.5); // Set the opacity of the polygon
    } else { // If the polygon should be outlined
      polygon.setStroke(Color.BLACK); // Set the stroke color of the polygon
      polygon.setFill(Color.TRANSPARENT); // Set the fill color of the polygon to transparent
    }
    pane.getChildren().add(polygon); // Add the polygon to the pane
  }

  /**
   * This method returns the StackPane of the RadarChart object.
   * @return The StackPane of the RadarChart object
   */
  public StackPane getPane() {
    return stackPane;
  }
}
