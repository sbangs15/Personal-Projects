package application;
	
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.GroupLayout.Alignment;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class LittleCaesars extends Application  {
	private Button btnCalc, btnClear,btnExit,btnCheck;
	private int num,topcount,sizeCheck,cokeIndex,spriteIndex,orangeIndex,rootbeerIndex;
	private double ptotal;
	private ComboBox<String> cboCoke,cboSprite,cboOrange,cboRootBeer;
	private TextField txtPizza,txtTopping,txtBeverages,txtSUB,  txtDELIVERY,txtHST,txtTOTAL;
	private ArrayList<Integer> tindex = new ArrayList <Integer>();
	private DecimalFormat df = new DecimalFormat("##.00");
	private ToggleGroup toggleGroup= new ToggleGroup();
	private RadioButton small, medium,large,party;
	private CheckBox  mush,greenpep,onion,hotpep,pep,bacon,tomato,cheese;
	private String size,toppings;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			GridPane root = new GridPane();
			Scene scene = new Scene(root);
			//root.setGridLinesVisible(true);
			root.setPadding(new Insets(10,10,10,10));
			root.setHgap(10);
			root.setVgap(20);
			
			
			Image imgTitle = new Image("file:littlecaesarslogo.png");
			
			Image imgPaymentOption = new Image("file:PaymentOptions.png");
			
			Label lblTitle = new Label();
			lblTitle.setGraphic(new ImageView(imgTitle));
			
			Label lblPay = new Label();
			
			lblPay.setGraphic(new ImageView(imgPaymentOption));
			lblPay.setAlignment(Pos.CENTER);
			root.setHalignment(lblPay, HPos.CENTER);
			root.add(lblPay,0, 4);
			

			
			Label lbltop = new Label();
			lbltop.setText("                  First (3) toppings are free!");
			lbltop.setAlignment(Pos.CENTER);
			
			GridPane.setColumnSpan(lblTitle, 4);
			GridPane.setHalignment(lblTitle, HPos.CENTER);
			
			
			
			root.add(lblTitle, 0, 0);
			
			
			btnCalc = new Button("CALCULATE");
			//btnCalc.setStyle(-fx);
			btnCalc.setPrefSize(100, 30);
			btnCalc.setOnAction(e -> Calc_Click());
			//btnCalc.setOnAction(this);
			
			btnClear = new Button("CLEAR");
			btnClear.setPrefSize(100, 30);
			btnClear.setOnAction(e -> Clear_Click());

			//btnClear.setOnAction(this);

			btnExit = new Button("EXIT");
			btnExit.setPrefSize(100, 30);
			//btnExit.setOnAction(this);
			btnExit.setOnAction(e -> Exit_Click());
			
			btnCheck = new Button("CHECKOUT");
			btnCheck.setStyle("-fx-background-color: green");
			btnCheck.setPrefSize(100, 30);
			btnCheck.setDisable(true);
			btnCheck.setOnAction(e -> Check_Click());
			
			TilePane tilepanes = new TilePane();
			tilepanes.setPrefColumns(1);
			//tilepanes.setPrefRows();
			tilepanes.setAlignment(Pos.TOP_CENTER);
			tilepanes.setVgap(18);
			
			small = new RadioButton("Small");
			small.setPrefSize(100, 20);
			small.setOnAction(e -> RadioButton_Click(small));
		
			medium = new RadioButton("Medium");
			medium.setPrefSize(100, 20);
			medium.setOnAction(e -> RadioButton_Click(medium));

			large = new RadioButton("Large");
			large.setPrefSize(100, 20);
			large.setOnAction(e -> RadioButton_Click(large));

			party = new RadioButton("Party");
			party.setPrefSize(100, 20);
			party.setOnAction(e -> RadioButton_Click(party));

			
			
			
			toggleGroup.getToggles().addAll(small, medium, large, party);
			
			tilepanes.getChildren().addAll(small, medium, large, party);
			
			
			
			
			TitledPane roots1 = new TitledPane();
			roots1.setText("Size:");
			roots1.setCollapsible(false);
			roots1.setContent(tilepanes);
			
			root.add(roots1, 0, 1);
			GridPane.setValignment(roots1, VPos.TOP);
			
			
			
			TilePane tilepanes1 = new TilePane();
			tilepanes1.setPrefColumns(2);
			tilepanes1.setPrefRows(4);
			tilepanes1.setOrientation(Orientation.VERTICAL);
			tilepanes1.setAlignment(Pos.TOP_CENTER);
			tilepanes1.setVgap(18);
		
			mush = new CheckBox("Mushrooms");
			mush.setPrefSize(120, 20);
			mush.setOnAction(e -> chkToppings_Click(mush));
			greenpep = new CheckBox("Green Peppers");
			greenpep.setPrefSize(120, 20);
			greenpep.setOnAction(e -> chkToppings_Click(greenpep));

			onion = new CheckBox("Onions");
			onion.setPrefSize(120, 20);
			onion.setOnAction(e -> chkToppings_Click(onion));

			hotpep = new CheckBox("Hot Peppers");
			hotpep.setPrefSize(120, 20);
			hotpep.setOnAction(e -> chkToppings_Click(hotpep));

			pep = new CheckBox("Peperonni");
			pep.setPrefSize(120, 20);
			pep.setOnAction(e -> chkToppings_Click(pep));

			bacon = new CheckBox("Bacon");
			bacon.setPrefSize(120, 20);
			bacon.setOnAction(e -> chkToppings_Click(bacon));

			
			tomato = new CheckBox("Tomatoes");
			tomato.setPrefSize(120, 20);
			tomato.setOnAction(e -> chkToppings_Click(tomato));

			cheese = new CheckBox("Extra Cheese");
			cheese.setPrefSize(120, 20);
			cheese.setOnAction(e -> chkToppings_Click(cheese));

			
			tilepanes1.getChildren().addAll(mush,greenpep,onion,hotpep,pep,bacon,tomato,cheese);
			
			TitledPane roots2 = new TitledPane();
			roots2.setText("TOPPINGS:");
			roots2.setCollapsible(false);
			roots2.setContent(tilepanes1);
			
			root.add(roots2, 1, 1);
			GridPane.setValignment(roots2, VPos.TOP);
			root.add(lbltop, 1, 2);
			
			
			TilePane tilepanes2 = new TilePane();
			tilepanes2.setPrefColumns(2);
			tilepanes2.setPrefRows(5);
			tilepanes2.setOrientation(Orientation.VERTICAL);
			
			cboCoke = new ComboBox<String>();
			cboCoke.getItems().addAll("0", "1", "2", "3", "4", "5","6" );
			cboCoke.setPromptText("0");
			cboCoke.setVisibleRowCount(4);
			cboCoke.setOnAction(e -> Bev_Click(cboCoke,cboSprite,cboOrange,cboRootBeer));
			Label lblCoke = new Label("Coke");
			lblCoke.setPrefSize(80, 20);
		
			cboSprite= new ComboBox<String>();
			cboSprite.getItems().addAll("0", "1", "2", "3", "4", "5","6" );
			cboSprite.setPromptText("0");
			cboSprite.setVisibleRowCount(4);
			cboSprite.setOnAction(e -> Bev_Click(cboCoke,cboSprite,cboOrange,cboRootBeer));

			Label lblSprite = new Label("Sprite");
			lblSprite.setPrefSize(80, 20);
			
			cboOrange = new ComboBox<String>();
			cboOrange.getItems().addAll("0", "1", "2", "3", "4", "5","6" );
			cboOrange.setPromptText("0");
			cboOrange.setVisibleRowCount(4);
			cboOrange.setOnAction(e -> Bev_Click(cboCoke,cboSprite,cboOrange,cboRootBeer));

			Label Orange = new Label("Orange");
			Orange.setPrefSize(80, 20);
			
			cboRootBeer = new ComboBox<String>();
			cboRootBeer.getItems().addAll("0", "1", "2", "3", "4", "5","6" );
			cboRootBeer.setPromptText("0");
			cboRootBeer.setVisibleRowCount(4);
			cboRootBeer.setOnAction(e -> Bev_Click(cboCoke,cboSprite,cboOrange,cboRootBeer));

			Label lblRootBeer = new Label("Root Beer");
			lblRootBeer.setPrefSize(80, 20);
			
			tilepanes2.getChildren().addAll(lblCoke,lblSprite,Orange,lblRootBeer,cboCoke,cboSprite,cboOrange,cboRootBeer);
			
			
			TitledPane roots3 = new TitledPane();
			roots3.setText("COLOURS");
			roots3.setAlignment(Pos.CENTER);
			roots3.setCollapsible(false);
			CheckBox red = new CheckBox("Red");
			red.setPrefSize(120, 20);
			CheckBox blue = new CheckBox("Blue");
			blue.setPrefSize(120, 20);
			CheckBox yel = new CheckBox("Yellow");
			yel.setPrefSize(120, 20);
			CheckBox orange = new CheckBox("Orange");
			orange.setPrefSize(120, 20);
			
			VBox v = new VBox();
			v.setAlignment(Pos.CENTER);
			v.getChildren().addAll(red,blue,yel,orange);
			
			VBox.setMargin(red, new Insets(0,0,10,0));
			VBox.setMargin(blue, new Insets(0, 0, 10, 0));
			VBox.setMargin(yel, new Insets(0, 0, 10, 0));
			VBox.setMargin(orange, new Insets(0, 0, 10, 0));
			
			
			
			roots3.setContent(tilepanes2);
			root.add(roots3, 2, 1);
			
			txtPizza = new TextField();
			txtPizza.setEditable(false);
			//txtPizza.setMaxSize(100,30);
			
			
			 txtTopping = new TextField();
			 txtTopping.setEditable(false);
			// txtTopping.setPrefSize(100,30);
			
			 txtBeverages = new TextField();
			 txtBeverages.setEditable(false);
			 //txtBeverages.setPrefSize(50,10);
			
			root.add(txtPizza, 0, 3);
			root.add(txtTopping, 1, 3);
			root.add(txtBeverages, 2, 3);
			
			
			
			
			TilePane totals = new TilePane();
			totals.setPrefRows(4);
			totals.setPrefColumns(2);
			totals.setVgap(10);
			
			Label lblSUB = new Label("SUBTOTAL");
			lblSUB.setPrefSize(100, 20);
			txtSUB = new TextField();
			txtSUB.setPrefSize(100,25);
			txtSUB.setEditable(false);
			
			Label lblDELIVERY = new Label("DELIVERY FEE");
			lblDELIVERY.setPrefSize(100, 20);
			txtDELIVERY = new TextField();
			txtDELIVERY.setPrefSize(100,25);
			txtDELIVERY.setEditable(false);
			
			
			Label lblHST = new Label("HST");
			lblHST.setPrefSize(100, 20);
			 txtHST= new TextField();
			txtHST.setPrefSize(100,20);
			txtHST.setEditable(false);
			
			Label lblTOTAL = new Label("GRAND TOTAL");
			lblTOTAL.setPrefSize(100, 20);
			txtTOTAL = new TextField();
			txtTOTAL.setPrefSize(100,25);
			txtTOTAL.setEditable(false);
			
			totals.getChildren().addAll(lblSUB,txtSUB,lblDELIVERY,txtDELIVERY,lblHST,txtHST,lblTOTAL,txtTOTAL);
			//totals.getChildren().addAll(txtSUB,txtDELIVERY,txtHST,txtTOTAL);
			
			
			VBox vboxPane = new VBox();
			vboxPane.setAlignment(Pos.CENTER);
			
			

			VBox.setMargin(btnCalc, new Insets(0,0,10,0));
			VBox.setMargin(btnClear, new Insets(0, 0, 10, 0));
			VBox.setMargin(btnCheck, new Insets(0, 0, 10, 0));

			vboxPane.getChildren().addAll(btnCalc, btnClear, btnCheck,btnExit);
			
			root.add(vboxPane,2, 4);
			root.setHalignment(totals, HPos.CENTER);
			root.add(totals, 1, 4);
			
			
			
			
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void RadioButton_Click(RadioButton rb) 
	{
		size = rb.getText();
		int small = 7,medium=8,large=9,party=10;
		
		
		if (size.equals("Small"))
		{
			txtPizza.setText("$" + small + ".99");
			sizeCheck = small;
			
		}
		else if (size.equals("Medium"))
		{
			txtPizza.setText("$" + medium + ".99");	
			sizeCheck = medium;

		}
		else if (size.equals("Large"))
		{
			txtPizza.setText("$" + large + ".99");
			sizeCheck = large;

		}
		else if (size.equals("Party"))
		{
			txtPizza.setText("$" + party + ".99");
			sizeCheck = party;

		}
		
	}
	private void chkToppings_Click(CheckBox chk) 
	{
		
		if (chk.isSelected())
		{
			num++;
			
		}
		else
		{
			num--;
		}
		
		if (num < 4)
		{
			txtTopping.setText("$0.00");
		}
		else
		{
			topcount = num -3;
			txtTopping.setText("$" + topcount + ".00");
		}
		
	}
	private void Bev_Click(ComboBox cboCoke, ComboBox cboSprite,ComboBox cboOrange, ComboBox cboRootBeer) 
	{
			cokeIndex = cboCoke.getSelectionModel().getSelectedIndex();
			spriteIndex = cboSprite.getSelectionModel().getSelectedIndex();
			orangeIndex = cboOrange.getSelectionModel().getSelectedIndex();
			rootbeerIndex = cboRootBeer.getSelectionModel().getSelectedIndex();
		
		if (cokeIndex == -1)
		{
			cokeIndex = 0;
		}
		if (spriteIndex == -1)
		{
			spriteIndex = 0;
		}
		if (orangeIndex == -1)
		{
			orangeIndex = 0;
		}
		if (rootbeerIndex == -1)
		{
			rootbeerIndex = 0;
		}
		/*System.out.println(cokeIndex);
		System.out.println(spriteIndex);
		System.out.println(orangeIndex);
		System.out.println(rootbeerIndex);*/
		
		int total = cokeIndex + spriteIndex + orangeIndex + rootbeerIndex;
	
		ptotal = total * 0.99;
		txtBeverages.setText("$" + df.format(ptotal));
		
	}
	
	private void Calc_Click() 
	{
		
		if (txtPizza.getText().equals(""))
		{
			Alert alerts = new Alert(AlertType.ERROR);
			alerts.setContentText("YOU MUST CHOOSE A PIZZA SIZE!");
			alerts.setHeaderText(null);
			alerts.showAndWait();
		}
		else
		{
		double dfee;
		
		double subtotal = ptotal + topcount + sizeCheck + 0.99;
		
		txtSUB.setText("$" + df.format(subtotal));
		
		if (subtotal > 15.00)
		{
			 dfee = 0.00;
			 txtDELIVERY.setStyle("-fx-control-inner-background: green");
		}
		else
		{
			dfee = 5.00;
			txtDELIVERY.setStyle("-fx-control-inner-background: white");
		}
		
		 txtDELIVERY.setText("$" + df.format(dfee));
		 
		 double tax = subtotal * 0.13;
		 double gtotal = subtotal + tax +dfee;
		 
		 txtHST.setText("$" + df.format(tax));
		 txtTOTAL.setText("$" + df.format(gtotal));
		 
		 btnCheck.setDisable(false);

		 
		}
	}
	
	private void Clear_Click() 
	{
		small.setSelected(false);
		medium.setSelected(false);
		large.setSelected(false);
		party.setSelected(false);
		
		mush.setSelected(false);
		greenpep.setSelected(false);
		hotpep.setSelected(false);
		onion.setSelected(false);
		pep.setSelected(false);
		bacon.setSelected(false);
		tomato.setSelected(false);
		cheese.setSelected(false);
		
		cboCoke.setPromptText("0");
		cboSprite.setPromptText("0");
		cboOrange.setPromptText("0");
		cboRootBeer.setPromptText("0");
		
		txtPizza.clear();
		txtTopping.clear();
		txtBeverages.clear();
		txtSUB.clear();
		txtDELIVERY.clear();
		txtHST.clear();
		txtTOTAL.clear();
	
		txtDELIVERY.setStyle("-fx-control-inner-background: white");
		
		btnCheck.setDisable(true);
		num =0;
			
	}
	private void Exit_Click() 
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to exit?");
		alert.setHeaderText(null);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.YES)
		{
			
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.setContentText("Thanks you for choosing Little Caesars!");
			alerts.setHeaderText(null);
			alerts.showAndWait();
			
			System.exit(0);
		}
	}
	private void Check_Click() 
	{
	
		String toppings = "";
		
		if (mush.isSelected())
		{
			toppings += "-Mushrooms\n";
		}
		if (greenpep.isSelected())
		{
			toppings += "-Green Pepper\n";
		}
		if (onion.isSelected())
		{
			toppings += "-Onions\n";
		}
		if (hotpep.isSelected())
		{
			toppings += "-Hot Peppers\n";
		}
		if (pep.isSelected())
		{
			toppings += "-Pepperoni\n";
		}
		if (bacon.isSelected())
		{
			toppings += "-Bacon\n";
		}
		if (tomato.isSelected())
		{
			toppings += "-Tomatos\n";
		}
		if (cheese.isSelected())
		{
			toppings += "-Extra Cheese\n";
		}
		
		System.out.print(toppings);
	
		String beverages="";
		
		if (cokeIndex > 0)
		{
			beverages += "-"  + String.valueOf(cokeIndex) + "x Coke\n" ;
		}
		if (spriteIndex > 0)
		{
			beverages += "-"  + String.valueOf(spriteIndex) + "x Sprite\n" ;
		}
		if (orangeIndex > 0)
		{
			beverages += "-"  + String.valueOf(orangeIndex) + "x Orange\n" ;
		}
		if (rootbeerIndex > 0)
		{
			beverages += "-"  + String.valueOf(rootbeerIndex) + "x Root Beer\n" ;
		}
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Is this order Correct?\n\nPIZZA:\n-" + size + "\n\nTOPPINGS:\n" + toppings + "\nBEVERAGES:\n" + beverages);
		alert.setHeaderText(null);
		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == ButtonType.YES)
		{
			
			Alert alerts = new Alert(AlertType.INFORMATION);
			alerts.setContentText("Thanks you for ordering from Little Caesars!\nYour pizza will be delivered in 30 min!");
			alerts.setHeaderText(null);
			alerts.showAndWait();
			
			System.exit(0);
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

