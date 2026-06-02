package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Card;

public class CardController extends Controller<Card> {
    @FXML private ImageView cardIv;

    @FXML
    public void initialize() {
        System.out.println(getPath());
        cardIv.setImage(new Image(getClass().getResourceAsStream(getPath())));
    }

    public String getPath() {
        return "/view/image/cards/"+getCard().getName().replace(" ", "").toLowerCase()+".png";
    }

    @FXML
    public void handleClose() {
        stage.close();
    }
    
    public Card getCard() {return model;}
}
