package application;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Rocket 
{
	//declare variables
	private double xPos,yPos;
	private double width, height;
	private Image imgRocket;
	public ImageView iview;
	private int index;
	
	
	public Rocket() 
	{
		//initialize variables 
		xPos = 0;
		yPos = 0;
		index = 0;
		
		imgRocket = new Image("file:download.png");
		width = imgRocket.getWidth();
		height = imgRocket.getHeight();
		iview = new ImageView(imgRocket);
		iview.setX(xPos);
		iview.setY(yPos);
	}
	public double getHeight()//method to get height 
	{
		return height;//returns height
	}
	
	public double getWidth()//method to get width
	{
		return width;//returns width
	}
	
	public ImageView getNode()//method that sets image 
	{
		return iview;//return image
	}
	
	public double getX() //gets X coordinate 
	{
		return xPos;//returns x position
	}
	
	public double getY()//gets y coordinate
	{
		return yPos;//reterns y position
	}
	
	public void move()//method that moves  MagiKoopa 
	{
			xPos += 1;
			yPos -= 1.25;
			
			iview.setX(xPos);
			iview.setY(yPos);
	}
	
	public void setLocation(double x, double y)//method that sets location
	{
		xPos = x;//sets x pos
		yPos= y;//sets y pos
		
		iview.setX(xPos);
		iview.setY(yPos);
		
	}
	

	public void setX(double x) 
	{
		xPos = x;//sets xPos
	}
	
	public void setY(double y) 
	{
		yPos = y;//sets yPos
	}
	
	public Image setDeadImage() //method that sets dead image for alert 
	{
		Image imgKaboom = new Image("file:kabooom.png");
		
		return imgKaboom;
	}
	
	

}