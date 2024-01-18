module com.example.rent_a_car_oop2 {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires postgresql;
    requires org.apache.logging.log4j;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens com.papasmurfie.rent_a_car_oop2 to javafx.fxml, org.hibernate.commons.annotations;
    exports com.papasmurfie.rent_a_car_oop2;
    exports com.papasmurfie.rent_a_car_oop2.controllers;
    opens com.papasmurfie.rent_a_car_oop2.controllers to javafx.fxml;
    exports com.papasmurfie.rent_a_car_oop2.models;
    opens com.papasmurfie.rent_a_car_oop2.models to javafx.fxml;
    opens com.papasmurfie.rent_a_car_oop2.entities;


}