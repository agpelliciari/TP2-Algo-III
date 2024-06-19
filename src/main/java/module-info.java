module tp2.clases {
    requires com.google.gson;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.graphics;
    opens tp2.clases to com.google.gson;
    exports tp2.clases;
}