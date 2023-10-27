package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BulletBill 
{
	//Declare Variables
	private Image imgBulletBill;
	private ImageView ivBulletBill;
	private int xPos,yPos,width,height;
	private Random rand = new Random();
	
	public BulletBill() 
	{
		//create images 
		imgBulletBill = new Image("file:bulletWest.png");
		ivBulletBill = new ImageView(imgBulletBill);
		
		xPos = 0;//set xPos
		yPos = 0;//set yPos
		width = (int)imgBulletBill.getWidth();//get image width
		height = (int)imgBulletBill.getHeight();//get image height
		
		rand = new Random();//create random 
	}
	public ImageView getImage()//method that gets Bullet Bill Image 
	{
		return ivBulletBill;//return image 
	}
	public void setX(int x)//method that sets X
	{
		xPos = x;//set xPos as X
	}
	public int getX()//method that gets X
	{
		return xPos;//returns x Posiiton
	}
	public void setY(int y)//method that gets Y
	{
		yPos = y;//sets y position 
	}
	public int getY()//method that gets Y 
	{
		return yPos;//return y pos
	}
	public int getHeight()//method that gets height 
	{
		return height;//returns height 
	}
	public int getWidth()//method that gets width
	{
		return width;//returns width
	}
	public void move (int move)//method that moves Bullet Bill
	{
		xPos -= move;//moves at speed entered in main
		ivBulletBill.setX(xPos);//sets x position
	}
	public void setLocation (int frameWidth, int frameHeight)//sets location 
	{
		xPos = frameWidth;//set starting x position at frame width
		yPos = rand.nextInt((frameHeight - height));//set y random y position
		
		ivBulletBill.setX(xPos);//set x position
		ivBulletBill.setY(yPos);//set y position
		
	}
}
