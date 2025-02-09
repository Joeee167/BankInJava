module com.example.bankproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.bankproject to javafx.fxml;
    exports com.example.bankproject;
}