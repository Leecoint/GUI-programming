package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Card;
import model.Player;


public class PlayerController extends Controller<Player> {
    @FXML private TableView<Card> playersTv;
    @FXML private ListView<Card> dealLv;
    @FXML private Button PlaceBtn;
    @FXML private Button SelectBtn;
    @FXML private ImageView CardIv;
    @FXML private TableColumn<Card, String> NameClm;
    @FXML private TableColumn<Card, String> AttackClm;
    @FXML private TableColumn<Card, String> StyleClm;
    @FXML private TableColumn<Card, String> HealthClm;


    private Player getPlayer() {    return model; }

    @FXML
    public void handleSelect(ActionEvent event){
        getPlayer().select(playersTv.getSelectionModel().selectedItemProperty().getValue());
        System.out.println("selected successful");
    }

    public String getPath(Card card) {
        if(card == null)    return "/view/image/cards/empty.png";
        return "/view/image/cards/"+card.getName().replace(" ", "").toLowerCase()+".png";
    }

    @FXML
    public void handlePlace(ActionEvent event) {
        try{
        getPlayer().place(dealLv.getSelectionModel().selectedItemProperty().getValue());
        } catch (Exception e){
            ViewLoader.showStage(e, "/view/ErrorView.fxml", "Error", new Stage());
        }
    }

    @FXML
    private void initialize() {
        playersTv.setItems(getPlayer().getHand());
        dealLv.setItems(getPlayer().getTempHand());

        NameClm.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        AttackClm.setCellValueFactory(cellData -> cellData.getValue().attackProperty().asString());
        StyleClm.setCellValueFactory(cellData -> cellData.getValue().styleProperty());
        HealthClm.setCellValueFactory(cellData -> cellData.getValue().healthProperty().asString());

        playersTv.getSelectionModel().selectedItemProperty().addListener((o, oldAct, newAct) ->  SelectBtn.setDisable(newAct == null));
        dealLv.getSelectionModel().selectedItemProperty().addListener((o, oldAct, newAct) ->  PlaceBtn.setDisable(newAct == null));
        getPlayer().selectedCardProperty().addListener((o, oldCard, newCard) -> CardIv.setImage(new Image(getClass().getResourceAsStream(getPath(newCard)))) );
    }
}
