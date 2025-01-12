package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StarClass implements gameElements {
    private ImageView star;
    public StarClass(double posY) throws FileNotFoundException {
        Image starImage = new Image(new FileInputStream("library/star.jpeg"));
        star = new ImageView(starImage);
        star.setLayoutX(205);
        star.setLayoutY(posY - 20);
        star.setFitHeight(40);
        star.setFitWidth(40);
        star.setPreserveRatio(true);
    }
    public ImageView getStar(){
        return star;
    }
    @Override
    public boolean checkContact(Ball gameBall) {
        return (star.getLayoutY() + 40) >= gameBall.getBall().getLayoutY();
    }
}
