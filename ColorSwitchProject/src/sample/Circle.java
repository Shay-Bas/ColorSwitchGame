package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.security.Key;

public class Circle extends Obstacle{
    Group circleObstacle;
    public Circle(int posX,int posY,Object Orientation,Ball gameBall){
        Color purpleColor = Color.rgb(141,20,249);
        Color yellowColor = Color.rgb(245,224,13);
        Color cyanColor = Color.rgb(54,225,243);
        Color magentaColor = Color.rgb(255,0,128);
        this.setPosX(posX);
        this.setPosY(posY);
        this.setOrientation(Orientation);
        this.setGameBall(gameBall);
        Arc arc1 =new Arc();
        arc1.setStroke(purpleColor);
        arc1.setStrokeWidth(10);
        arc1.setCenterX(posX);
        arc1.setCenterY(posY);
        arc1.setRadiusX(90.0f);
        arc1.setRadiusY(90.0f);
        arc1.setStartAngle(270.0f);
        arc1.setLength(90.0f);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setType(ArcType.OPEN);

        Arc arc2 = new Arc();
        arc2.setStroke(yellowColor);
        arc2.setStrokeWidth(10);
        arc2.setCenterX(posX);
        arc2.setCenterY(posY);
        arc2.setRadiusX(90.0f);
        arc2.setRadiusY(90.0f);
        arc2.setStartAngle(180.0f);
        arc2.setLength(90.0f);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.OPEN);

        Arc arc3 = new Arc();
        arc3.setStroke(cyanColor);
        arc3.setStrokeWidth(10);
        arc3.setCenterX(posX);
        arc3.setCenterY(posY);
        arc3.setRadiusX(90.0f);
        arc3.setRadiusY(90.0f);
        arc3.setStartAngle(90.0f);
        arc3.setLength(90.0f);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setType(ArcType.OPEN);

        Arc arc4 = new Arc();
        arc4.setStroke(magentaColor);
        arc4.setStrokeWidth(10);
        arc4.setCenterX(posX);
        arc4.setCenterY(posY);
        arc4.setRadiusX(90.0f);
        arc4.setRadiusY(90.0f);
        arc4.setStartAngle(0.0f);
        arc4.setLength(90.0f);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setType(ArcType.OPEN);

        circleObstacle = new Group();
        circleObstacle.getChildren().addAll(arc1,arc2,arc3,arc4);
        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.INDEFINITE);
        rotate.setAutoReverse(false);
        rotate.setRate(0.10);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setNode(circleObstacle);
        rotate.play();
    }
    @Override
    public void moveDown(){
        circleObstacle.setLayoutY(circleObstacle.getLayoutY() + 20);
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
        return null;
    }
}
