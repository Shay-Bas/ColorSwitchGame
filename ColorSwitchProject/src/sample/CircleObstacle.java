package sample;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.geometry.Bounds;
import javafx.scene.Group;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;


public class CircleObstacle extends Obstacle{

    public Arc Components[];
    Stage primaryStage;
    Rotate rotate;
    public CircleObstacle(int posX, int posY, Object Orientation, Ball gameBall, Stage primaryStage){
        Color purpleColor = Color.rgb(141,20,249);
        Color yellowColor = Color.rgb(245,224,13);
        Color cyanColor = Color.rgb(54,225,243);
        Color magentaColor = Color.rgb(255,0,128);
        double radius = 100.0;
        this.primaryStage = primaryStage;
        this.setPosX(posX);
        this.setPosY(posY);
        this.setOrientation(Orientation);
        this.setGameBall(gameBall);
        this.type = "CircleObstacle";
        Arc arc1 =new Arc();
        arc1.setStroke(purpleColor);
        arc1.setStrokeWidth(10);
        arc1.setCenterX(posX);
        arc1.setCenterY(posY);
        arc1.setRadiusX(radius);
        arc1.setRadiusY(radius);
        arc1.setStartAngle(270.0f);
        arc1.setLength(90.0f);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setType(ArcType.OPEN);
        arc1.setId("purple");

        Arc arc2 = new Arc();
        arc2.setStroke(yellowColor);
        arc2.setStrokeWidth(10);
        arc2.setCenterX(posX);
        arc2.setCenterY(posY);
        arc2.setRadiusX(radius);
        arc2.setRadiusY(radius);
        arc2.setStartAngle(180.0f);
        arc2.setLength(90.0f);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.OPEN);
        arc2.setId("yellow");

        Arc arc3 = new Arc();
        arc3.setStroke(cyanColor);
        arc3.setStrokeWidth(10);
        arc3.setCenterX(posX);
        arc3.setCenterY(posY);
        arc3.setRadiusX(radius);
        arc3.setRadiusY(radius);
        arc3.setStartAngle(90.0f);
        arc3.setLength(90.0f);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setType(ArcType.OPEN);
        arc3.setId("cyan");

        Arc arc4 = new Arc();
        arc4.setStroke(magentaColor);
        arc4.setStrokeWidth(10);
        arc4.setCenterX(posX);
        arc4.setCenterY(posY);
        arc4.setRadiusX(radius);
        arc4.setRadiusY(radius);
        arc4.setStartAngle(0.0f);
        arc4.setLength(90.0f);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setType(ArcType.OPEN);
        arc4.setId("magenta");

        Components = new Arc[4];
        Components[0] = arc1;
        Components[1] = arc2;
        Components[2] = arc3;
        Components[3] = arc4;


        parts = new Group();
        parts.getChildren().addAll(arc1,arc2,arc3,arc4);
        rotate = new Rotate(15.0f,posX,posY);

        arc1.getTransforms().add(rotate);
        arc2.getTransforms().add(rotate);
        arc3.getTransforms().add(rotate);
        arc4.getTransforms().add(rotate);
        rotationTimeline = new Timeline();
        KeyFrame key1 = new KeyFrame(
                new javafx.util.Duration(0),
                new KeyValue(rotate.angleProperty(),0)
        );
        KeyFrame key2 =new KeyFrame(
                new javafx.util.Duration(Obstacle.rotationSpeed),
                new KeyValue(rotate.angleProperty(),360)
        );
        rotationTimeline.setCycleCount(Animation.INDEFINITE);
        rotationTimeline.getKeyFrames().addAll(key1,key2);
        rotationTimeline.playFromStart();




        checkCollision();
    }
    @Override
    public void moveDown(){
        setPosY(getPosY() + Obstacle.downValue);
        rotate.setPivotY(getPosY());
        for(Arc arc:Components){
            arc.setCenterY(arc.getCenterY() + this.downValue);

        }

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
        Components[0].boundsInParentProperty().addListener((ChangeListener<? super Bounds>) new ChangeListener<Bounds>(){

            @Override
            public void changed(ObservableValue<? extends Bounds> observableValue, Bounds bounds, Bounds t1) {

                for(Arc arc:Components){
                    if(((Path)Shape.intersect(getGameBall().getBall(),arc)).getElements().size() > 0){

                        if(!getGameBall().getBall().getId().equals(arc.getId())){


                            Main.endgameScreen(primaryStage);
                        }
                    }
                }
            }
        });
        return null;
    }

}
