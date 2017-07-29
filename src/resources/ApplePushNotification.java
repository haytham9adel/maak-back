package resources;

import java.io.InputStream;

import javax.net.ssl.SSLContext;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.PayloadBuilder;
 

public class ApplePushNotification {
	
	/*public void pushMessage() {
        ApnsService service = null;
        try {
        	 // get the certificate
            InputStream certStream = this.getClass().getClassLoader().getResourceAsStream("resources/NewCertificatesFile.p12");
        	service = APNS.newService().withCert(certStream, "Rudiment123").withSandboxDestination().build();
            service.start();
            
                try {
                	PayloadBuilder payloadBuilder = APNS.newPayload();
                    payloadBuilder = payloadBuilder.badge(5).alertBody("Hello, How are you?");
                    if (payloadBuilder.isTooLong()) {
                        payloadBuilder = payloadBuilder.shrinkBody();
                    }
                    System.out.println(payloadBuilder);
                    String payload = payloadBuilder.build();
                    String token = "d3d41770d7a145ab4250be9381e6d33ddbe32f2b467705331cb6feef86d5a003";
                    service.push(token, payload);
                } catch (Exception ex) {
                
                }
            //}
        } catch (Exception ex) {
            // more logging
        	
        } finally {
            // check if the service was successfull initialized and stop it here, if it was
            if (service != null) {
            	 
                service.stop();
            }
 
        }
    }*/
	
// Method for send pushNotification for Apple IOS
	
	public void pushMessage(String device_token,String msg,String msg_show,int sound_sett) {
        ApnsService service = null;
        try {
        	 // get the certificate
            InputStream certStream = this.getClass().getClassLoader().getResourceAsStream("resources/ParentAppDistributionCertificates.p12");
        	//service = APNS.newService().withCert(certStream, "Rudiment123").withSandboxDestination().build();
        	service = APNS.newService().withCert(certStream, "Rudiment123").withProductionDestination().build();
            service.start();
            
                try {
                	PayloadBuilder payloadBuilder = APNS.newPayload();
                	//System.out.println("Sound"+sound_sett);
                	if(sound_sett==0)
                	{
                		System.out.println("no");
                		payloadBuilder = payloadBuilder.badge(1).alertBody(msg_show).customField("msg",msg).sound("default");	
                	}else
                	{
                		System.out.println("yes");
                		payloadBuilder = payloadBuilder.badge(1).alertBody(msg_show).customField("msg",msg);
                	}
                  //  payloadBuilder = payloadBuilder.badge(1).alertBody(msg_show).customField("msg",msg).sound("default");
                    if (payloadBuilder.isTooLong()) {
                        payloadBuilder = payloadBuilder.shrinkBody();
                    }
                    System.out.println(payloadBuilder);
                    String payload = payloadBuilder.build();
                    String token=device_token;
                   // String token = "d3d41770d7a145ab4250be9381e6d33ddbe32f2b467705331cb6feef86d5a003";
                    service.push(token, payload);
                } catch (Exception ex) {
                
                }
            //}
        } catch (Exception ex) {
            // more logging
        	
        } finally {
            // check if the service was successfull initialized and stop it here, if it was
            if (service != null) {
            	 
                service.stop();
            }
 
        }
    }
 	 public static void main(String args[])
	 {
 		// System.out.println("ABC");
 		// String a="d3d41770d7a145ab4250be9381e6d33ddbe32f2b467705331cb6feef86d5a003";
 		// System.out.println(a.length());
	    ApplePushNotification ap=new ApplePushNotification();
		 ap.pushMessage("d3d41770d7a145ab4250be9381e6d33ddbe32f2b467705331cb6feef86d5a003","Ravi",null, 1);
	 }
}
