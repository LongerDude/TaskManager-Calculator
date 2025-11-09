module com.longerdude.taskmanagerandcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires jdk.unsupported.desktop;

    opens com.longerdude.taskmanagerandcalculator to javafx.fxml;
    exports com.longerdude.taskmanagerandcalculator;
}