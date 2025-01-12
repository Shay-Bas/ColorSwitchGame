package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class colorPalleteClass implements gameElements {
    private ImageView colorPallete;
    public colorPalleteClass(double posY) throws FileNotFoundException {
        Image colorPaleteImage = new Image(new FileInputStream("library/colorPallete.jpeg"));
        colorPallete = new ImageView(colorPaleteImage);
        colorPallete.setLayoutX(205);
        colorPallete.setY(posY-230);
        colorPallete.setFitHeight(40);
        colorPallete.setFitWidth(40);
        colorPallete.setPreserveRatio(true);
    }

    public ImageView getColorPallete() {
        return colorPallete;
    }

    @Override
    public boolean checkContact(Ball gameBall) {
        return (colorPallete.getLayoutY() + 40) >= gameBall.getBall().getLayoutY();
    }
}
