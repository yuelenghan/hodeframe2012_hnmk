


public class Test {

	public static void getDirPath(String strIDCard)
    {
		String strRtn  = "";
		if(strIDCard.length() == 18 )
		{
			strRtn = strIDCard.substring(6,10)+"-"+strIDCard.substring(10,12)+"-"+strIDCard.substring(12,14);
		}
		else if(strIDCard.length() == 15 )
		{
			strRtn = "19"+strIDCard.substring(6,8)+"-"+strIDCard.substring(8,10)+"-"+strIDCard.substring(10,12);
		}
		System.out.println(strRtn);
    }
	public static void main(String[] args)
	{
		String s = "qwe�鳤ban";
		System.out.println(s.indexOf("���鳤"));
	}
}
