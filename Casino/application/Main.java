package application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


//initialize variables  
public class Main extends Application {
	private Image imgBack, imgLogo;
	private ImageView ivBack,ivLogo,ivBell,ivCherry,iv7,ivBar,ivBell2,ivCherry2,iv72,ivBar2,ivBell3,ivCherry3,iv73,ivBar3,ivSpace,ivBlack,iRou;
	private Label lbl,lbl2,welcome,logo, name,age,deposit,lblgames,lblBJ,lblSlots,lblRoulette,lblCrash,lblBalance,lblBet,lblslotmachine,lblSpace,lbl3,l1,l2,l3,lblwinning,lblBlack,lblwelcomecrash,lbl1x,lbl2x,lbl3x,lbl4x,lbl5x,lbl6x,lbl10x,lblRou,start,lblexplode ;
	private TextField txtName,txtAge,txtDeposit,txtBet,txtwinnings;
	private Button btnStand,btnHit,btnStart,BJStart,slotsStart,rouStart,crashStart,btnBack,btnSpin,btnAdd,btnCashout,btnCstart,btncout,lblRed,lblBlack2,lblGreen;
	private GridPane roots;
	private Pane root;
	private int BJSum2 = 0,BJSum = 0,count =0,multiply,num=0,colCount = 0,colorNum,balance,cardX = 375,cardX2 = 375;
	private double gtotal=0,bet,bet4,win,winnings,minus; 
	private ArrayList<ImageView> slot1,slot2,slot3;
	private ArrayList<Double> initial,end,sum;
	private ArrayList<String> nameslist,sum2;
	private Timeline t,timer,timer2;
	private Rocket m = new Rocket();
	private Random rand;
	private int y;
	private boolean W = true;
	private File dataFile = new File("Money Log.txt"), dFile = new File("Money Log 2.txt"), dF = new File("Money Log 3.txt"),d =  new File("names.txt");
	private FileWriter out,out2;
	private BufferedWriter writeFile,writeFile2;
	private TextArea WList;
	private String [][] data;	
	private double addDeposit,finalAddDeposit;
	MediaPlayer mPlayer,mPlayer2;
	private int clicks=0;
	
	
	
	
	
	public void start(Stage primaryStage) {
		try {
			
			
			File f = new File("lobby.mp3");//create background music
			Media media = new Media(f.toURI().toString());
			mPlayer = new MediaPlayer(media);
			mPlayer.setAutoPlay(true);//set auto play to true
			mPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
					mPlayer.seek(Duration.ZERO);
				}
			});
			
			roots = new GridPane();//create gridpane
			
			imgBack = new Image("file:bknd.png");//create background image
			ivBack = new ImageView(imgBack);//add image to image view
			
			//roots.setGridLinesVisible(true);
			roots.setPadding(new Insets(10,10,10,10));//set spacing between nodes
			roots.setHgap(10);
			roots.setVgap(10);
			
			//create welcome Label
			welcome = new Label("   Welcome to Spades Casino!");
			welcome.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",70));//set font
			welcome.setTextFill(Color.WHITE);

			
			GridPane.setColumnSpan(welcome, 4);//set title to span over 4 nodes
			GridPane.setHalignment(welcome, HPos.CENTER);//place title in the middle 
			
			
			//set gridpane background, got the code from https://stackoverflow.com/questions/9738146/javafx-how-to-set-scene-background-image
			roots.setBackground(new Background(new BackgroundImage(imgBack, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
			roots.add(welcome,0,0);//add title to gridpane 
			
			imgLogo = new Image("file:logo.png");//create background image
			ivLogo = new ImageView(imgLogo);//add image to image view
			
			
			//create logo image in label
			logo = new Label();
			logo.setGraphic(ivLogo);
			
			//create name label 
			name = new Label("Please enter your name: ");
			name.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",20));//set font
			name.setTextFill(Color.WHITE);
			name.setLayoutX(270);
			name.setLayoutY(500);
			
			//create name textfield
			txtName = new TextField();
			txtName.setMaxSize(150, 20);
			txtName.setLayoutX(550);
			txtName.setLayoutY(495);
			
			//create age label 
			age = new Label("Please enter your age: ");
			age.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",20));//set font
			age.setTextFill(Color.WHITE);//set text to white 
			
		
			//create age textfield 
			txtAge = new TextField();
			txtAge.setMaxSize(150, 20);
			
			//create deposit label
			deposit = new Label("Enter you Deposit: ");
			deposit.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",20));//set font
			deposit.setTextFill(Color.WHITE);//set text to white 
		
			//create start label 
			start = new Label("Click Start to Begin! ");
			start.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",20));//set font
			start.setTextFill(Color.WHITE);//set text to white 
			
			//create deposit textfield
			txtDeposit = new TextField();
			txtDeposit.setMaxSize(150, 20);//set max size
			
			//create start button
			btnStart = new Button();
			btnStart.setPrefSize(150,50);
			btnStart.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));//set font
			btnStart.setText("Start");//set text 
			btnStart.setOnAction(e -> {            //set to go to start method when clicked 
				try {
					Start_Click(primaryStage);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			
			KeyFrame kf = new KeyFrame(Duration.millis(300), new
					EventHandler<ActionEvent>() {
						public void handle(ActionEvent e)
						{
							
							//If num is greater than or equal to count
							if(num >= count)
							{
								//Set values of both variables to 0.
								num = 0;
								count = 0;
								
								//If colorNum equals to colCount, the spinner landed on the bet
								if(colorNum == colCount)
								{
									//Increase total money 
									gtotal += Integer.parseInt(txtBet.getText()) * multiply;
									AudioClip winner = new AudioClip("file:winner.mp3");// creates game over music 
									winner.play();//plays game over sound when Magikoopa crashes into a bullet 
									
									//Changing the text of balance label to new value
									lblBalance.setText("BALANCE: $" + gtotal);
								}
								//The spinner does not land on the bet
								else
								{
									//Decrease total money
									gtotal -= Integer.parseInt(txtBet.getText());
									
									//Changing the text of balance label to new value
									lblBalance.setText("BALANCE: $" + gtotal);
								}
								
								//Stop the timer
								timer.stop();
								
							}
							//Else
							else
							{
								//If the spinner is currently on green
								if(num == 0 || num == 7||num == 14)
								{
									//Change spinner to point the direction to the red on the left
									Image imgRou = new Image ("file:roulette3.png");
									iRou = new ImageView(imgRou);
									lblRou.setGraphic(iRou);
									
									//Assign colorNum the value of 2
									colorNum = 2;

								}
								//If the spinner is currently on the first red
								else if(num == 1 || num == 8||num == 15)
								{
									//Change spinner to point the direction to the black on the left
									Image imgRou = new Image ("file:roulette4.png");
									iRou = new ImageView(imgRou);
									lblRou.setGraphic(iRou);

									//Change value of colorNum to 1.
									colorNum = 1;
								}
								//If the spinner is currently on the first black
								else if(num == 2 || num == 9||num == 16)
								{
									//Change spinner to point the direction to the red on the left
									Image imgRou = new Image ("file:roulette5.png");
									iRou = new ImageView(imgRou);
									lblRou.setGraphic(iRou);
									
									//Assign colorNum the value of 2
									colorNum = 2;

								}
								//If the spinner is currently on the second red
								else if(num == 3||num == 10||num == 17)
								{
									//Change spinner to point the direction to the black on the left
									Image imgRou = new Image ("file:roulette6.png");
									iRou = new ImageView(imgRou);
									lblRou.setGraphic(iRou);
									
									//Change value of colorNum to 1.
									colorNum = 1;

								}
								//If the spinner is currently on the second black
								else if(num == 4 || num == 11||num == 18)
								{
									//Change spinner to point the direction to the red on the left
									Image imgRou= new Image ("file:roulette7.png");
									ImageView iRou = new ImageView(imgRou);
									lblRou.setGraphic(iRou);
									
									//Change value of colorNum to 2.
									colorNum = 2;

								}
								//If the spinner is currently on the third red
								else if(num == 5 || num == 12||num == 19)
								{
									//Change spinner to point the direction to the black on the left
									Image imgRou = new Image ("file:roulette8.png");
									ImageView iRou = new ImageView(imgRou);
									lblRou.setGraphic(iRou);
									
									//Change value of colorNum to 1.
									colorNum = 1;

								}
								//If the spinner is currently on the third black
								else if(num == 6 || num == 13||num == 20)
								{
									//Change spinner to point the direction to the green
									Image imgRou = new Image ("file:roulette2.png");
									ImageView iRou = new ImageView(imgRou);
									lblRou.setGraphic(iRou);

									//Change the value of colorNum to 3.
									colorNum = 3;
								}


							}
							
							//Increasing the num counter
							num++;
						}
			}); 
			
			KeyFrame kf2 = new KeyFrame(Duration.millis(1000), new
					EventHandler<ActionEvent>() {
				public void handle(ActionEvent e)
				{
					//If the dealer's sum is below the user's sum
					if(BJSum2 < BJSum)
					{
						//Calling random class
						Random rnd = new Random();
						
						//Generating random number between 1 and 13
						int num3 = rnd.nextInt(13) + 1;
						
						//Generating random number between 1 and 4
						int num4 = rnd.nextInt(4)+1;
						
						//Increasing the dealer's sum
						BJSum2 += num3;
						
						//Initializing string variable
						String str2 = "";
						
						//If num4 is 1, the suit is clubs
						if(num4 ==1)
						{
							str2 = "clubs";
						}
						//If num4 is 2, the suit is hearts
						else if(num4 == 2)
						{
							str2 = "hearts";
						}
						//If num4 is 3, the suit is spades
						else if(num4 == 3)
						{
							str2 = "spades";
						}
						//If num4 is 4, the suit is diamonds
						else if(num4 == 4)
						{
							str2 = "diamonds";
						}
						
						//Creating the image and image view objects for the cards
						Image imgCards2 = new Image(("file:cards/" + Integer.toString(num3) + "_of_" + str2 + ".png"));
						ImageView iCards2 = new ImageView(imgCards2);
						
						//Label for the dealer's cards
						lbl2 = new Label();
						lbl2.setGraphic(iCards2);
						lbl2.setLayoutX(cardX2);
						lbl2.setLayoutY(200);
						
						//Adding to the pane
						root.getChildren().addAll(lbl2);
						
						//Increasing the x coordinate of the card's x coordinate by 75
						cardX2 += 75;
					}
					//Else
					else
					{
						if(BJSum2 == BJSum)
						{
							btnHit.setDisable(true);
							btnStand.setDisable(true);
							
							
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("ERROR");
							alert.setContentText("Both you and the dealer got the same score! This round was a tie!!!");
							alert.setHeaderText(null);
							alert.show();
							
							

						}
						else if(BJSum2 > BJSum && BJSum2 < 22)
						{
							btnHit.setDisable(true);
							btnStand.setDisable(true);
							
							
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("ERROR");
							alert.setContentText("The dealer won this Round!!!");
							alert.setHeaderText(null);
							alert.show();
							
							//Increase total money 
							gtotal -= Double.parseDouble(txtBet.getText());
							
							//Changing the text of balance label to new value
							lblBalance.setText("BALANCE: $" + gtotal);

						}
						else if(BJSum2 > 21)
						{
							btnHit.setDisable(true);
							btnStand.setDisable(true);
							
							
							
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("ERROR");
							alert.setContentText("You won this Round!!!");
							alert.setHeaderText(null);
							alert.show();
							
							
							//Increase total money 
							gtotal += Double.parseDouble(txtBet.getText());
							AudioClip winner = new AudioClip("file:winner.mp3");// creates game over music 
							winner.play();//plays game over sound when Magikoopa crashes into a bullet 
							
							//Changing the text of balance label to new value
							lblBalance.setText("BALANCE: $" + gtotal);
						}
						
						//Stop the timer
						timer2.stop();
					} 
				}
				
			});
			
			
			//Creating timers
			timer = new Timeline(kf);
			timer.setCycleCount(Timeline.INDEFINITE);
			timer2 = new Timeline (kf2);
			timer2.setCycleCount(Timeline.INDEFINITE);
			
			Scene scene = new Scene(roots,1000,800);//set scene size 
			Image imgRocket = new Image("file:sRocket.png");//set rocket image
			ImageView iview = new ImageView(imgRocket);//set rocket image view 
			
			//create bet label 
			lblBet = new Label("Place Bet:");
			lblBet.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",32));//set font
			lblBet.setTextFill(Color.WHITE);//set text to white 
			lblBet.setLayoutX(50);
			lblBet.setLayoutY(300);
						
			
			GridPane.setHalignment(logo, HPos.CENTER);//center logo
			GridPane.setColumnSpan(logo, 2);//set logo to be in two cells
			GridPane.setColumnSpan(btnStart, 2);//set button to be in 2 cells
			GridPane.setHalignment(btnStart, HPos.CENTER);//center start button
			
			
			//add components to specific cells
			roots.add(logo, 1,2);
			roots.add(name, 1, 8);
			roots.add(txtName, 2, 8);
			roots.add(age, 1, 11);
			roots.add(txtAge, 2, 11);
			roots.add(deposit, 1, 14);
			roots.add(txtDeposit, 2, 14);
			roots.add(start, 0, 24);
			roots.add(btnStart, 1, 24);
			
			//root.getChildren().addAll(ivBack,welcome,logo, name, txtName, age, txtAge, deposit,txtDeposit,btnStart);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void Start_Click(Stage primaryStage) throws IOException//method for when start button is clicked in main screen
	{	
		
		
		//create variables 
		int ages = 0;
		String names = txtName.getText();
		String ageS = txtAge.getText();
		String deposits = txtDeposit.getText();
		
		
		out2 = new FileWriter (d,true);
		writeFile2 = new BufferedWriter(out2);
		out = new FileWriter (dataFile,true);
		writeFile = new BufferedWriter(out);
		
		
		
		//Will run if the age and deposit text fields are not empty
		if(!txtAge.getText().equals("")&&!txtDeposit.getText().equals(""))//checks if textfield is not empty 
		{
			 try
			 {
				 ages = Integer.parseInt(ageS);
				 gtotal = Double.parseDouble(deposits);
				 
				 if (names.equals("")|| ages < 18|| gtotal < 1)//checks to see if a name is entered, age is over 18, and a deposit over a dollar is made 
					{
						//alert that tells user they have incorrectly entered info
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ERROR");
						alert.setContentText("ERROR! You must enter you name! You must be 18 years old! You must deposit at least 1 dollar!");
						alert.setHeaderText(null);
						alert.showAndWait();
					}
				 else
				 {
					 root = new Pane();//create Pane
					 Scene scene = new Scene(root,1000,800);//set scene size 
					 
					 primaryStage.setScene(scene);//set scene
					 primaryStage.show();//show scene
						
						
						primaryStage.setOnCloseRequest(e -> { //set when close tab to go to cash out method 
							
						if (clicks == 0)
						{
							try {
								Cashout_Click();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else
						{
							gg_click();
						}
						
						});;
						
						
						
						try
						{
							//write deposit and names to seperate files 
							writeFile.write(String.valueOf(deposits));
							writeFile.newLine();
							
							writeFile2.write(names);
							writeFile2.newLine();;
							
							//close writers 
							writeFile.close();
							out.close();
							writeFile2.close();
							out2.close();
							//System.out.println("Data written to file.");
						}
						catch (IOException e)
						{
							System.out.println("Problem writing to file.");
							System.err.println("IOException: "+ e.getMessage());
						}
						
						//create Label to welcome user to games menu 
						lblgames = new Label();
						lblgames.setText("Welcome " + names + ", select a game from below to play:");
						lblgames.setTextFill(Color.WHITE);
						lblgames.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",32));
						lblgames.setLayoutX(130);
						lblgames.setLayoutY(50);
						
						//create image and image view for blackjack 
						Image imgBJ = new Image("file:BlackJack.png");
						ImageView ivBJ = new ImageView(imgBJ);
						
						//set image in label 
						lblBJ = new Label();
						lblBJ.setGraphic(ivBJ);
						lblBJ.setLayoutX(50);///set X
						lblBJ.setLayoutY(250);//set Y 
						
						//create start button for blackjack
						BJStart = new Button();
						BJStart.setPrefSize(150,50);//set size
						BJStart.setLayoutX(50);//set X
						BJStart.setLayoutY(500);//set Y 
						BJStart.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));//set font
						BJStart.setText("BLACKJACK");
						BJStart.setOnAction(e -> BJStart_Click());//go to BJ method when clicked 
						
						//create slots image and image view
						Image imgSlots = new Image("file:Slots.png");
						ImageView ivSlots = new ImageView(imgSlots);
						
						
						//set image in label 
						lblSlots = new Label();
						lblSlots.setGraphic(ivSlots);
						lblSlots.setLayoutX(250);//set x
						lblSlots.setLayoutY(250);//set y
						
						//create button for slots start 
						slotsStart = new Button();
						slotsStart.setPrefSize(150,50);//set size 
						slotsStart.setLayoutX(270);//set x
						slotsStart.setLayoutY(500);//set y
						slotsStart.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));//set font 
						slotsStart.setText("SLOTS");//set text
						slotsStart.setOnAction(e -> slotsStart_Click());//goes to slots start method when button is clicked 
						
						//create roullete image and image view 
						Image imgRoulette = new Image("file:roulette.png");
						ImageView ivRoullete = new ImageView(imgRoulette);
						
						//set image in label 
						lblRoulette = new Label();
						lblRoulette.setGraphic(ivRoullete);
						lblRoulette.setLayoutX(500);//set x
						lblRoulette.setLayoutY(250);//set y
						
						//create roulette start button
						rouStart = new Button();
						rouStart.setPrefSize(150,50);//set size 
						rouStart.setLayoutX(520);//set x
						rouStart.setLayoutY(500);//set y 
						rouStart.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));//set font 
						rouStart.setText("ROULETTE");
						rouStart.setOnAction(e -> rouStart_Click());//goes to roulette start method when clicked 
						
						
						//create rocket image and image view
						Image imgCrash = new Image("file:Rocket.png");
						ImageView ivCrash = new ImageView(imgCrash);
						
						//set image in label 
						lblCrash = new Label();
						lblCrash.setGraphic(ivCrash);
						lblCrash.setLayoutX(400);//set x
						lblCrash.setLayoutY(275);//set y
						
						//textfield for bet 
						txtBet = new TextField();
						txtBet.setPrefSize(150, 20);//set size 
						txtBet.setLayoutX(215);//set x
						txtBet.setLayoutY(300);//set y 
						
						//create label for balance 
						lblBalance = new Label("BALANCE: $" + gtotal);
						lblBalance.setTextFill(Color.WHITE);//set text to white 
						lblBalance.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",50));//set font
						lblBalance.setLayoutX(350);//set X
						lblBalance.setLayoutY(700);//set Y 
						
						//create start button for crash 
						crashStart = new Button();
						crashStart.setPrefSize(150,50);//set size
						crashStart.setLayoutX(800);//set x
						crashStart.setLayoutY(500);//set y
						crashStart.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));//set font
						crashStart.setText("CRASH");
						crashStart.setOnAction(e -> crashStart_Click());//go to start crash method when clicked 
						
						//create button for games menu 
						btnBack = new Button("Games MENU");
						btnBack.setPrefSize(150,50);
						btnBack.setLayoutX(10);
						btnBack.setLayoutY(10);
						btnBack.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));
						btnBack.setOnAction(e -> Back_Click());
						
						//create add funds button
						btnAdd = new Button("ADD FUNDS");
						btnAdd.setPrefSize(150,50);
						btnAdd.setLayoutX(150);
						btnAdd.setLayoutY(700);
						btnAdd.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));
						btnAdd.setOnAction(e -> Add_Click());
						//create cashout button
						btnCashout= new Button("CASH OUT");
						btnCashout.setPrefSize(150,50);
						btnCashout.setLayoutX(700);
						btnCashout.setLayoutY(700);
						btnCashout.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));
						btnCashout.setOnAction(e -> {		//goes to cash out method when clicked 
							try {
								Cashout_Click();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
						});
						
						//add all components to the pane 
						root.getChildren().addAll(ivBack,lblgames,lblBJ,lblSlots,lblRoulette,lblCrash,BJStart,slotsStart,rouStart,crashStart,lblBalance,btnAdd,btnCashout);
				 }
			 }
			 catch(Exception e)
			 {
				 	//alert if any misput is put s
				 	Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setContentText("ERROR! You must enter a valid age and deposit!");
					alert.setHeaderText(null);
					alert.showAndWait();
			 }
		}
		else
		{
			//alert that tells name,age, money is the same
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("ERROR! You must enter you name! You must be 18 years old! You must deposit at least 1 dollar!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
	
		
		
		//double money = Integer.parseInt(deposits);
		
		//System.out.print(names) ;
		
		//create File Writers and Buffered Writers 
		
		
		
		
			
		
	}
	private void BJStart_Click()
	{
		mPlayer.stop();
		//Removing everything except the background and adding the back button
		root.getChildren().removeAll(lblgames,lblBJ,lblSlots,lblRoulette,lblCrash,BJStart,slotsStart,rouStart,crashStart);
		root.getChildren().add(btnBack);
		
		//Setting the y coordinates of the add funds, cashout, and balance
		btnAdd.setLayoutY(700);
		btnCashout.setLayoutY(700);
		lblBalance.setLayoutY(700);
		
		//Setting coordinates of bet label
		lblBet.setLayoutY(638);
		lblBet.setLayoutX(200);
		
		
		
		
		//Creating start button and seeting certain characteristics such as layout, size, etc.
		Button btnStart = new Button();
		btnStart.setText("START");
		btnStart.setAlignment(Pos.CENTER);
		btnStart.setStyle("-fx-background-color: blue");
		btnStart.setLayoutX(700);
		btnStart.setLayoutY(625);
		btnStart.setPrefSize(100, 50);
		btnStart.setOnAction(e->BJStart());
		
		//Changing characteristics of the bet textfield
		txtBet.setLayoutX(400);
		txtBet.setLayoutY(625);
		txtBet.setPrefSize(200, 50);
		
		//Adding to the pane
		root.getChildren().addAll(btnStart,txtBet,lblBet);
		
		
	}
	private void Add_Click()
	{
		//alert that asks the user how much more they want to deposit 
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Text Input Dialog");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter how much you want to Deposit: ");//set text 

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
		{
			addDeposit = Double.parseDouble(result.get());//parese deposit to double
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Thank You! You're $ " + addDeposit + "  has been deposited.");//set second alert text
			alert.setHeaderText(null);
			alert.showAndWait();
			
			finalAddDeposit += addDeposit;//add deposit to variable
			gtotal += addDeposit;//increase deposit by gtotal 
			AudioClip cashOut = new AudioClip("file:cash.mp3");// creates game over music 
			cashOut.play();//plays game over sound when Magikoopa crashes into a bullet 
			lblBalance.setText("BALANCE: $" + gtotal );//set balance text
		}
	}
	private void slotsStart_Click()
	{
		mPlayer.stop();//stop background music
		root.getChildren().removeAll(lblgames,lblBJ,lblSlots,lblRoulette,lblCrash,BJStart,slotsStart,rouStart,crashStart);//remove components from games menu
		
		
		//initialize new images 
		Image imgSlotMachine = new Image("file:sMachine1.png");
		ImageView ivCrash = new ImageView(imgSlotMachine);
		Image imgBell = new Image("file:Bell.png");
		Image img7= new Image("file:slots7.png");
		Image imgBar = new Image("file:slotsBar.png");
		Image imgCherry= new Image("file:slotscherry.png");
		
		ivBell = new ImageView(imgBell);//add image to image view
		iv7 = new ImageView(img7);//add image to image view
		ivBar = new ImageView(imgBar);//add image to image view
		ivCherry = new ImageView(imgCherry);//add image to image view
		
		ivBell2 = new ImageView(imgBell);//add image to image view
		iv72= new ImageView(img7);//add image to image view
		ivBar2 = new ImageView(imgBar);//add image to image view
		ivCherry2 = new ImageView(imgCherry);//add image to image view
		
		ivBell3 = new ImageView(imgBell);//add image to image view
		iv73= new ImageView(img7);//add image to image view
		ivBar3 = new ImageView(imgBar);//add image to image view
		ivCherry3 = new ImageView(imgCherry);//add image to image view
		
		//create label for slot machine 
		lblslotmachine = new Label();
		lblslotmachine.setGraphic(ivCrash);
		lblslotmachine.setLayoutX(500);
		lblslotmachine.setLayoutY(100);
		
		//create label for place bet 
		lblBet = new Label("Place Bet:");//set text
		lblBet.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",32));//set font
		lblBet.setTextFill(Color.WHITE);//set text fill to white 
		lblBet.setLayoutX(50);//set x
		lblBet.setLayoutY(300);//set y
		
		//create textfield for bet 
		txtBet = new TextField();
		txtBet.setPrefSize(150, 20);
		txtBet.setLayoutX(215);
		txtBet.setLayoutY(300);
		
		//create an arraylist for each slot and add the pictures
		slot1 = new ArrayList<ImageView>();
		slot1.add(ivBell);
		slot1.add(iv7);
		slot1.add(ivBar);
		slot1.add(ivCherry);
		
		slot2 = new ArrayList<ImageView>();
		slot2.add(ivBell2);
		slot2.add(iv72);
		slot2.add(ivBar2);
		slot2.add(ivCherry2);
		
		slot3 = new ArrayList<ImageView>();
		slot3.add(ivBell3);
		slot3.add(iv73);
		slot3.add(ivBar3);
		slot3.add(ivCherry3);
		
		//create a label for each slot
		l1 = new Label();
		l1.setLayoutX(565);//set x
		l1.setLayoutY(325);//set y
		
		l2 = new Label();
		l2.setLayoutX(680);//set x 
		l2.setLayoutY(325);//set y
		
		l3 = new Label();
		l3.setLayoutX(790);//set x
		l3.setLayoutY(325);//set y
		
		//create spin button
		btnSpin = new Button("SPIN!");
		btnSpin.setPrefSize(200,150);//set size
		btnSpin.setLayoutX(135);//set x
		btnSpin.setLayoutY(400);//set y
		btnSpin.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",32));//set font
		btnSpin.setOnAction(e -> Spin_Click());//goes to spin click method when clicked 
		
		//create label 
		lbl3 = new Label("MATCH ALL 3 TO WIN!");
		lbl3.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",32));//set font
		lbl3.setTextFill(Color.BLACK);//set font to black 
		lbl3.setLayoutX(585);//set x
		lbl3.setLayoutY(165);//set y 
		
		//set graphic to the first image 
		l1.setGraphic(slot1.get(0));
		l2.setGraphic(slot2.get(0));
		l3.setGraphic(slot3.get(0));		
		
		root.getChildren().addAll(btnBack,lblBet,txtBet,lblslotmachine,btnSpin,lbl3,l1,l2,l3);//add all components to the scene
		
		
		
	}
	private void Spin_Click()
	{
		count++;//count increaases 
		
		
		if(txtBet.getText().isEmpty())//checks if bet is empty 
		{
			//alert that tells the userthey must bet atleast one dollar
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("ERORR, YOU MUST ENTER A BET! BET MUST BE ATLEAST 1 DOLLAR!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		else
		{
			try
			{
				bet = Double.parseDouble(txtBet.getText());//parses bet to a double
				
				if (gtotal == 0)//if balance is 0
				{
					//alert that tells the user they have no money left 
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setContentText("ERROR! YOU HAVE NO MONEY LEFT!");
					alert.setHeaderText(null);
					alert.showAndWait();
				}
				else
				{
					//shuffle arraylists 
					Collections.shuffle(slot1);
					Collections.shuffle(slot2);
					Collections.shuffle(slot3);
					
					//set labels to first image in the array list 
					l1.setGraphic(slot1.get(0));
					l2.setGraphic(slot2.get(0));
					l3.setGraphic(slot3.get(0));	
					
				if(slot1.get(0).getImage() == slot2.get(0).getImage() && slot1.get(0).getImage() == slot3.get(0).getImage())//if all the images are the same (win) 
				{
					double winnings = bet * 2;//winnings are double the bet 
					gtotal += winnings;//increase the balance by the winnings 
					lblBalance.setText("BALANCE: $" + gtotal);//set balance text
					
					AudioClip jack= new AudioClip("file:Jackpot.wav");// creates game over music 
					jack.play();//play the audio 
					
					System.out.print(gtotal);//print balance 
				}
				else //if they lose 
				{
					gtotal -=bet;//decrease balance bet 
					lblBalance.setText("BALANCE: $" + gtotal);//set balance 
					//System.out.print(gtotal);
				}
				
				gtotal = gtotal;
				}	
			}
			catch(Exception e)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("Please enter a valid bet!");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			
			
		}
	}
	private void rouStart_Click()
	{
		mPlayer.stop();//stop music
		//Removing everything except background and adding back button
		root.getChildren().removeAll(lblgames,lblBJ,lblSlots,lblRou,lblCrash,BJStart,slotsStart,rouStart,crashStart,lblslotmachine,btnSpin,lblRoulette,txtBet,lblBalance,lblBet);
		root.getChildren().add(btnBack);
		lblBalance.setLayoutY(700);
		//Creating an image and image view object for the roulette spinner.
		Image imgRoulette = new Image ("file:roulette2.png");
		ImageView iRoulette = new ImageView(imgRoulette);
		
		//Creating roulette label and changing its characteristics
		lblRou = new Label();
		lblRou.setGraphic(iRoulette);
		lblRou.setLayoutX(280);
		lblRou.setLayoutY(150);
		
		//changing y coordinate of balance label
		
		//Changing coordinates of bet label
		lblBet.setLayoutY(645);
		lblBet.setLayoutX(300);
		
		//Changing bet text field coordinates
		txtBet.setLayoutX(500);
		txtBet.setLayoutY(645);
		
		//Creating red label and changing its characteristics
		lblRed = new Button();
		lblRed.setText("RED"); 
		lblRed.setStyle("-fx-background-color: red");
		lblRed.setLayoutX(800);
		lblRed.setLayoutY(200);
		lblRed.setPrefSize(100, 100);
		lblRed.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, null, null)));
		lblRed.setOnAction(e->rouStartRed());

		//Creating black label and changing its characteristics
		lblBlack2 = new Button();
		lblBlack2.setText("BLACK");
		lblBlack2.setStyle("-fx-background-color: black");
		lblBlack2.setLayoutX(800);
		lblBlack2.setLayoutY(350);
		lblBlack2.setPrefSize(100, 100);
		lblBlack2.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, null, null)));
		lblBlack2.setOnAction(e->rouStartBlack());

		//Creating green label and changing its characteristics
		lblGreen = new Button();
		lblGreen.setText("GREEN");
		lblGreen.setStyle("-fx-background-color: green");
		lblGreen.setLayoutX(800);
		lblGreen.setLayoutY(500);
		lblGreen.setPrefSize(100, 100);
		lblGreen.setBorder(new Border(new BorderStroke(Color.BLACK,
				BorderStrokeStyle.SOLID, null, null)));
		lblGreen.setOnAction(e->rouStartGreen());
		
		
		//Adding to the pane
		root.getChildren().addAll(lblRou,txtBet,lblBalance,lblBet,lblRed,lblBlack2,lblGreen);
		

	}
	private void crashStart_Click()
	{
		mPlayer.stop();//stop music
		root.getChildren().removeAll(lblgames,lblBJ,lblSlots,lblRoulette,lblCrash,BJStart,slotsStart,rouStart,crashStart,ivBack,btnBack,lblBalance);//remove all irrelevant images from pane

		//create new image and imageview for space backrounbd 
		Image imgSpace = new Image("file:galaxy.v1.jpg");
		ivSpace = new ImageView(imgSpace);
		//create image and image view for divider 
		Image imgBlack = new Image("file:black2.png");
		ivBlack = new ImageView(imgBlack);
		
		//create a label for bet 
		lblBet = new Label("Place Bet:");
		lblBet.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
		lblBet.setTextFill(Color.WHITE);//set font fill
		lblBet.setLayoutX(25);//set x
		lblBet.setLayoutY(300);//set y 
		
		//create textfield
		txtBet = new TextField();
		txtBet.setPrefSize(75, 20);//set pref size
		txtBet.setLayoutX(160);//set x
		txtBet.setLayoutY(300);//set y
		
		
		//create label for winnings
		lblwinning = new Label("Winnings:");
		lblwinning.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
		lblwinning.setTextFill(Color.WHITE);//set font colour
		lblwinning.setLayoutX(25);//set x
		lblwinning.setLayoutY(400);//set y
		
		//create textfield  taht displays winnings 
		txtwinnings = new TextField();
		txtwinnings.setEditable(false);//set editable to false
		txtwinnings.setPrefSize(75, 20);//set preffered size
		txtwinnings.setLayoutX(160);//set x
		txtwinnings.setLayoutY(400);//set y 
		
		//create label 
		lblBlack = new Label();
		lblBlack.setGraphic(ivBlack);//set image
		lblBlack.setLayoutX(0);//set x
		lblBlack.setLayoutY(0);//set y
		
		lblBalance.setLayoutX(10);//set x

		//create array with y positions
		int [] ypositions = {600,550,500,450,420,380,350,330,320,310,300,290,280,270,260,250,240,230,220,210,200,190,180,170,160,150,140,130,120,110,100,90,80,70,60,50,10,0,-10};

		//create start button
		btnCstart = new Button();
		btnCstart.setPrefSize(200,50);//set size
		btnCstart.setLayoutX(50);//set x
		btnCstart.setLayoutY(500);//set y 
		btnCstart.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));//set font 
		btnCstart.setText("START");//set text 
		btnCstart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				if (btnCstart.getText().equals("START"))//if button text is startv 
				{
					
					if (txtBet.getText().isEmpty())//if textfield is empty 
					{
						//create alert that says alert is empty 
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("ERROR");
						alert.setContentText("ERORR, YOU MUST ENTER A BET! BET MUST BE ATLEAST 1 DOLLAR!");
						alert.setHeaderText(null);
						alert.showAndWait();
					}
					else
					{
						try
						{
							Double num5 = Double.parseDouble(txtBet.getText());//parse the bet to double 
							
							if (gtotal == 0)//if balance is 0 
							{
								//alert that tells user they have no money left 
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("ERROR");
								alert.setContentText("ERROR! YOU HAVE NO MONEY LEFT!");
								alert.setHeaderText(null);
								alert.showAndWait();
							}
							else
							{
								//create random generator
								rand = new Random();
								y = rand.nextInt(ypositions.length);//set y to a random number 
								
								//System.out.print(ypositions[y]);
								t.play();//play animation 
								btnCstart.setText("CASH OUT");//set text to cashout 
								
								bet4 = Double.parseDouble(txtBet.getText());//parses bet to double 
								
								gtotal -= bet4;//decrease balance by bet 4
								lblBalance.setText("BALANCE: $" + gtotal);//set text
							}
						}
						catch(Exception e2)//catch for bet  textfield
						{
							//alert to set valid text
							Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("ERROR");
							alert.setContentText("ERROR! Enter a valid bet!");
							alert.setHeaderText(null);
							alert.showAndWait();
						}
						
						
						
						
					}
				}
				else
				{
					t.stop();//stop timer
					btnCstart.setText("START");//set button text to start 
					m.setLocation(275, 650);//reset rocket position 
					txtBet.clear();//clear textfield
					txtwinnings.clear();//clear textfield
					//remove multipliers and explosion 
					root.getChildren().removeAll(lbl1x,lbl2x,lbl3x,lbl4x,lbl5x,lbl6x,lbl10x,lblexplode);
					
					if (W == true)//if win is true 
					{
						gtotal += winnings;//increase balance by winnings
						lblBalance.setText("BALANCE: $" + gtotal);//set balance text
				
					}
					winnings =0;//set winnings to 0 
					
					//System.out.print(gtotal);
				}
			
			}
		});
				
		KeyFrame kf = new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() { //create keyframe for rocket movement
			public void handle(ActionEvent e) 
			{
				m.move();//move rocket

				
				//checks if the rocket reached a multiplier  creates label, and muliples winnings
				if (m.getY() == 600)
				{
					lbl1x = new Label();
					lbl1x.setText("1.5X");
					lbl1x.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
					lbl1x.setTextFill(Color.RED);//set font colour to red 
					lbl1x.setLayoutX(m.getX()+ 100);//set x
					lbl1x.setLayoutY(m.getY());//set y 
					root.getChildren().add(lbl1x);
					
					win = bet4 * 1.5;
					winnings += win;
					txtwinnings.setText("$" + winnings);
					
					System.out.println(winnings);
					
					
				}
				//checks if the rocket reached a multiplier,creates label, and muliples winnings
				else if (m.getY() == 500)
				{
					lbl2x = new Label();
					lbl2x.setText("2X");
					lbl2x.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
					lbl2x.setTextFill(Color.RED);
					lbl2x.setLayoutX(m.getX()+ 100);
					lbl2x.setLayoutY(m.getY());
					
					root.getChildren().add(lbl2x);
					
					win = winnings * 2;
					
					winnings += win;
					
					txtwinnings.setText("$" + winnings);
					
					System.out.println(winnings);
				}
				//checks if the rocket reached a multiplier  creates label, and muliples winnings
				else if (m.getY() == 420)
				{
					lbl3x = new Label();
					lbl3x.setText("3X");
					lbl3x.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
					lbl3x.setTextFill(Color.RED);
					lbl3x.setLayoutX(m.getX()+ 100);
					lbl3x.setLayoutY(m.getY());
					
					root.getChildren().add(lbl3x);
					
					win = winnings * 3;
					
					winnings += win;
					
					txtwinnings.setText("$" + winnings);
					System.out.println(winnings);
					
				}
				//checks if the rocket reached a multiplier  creates label, and muliples winnings
				else if (m.getY() == 300)
				{
					lbl4x = new Label();
					lbl4x.setText("4X");
					lbl4x.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
					lbl4x.setTextFill(Color.RED);
					lbl4x.setLayoutX(m.getX()+ 100);
					lbl4x.setLayoutY(m.getY());
					
					root.getChildren().add(lbl4x);
					
					win = winnings * 4;
					
					winnings += win;
					
					txtwinnings.setText("$" + winnings);
					
					System.out.println(winnings);
					
				}
				//checks if the rocket reached a multiplier  creates label, and muliples winnings
				else if (m.getY() == 200)
				{
					lbl5x = new Label();
					lbl5x.setText("5X");
					lbl5x.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
					lbl5x.setTextFill(Color.RED);
					lbl5x.setLayoutX(m.getX()+ 100);
					lbl5x.setLayoutY(m.getY());
					
					root.getChildren().add(lbl5x);
					
					win = winnings* 5;
					
					winnings += win;
					
					txtwinnings.setText("$" + winnings);
					
					System.out.println(winnings);
					
				}
				//checks if the rocket reached a multiplier  creates label, and muliples winnings
				else if (m.getY() == 100)
				{
					lbl6x = new Label();
					lbl6x.setText("6X");
					lbl6x.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
					lbl6x.setTextFill(Color.RED);
					lbl6x.setLayoutX(m.getX()+ 100);
					lbl6x.setLayoutY(m.getY());
					
					root.getChildren().add(lbl6x);
					
					win = winnings * 6;
					
					winnings += win;
					
					txtwinnings.setText("$" + winnings);
					
					System.out.println(winnings);
					
				}
				//checks if the rocket reached a multiplier  creates label, and muliples winnings
				else if (m.getY() == 10)
				{
					lbl10x = new Label();
					lbl10x.setText("10X GRAND PRIZE!");
					lbl10x.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",24));//set font
					lbl10x.setTextFill(Color.RED);
					lbl10x.setLayoutX(m.getX()+ 100);
					lbl10x.setLayoutY(m.getY());
					root.getChildren().add(lbl10x);
					
					win = winnings * 10;
					
					winnings += win;
					txtwinnings.setText("$" + winnings);
					t.stop();
					
					System.out.println(winnings);
					
				}
				//checks if it reaches random y point
				if(m.getY() == ypositions[y])
				{
					//create image and image view for explosion
					Image imgKaboom = new Image("file:kabooom.png");
					ImageView ivDead = new ImageView(imgKaboom);
					
					//create label 
					lblexplode = new Label();
					lblexplode.setGraphic(ivDead);
					lblexplode.setLayoutX(m.getX() + 70);//set x 
					lblexplode.setLayoutY(m.getY() +30);//set y 

					
					root.getChildren().add(lblexplode);//add explosion
					
					//print game over
					System.out.print("GAME OVER");
					t.stop();//stop timer
					btnCstart.setText("YOU LOST! CLICK TO RESET");//set button text 
					W = false;//set boolean to fals e
					//mPlayer2.stop();
					AudioClip explode = new AudioClip("file:explosion.wav");//create audio clip 
					explode.play();//play audio clip 
					
					
				}
				
			}
		});
		
		m.setLocation(275, 650);//set location of rocket
		
		
		root.getChildren().addAll(ivSpace,lblBlack,btnBack,lblBalance,lblBet,txtBet,lblwinning,txtwinnings,btnCstart,m.getNode());//add crash components 
		
		t = new Timeline(kf);//create new timeline
		t.setCycleCount(Timeline.INDEFINITE);//set count to infinite

	}
	private void Back_Click()
	{ 
		mPlayer.play();//play sound 
		lblBalance.setLayoutX(350);//set x
		lblBalance.setLayoutY(700);//set y 
		
		//remove all components 
		root.getChildren().removeAll(ivBack,btnBack,btnBack,lblBet,txtBet,lblslotmachine,btnSpin,lbl3,l1,l2,l3,lblwinning,txtwinnings,btnCstart,m.getNode(),lblBlack,ivSpace,lblBalance,btnCashout,btnAdd);
		root.getChildren().removeAll(lblRou,txtBet,lblBalance,lblBet,lblRed,lblBlack2,lblGreen);

		//add games menu components 
		root.getChildren().addAll(ivBack,lblgames,lblBJ,lblSlots,lblRoulette,lblCrash,BJStart,slotsStart,rouStart,crashStart,lblBalance,btnCashout,btnAdd);
		AudioClip gameOver = new AudioClip("file:thankyou.wav");// creates thank you audio clip 
		gameOver.play();//plays clip 
		
	}
	
	
	
	private void Cashout_Click() throws IOException
	{
		clicks = 1;
		gtotal -= finalAddDeposit;
	
		AudioClip cashOut = new AudioClip("file:cash.mp3");// creates game over music 
		cashOut.play();//plays game over sound when Magikoopa crashes into a bullet 
		
		//remove all components 
		root.getChildren().removeAll(btnBack,btnBack,lblBet,txtBet,lblslotmachine,btnSpin,lbl3,l1,l2,l3,lblwinning,txtwinnings,btnCstart,m.getNode(),lblBlack,ivSpace,lblBalance,btnCashout,btnAdd,lblgames,lblBJ,lblSlots,lblRoulette,lblCrash,BJStart,slotsStart,rouStart,crashStart,btnBack,lblBalance);
		
		//create writers 
		out = new FileWriter (dFile,true);
		writeFile = new BufferedWriter(out);
		
		
		//initialize array list 
		initial = new ArrayList<Double>();
		end = new ArrayList<Double>();
		sum = new ArrayList<Double>();
		nameslist =  new ArrayList<String>();
		sum2 =  new ArrayList<String>();
		
		try
		{
			System.out.print(gtotal);
			writeFile.write(String.valueOf(gtotal));//write total balance to file
			writeFile.newLine();//create new line
			
			//close writers 
			writeFile.close();
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("Problem writing to file.");
			System.err.println("IOException: "+ e.getMessage());
		}
		
		//create writers 
		FileReader in = new FileReader(dataFile);
		BufferedReader readFile = new BufferedReader(in);
		FileReader in2 = new FileReader(dFile);
		BufferedReader readFile2 = new BufferedReader(in2);
		FileReader in3 = new FileReader(d);
		BufferedReader readFile3 = new BufferedReader(in3);
		
		String lineOfText;
		
		
		try
		{
		while ((lineOfText = readFile.readLine())!= null )//read file
		{
			initial.add(Double.parseDouble(lineOfText));//add to arraylist 
		}
			
			//close readers
			readFile.close();
			in.close();
		}
		catch (FileNotFoundException e)//catch
		{
			System.out.println("File does not exist or is not found.");
			System.out.println("FileNotFoundException: " + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.print("Problem reading file");
		}
		
		
		try
		{
		while ((lineOfText = readFile2.readLine())!= null )//read files 
		{
			end.add(Double.parseDouble(lineOfText));//add to arraylist 
		}
			//close readers 
			readFile2.close();
			in2.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File does not exist or is not found.");
			System.out.println("FileNotFoundException: " + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.print("Problem reading file");
		}
		
		
		try
		{
		while ((lineOfText = readFile3.readLine())!= null )//read file 
		{
			nameslist.add(lineOfText);//add to arraylist 
		}
			
			//close readers 
			readFile3.close();
			in3.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File does not exist or is not found.");
			System.out.println("FileNotFoundException: " + e.getMessage());
		}
		catch (IOException e)
		{
			System.out.print("Problem reading file");
		}
		
		//create file writers 
		out = new FileWriter (dF);
		writeFile = new BufferedWriter(out);
		
		try
		{
			for (int i = 0; i < end.size();i ++)
			{
				minus = end.get(i) - initial.get(i);//get the difference 
				sum.add(minus);//add to array list 
				sum2.add(String.valueOf(minus));//add string version to arraylist 
				writeFile.write(String.valueOf(minus));//write file 
				writeFile.newLine();
			}
			
			//close file writers 
			writeFile.close();
			out.close();
			
		}
		catch (IOException e)
		{
			System.out.println("Problem writing to file.");
			System.err.println("IOException: "+ e.getMessage());
		}
		
		insertionSort(sum);//goes to method to sort numbers 
	

		
		data = new String[sum.size()][2];//create 2d array 
		
		
		System.out.println("\nCasino Player History (EMPLOYEE EYES ONLY)");//print data logo
		System.out.println("-------------------------------------------------");

		for (int rows = 0; rows < sum.size(); rows++)
		{
			data[rows][0] = nameslist.get(rows);//add to 2d array 
			data[rows][1] = sum2.get(rows);//add to 2d array 
			System.out.println(data[rows][0] + "\t\t\t" + data[rows][1]);//print 2d array 
		}
		System.out.println("-------------------------------------------------");

		
		
		//create label 
		Label TY = new Label("THANK YOU FOR CHOOSING SPADES CASINO");
		TY.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",50));//set font
		TY.setTextFill(Color.WHITE);//set text colour to white 
		TY.setLayoutX(60);//set x
		TY.setLayoutY(50);//set y
		
		//create label 
		Label rank = new Label("PAY OUT RANKS");
		rank.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf", 40));//set font
		rank.setTextFill(Color.WHITE);//set text colour to white 
		rank.setLayoutX(300);//set x
		rank.setLayoutY(110);//set y 
		
		//create text area 
		WList = new TextArea();
		WList.setPrefSize(500, 500);//set size
		WList.setEditable(false);//set editable to false 
		WList.setLayoutX(200);//set x
		WList.setLayoutY(150);//set y
		
		//create new buttonn 
		Button btnGG = new Button();
		btnGG.setPrefSize(150,50);//set button size 
		btnGG.setLayoutX(450);//set x
		btnGG.setLayoutY(700);//set y 
		btnGG.setFont(Font.loadFont("file:Mufferaw Regular (1).ttf",14));//set font 
		btnGG.setText("EXIT");//set text
		btnGG.setOnAction(e -> gg_click());//goes to method when clicked 
		

		root.getChildren().addAll(TY,WList,rank,btnGG);//add components to the scene 

		//System.out.print(sum.size());
		
		for (int i = 1; i <= sum.size(); i++)
		{
			WList.appendText( ("# " +  i + " PAYOUT: $" +  sum.get(i-1) + "\n"));//writes total to text area 
		}
	}
	
	public static void insertionSort(ArrayList <Double> arr)
	{
		//Controls the pointer for the sorted left half of the array
		for(int endNum = 1;endNum < arr.size();endNum++)
		{
			Double str = arr.get(endNum);
			int i = endNum;
			
			//Searching to see where to insert the value
			while(i > 0 && str.compareTo(arr.get(i-1)) > 0)
			{
				//Moving the larger value over right
				arr.set(i, arr.get(i-1));
				i--;
			}
			//Move the smaller value to the sorted left hand side
			arr.set(i, str);
		}
		
	}
	
	private void rouStartGreen()
	{
		//If the user did not enter a bet
		if (txtBet.getText().isEmpty())
		{
			//An alert will pop up and tell the user to enter a bet
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("ERORR, YOU MUST ENTER A BET! BET MUST BE ATLEAST 1 DOLLAR!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		//Else
		else
		{
			try
			{
				
				//Checking to see if user enters a number. Will run exception if error occurs.
				Double num5 = Double.parseDouble(txtBet.getText());
				
				//Changing the value of int variables that help run the roulette game
				multiply = 10;
				colCount = 3;
				
				//Creating Image and ImageView objects of the roulette
				Image imgRoulette = new Image ("file:roulette2.png");
				iRou = new ImageView(imgRoulette);
				
				//Setting the image of the roulette to the image view objects above
				lblRou.setGraphic(iRou);
				
				//Runs the rouStart() method
				rouStart();
			}
			//If the user does not enter a number
			catch(Exception e)
			{
				//An alert will pop up telling the user to enter a number
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("ERROR, Please enter a valid bet!");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			
		}
	}
	private void rouStartRed()
	{
		//If the user did not enter a bet
		if (txtBet.getText().isEmpty())
		{
			//An alert will pop up and tell the user to enter a bet
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("ERORR, YOU MUST ENTER A BET! BET MUST BE ATLEAST 1 DOLLAR!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		//Else
		else
		{
			try
			{				
				//Checking to see if user enters a number. Will run exception if error occurs.
				Double num5 = Double.parseDouble(txtBet.getText());

				//Changing the value of int variables that help run the roulette game
				multiply = 2;
				colCount = 1;
				
				//Creating Image and ImageView objects of the roulette
				Image imgRoulette = new Image ("file:roulette2.png");
				iRou = new ImageView(imgRoulette);
				
				//Setting the image of the roulette to the image view objects above
				lblRou.setGraphic(iRou);
				
				//Runs the rouStart() method
				rouStart();
			}
			//If the user does not enter a number
			catch(Exception e)
			{
				//An alert will pop up telling the user to enter a number
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("ERORR, Please enter a valid bet!");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			
		}
	}
	
	private void rouStartBlack()
	{
		//If the user did not enter a bet
		if (txtBet.getText().isEmpty())
		{
			//An alert will pop up and tell the user to enter a bet
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("ERORR, YOU MUST ENTER A BET! BET MUST BE ATLEAST 1 DOLLAR!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		//Else
		else
		{
			try
			{
				//Checking to see if user enters a number. Will run exception if error occurs.
				Double num5 = Double.parseDouble(txtBet.getText());

				//Changing the value of int variables that help run the roulette game
				multiply = 2;
				colCount = 2;
				
				//Creating Image and ImageView objects of the roulette
				Image imgRoulette = new Image ("file:roulette2.png");
				iRou = new ImageView(imgRoulette);
				
				//Setting the image of the roulette to the image view objects above
				lblRou.setGraphic(iRou);
			
				//Runs the rouStart() method
				rouStart();
			}
			//If the user does not enter a number
			catch(Exception e)
			{
				//An alert will pop up telling the user to enter a number
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("ERROR, Please enter a valid bet!");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
			
		}
	}
	private void rouStart()
	{
		//Generating random number from 7-14 and assigning it to count variable
		Random rnd = new Random();
		count = rnd.nextInt(7)+15;
		
		//Starting the timer
		timer.play();
	} 
	
	private void gg_click()
	{
		System.exit(3);
	}
	
	private void BJStart() 
	{
		//If the bet textfield is empty
		if(txtBet.getText().isEmpty())
		{
			//An alert will pop up telling the user to enter a valid bet
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("ERORR, YOU MUST ENTER A BET! BET MUST BE ATLEAST 1 DOLLAR!");
			alert.setHeaderText(null);
			alert.showAndWait();
		}
		//Else
		else
		{
			try
			{
				//Setting up the first card's x coordinate
				cardX = 375;
				cardX2 = 375;
				
				//Setting up the initial sums of the dealer and user
				BJSum = 0;
				BJSum2 = 0;
				
				root.getChildren().removeAll(lbl,lbl2);
				
				//Checking to see if the user enter a number. If not, exception is thrown
				bet = Double.parseDouble(txtBet.getText());
				
				//Creating Image and Image View objects of black jack table
				Image imgTable = new Image ("file:Table.jpg");
				ImageView iTable = new ImageView(imgTable);
				
				//Creating label for the table
				Label lblTable = new Label();
				lblTable.setGraphic(iTable);
				lblTable.setLayoutX(125);
				lblTable.setLayoutY(100);
				
				//Creating the label that says "you"
				Label lblYou = new Label();
				lblYou.setPrefSize(100, 25);
				lblYou.setStyle("-fx-background-color: black");
				lblYou.setText("YOU");
				lblYou.setLayoutX(450);
				lblYou.setLayoutY(579);
				lblYou.setAlignment(Pos.CENTER);
				
				//Creating the label that says "dealer"
				Label lblDealer = new Label();
				lblDealer.setPrefSize(100, 25);
				lblDealer.setStyle("-fx-background-color: black");
				lblDealer.setText("DEALER");
				lblDealer.setLayoutX(450);
				lblDealer.setLayoutY(100);
				lblDealer.setAlignment(Pos.CENTER);
				
				//Creating a line in the middle of the table to seperate both sides
				Label lblLine = new Label();
				lblLine.setPrefSize(755, 1);
				lblLine.setLayoutX(125);
				lblLine.setLayoutY(340);
				lblLine.setStyle("-fx-background-color: black");
				
				//Adding to the pane
				root.getChildren().addAll(lblTable,lblYou,lblDealer,lblLine);
				
				//Running BJYou() method
				BJYou();
			}
			//If the user did not enter a number
			catch(Exception e)
			{
				//An alert pops up telling the user to enter a valid bet
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("ERORR, Please enter a valid bet!");
				alert.setHeaderText(null);
				alert.showAndWait();
			}
		}
		
		
		
	}
	
	private void BJYou()
	{
		//Calling random class
		Random rnd = new Random();
				
		//Loop will run 2 times to generate the player and dealer's first 2 cards
		for(int i = 0;i<2;i++)
		{
			//Generating a random number between 1 and 13
			int num1 = rnd.nextInt(13)+1;
			
			//Generating random number between 1 and 4 for the suits
			int num2 = rnd.nextInt(4)+1;
			
			//Generating a random number between 1 and 13
			int num3 = rnd.nextInt(13) + 1;
			
			//Generating a random number between 1 and 4 for the suits
			int num4 = rnd.nextInt(4)+1;
			
			
			//Adding the randomly generated numbers to both sums
			BJSum += num1;
			BJSum2 += num3;
			
			//Initializing two string variables
			String str = "";
			String str2 = "";
			
			//If num2 is 1, the suit is clubs
			if(num2 ==1)
			{
				str = "clubs";
			}
			//If num2 is 2, the suit is hearts
			else if(num2 == 2)
			{
				str = "hearts";
			}
			//If num2 is 3, the suits is spades
			else if(num2 == 3)
			{
				str = "spades";
			}
			//If num2 is 4, the suit is diamonds
			else if(num2 == 4)
			{
				str = "diamonds";
			}
			
			//If num4 is 1, the suit is clubs
			if(num4 ==1)
			{
				str2 = "clubs";
			}
			
			//If num4 is 2, the suit is hearts
			else if(num4 == 2)
			{
				str2 = "hearts";
			}
			//If num4 is 3, the suit is spades
			else if(num4 == 3)
			{
				str2 = "spades";
			}
			//If num4 is 1, the suit is diamonds
			else if(num4 == 4)
			{
				str2 = "diamonds";
			}
			
			//Creating Image and Image View objects for the cards
			Image imgCards = new Image ("file:cards/" + Integer.toString(num1) + "_of_" + str + ".png");
			ImageView iCards = new ImageView(imgCards);
			Image imgCards2 = new Image(("file:cards/" + Integer.toString(num3) + "_of_" + str2 + ".png"));
			ImageView iCards2 = new ImageView(imgCards2);

			//Creating label for the user's cards
			lbl = new Label();
			lbl.setGraphic(iCards);
			lbl.setLayoutX(cardX);
			lbl.setLayoutY(450);
			
			//Creating label for the dealer's cards
			lbl2 = new Label();
			lbl2.setGraphic(iCards2);
			lbl2.setLayoutX(cardX);
			lbl2.setLayoutY(200);
			
			//Adding to the pane
			root.getChildren().addAll(lbl,lbl2);
			
			//Increasing x coordinates of cards by 75 pixels
			cardX += 75;
			cardX2 += 75;
			
		}
		
		//Creating hit button 
		btnHit = new Button();
		btnHit.setText("HIT");
		btnHit.setPrefSize(75, 50);
		btnHit.setAlignment(Pos.CENTER);
		btnHit.setStyle("-fx-background-color: red");
		btnHit.setLayoutX(175);
		btnHit.setLayoutY(425);
		btnHit.setOnAction(e->BJHit());
		
		
		//Creating stand button
		btnStand = new Button();
		btnStand.setText("STAND");
		btnStand.setPrefSize(75, 50);
		btnStand.setAlignment(Pos.CENTER);
		btnStand.setStyle("-fx-background-color: blue");
		btnStand.setLayoutX(175);
		btnStand.setLayoutY(500);
		btnStand.setOnAction(e->BJStand());
		
		if(BJSum > 21 && BJSum2 > 21)
		{
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Both you and the dealer busted! This round was a tie!!!");
			alert.setHeaderText(null);
			alert.show();
		}
		
		//If the user's sum is above 21, he busted
		else if(BJSum > 21)
		{
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("You busted! dealer wins the round!!!");
			alert.setHeaderText(null);
			alert.show();
			
			//Increase total money 
			gtotal -= Double.parseDouble(txtBet.getText()); 
			
			//Changing the text of balance label to new value
			lblBalance.setText("BALANCE: $" + gtotal);
		}
		//If the dealer's sum is above 21
		else if(BJSum2 > 21)
		{
			//Disable both buttons
			btnStand.setDisable(true);
			btnHit.setDisable(true);
			
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Dealer busted! You won the round!!!");
			alert.setHeaderText(null);
			alert.show();
			
			//Increase total money 
			gtotal += Double.parseDouble(txtBet.getText());
			AudioClip winner = new AudioClip("file:winner.mp3");// creates game over music 
			winner.play();//plays game over sound when player wins the round
			
			//Changing the text of balance label to new value
			lblBalance.setText("BALANCE: $" + gtotal);

		}
		else if(BJSum == 21 && BJSum2 == 21)
		{
			btnHit.setDisable(true);
			btnStand.setDisable(true);
			
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Both you and the dealer got BLACKJACK! This round was a tie!!!");
			alert.setHeaderText(null);
			alert.show();
			
			
		}
		
		else if(BJSum == 21)
		{
			BJStand();
		}
		
		
		//Adding to pane
		root.getChildren().addAll(btnHit,btnStand);
	}
	
	private void BJHit() 
	{
		//Calling random class
		Random rnd = new Random();
		
		//Generating random number between 1 and 13
		int num1 = rnd.nextInt(13)+1;
		
		//Generating random number between 1 and 4
		int num2 = rnd.nextInt(4)+1;
		
		//Increasing user's sum
		BJSum += num1;
		
		//Initializing string variable
		String str = "";
		
		//If num2 is 1, the suit is clubs
		if(num2 ==1)
		{
			str = "clubs";
		}
		//If num2 is 2, the suit is hearts
		else if(num2 == 2)
		{
			str = "hearts";
		}
		//If num2 is 3, the suit is spades
		else if(num2 == 3)
		{
			str = "spades";
		}
		//If num2 is 4, the suit is diamonds
		else if(num2 == 4)
		{
			str = "diamonds";
		}
		
		//Creating image and image view objects for the cards
		Image imgCards = new Image ("file:cards/" + Integer.toString(num1) + "_of_" + str + ".png");
		ImageView iCards = new ImageView(imgCards);
		
		//Creating label for the player's cards
		Label 	lbl = new Label();
		lbl.setGraphic(iCards);
		lbl.setLayoutX(cardX);
		lbl.setLayoutY(450);
		
		//Adding to pane
		root.getChildren().addAll(lbl);
		
		//Increasing x coordinate of cards by 75
		cardX+=75;
		
		//If the user's sum is above 21
		if(BJSum >= 21)
		{
			
			
			if(BJSum == 21)
			{
				BJStand();
			}
			else
			{
				//Disable both buttons
				btnHit.setDisable(true);
				btnStand.setDisable(true);
				
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("ERROR");
				alert.setContentText("You busted! dealer wins the round!!!");
				alert.setHeaderText(null);
				alert.show();

				//Increase total money 
				gtotal -= Double.parseDouble(txtBet.getText()); 
				//Changing the text of balance label to new value
				lblBalance.setText("BALANCE: $" + gtotal);
				
			}
		}
		
		
	}
	
	
	private void BJStand()
	{
		//Disabling both buttons
		btnHit.setDisable(true);
		btnStand.setDisable(true);
		
		//Playing the timer
		timer2.play();
	} 

	
} 
