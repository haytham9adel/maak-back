package resources;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import antlr.StringUtils;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;

public class Gcm {
	public final static String AUTH_KEY_FCM = "AIzaSyCVBbuhCxpZNmIIIgpdwbT4VZzUxN8ncEE";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	/**
	 * gcmRegId is the id which android app will give to server (one time)
	 **/
	public boolean pushFCMNotification(String gcmRegId, String message) {
		System.out.println(message);
		final String GCM_API_KEY = "AIzaSyCHVtSNxflPgE60xeykRtNnERvBwHj_XOA";
		final int retries = 3;
		Sender sender = new Sender(GCM_API_KEY);
		Message msg = new Message.Builder().addData("msg", message).build();
		System.out.println(msg);
		try {

			com.google.android.gcm.server.Result result = sender.send(msg,
					gcmRegId, retries);
			System.out.println(result.getErrorCodeName());
			if(result!=null)
			{
				System.out.println("notification sent successfully !");
			}

			/**
			 * if you want to send to multiple then use below method
			 * send(Message message, List<String> regIds, int retries)
			 **/
		} catch (Exception e) {
			System.out.println("notification is not sent");
		}
		return false;

	}
	public void pushNotificationToGCM(String userDeviceIdKey, String message) throws Exception{
			try{
		   String authKey = AUTH_KEY_FCM; // You FCM AUTH key
		   String FMCurl = API_URL_FCM; 

		   URL url = new URL(FMCurl);
		   HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		   conn.setUseCaches(false);
		   conn.setDoInput(true);
		   conn.setDoOutput(true);

		   conn.setRequestMethod("POST");
		   conn.setRequestProperty("Authorization","key="+authKey);
		   conn.setRequestProperty("Content-Type","application/json");

		   JSONObject json = new JSONObject();
		   json.put("to",userDeviceIdKey.trim());
		   JSONObject info = new JSONObject();
		   info.put("title", "Notificatoin Title"); // Notification title
		   info.put("body", ""); // Notification body
		   json.put("data", info);
		   info.put("msg", message);
		//   JSONObject data = new JSONObject();
		   
		    
		    
		   OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		   wr.write(json.toString());
		   wr.flush();
		   conn.getInputStream();
		   System.out.println(json);
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	public static void main(String ar[]) throws Exception {
		Gcm g = new Gcm();
	/*	g.pushNotificationToGCM(
				"APA91bHyA8QcxZsYIILoFV4M9WWTdTeJxRpGm1ExCoWJ-FgLcdCXPYweJyOEZvGDXdkew-MwbgCNfb_Rpen6-MOCgQpvE-Er-GHN-JmbvTLymb0a-n_sCNc3KzpGvB0-KlVX8k4LCMoKjKVhp2Zf5cMGzUEntzD_5A",
				"Hello Ravi, Whats going on!");*/
		
		g.pushFCMNotification("edPiYTPQ544:APA91bGaf_qKY52O9F7llUQFuQ-0IMwK1C-H4PHaSyy-xkR66Se1K-7VOBrUnKMTZLfN3Gt0OQ6Uqx4WjH3n-mibIZeQmD2KSi5mQe13zPpUHrQIg7a8VlFDIiJmjPguO0BoHWvXdJCG","Hi");

	}
}
