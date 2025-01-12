package sample;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.security.Key;

public class SquareTest extends Obstacle{
    Group squareObstacle;
    Line line1,line2,line3,line4;
    Line Lines[] = {line1,line2,line3,line4};
    public SquareTest(int posX, int posY, Object Orientation, Ball gameBall){
        Color purpleColor = Color.rgb(141,20,249);
        Color yellowColor = Color.rgb(245,224,13);
        Color cyanColor = Color.rgb(54,225,243);
        Color magentaColor = Color.rgb(255,0,128);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setOrientation(Orientation);
        this.setGameBall(gameBall);

        Rectangle r1 = new Rectangle();

        line1 = new Line();
        line1.setStrokeWidth(10);
        line1.setStroke(purpleColor);
        line1.setStartY(posY+90);
        line1.setEndY(posY-90);
        line1.setStartX(posX + 90);
        line1.setEndX(posX + 90);
        line1.setId("purple");

        line2 = new Line();
        line2.setStrokeWidth(10);
        line2.setStroke(yellowColor);
        line2.setStartY(posY+90);
        line2.setEndY(posY+90);
        line2.setStartX(posX + 90);
        line2.setEndX(posX - 90);
        line2.setId("yellow");

        line3 = new Line();
        line3.setStrokeWidth(10);
        line3.setStroke(cyanColor);
        line3.setStartY(posY+90);
        line3.setEndY(posY-90);
        line3.setStartX(posX - 90);
        line3.setEndX(posX - 90);
        line3.setId("cyan");

        line4 = new Line();
        line4.setStrokeWidth(10);
        line4.setStroke(magentaColor);
        line4.setStartY(posY-90);
        line4.setEndY(posY-90);
        line4.setStartX(posX + 90);
        line4.setEndX(posX - 90);
        line4.setId("magenta");

        Rotate rotate = new Rotate(15.0f,posX,posY);
        Lines[0].getTransforms().add(rotate);
        line2.getTransforms().add(rotate);
        line3.getTransforms().add(rotate);
        line4.getTransforms().add(rotate);
        Timeline timelineRot = new Timeline();
        KeyFrame key1 = new KeyFrame(
                new javafx.util.Duration(0),
                new KeyValue(rotate.angleProperty(),0)
        );
        KeyFrame key2 =new KeyFrame(
                new javafx.util.Duration(2500),
                new KeyValue(rotate.angleProperty(),360)
        );
        timelineRot.setCycleCount(Animation.INDEFINITE);
        timelineRot.getKeyFrames().addAll(key1,key2);
        timelineRot.playFromStart();

        checkCollision();
    }
    public int getY(){
        return 0;
    }
    @Override
    public void moveDown(){
        Lines[0].setLayoutY(Lines[0].getLayoutY() + 20);
        line2.setLayoutY(line2.getLayoutY() + 20);
        line3.setLayoutY(line3.getLayoutY() + 20);
        line4.setLayoutY(line4.getLayoutY() + 20);

    }
    @Override
    public Object getPosition() {
        return null;
    }

    @Override
    public Object getNewOrientation() {
        return null;
    }

    @Override
    public Boolean checkCollision() {
        Timeline collision = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                for(Line line: Lines){
                    if (line.getBoundsInParent().intersects(getGameBall().getBall().getBoundsInParent())) {
                        if(line.getId().equals("purple")){
                            System.out.println("With purple");
                        }
                    }

                }
            }
        }));
        collision.setCycleCount(Timeline.INDEFINITE);
        collision.play();
        return null;

    }
}
