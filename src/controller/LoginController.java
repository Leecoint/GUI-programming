package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

//Note that this class is not complete!
//This will only add 2 players to the game.
public class LoginController extends Controller<LoginModel> {

    @FXML private TextField player1Tf;
    @FXML private TextField player2Tf;
    @FXML private TextField player3Tf;
    @FXML private TextField player4Tf;

    @FXML
    private void initialize() {
        player1Tf.setText("Davey");
        player2Tf.setText("Jenny");
        // player3Tf.setText("Texting"); //need to remove
        player1Tf.setEditable(false);
        player2Tf.setEditable(false);
    }

    private LoginModel getLoginModel() {
        return model;
    }

    private boolean hasPlayer(TextField playername) {
        return !(playername.getText().equals(""));
    }

    @FXML
    private void handleStart(ActionEvent event) {
        getLoginModel().getPlayers().clear();
        getLoginModel().addToGame(new Player(player1Tf.getText()));
        getLoginModel().addToGame(new Player(player2Tf.getText()));
        if(hasPlayer(player3Tf))    getLoginModel().addToGame(new Player(player3Tf.getText()));
        if(hasPlayer(player4Tf))    getLoginModel().addToGame(new Player(player4Tf.getText()));
        if(getLoginModel().getPlayers().size() <= 2)    throw new RuntimeException("more player needed");
        ViewLoader.showStage(new Dealer(getLoginModel().getPlayers()), "/view/DealerView.fxml", "Dealer", stage);
        for (Player player : getLoginModel().getPlayers()) 
            ViewLoader.showStage(player, "/view/PlayerView.fxml", player.getName(), new Stage());
    }
}
