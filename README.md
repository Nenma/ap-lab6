# ap-lab6

## Overview
At the moment, the app contains:
- A main frame consisting in a BorderPane
- A configuration panel at the top of the main frame containing:
  - A Label and a TextField for choosing brush size
  - A ColorPicker for choosing brush color
  - A Label and a ComboBox for drawing shapes
  - A CheckBox for deciding whether to draw or to erase
- A canvas in the center of the main frame in which the user can draw respecting the selected configurations among the ones above
- A control panel at the bottom of the frame with working *Save*, *Reset* and *Exit* options, *Load* is not yet implemented

The app is made using JavaFX and, at the moment, it is only composed of a Main and a Controller class along with a FXML file:
- The Main class launches the app window
- The Controller class handles the user interation and events
- The sample.fxml file models the structure of the main frame


## Optional
The tasks are:
- Add support for drawing multiple types of components. Consider creating a new panel, containing a *list* of available shapes. The configuration panel must adapt according to the type of the selected shape. Implement at least two types of shapes.
- Use a *file chooser* in order to specify the file where the image will be saved (or load).
- Implement the *retained mode* drawing and add support for deleting shapes.

## Compulsory
The tasks are:
Create the following components:
- The *main frame* of the application.
- A *configuration panel* for introducing parameters regarding the shapes that will be drawn: the size, the number of sides, the stroke, etc.
The panel must be placed at the *top* part of the frame. The panel must contain at least one label and one input component for specifying the size of the component.
- A *canvas* (*drawing panel*) for drawing various types of shapes: circles, squares, regular polygons, snow flakes, etc. You must implement at least one shape type. This panel must be placed in the *center* part of the frame.
When the users executes mouse pressed operation, a shape must be drawn at the mouse location. You must use the properties defined in the configuration panel (at least one) and generate random values for others (color, etc.).
- A *control panel* for managing the image being created. This panel will contains the buttons: *Load*, *Save*, *Reset*, *Exit* and it will be placed at the *bottom* part of the frame.
