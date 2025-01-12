package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class menuAnimation {
    int posX;
    int posY;
    Group circleObstacle1;
    Group circleObstacle2;
    Group circleObstacle3;
    public menuAnimation(int posX,int posY){
        Color purpleColor = Color.rgb(141,20,249);
        Color yellowColor = Color.rgb(245,224,13);
        Color cyanColor = Color.rgb(54,225,243);
        Color magentaColor = Color.rgb(255,0,128);
        this.posX = posX;
        this.posY = posY;

        Arc arc1 =new Arc();
        arc1.setStroke(purpleColor);
        arc1.setStrokeWidth(10);
        arc1.setCenterX(posX);
        arc1.setCenterY(posY);
        arc1.setRadiusX(61);
        arc1.setRadiusY(61);
        arc1.setStartAngle(270.0f);
        arc1.setLength(90.0f);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setType(ArcType.OPEN);

        Arc arc2 = new Arc();
        arc2.setStroke(yellowColor);
        arc2.setStrokeWidth(10);
        arc2.setCenterX(posX);
        arc2.setCenterY(posY);
        arc2.setRadiusX(61);
        arc2.setRadiusY(61);
        arc2.setStartAngle(180.0f);
        arc2.setLength(90.0f);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setType(ArcType.OPEN);

        Arc arc3 = new Arc();
        arc3.setStroke(cyanColor);
        arc3.setStrokeWidth(10);
        arc3.setCenterX(posX);
        arc3.setCenterY(posY);
        arc3.setRadiusX(61);
        arc3.setRadiusY(61);
        arc3.setStartAngle(90.0f);
        arc3.setLength(90.0f);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setType(ArcType.OPEN);

        Arc arc4 = new Arc();
        arc4.setStroke(magentaColor);
        arc4.setStrokeWidth(10);
        arc4.setCenterX(posX);
        arc4.setCenterY(posY);
        arc4.setRadiusX(61);
        arc4.setRadiusY(61);
        arc4.setStartAngle(0.0f);
        arc4.setLength(90.0f);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setType(ArcType.OPEN);

        circleObstacle1 = new Group();
        circleObstacle1.getChildren().addAll(arc1,arc2,arc3,arc4);

        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(Animation.INDEFINITE);
        rotate.setDuration(Duration.INDEFINITE);
        rotate.setAutoReverse(false);
        rotate.setRate(0.10);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setNode(circleObstacle1);
        rotate.play();

        //Circle2


        Arc arc1a =new Arc();
        arc1a.setStroke(purpleColor);
        arc1a.setStrokeWidth(15);
        arc1a.setCenterX(posX);
        arc1a.setCenterY(posY);
        arc1a.setRadiusX(80);
        arc1a.setRadiusY(80);
        arc1a.setStartAngle(270.0f);
        arc1a.setLength(90.0f);
        arc1a.setFill(Color.TRANSPARENT);
        arc1a.setType(ArcType.OPEN);

        Arc arc2a = new Arc();
        arc2a.setStroke(yellowColor);
        arc2a.setStrokeWidth(15);
        arc2a.setCenterX(posX);
        arc2a.setCenterY(posY);
        arc2a.setRadiusX(80);
        arc2a.setRadiusY(80);
        arc2a.setStartAngle(180.0f);
        arc2a.setLength(90.0f);
        arc2a.setFill(Color.TRANSPARENT);
        arc2a.setType(ArcType.OPEN);

        Arc arc3a = new Arc();
        arc3a.setStroke(cyanColor);
        arc3a.setStrokeWidth(15);
        arc3a.setCenterX(posX);
        arc3a.setCenterY(posY);
        arc3a.setRadiusX(80);
        arc3a.setRadiusY(80);
        arc3a.setStartAngle(90.0f);
        arc3a.setLength(90.0f);
        arc3a.setFill(Color.TRANSPARENT);
        arc3a.setType(ArcType.OPEN);

        Arc arc4a = new Arc();
        arc4a.setStroke(magentaColor);
        arc4a.setStrokeWidth(15);
        arc4a.setCenterX(posX);
        arc4a.setCenterY(posY);
        arc4a.setRadiusX(80);
        arc4a.setRadiusY(80);
        arc4a.setStartAngle(0.0f);
        arc4a.setLength(90.0f);
        arc4a.setFill(Color.TRANSPARENT);
        arc4a.setType(ArcType.OPEN);

        circleObstacle2 = new Group();
        circleObstacle2.getChildren().addAll(arc1a,arc2a,arc3a,arc4a);

        RotateTransition rotate2 = new RotateTransition();
        rotate2.setAxis(Rotate.Z_AXIS);
        rotate2.setByAngle(360);
        rotate2.setCycleCount(Animation.INDEFINITE);
        rotate2.setDuration(Duration.millis(250));
        rotate2.setAutoReverse(true);
        rotate2.setRate(0.10);
        rotate2.setInterpolator(Interpolator.LINEAR);
        rotate2.setNode(circleObstacle2);
        rotate2.play();

        //Circle 3

        Arc arc1b =new Arc();
        arc1b.setStroke(purpleColor);
        arc1b.setStrokeWidth(18);
        arc1b.setCenterX(posX);
        arc1b.setCenterY(posY);
        arc1b.setRadiusX(103);
        arc1b.setRadiusY(103);
        arc1b.setStartAngle(270.0f);
        arc1b.setLength(90.0f);
        arc1b.setFill(Color.TRANSPARENT);
        arc1b.setType(ArcType.OPEN);

        Arc arc2b = new Arc();
        arc2b.setStroke(yellowColor);
        arc2b.setStrokeWidth(18);
        arc2b.setCenterX(posX);
        arc2b.setCenterY(posY);
        arc2b.setRadiusX(103);
        arc2b.setRadiusY(103);
        arc2b.setStartAngle(180.0f);
        arc2b.setLength(90.0f);
        arc2b.setFill(Color.TRANSPARENT);
        arc2b.setType(ArcType.OPEN);

        Arc arc3b = new Arc();
        arc3b.setStroke(cyanColor);
        arc3b.setStrokeWidth(18);
        arc3b.setCenterX(posX);
        arc3b.setCenterY(posY);
        arc3b.setRadiusX(103);
        arc3b.setRadiusY(103);
        arc3b.setStartAngle(90.0f);
        arc3b.setLength(90.0f);
        arc3b.setFill(Color.TRANSPARENT);
        arc3b.setType(ArcType.OPEN);

        Arc arc4b = new Arc();
        arc4b.setStroke(magentaColor);
        arc4b.setStrokeWidth(18);
        arc4b.setCenterX(posX);
        arc4b.setCenterY(posY);
        arc4b.setRadiusX(103);
        arc4b.setRadiusY(103);
        arc4b.setStartAngle(0.0f);
        arc4b.setLength(90.0f);
        arc4b.setFill(Color.TRANSPARENT);
        arc4b.setType(ArcType.OPEN);

        circleObstacle3 = new Group();
        circleObstacle3.getChildren().addAll(arc1b,arc2b,arc3b,arc4b);

        RotateTransition rotate3 = new RotateTransition();
        rotate3.setAxis(Rotate.Z_AXIS);
        rotate3.setByAngle(360);
        rotate3.setCycleCount(Animation.INDEFINITE);
        rotate3.setDuration(Duration.millis(500));
        rotate3.setAutoReverse(true);
        rotate3.setRate(0.10);
        rotate3.setInterpolator(Interpolator.LINEAR);
        rotate3.setNode(circleObstacle3);
        rotate3.play();
    }
}
