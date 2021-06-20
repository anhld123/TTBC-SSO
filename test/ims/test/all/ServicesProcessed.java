package ims.test.all;




import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;


public class ServicesProcessed 
{

	public ServicesProcessed()
	{
		
	}
	
	private int iAutoId;
	private String strServiceCode;
	private String strServiceNumber;
	private String strContentIn;
	//private int iAutoId;
	private String strFromNumber;
	private String strMobile_Operin;
	private Timestamp dateIn;
	private String strContenIn;
	private int iGroupId;
	private String strToNumber;
	private String strMobile_Operout;
	private Timestamp dateOut;
//	private String strServiceCode;
//	private String strServiceNumber;
	private String strVaspid;
	private String strContentOut;
	private int idataType;
	private int iStatus;
	private String strOther;

	public void Processedcontnetin( int iAutoId, String strFromnumber, String strServiceCode, String strServiceNumber, String strContentIn) 
	{
		System.out.println("Da xu ly thanh cong nhe haha");
		System.out.println(iAutoId+"  "+strServiceCode+"   "+strServiceNumber+"   "+strContentIn);
	}
	//Vi du khi viet 1 dich vu tra ra so may man
	public int add(int a, int b)
        {
            return a+b;
        }

}
