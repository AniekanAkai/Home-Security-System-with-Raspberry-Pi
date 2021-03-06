package securitySystem;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class TwilioText {

    public static final String ACCOUNT_SID = "AC91d312796334939a76eb0464baa56a62";
	public static final String AUTH_TOKEN = "6f5f74337518c0a4b0dc62c8bd375b8d";
	public static TwilioRestClient client = null;
	
	public static void init(){
		client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	}
    
	public static String sendTextMessage(String to, String body) throws TwilioRestException{
		// Build a filter for the MessageList
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("Body", body));
	    params.add(new BasicNameValuePair("To", to));
	    params.add(new BasicNameValuePair("From", "+18194101139"));
	    //params.add(new BasicNameValuePair("MediaUrl", "C:\\Users\\Teddy\\SkyDrive\\Pictures\\Balmain.png"));
	    
	     
	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message = messageFactory.create(params);
	    return message.getSid();
	}
}
