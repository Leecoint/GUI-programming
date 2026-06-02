package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Deck;
import model.Card;

public class DeckController extends Controller<Deck> {
    @FXML private ListView<Card> cardLv;
    @FXML private Button showBtn;


    public Deck getDeck() {
        return model;
    }
    
    @FXML
    public void initialize() {
        cardLv.setItems(getDeck().getCards());
        cardLv.getSelectionModel().selectedItemProperty().addListener((o, oldCard, newCard) ->  showBtn.setDisable(newCard == null));
    }

    @FXML
    private void handleShow(ActionEvent event) {
        ViewLoader.showStage(cardLv.getSelectionModel().getSelectedItem(), "/view/CardView.fxml", cardLv.getSelectionModel().getSelectedItem().getName(), new Stage());
    }

    @FXML
    public void handleClose(ActionEvent event) {
        stage.close();
    }
}
