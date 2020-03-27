package sample;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Canvas canvas;

    @FXML
    private TextField brushSize;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private ComboBox<String> shapes;

    @FXML
    private CheckBox eraser;

    private GraphicsContext context;
    private FileChooser fileChooser = new FileChooser();

    /**
     * When the app first launches, a mouse drag event will be created to allow the user
     * to draw with the mouse cursor.
     * Depending on the parameters selected in the configuration panel, the action changes.
     */
    public void initialize() {
        context = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(event -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = event.getX();
            double y = event.getY();

            if (eraser.isSelected()) {
                context.clearRect(x, y, size, size);
            } else {
                context.setFill(colorPicker.getValue());
                context.fillOval(x, y, size, size);
            }
        });
    }

    /**
     * When a new value is selected in the Shape ComboBox, a new mouse action event will be created
     * depending on the selected shape, after eliminating previous mouse events
     */
    public void drawShape() {
        context = canvas.getGraphicsContext2D();
        if (!shapes.getValue().equals("Line")) {
            canvas.setOnMouseDragged(null);
            canvas.setOnMousePressed(event -> {
                double size = Double.parseDouble(brushSize.getText());
                double x = event.getX();
                double y = event.getY();

                context.setFill(colorPicker.getValue());
                if (shapes.getValue().equals("Circle")) {
                    context.fillOval(x, y, 100, 100);
                }
                else if (shapes.getValue().equals("Oval")) {
                    context.fillOval(x, y, 200, 100);
                }
                else if(shapes.getValue().equals("Rectangle")) {
                    context.fillRect(x, y, 200, 100);
                } else {
                    context.fillRect(x, y, 100, 100);
                }
            });
        } else {
            canvas.setOnMousePressed(null);
            initialize();
        }
    }

    /**
     * Not yet implemented
     */
    public void load() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        Stage stage = (Stage) borderPane.getScene().getWindow();
        File paintingFile = fileChooser.showOpenDialog(stage);
    }

    /**
     * When the 'Save' option is selected in the menu, the FileChooser will prompt the user for a new PNG file
     * name and a location where it will be saved, then the drawing in the canvas is transformed into an image
     * that is then written to the new file
     */
    public void save() {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        Stage stage = (Stage) borderPane.getScene().getWindow();
        File paintingFile = fileChooser.showSaveDialog(stage);
        if (paintingFile != null) {
            Image painting = canvas.snapshot(null, null);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(painting, null), "png", paintingFile);
            } catch (IOException ioe) {
                System.out.println("Unexpected error writing to file!" + ioe.getMessage());
            }
        }
    }

    /**
     * When the 'Reset' option is selected in the menu, the canvas gets completely cleared
     */
    public void reset() {
        context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void exit() {
        Platform.exit();
    }
}
