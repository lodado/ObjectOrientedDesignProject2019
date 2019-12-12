/*
 * 
 */
package Map;

import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import playground.GameCharacter;

/**
 * Map Class
 * @author Chungheon Lee
 */
public class map
{
	
	/** number of MapLocation  0..8 */
	private int location; 
	
	/**   Name of Map */
	private String mapName; 
	
	/**  Image of Map */
	private ImageIcon image;
	
	/**  Icon of Map */
	private ImageIcon iconImage;
	
	/**  Contain AI in list */
	LinkedList<GameCharacter> AI = new LinkedList<>();
	
	/**  flag for thread true(not harmful place), false(harmful) */
	private boolean flag = true;
	
	/**
	 * Instantiates a new map.
	 * @param mylocation the location
	 * @param name the name of map
	 */
	public map(int mylocation,String name)
	{
		this.mapName = name;
		this.location = mylocation;
	}
	
	
	/**
	 * Gets the loc.
	 *
	 * @return location
	 */
	public int getLoc()
	{
		
		return location;
	}
	
	/**
	 * Gets the map name.
	 * @return mapName
	 */
	public String getMapName()
	{
		return mapName;
	}
	
	/** set Location
	 * @param setlocation set my location
	 */
	public void setLoc(int setlocation)
	{
		this.location = setlocation;	
	}
	
	/**
	 * get AInumber in map
	 * @return total AInumber in a map
	 */
	public int getAINumber()
	{
		return AI.size();
	}
	
	/**
	 * Gets the ai.
	 * @return the ai
	 */
	public LinkedList<GameCharacter> getAI(){
		return AI;
	}
	
	/**
	 * addAI in map
	 * @param AImove addAI
	 */
	public void addAI(GameCharacter AImove)
	{
		this.AI.add(AImove);
	}
	
	/**
	 * Pop AI in linkedList
	 * @param name nameofMap
	 */
	public void popAI(String name)
	{
		for(int i=0; i<AI.size(); i++)
			{	
				if(AI.get(i).getName() == name)
					{	
						AI.remove(AI.get(i));			
					}
			}
	}
	
	/**
	 * Sets the image.
	 * @param ima the new image
	 */
	public void setImage(ImageIcon ima)
	{
		image = (ima);
	}
	
	
	/**
	 * Sets the icon image.
	 * @param ima the new icon image
	 */
	public void setIconImage(ImageIcon ima)
	{
		iconImage = (ima);
	}
	
	/**
	 * Gets the map image.
	 * @return the map image
	 */
	public ImageIcon getMapImage()
	{
		return image;
	}
	
	
	/**
	 * Gets the icon image.
	 * @return the icon image
	 */
	public ImageIcon getIconImage()
	{
		return iconImage;
	}
	
	/**
	 * Sets the flag for Thread.
	 */
	public void setFlag()
	{
		flag=!flag;
	}
	
	/**
	 *  false harmful place, true safe.
	 * @return flag
	 */
	public boolean getFlag()
	{
		return flag;
	}
}
