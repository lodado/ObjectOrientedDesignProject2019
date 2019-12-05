package playground;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * �ʿ� ���� Ŭ����
 * @author Chungheon Lee(������)
 *
 */

public class M
{
	/**�� �����̼� ��ġ(��ȣ) ������ ���� {x ��  Z , 0<=x<=8} */
	private int location; 
	
	/**  �� �̸� */
	private String mapName; 
	
	/** �� ���� �� �ȿ� �ִ� ������ ��  */
	private int UserNumber;
	
	/** �� ���� �̹��� */
	private ImageIcon image;
	
	/** �� ���� ������ �̹��� */
	private ImageIcon iconImage;
	
	/** flag for thread true �� �ȴݱ��Ű� false�� �ݱ�*/
	private boolean flag = true;
	
			
	/** map�� setting �ϴ� map�� ������
	 * @param mylocation  map���� �� ��ġ�� �˷��ִ� ������
	 * @param name        map�� ��ġ�� �̸�
	 */
	public M(int mylocation,String name)
	{
		this.mapName = name;
		this.location = mylocation;
		UserNumber = 0;
	}
	
	
	/** @return �ʿ��� �ڱ� ��ġ ��ȯ */
	public int getLoc()
	{
		
		return location;
	}
	
	/** @return �ڽ� �̸� ��ȯ  */
	public String getMapName()
	{
		return mapName;
	}
	
	/** �ڱ� ���Ҹ� setting �ϴ� �޼ҵ� 
	 * @param setlocation �ڱ� ����(��ġ) ����
	 */
	public void setLoc(int setlocation)
	{
		this.location = setlocation;	
	}
	
	/** �� �� ���� �������� ��ȯ�ϴ� �޼ҵ� 
	 * @return ���� �ڱ� �ڽ� ���� �� ������ ��ȯ
	 */
	public int getUserNumber()
	{
		return UserNumber;
	}
	
	/** �� ���� �������� �����ϴ� �޼ҵ� 
	 * @param number  
	 */
	public void setUserNumber(int number)
	{
		this.UserNumber = number;
	}
	
	/** ���� �̹����� �����ϴ� �޼ҵ� 
	 * @param ima �ڱ� �̹��� ����
	 */
	public void setImage(ImageIcon ima)
	{
		image = (ima);
	}
	
	/** ���� ������ �̹����� �����ϴ� �޼ҵ� 
	 * @param ima �ڱ� ������ �̹��� ����
	 */
	public void setIconImage(ImageIcon ima)
	{
		iconImage = (ima);
	}
	
	/** �ڽ��� �̹����� ��ȯ�ϴ� �޼ҵ�
	 * @return �ڱ� �̹��� ��ȯ
	 */
	public ImageIcon getMapImage()
	{
		return image;
	}
	
	/** �ڽ��� ������ �̹����� ��ȯ�ϴ� �޼ҵ�
	 * @return �ڱ� ������ �̹��� ��ȯ
	 */
	public ImageIcon getIconImage()
	{
		return iconImage;
	}
	
	/**
	 * ȭ�� �ѱ涧 ���� ������ ���� ������
	 */
	public void setFlag()
	{
		flag=!flag;
	}
	
	/** false�� ���� ���Ұ� true�� ���� ����
	 * @return ������ ��ȯ
	 */
	public boolean getFlag()
	{
		return flag;
	}
}
