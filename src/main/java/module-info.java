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
    requires java.persistence;


    opens com.papasmurfie.rent_a_car_oop2 to javafx.fxml, org.hibernate.commons.annotations;
    exports com.papasmurfie.rent_a_car_oop2;
    exports com.papasmurfie.rent_a_car_oop2.controllers;
    opens com.papasmurfie.rent_a_car_oop2.controllers to javafx.fxml;
    exports com.papasmurfie.rent_a_car_oop2.models;
    opens com.papasmurfie.rent_a_car_oop2.models to javafx.fxml;
    opens com.papasmurfie.rent_a_car_oop2.entity;
    exports com.papasmurfie.rent_a_car_oop2.helpers;
    opens com.papasmurfie.rent_a_car_oop2.helpers to javafx.fxml, org.hibernate.commons.annotations;
    exports com.papasmurfie.rent_a_car_oop2.controllers.admin;
    opens com.papasmurfie.rent_a_car_oop2.controllers.admin to javafx.fxml;
    exports com.papasmurfie.rent_a_car_oop2.controllers.login;
    opens com.papasmurfie.rent_a_car_oop2.controllers.login to javafx.fxml;


}