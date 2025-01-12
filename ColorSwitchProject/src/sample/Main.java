package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main extends Application {
    public static ImageView star;
    public static ImageView colorPallete;
    public static Timeline timeLine;
    public static ArrayList<Obstacle> obstacleArrayList;
    public static Obstacle currentObstacle;
    public static Ball gameBall;
    public static boolean resumeGameVariable = false;
    public static Stage finalprimaryStage;
    public static Scene gamePlayScene;
    public static int selectedIndex = 0;
    public static MediaPlayer mediaPlayer;
    public static AudioClip starSound = new AudioClip(new File("library/star.mp3").toURI().toString());
    public static AudioClip switchSound = new AudioClip(new File("library/switch.mp3").toURI().toString());

    public static Text score;
    @Override
    public void start(Stage primaryStage) throws Exception{
        final Task task = new Task() {

            @Override
            protected Object call() throws Exception {
                String path  = "library/backgroundMusic.mp3";
                Media media = new Media(new File(path).toURI().toString());

                //Instantiating MediaPlayer class
                mediaPlayer = new MediaPlayer(media);


                //by setting this property to true, the audio will be played
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setVolume(0.5);
                mediaPlayer.setCycleCount(10);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        mainMenu(primaryStage);
        finalprimaryStage =primaryStage;
    }


    public static void main(String[] args)
    {
        launch(args);
    }
    public static void mainMenu(Stage primaryStage) throws FileNotFoundException {
        finalprimaryStage =primaryStage;
        Image image = new Image(new FileInputStream("library/logo.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setX(-78);
        imageView.setY(-30);
        imageView.setFitHeight(450);
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);

        Image playlogo = new Image(new FileInputStream("library/playLogo.png"));
        ImageView imageView2 = new ImageView(playlogo);
        imageView2.setX(175);
        imageView2.setY(150);
        imageView2.setFitHeight(100);
        imageView2.setFitWidth(100);
        imageView2.setPreserveRatio(true);
        Group root =   new Group(imageView);
        root.getChildren().add(imageView2);
        //Creating a scene for the mainScreen;
        Scene mainScreen = new Scene(root,450,650);
        Color backgroundColor = Color.rgb(41,41,41);
        mainScreen.setFill(backgroundColor);

        menuAnimation c1 = new menuAnimation(225,200);
        root.getChildren().addAll(c1.circleObstacle1,c1.circleObstacle2,c1.circleObstacle3);

        Button playButton = new Button();
        playButton.setText("Play");
        playButton.setMinSize(200,48);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    gameStart(finalprimaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        playButton.setLayoutX(132);
        playButton.setLayoutY(350);
        playButton.setStyle("-fx-background-color: #8d13fa;-fx-font-size: 1.5em; ");
        root.getChildren().add(playButton);


        //Resume Button
        Button resumeButton = new Button();
        resumeButton.setText("Resume");
        resumeButton.setMinSize(200,48);
        resumeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                savedStateScene(finalprimaryStage);
            }
        });
        resumeButton.setLayoutX(132);
        resumeButton.setLayoutY(450);
        resumeButton.setStyle("-fx-background-color: #f6df0b;-fx-font-size: 1.5em;  ");
        root.getChildren().add(resumeButton);

        //Exit Button
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setMinSize(200,48);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Exiting");
                System.exit(0);
            }
        });
        exitButton.setLayoutX(132);
        exitButton.setLayoutY(550);
        exitButton.setStyle("-fx-background-color: #ff0080; -fx-font-size: 1.5em;");


        root.getChildren().add(exitButton);
        finalprimaryStage.setTitle("Color Switch:Main Menu");
        finalprimaryStage.setScene(mainScreen);
        finalprimaryStage.show();
    }
    public static void gameStart(Stage finalprimaryStage) throws FileNotFoundException {
        mediaPlayer.setVolume(0.1);
        obstacleArrayList = new ArrayList<>();
        finalprimaryStage.setTitle("GamePlay");
        Group root =   new Group();
        //Creating a scene for the gameplayScreen;
        gamePlayScene = new Scene(root,450,650);
        Color backgroundColor = Color.rgb(41,41,41);
        gamePlayScene.setFill(backgroundColor);
        gamePlayScene.setFill(backgroundColor);
        //Adding Score Label
        score = new Text("Score:0");
        score.setFont(Font.font("WHITE", FontWeight.BOLD, FontPosture.REGULAR,20));
        score.setFill(Color.WHITE);
        score.setLayoutX(10);
        score.setLayoutY(20);
        score.setStrokeWidth(500);
        root.getChildren().add(score);
        Image finger = new Image(new FileInputStream("library/finger.jpeg"));
        ImageView imageView = new ImageView(finger);
        imageView.setX(135);
        imageView.setY(550);
        imageView.setFitHeight(50);
        imageView.setFitWidth(600);
        imageView.setPreserveRatio(true);
        root.getChildren().add(imageView);

        gameBall = new Ball(root,gamePlayScene,imageView,530,"cyan");
        startTimeline(gameBall,finalprimaryStage,root);

         currentObstacle = new CircleObstacle(225,200,null,gameBall,finalprimaryStage);

        root.getChildren().addAll(currentObstacle.getComponents());
        obstacleArrayList.add(currentObstacle);

        //Adding ColorPallete
        colorPalleteClass colorProvider = new colorPalleteClass(currentObstacle.getPosY());
        colorPallete = colorProvider.getColorPallete();
        root.getChildren().add(colorPallete);


        //Adding Star

        StarClass starProvider = new StarClass(currentObstacle.getPosY());
        star = starProvider.getStar();
        root.getChildren().add(star);


        if(resumeGameVariable){
            ArrayList<ArrayList<Object>> readSavedGame = null;
            obstacleArrayList = new ArrayList<>();
            try{
                FileInputStream fis = new FileInputStream("/savedGames.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                readSavedGame = (ArrayList<ArrayList<Object>>)ois.readObject();
            }
            catch (FileNotFoundException e) {
                System.out.println("Saved Games Not Found");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ArrayList<Object> readGame;
            readGame = readSavedGame.get(selectedIndex);

            ballSerialize serializedBall = (ballSerialize)readGame.get(0);

            root =   new Group();
            //Creating a scene for the gameplayScreen;
            gamePlayScene = new Scene(root,450,650);
            backgroundColor = Color.rgb(41,41,41);
            gamePlayScene.setFill(backgroundColor);
            gamePlayScene.setFill(backgroundColor);

            //Adding Score Label
            score = new Text("Score:" + serializedBall.score);
            score.setFont(Font.font("WHITE", FontWeight.BOLD, FontPosture.REGULAR,20));
            score.setFill(Color.WHITE);
            score.setLayoutX(10);
            score.setLayoutY(20);
            score.setStrokeWidth(500);
            root.getChildren().add(score);


            gameBall = new Ball(root,gamePlayScene,null,serializedBall.posY,serializedBall.color);
            gameBall.setScore(serializedBall.score);
            startTimeline(gameBall,finalprimaryStage,root);
            ArrayList<Obstacle> serializedObstacle = new ArrayList<>();
            for(int i=2;i<readGame.size();i++){
                ObstacleSerialize ob1 = ((ObstacleSerialize)readGame.get(i));
                switch(ob1.type){
                    case "Rhombus":
                        serializedObstacle.add( new Rhombus(225,ob1.posY,null,gameBall,finalprimaryStage)) ;
                        break;
                    case "Plus":
                        serializedObstacle.add( new Plus(225,ob1.posY,null,gameBall,finalprimaryStage));
                        break;
                    case "doubleCircle":
                        serializedObstacle.add( new doubleCircle(225,ob1.posY,null,gameBall,finalprimaryStage));
                        break;
                    case "CircleObstacle":
                        serializedObstacle.add( new CircleObstacle(225,ob1.posY,null,gameBall,finalprimaryStage));
                        break;
                    case "tripleCircle":
                        serializedObstacle.add( new tripleCircle(225,ob1.posY,null,gameBall,finalprimaryStage));
                        break;
                    case "Square":
                        serializedObstacle.add( new Square(225,ob1.posY,null,gameBall,finalprimaryStage));
                        break;
                }
            }

            currentObstacle = serializedObstacle.get(serializedObstacle.size()-1);


            for(Obstacle ob:serializedObstacle){
                obstacleArrayList.add(ob);
                root.getChildren().add(ob.getComponents());
            }

            //Adding ColorPallete
            colorProvider = new colorPalleteClass((double)readGame.get(1) + 230);
            colorPallete = colorProvider.getColorPallete();
            root.getChildren().add(colorPallete);


            //Adding Star
            starProvider = new StarClass(currentObstacle.getPosY());
            star = starProvider.getStar();
            root.getChildren().add(star);
            resumeGameVariable = false;

        }
        //Adding PauseGame Button;
        Button pauseButton = new Button();
        pauseButton.setText("PauseGame(PressP)");
        pauseButton.setMinSize(50,30);

        Scene finalGamePlayScene = gamePlayScene;
        pauseButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.P){
                    timeLine.pause();
                    for(Obstacle ob:obstacleArrayList){
                        ob.pauseTimeline();
                    }
                    pauseScreen(finalprimaryStage, finalGamePlayScene,gameBall);
                }
            }
        });
        pauseButton.setLayoutX(330);
        pauseButton.setLayoutY(0);
        pauseButton.setStyle("-fx-background-color: #ff0080; -fx-font-size: 1em;");
        root.getChildren().add(pauseButton);




        finalprimaryStage.setScene(gamePlayScene);
        finalprimaryStage.show();


    }
    public static void pauseScreen(Stage finalprimaryStage,Scene gameplayScene,Ball gameBall){
        finalprimaryStage.setTitle("Pause Screen");
        Group root = new Group();
        Scene pauseScene = new Scene(root,450,650);
        Color backgroundColor = Color.rgb(41,41,41);
        pauseScene.setFill(backgroundColor);
        //Resume Button
        Button resumeButton = new Button();
        resumeButton.setText("Resume");
        resumeButton.setMinSize(200,48);

        resumeButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                resumeGame(finalprimaryStage,gameplayScene,gameBall);
            }
        });
        resumeButton.setLayoutX(132);
        resumeButton.setLayoutY(250);
        resumeButton.setStyle("-fx-background-color: #f6df0b; -fx-font-size: 1.5em;");
        root.getChildren().add(resumeButton);

        Text score = new Text("Score:" + gameBall.getScore());
        score.setFont(Font.font("WHITE", FontWeight.BOLD, FontPosture.REGULAR,20));
        score.setFill(Color.WHITE);
        score.setLayoutX(10);
        score.setLayoutY(20);
        score.setStrokeWidth(500);
        root.getChildren().add(score);
        //Save State Button
        Button saveButton = new Button();
        saveButton.setText("Save State");
        saveButton.setMinSize(200,48);

        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                saveStatetoFile();

            }
        });
        saveButton.setLayoutX(132);
        saveButton.setLayoutY(450);
        saveButton.setStyle("-fx-background-color: #8d13fa; -fx-font-size: 1.5em;");
        root.getChildren().add(saveButton);
        finalprimaryStage.setScene(pauseScene);
        finalprimaryStage.show();
    }
    public static void resumeGame(Stage finalprimaryStage,Scene gameplayScene,Ball gameBall){
        finalprimaryStage.setScene(gameplayScene);
        timeLine.play();
        for(Obstacle ob:obstacleArrayList){
            ob.resumeTimeline();
        }
        finalprimaryStage.show();
    }
    public static void continueGame(Stage finalprimaryStage,Scene gameplayScene,Ball gameBall){
        finalprimaryStage.setScene(gameplayScene);
        //timeLine.play();
        for(Obstacle ob:obstacleArrayList){
            ob.resumeTimeline();
        }
        finalprimaryStage.show();
    }
    public static void endgameScreen(Stage finalprimaryStage){
        timeLine.pause();
        for(Obstacle ob:obstacleArrayList){
            ob.pauseTimeline();
        }
        mediaPlayer.setVolume(0.5);
        finalprimaryStage.setTitle("EndGame Screen");
        Group root = new Group();
        Scene endgameScene = new Scene(root,450,650);
        Color backgroundColor = Color.rgb(41,41,41);
        endgameScene.setFill(backgroundColor);


        Text score = new Text("Score:" + gameBall.getScore());
        score.setUnderline(true);
        score.setFont(Font.font("WHITE", FontWeight.BOLD, FontPosture.REGULAR,20));
        score.setFill(Color.WHITE);
        score.setStyle("-fx-font: 48 arial;");
        score.setLayoutX(140);
        score.setLayoutY(500);
        score.setStrokeWidth(1000);
        root.getChildren().add(score);

        //Color Switch Logo
        ImageView imageView = null;
        try {
            Image image = new Image(new FileInputStream("library/logo.jpg"));
            imageView = new ImageView(image);
            imageView.setX(-78);
            imageView.setY(0);
            imageView.setFitHeight(450);
            imageView.setFitWidth(600);
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        root.getChildren().add(imageView);

        //Resume Button
        Button restartButton = new Button();
        restartButton.setText("Restart");
        restartButton.setMinSize(200,48);

        restartButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    gameStart(finalprimaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        restartButton.setLayoutX(132);
        restartButton.setLayoutY(150);
        restartButton.setStyle("-fx-background-color: #f6df0b; -fx-font-size: 1.5em;");
        root.getChildren().add(restartButton);

        //Continue with points Button
        Button continueButton = new Button();
        continueButton.setText("Continue with Stars(5 Points)");
        continueButton.setMinSize(200,48);


        continueButton.setLayoutX(132);
        continueButton.setLayoutY(250);
        continueButton.setStyle("-fx-background-color: #8d13fa; -fx-font-size: 1.5em;");
        root.getChildren().add(continueButton);

        //Main Menu Button
        Button menuButton = new Button();
        menuButton.setText("Main Menu");
        menuButton.setMinSize(200,48);

        menuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    mainMenu(finalprimaryStage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        menuButton.setLayoutX(132);
        menuButton.setLayoutY(350);
        menuButton.setStyle("-fx-background-color: #36e1f3; -fx-font-size: 1.5em;");
        root.getChildren().add(menuButton);
        finalprimaryStage.setScene(endgameScene);
        finalprimaryStage.show();
        continueButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(gameBall.getScore() >=5){
                    continueGame(finalprimaryStage,gamePlayScene,gameBall);
                    gameBall.getBall().setLayoutY(obstacleArrayList.get(0).getPosY() - 170);
                    gameBall.setScore(gameBall.getScore() - 5);
                    System.out.println("Continuing with points...");
                }

            }
        });
    }
    public static void moveStars(){
        colorPallete.setY(colorPallete.getY() + Obstacle.downValue);
        star.setLayoutY(star.getLayoutY() + Obstacle.downValue);
    }
    public static void savedStateScene(Stage finalprimaryStage){
        finalprimaryStage.setTitle("Saved State Screen");
        Group root = new Group();
        Scene savedStateScene = new Scene(root,450,650);
        Color backgroundColor = Color.rgb(41,41,41);
        savedStateScene.setFill(backgroundColor);
        Text saveText = new Text("Reload from Saved State");
        saveText.setFont(Font.font("WHITE", FontWeight.BOLD, FontPosture.REGULAR,20));
        saveText.setFill(Color.WHITE);
        saveText.setLayoutX(120);
        saveText.setLayoutY(20);
        saveText.setStrokeWidth(500);
        root.getChildren().add(saveText);
        ArrayList<ArrayList<Object>> readSavedGames = null;
        try{
            FileInputStream fis = new FileInputStream("/savedGames.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            readSavedGames = (ArrayList<ArrayList<Object>>)ois.readObject();
        }
        catch (FileNotFoundException e) {
            System.out.println("Saved Games File not Present");
            System.out.println("Save Games First");
            readSavedGames = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ListView listView = new ListView();
        listView.setLayoutX(100);
        listView.setLayoutY(100);
        listView.setStyle("-fx-background-color: red; -fx-text-fill: black;");

        int i = 1;
        for(ArrayList<Object> ob:readSavedGames) {
            listView.getItems().add("Saved Game " + i);
            i++;
        }
        root.getChildren().add(listView);
        //SelectButton
        Button selectButton = new Button();
        selectButton.setText("Select");
        selectButton.setMinSize(200,48);
        selectButton.setLayoutX(128);
        selectButton.setLayoutY(550);
        selectButton.setStyle("-fx-background-color: #8d13fa; -fx-font-size: 1.5em;");
        selectButton.setOnMouseClicked(mouseEvent -> {
            ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
            selectedIndex = (int)selectedIndices.get(0);
            resumeGameVariable = true;

            try {
                gameStart(finalprimaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(selectButton);
        finalprimaryStage.setScene(savedStateScene);
        finalprimaryStage.show();
    }
    public static void startTimeline(Ball gameBall,Stage finalprimaryStage,Group gamePlayRoot){
        final Task task = new Task() {

            @Override
            protected Object call() throws Exception {
                timeLine = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler <ActionEvent>() {


                    float ddy = 0.15f;//Acceleration due to gravity;

                    @Override
                    public void handle(ActionEvent t) {

                        if((colorPallete.getY() + 40) >= gameBall.getBall().getLayoutY()){
                            switchSound.play();
                            colorPallete.setY(star.getLayoutY() - 230);
                            int checkColor = -1;
                            String color = gameBall.getBall().getId();
                            switch (color) {
                                case "purple" -> checkColor = 0;
                                case "magenta" -> checkColor = 1;
                                case "cyan" -> checkColor = 2;
                                case "yellow" -> checkColor = 3;
                            }
                            Random rand = new Random();
                            int upperbound = 4;
                            int number = rand.nextInt(upperbound);
                            while(checkColor == number){
                                number = rand.nextInt(upperbound);
                            }

                            switch (number) {
                                case 0 -> {
                                    gameBall.getBall().setFill(Ball.purpleColor);
                                    gameBall.getBall().setId("purple");
                                }
                                case 1 -> {
                                    gameBall.getBall().setFill(Ball.magentaColor);
                                    gameBall.getBall().setId("magenta");
                                }
                                case 2 -> {
                                    gameBall.getBall().setFill(Ball.cyanColor);
                                    gameBall.getBall().setId("cyan");
                                }
                                case 3 -> {
                                    gameBall.getBall().setFill(Ball.yellowColor);
                                    gameBall.getBall().setId("yellow");
                                }
                            }
                        }
                        if((star.getLayoutY() + 40) >= gameBall.getBall().getLayoutY()){
                            starSound.play();
                            gameBall.setScore(gameBall.getScore() + 1);
                            Obstacle.updateDownValue();


                            score.setText("Score:" + gameBall.getScore());
                            currentObstacle = spawnNextObstacle(gameBall);
                            obstacleArrayList.add(currentObstacle);
                            gamePlayRoot.getChildren().add(currentObstacle.getComponents());


                            star.setLayoutY(star.getLayoutY()-470);

                        }
                        if(gameBall.getBall().getLayoutY() <= 400){
                            Iterator<Obstacle> i = obstacleArrayList.iterator();
                            while(i.hasNext()){
                                Obstacle ob = i.next();
                                ob.moveDown();
                                if(ob.getPosY() > 900){
                                    ob.pauseTimeline();
                                    i.remove();

                                }
                            }

                            Main.moveStars();
                            gameBall.setCurrentY(gameBall.getBall().getLayoutY());
                        }

                        gameBall.getBall().setLayoutY(gameBall.getBall().getLayoutY() + gameBall.getVelocity());
                        gameBall.setVelocity(gameBall.getVelocity() + ddy);
                        if(gameBall.getBall().getLayoutY() >= 600){
                            Main.endgameScreen(finalprimaryStage);
                            timeLine.stop();
                            for(Obstacle ob:obstacleArrayList){
                                ob.pauseTimeline();
                            }
                        }
                    }
                }));
                timeLine.setCycleCount(Timeline.INDEFINITE);
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.start();

    }


    public static Obstacle spawnNextObstacle(Ball gameBall){
        Random rand = new Random();
        int upperbound = 6;
        int number = rand.nextInt(upperbound);
        return switch (number) {
            case 0 -> new CircleObstacle(225, (int)(star.getLayoutY()-450), null, gameBall,finalprimaryStage);
            case 1 -> new doubleCircle(225,(int)(star.getLayoutY()-450), null, gameBall,finalprimaryStage);
            case 2 -> new tripleCircle(225,(int)(star.getLayoutY()-450), null, gameBall,finalprimaryStage);
            case 3 -> new Square(225, (int)(star.getLayoutY()-450),null, gameBall,finalprimaryStage);
            case 4 -> new Plus(225,(int)(star.getLayoutY()-450), null, gameBall,finalprimaryStage);
            case 5 -> new Rhombus(225,(int)(star.getLayoutY()-450), null, gameBall,finalprimaryStage);
            default -> null;
        };

    }

    public static void saveStatetoFile(){
        ArrayList<ArrayList<Object>> readSavedGames = null;
        try{
            FileInputStream fis = new FileInputStream("/savedGames.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            readSavedGames = (ArrayList<ArrayList<Object>>)ois.readObject();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            if(readSavedGames == null)
                readSavedGames = new ArrayList<ArrayList<Object>>();

            FileOutputStream fileOut = new FileOutputStream("/savedGames.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            ArrayList<Object> saveGame = new ArrayList<>();
            saveGame.add(gameBall.getSerializableObject());
            saveGame.add(colorPallete.getY());

            for(Obstacle ob:obstacleArrayList){
                saveGame.add(ob.getserializableObject());

            }
            readSavedGames.add(saveGame);
            out.writeObject(readSavedGames);
            out.close();
            fileOut.close();
            System.out.println("Saved State");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
