package resources;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import resources.Assets;
public class Sms_api {


	
	public static String ToHexString(String input)
	{
		StringBuilder builder = new StringBuilder();

		for (char c : input.toCharArray())
		{
			builder.append(String.format("%04x", (int)c));
		}

		return builder.toString().toUpperCase();

	}
	
	public void sendhttp(String message, int msg_type,String mobile) {
		String param="";

     String urlstr="";
     if(arabicMesge(message)==1)
     {
       message=ToHexString(message);
       urlstr = "?username=" + Assets.USERNAME + "&password=" + Assets.PASSWORD
				+ "&mobile=" + mobile + "&unicode=U&message="
				+ message + "&sender="
				+Assets.SENDER;    }
    else
    {
    	urlstr = "?username=" + Assets.USERNAME + "&password=" + Assets.PASSWORD
				+ "&mobile=" + mobile + "&unicode=e&message="
				+ message + "&sender="
				+Assets.SENDER;    }
    try
    {
         URL u = new URL("http://www.jawalsms.net/httpSmsProvider.aspx"+urlstr);
         URLConnection uc = u.openConnection();
         uc.setDoOutput(true);
         uc.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

         String query = param;
         PrintWriter pw = new PrintWriter(uc.getOutputStream());
         pw.println(query);
         pw.close();

         //get the input from the request
         BufferedReader in = new BufferedReader(
         new InputStreamReader(uc.getInputStream()));
         String res = in.readLine();

         switch (res)
         {
             case "0":
             {
                  System.out.println("تم الارسال بنجاح");
             }
                 break;
             case "101":
                  System.out.println( "معاملات خاطئة");
                 break;
             case "104":
                  System.out.println("قد يكون اسم المستخدم او كلمة الملرور غير صحيح ");
                 break;
             case "105":
                  System.out.println("بطاقة غير متوفرة");
                 break;
             case "106":
                  System.out.println("unicode خاطئ");
                 break;
             case "107":
            System.out.println("اسم المرسل غير مسموح");
                 break;
             case "108":
                  System.out.println("اسم المرسل غير موجود");
                 break;
             default:
                  System.out.println("هناك خطاء في الارسال ");
                 break;
         }



in.close();

}
 
 
 catch(Exception ex )
 {
     
 }
}
	
	
	public int arabicMesge(String strText)
    {

        String str = "دجحخهعغفقثصضطكمنتالبيسشظزوةىلارؤءئإلإألأآلآ";

        for (int i = 0; i < strText.length(); i++)

            if (str.indexOf(strText.charAt(i)) != -1)

                return 1;

        return 0;

    }

}
