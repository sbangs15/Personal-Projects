package application;	
import java.io.File;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	//declare variables
	private Image imgBack;
	private ImageView ivBack;
	private Label lblScore,lblTitle;
	private AnimationTimer animation;
	private Magikoopa koopa;
	private BulletBill bullet;
	private ArrayList<BulletBill> bill = new ArrayList<BulletBill>();
	private int dir = -1,secs=3,bulletCount=-1,score=0;
	private Timeline bulletTimer;

	
	public void start(Stage primaryStage) {
		try {
			
			Pane root = new Pane();//create pane 
			Scene scene = new Scene(root);//add scene to pane
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			imgBack = new Image("file:game-background.png");//create background image
			ivBack = new ImageView(imgBack);//add image to image view
			
			
			
			File f = new File("background.wav");//create background music
			Media media = new Media(f.toURI().toString());
			MediaPlayer mPlayer = new MediaPlayer(media);
			mPlayer.setAutoPlay(true);//set auto play to true
			mPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					mPlayer.seek(Duration.ZERO);
				}
			});
			
			
			lblTitle = new Label("Magikoopa vs Bullet Bill");//create label for title
			lblTitle.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",40));//set font
			lblTitle.setLayoutX(180);//set x position
			lblTitle.setLayoutY(25);//set y position
			
			lblScore = new Label("SCORE:" + score);//create label for score
			lblScore.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",30));//set font
			lblScore.setLayoutX(320);//set x position
			lblScore.setLayoutY(75);//set y position
			
			koopa = new Magikoopa();//create variable to call Magikoopa class
			koopa.setLocation(0,0);//set location of Magikoopa at (0,0) when the game begins 
		
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent> () //set key pressed event
			{
				public void handle (KeyEvent e) {
					if (e.getCode() == KeyCode.RIGHT) //when right key is clicked set dir to 0 
					{
						dir = 0;
					} 
					else if (e.getCode() == KeyCode.LEFT) //when left key is clicked set dir to 180
					{
						dir = 180;
					}
					else if (e.getCode() == KeyCode.UP)//when up key is clicked set dir to 90
					{
						dir = 90;
					} else if (e.getCode() == KeyCode.DOWN) //when down key is clicked set dir to 270
					{
						dir = 270;
					}
				}
			});
			
			scene.setOnKeyReleased(new EventHandler<KeyEvent> ()//set key released events 
			{
				public void handle (KeyEvent e) 
				{
					dir = -1;//set dir to -1 
					
				}
			});
			
			
		
			KeyFrame kf = new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent> () //start keyframe for every 300 miliseconds
			{
				
					public void handle (ActionEvent e) 
					{
					bulletCount++;//adds to bullet count
					bill.add(bulletCount, new BulletBill());//adds to array list
					bill.get(bulletCount).setLocation((int)scene.getWidth(),(int) scene.getHeight());//sets location of bullet bill
					root.getChildren().add(bill.get(bulletCount).getImage());//adds bullet bill to the pane 
			}
			});
			bulletTimer = new Timeline(kf);//create new timer
			bulletTimer.setCycleCount(Timeline.INDEFINITE);
			bulletTimer.play();//play timer
			
			root.getChildren().addAll(ivBack,lblTitle,lblScore,koopa.getNode());//adds background, title label,score label, and Magikoopa to the pane
			
			animation = new AnimationTimer() {//animation timer
				
				public void handle(long val) {
					
					for (int i = 0; i < bill.size(); i ++)
					{
						bill.get(i).move(10);//moves bullet bill at the speed of 10
						
						if (bill.get(i).getX() < -50)//checks when bullet bill leaves the pane on the left side
						{
							root.getChildren().remove(bill.get(i).getImage());//removes bullet bill
							bill.remove(i);//remove from the arraylist
							bulletCount--;//decreases bullet count
							//System.out.print(score);
							score += 10;//increases score by 10
							lblScore.setText("Score: " + score);//update score
							
							AudioClip laugh = new AudioClip("file:clash-royale-laugh.mp3");//create audio
							
							if (score % 100 == 0)
							{
								laugh.play();//plays laugh audio every 100 points the player gets 
							}
							
						}
						
						if (koopa.getObjectBounds().intersects(bill.get(i).getImage().getBoundsInParent()))//checks if Magikoopa and Bullet Bill intersect
						{
							root.getChildren().removeAll(bill.get(i).getImage(),koopa.getNode());//remove bullet bill 
							
							koopa.ivMagikoopa = new ImageView(koopa.setDeadImage());//set new imageview 
							root.getChildren().addAll(koopa.ivMagikoopa);//add new imageview
							bill.remove(i);//remove from array list 
							bulletCount--;//decrease bullet count 
							bulletTimer.stop();//stops bullet timer
							animation.stop();//stops animation
							mPlayer.stop();//stops background music
							
							AudioClip gameOver = new AudioClip("file:gameover.wav");// creates game over music 
							gameOver.play();//plays game over sound when Magikoopa crashes into a bullet 
							
							Platform.runLater(new Runnable() {
								public void run() 
								{
									ImageView alertImg = new ImageView(koopa.setDeadImage());//create image view for alert, and call on get Image method to 
																							//return a proper image
									//sets alert when magikoopa collides with a bullet 
									Alert startAlert = new Alert(AlertType.NONE);
									startAlert.setContentText("Game Over!\n" + "Your score was: " + score + "!");//tells user game is over amd their final score
									startAlert.setTitle("GAME OVER!");//sets alert title 
									startAlert.getButtonTypes().clear();//clears button types
									startAlert.getButtonTypes().add(ButtonType.OK);//creates OK button
									startAlert.setGraphic(alertImg);//set image 
									startAlert.showAndWait();
									System.exit(0);	//exits program
								}
							});
						}
					}
					//checks if Magikoopa is within the bounds of the scene, and does not allow the player to leave the scene
					if ((koopa.getX() + koopa.getWidth()) >= scene.getWidth())//checks if it is outside on the right side of the scene
					{
						koopa.setX(koopa.getX() - 1);//sets x position in the scene
					}
					else if (koopa.getX() <= 0)//checks if the player is outside the left side of the room
					{
						koopa.setX(koopa.getX() + 1);//sets x pos in the scene 
					}
					else if ((koopa.getY() + koopa.getHeight()) >= scene.getHeight())//checks if player is out the scene at the bottom
					{
						koopa.setY(koopa.getY() - 1);//sets y pos back in the scene 
					}
					else if (koopa.getY() <= 0)//checks if player is out the scene at the top
					{
						koopa.setY(koopa.getY() + 1);//sets y pos back in the scene 
					}
					else 
					{
						koopa.move(dir);//moves koopa
						koopa.getNode();//gets koopa image
					}
				}
			};
			animation.start();//start animation timer
			
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Magikoopa vs Bullet Bill");//set primary stage title 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

