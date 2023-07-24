module com.example.rent_a_car_oop2 {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    //requires mysql.connector.java;  ne raboti?!?!

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires postgresql;

    opens com.example.rent_a_car_oop2 to javafx.fxml;
    exports com.example.rent_a_car_oop2;
    /*original:
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.rent_a_car_oop2 to javafx.fxml;
    exports com.example.rent_a_car_oop2;*/
}