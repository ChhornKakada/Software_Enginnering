module i4.gic.tic {
    requires javafx.controls;
    requires javafx.fxml;

    opens i4.gic.tic to javafx.fxml;
    exports i4.gic.tic;
}
