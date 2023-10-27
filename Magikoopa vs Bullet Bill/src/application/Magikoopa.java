package application;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Magikoopa 
{
	//declare variables 
	public boolean isRight;
	public Image imgLeft, imgRight, imgDead;
	public ImageView ivMagikoopa;
	public static final int NORTH = 90, SOUTH = 270, EAST = 0, WEST = 180;
	private int dir;
	private double xPos, yPos, width, height;
	
	
	
	public Magikoopa() {
		
		//create images and imageview
		imgLeft = new Image("file:koopaWest.png");
		imgRight = new Image("file:koopaEast.png");
		ivMagikoopa = new ImageView(imgRight);
		dir = EAST;//set intitial dir 
		width = imgRight.getWidth();//set width to image width
		height = imgRight.getHeight();//set height to image height
		xPos = 0;
		yPos = 0;
	}
	public double getHeight()//method to get height 
	{
		return height;//returns height
	}
	
	public double getWidth()//method to get width
	{
		return width;//returns width
	}
	
	public int getDirection()//method to get direction
	{
		return dir;//returns direction
	}
	
	public void setDirection()//method to set Direction
	{
		if (ivMagikoopa.getImage().equals(imgLeft))//checks if image is facing left
		{
			dir = 180;//sets dir to 180
			
		} 
		else if (ivMagikoopa.getImage().equals(imgRight)) //checks if image is facing right
		{
			dir= 0;//sets dir to 0 
		}
	}
	
	public Image setDeadImage() //method that sets dead image for alert 
	{
		if (ivMagikoopa.getImage().equals(imgLeft))//if image is left facing 
		{
			imgDead = new Image("file:deadWest.png");//set imgDead west facing
			
		} 
		else if (ivMagikoopa.getImage().equals(imgRight)) //if image is right facing
		{
			imgDead = new Image("file:deadEast.png");//set imgDead east facing
		}
		
		return imgDead;//return imageview
	}
	
	
	public ImageView getNode()//method that sets image 
	{
		
		if (dir== EAST) //if direction is east
		{
			ivMagikoopa.setImage(imgRight);//set image to right facing magikoopa
		} 
		else if (dir == WEST)// if direction is west 
		{
			ivMagikoopa.setImage(imgLeft);//set image to left facing magikoopa
		}
		
		
		return ivMagikoopa;//return image
	}
	
	public double getX() //gets X coordinate 
	{
		return xPos;//returns x position
	}
	
	public double getY()//gets y coordinate
	{
		return yPos;//reterns y position
	}
	
	public void move(int dir)//method that moves  MagiKoopa 
	{
		this.dir = dir ;
		
		if (this.dir == EAST)//if direction is east 
		{
			xPos += 10;//moves 10 pixels east
		} 
		if (this.dir == WEST) //if direction is west
		{
			xPos -= 10;//moves 10 pixels west 
		}
		if (this.dir == NORTH) //if direction is north
		{
			yPos -= 10;//moves 10 pixels north
		} 
		if (this.dir == SOUTH) //if direction is south
		{
			yPos += 10;//moves 10 pixels south
		}
		
		ivMagikoopa.setX(xPos);//sets xPos
		ivMagikoopa.setY(yPos);//sets yPos
		
		
	}
	
	public void setLocation(double x, double y)//method that sets location
	{
		xPos = x;//sets x pos
		yPos = y;//sets y pos
		
		ivMagikoopa.setX(xPos);
		ivMagikoopa.setY(yPos);
		
	}
	

	public void setX(double x) 
	{
		xPos = x;//sets xPos
	}
	
	public void setY(double y) 
	{
		yPos = y;//sets yPos
	}
	
	public Bounds getObjectBounds() 
	{
		Rectangle rect = new Rectangle(xPos + 15, yPos + 15, width - 30,height - 15);
		return rect.getBoundsInParent();
	}
	
	

}
