package controller;

import javax.swing.Action;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ErrorController extends Controller<Exception> {

    
    @FXML
    public String getExceptionType() {
        return getException().getClass().getSimpleName();
    }

    @FXML
    private void initialize() {
        System.out.println(getException().getClass().getSimpleName());
    }

    @FXML
    public void handleClose(ActionEvent event) {
        stage.close();
    }

    public Exception getException() {   return model; }
}

