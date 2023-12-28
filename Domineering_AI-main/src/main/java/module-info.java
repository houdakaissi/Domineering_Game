module com.example.domineering {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.domineering to javafx.fxml;
    exports com.example.domineering;
    //exports com.example.domineering;
    //opens com.example.domineering.Agent to javafx.fxml;
    //exports com.example.domineering.Position;
    //opens com.example.domineering.Position to javafx.fxml;
    //exports com.example.domineering;
    //opens com.example.domineering.GameSearch to javafx.fxml;
    //exports com.example.domineering.Move;
   // opens com.example.domineering.Move to javafx.fxml;
}