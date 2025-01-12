package sample;

import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

class ObstacleSerialize implements Serializable {
        int posY;
        double rotationSpeed;
        String type;

}
public abstract class Obstacle {
    private int posX;
    private double posY;
    private Object Orientation;
    private Ball gameBall;
    public Timeline rotationTimeline;
    public Group parts;
    public String type;
    protected Rotate rotate;
    protected static double rotationSpeed = 2800;
    protected static double downValue = 2;
    public abstract void moveDown();
    public int getPosX() {
        return posX;
    }
    public static void updateDownValue(){
        rotationSpeed = rotationSpeed - 20;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Object getOrientation() {
        return Orientation;
    }

    public void setOrientation(Object orientation) {
        Orientation = orientation;
    }

    public Ball getGameBall() {
        return gameBall;
    }

    public Group getComponents(){
        return parts;
    };

    public void setGameBall(Ball gameBall) {
        this.gameBall = gameBall;
    }

    public void pauseTimeline(){
        this.rotationTimeline.pause();
    }
    public void resumeTimeline(){
        this.rotationTimeline.play();
    }

    public abstract Object getPosition();
    public abstract Object getNewOrientation();
    public abstract Boolean checkCollision();

    public ObstacleSerialize getserializableObject(){
        ObstacleSerialize ob = new ObstacleSerialize();
        ob.type = this.type;
        ob.posY = (int)this.getPosY();
        ob.rotationSpeed = Obstacle.rotationSpeed;
        return ob;
    }
}
