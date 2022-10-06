module com.example.ravendecoder {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens com.example.ravendecoder to javafx.fxml;
    exports com.example.ravendecoder;
}