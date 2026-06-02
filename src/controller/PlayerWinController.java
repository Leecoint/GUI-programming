package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Player;

public class PlayerWinController extends Controller<Player> {

    @FXML
    public String getWinner() {
        return getPlayerWin().getName()+" wins!";
    }

    @FXML
    public String getWinnerHealth() {
        return "Total health: "+getPlayerWin().totalHealthProperty().getValue().toString();
    }

    @FXML
    public void handleClose(ActionEvent event) {
        stage.close();
    }

    public Player getPlayerWin() {  return model;}
}
