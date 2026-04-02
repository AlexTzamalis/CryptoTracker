module me.alextzamalis.cryptotracker.cryptotracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires eu.hansolo.tilesfx;

    opens me.alextzamalis.cryptotracker to javafx.fxml;
    exports me.alextzamalis.cryptotracker;
}