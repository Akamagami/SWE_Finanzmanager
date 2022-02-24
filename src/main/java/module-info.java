module com.example.swe_finanzmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires java.xml;


    opens com.example.swe_finanzmanager to javafx.fxml;
    exports com.example.swe_finanzmanager;
}