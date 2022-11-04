module com.example.ravendecoder {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires org.junit.jupiter.api;


    opens com.example.ravendecoder to javafx.fxml;
    exports com.example.ravendecoder;
}