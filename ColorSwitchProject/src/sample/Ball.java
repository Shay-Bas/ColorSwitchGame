package sample;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.util.Duration;

import java.io.File;
import java.io.Serializable;


class ballSerialize implements Serializable{
    int posY;
    int score;
    String color;

    public ballSerialize(int posY,int score,String color){
        this.posY = posY;
        this.score = score;
        this.color = color;
    }
}
public class Ball {

    private int posX = 225;
    private int posY;
    private float velocity = 0;
    private int color;
    private int score = 0;
    private int start = -1;
    private Circle ball;
    AudioClip mediaPlayer;
    public static Color purpleColor = Color.rgb(141,20,249);
    public static Color yellowColor = Color.rgb(245,224,13);
    public static Color cyanColor = Color.rgb(54,225,243);
    public static Color magentaColor = Color.rgb(255,0,128);


    public double currentY;

    public float getVelocity() {
        return velocity;
    }



    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Circle getBall() {
        return ball;
    }

    public void setBall(Circle ball) {
        this.ball = ball;
    }

    public Ball(Group root, Scene gameplayScene, ImageView imageView,int posY,String Color){

            this.posY = posY;
            ball = new Circle(10.0f);
            ball.setLayoutX(posX);
            ball.setLayoutY(posY);
            ball.setId(Color);
            switch (Color) {
                case "magenta" -> ball.setFill(magentaColor);
                case "cyan" -> ball.setFill(cyanColor);
                case "purple" -> ball.setFill(purpleColor);
                case "yellow" -> ball.setFill(yellowColor);
            }
            root.getChildren().add(ball);

            gameplayScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {

                    if(start<0) {
                        Main.timeLine.play();
                        start = 0;
                        if(imageView != null) {
                            root.getChildren().remove(imageView);
                        }
                    }
                    Main.timeLine.play();
                    if(keyEvent.getCode() == KeyCode.SPACE){
                        velocity = -4;
                        mediaPlayer.play();

                    }
                }
            });
            calculatePosition();
            playSound();


    }



    public int calculatePosition(){
        final Task task = new Task() {

            @Override
            protected Object call() throws Exception {
                currentY = ball.getLayoutY();
                // System.out.println("CurrenY:"+currentY);
                Timeline updateCurrY = new Timeline(new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        currentY = ball.getLayoutY();
                    }
                }));
                updateCurrY.setCycleCount(Timeline.INDEFINITE);
                updateCurrY.play();
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();

//
        return 0;
    }
    public void changePosition(){

    }
    public void playSound(){
        String path  = "library/jump.mp3";
        mediaPlayer = new AudioClip(new File(path).toURI().toString());


    }
    public ballSerialize getSerializableObject(){
        ballSerialize b = new ballSerialize((int)this.ball.getLayoutY(),this.getScore(),this.ball.getId());
        return b;
    }

}
