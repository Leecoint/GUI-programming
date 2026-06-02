package controller;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.Collections;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Dealer;
import model.Deck;
import model.Player;

public class DealerController extends Controller<Dealer> {
    @FXML private Text P1Score;
    @FXML private Text P2Score;
    @FXML private Text P3Score;
    @FXML private Text P4Score;
    @FXML private Button CallBtn;
    @FXML private Button DealBtn;
    @FXML private Button PlayBtn;
    @FXML private Button ExitBtn;

    @FXML
    private void initialize() {
        P1Score.textProperty().bind(getDealer().getPlayers().get(0).totalHealthProperty().asString());
        P2Score.textProperty().bind(getDealer().getPlayers().get(1).totalHealthProperty().asString());
        if(getDealer().getPlayers().size()>=3) P3Score.textProperty().bind(getDealer().getPlayers().get(2).totalHealthProperty().asString());
        if(getDealer().getPlayers().size()==4) P4Score.textProperty().bind(getDealer().getPlayers().get(3).totalHealthProperty().asString());
        
    }

    public Dealer getDealer() {
        return model;
    }

    private Player currentWinner() {
        int max = 0;
        Player winner = getDealer().getPlayers().get(0);
        for (Player t:getDealer().getPlayers()){
            if(max < t.totalHealthProperty().intValue()){
                max = t.totalHealthProperty().intValue();
                winner = t;
            }
        }
        return winner;
    }

    @FXML
    private void handleCall(ActionEvent event) {
        ViewLoader.showStage(currentWinner(), "/view/PlayerWinView.fxml", "Game Over", new Stage());
    }

    @FXML
    private void handleDeal(ActionEvent event) {
        try{
            getDealer().deal();
        } catch (Exception e){
            ViewLoader.showStage(e, "/view/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML
    private void handlePlay(ActionEvent event) {
        try{
            getDealer().play();
        } catch (Exception e) {
            ViewLoader.showStage(e, "/view/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void handleMainDeckClick(MouseEvent event) {
        ViewLoader.showStage(getDealer().getMainDeck(), "/view/DeckView.fxml", "Main Deck", new Stage());
    }

    @FXML
    private void handleSecDeckClick(MouseEvent event) {
        ViewLoader.showStage(getDealer().getSecondaryDeck(), "/view/DeckView.fxml", "Secondary Deck", new Stage());
    }
}
