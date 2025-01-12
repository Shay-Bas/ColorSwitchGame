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


public class doubleCircle extends Obstacle{

    Arc Components[];
    Stage primaryStage;

    public doubleCircle(int posX,int posY,Object Orientation,Ball gameBall,Stage primaryStage){
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
        this.type = "doubleCircle";
        Arc arc1 =new Arc();
        arc1.setStroke(purpleColor);
        arc1.setId("purple");
        arc1.setStrokeWidth(10);
        arc1.setCenterX(posX);
        arc1.setCenterY(posY);
        arc1.setRadiusX(radius);
        arc1.setRadiusY(radius);
        arc1.setStartAngle(270.0f);
        arc1.setLength(90.0f);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setType(ArcType.OPEN);

        Arc arc1a =new Arc();
        arc1a.setStroke(purpleColor);
        arc1a.setId("purple");
        arc1a.setStrokeWidth(10);
        arc1a.setCenterX(posX);
        arc1a.setCenterY(posY);
        arc1a.setRadiusX(radius  + 20);
        arc1a.setRadiusY(radius + 20);
        arc1a.setStartAngle(270.0f);
        arc1a.setLength(90.0f);
        arc1a.setFill(Color.TRANSPARENT);
        arc1a.setType(ArcType.OPEN);

        Arc arc2 = new Arc();
        arc2.setStroke(yellowColor);
        arc2.setId("yellow");
        arc2.setStrokeWidth(10);
        arc2.setCenterX(posX);
        arc2.setCenterY(posY);
        arc2.setRadiusX(radius);
        arc2.setRadiusY(radius);
        arc2.setStartAngle(180.0f);
        arc2.setLength(90.0f);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.OPEN);

        Arc arc2a = new Arc();
        arc2a.setStroke(yellowColor);
        arc2a.setId("yellow");
        arc2a.setStrokeWidth(10);
        arc2a.setCenterX(posX);
        arc2a.setCenterY(posY);
        arc2a.setRadiusX(radius + 20);
        arc2a.setRadiusY(radius + 20);
        arc2a.setStartAngle(180.0f);
        arc2a.setLength(90.0f);
        arc2a.setFill(Color.TRANSPARENT);
        arc2a.setType(ArcType.OPEN);

        Arc arc3 = new Arc();
        arc3.setStroke(cyanColor);
        arc3.setId("cyan");
        arc3.setStrokeWidth(10);
        arc3.setCenterX(posX);
        arc3.setCenterY(posY);
        arc3.setRadiusX(radius);
        arc3.setRadiusY(radius);
        arc3.setStartAngle(90.0f);
        arc3.setLength(90.0f);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setType(ArcType.OPEN);


        Arc arc3a = new Arc();
        arc3a.setStroke(cyanColor);
        arc3a.setId("cyan");
        arc3a.setStrokeWidth(10);
        arc3a.setCenterX(posX);
        arc3a.setCenterY(posY);
        arc3a.setRadiusX(radius + 20);
        arc3a.setRadiusY(radius + 20);
        arc3a.setStartAngle(90.0f);
        arc3a.setLength(90.0f);
        arc3a.setFill(Color.TRANSPARENT);
        arc3a.setType(ArcType.OPEN);

        Arc arc4 = new Arc();
        arc4.setStroke(magentaColor);
        arc4.setId("magenta");
        arc4.setStrokeWidth(10);
        arc4.setCenterX(posX);
        arc4.setCenterY(posY);
        arc4.setRadiusX(radius);
        arc4.setRadiusY(radius);
        arc4.setStartAngle(0.0f);
        arc4.setLength(90.0f);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setType(ArcType.OPEN);

        Arc arc4a = new Arc();
        arc4a.setStroke(magentaColor);
        arc4a.setId("magenta");
        arc4a.setStrokeWidth(10);
        arc4a.setCenterX(posX);
        arc4a.setCenterY(posY);
        arc4a.setRadiusX(radius  + 20);
        arc4a.setRadiusY(radius + 20);
        arc4a.setStartAngle(0.0f);
        arc4a.setLength(90.0f);
        arc4a.setFill(Color.TRANSPARENT);
        arc4a.setType(ArcType.OPEN);

        Components = new Arc[8];
        Components[0] = arc1;
        Components[1] = arc1a;
        Components[2] = arc2;
        Components[3] = arc2a;
        Components[4] = arc3;
        Components[5] = arc3a;
        Components[6] = arc4;
        Components[7] = arc4a;




        parts= new Group();
        parts.getChildren().addAll(arc1,arc1a,arc2,arc2a,arc3,arc3a,arc4,arc4a);

        rotate = new Rotate(15.0f,posX,posY);

        arc1.getTransforms().add(rotate);
        arc2.getTransforms().add(rotate);
        arc3.getTransforms().add(rotate);
        arc4.getTransforms().add(rotate);
        arc1a.getTransforms().add(rotate);
        arc2a.getTransforms().add(rotate);
        arc3a.getTransforms().add(rotate);
        arc4a.getTransforms().add(rotate);

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
                    if(((Path) Shape.intersect(getGameBall().getBall(),arc)).getElements().size() > 0){

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
