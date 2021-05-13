package MODEL;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MensagemZap {
	// Find your Account Sid and Token at twilio.com/console
    // DANGER! This is insecure. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC8996d9bcb41a1ad05e06f7f64bdb59c9";
    public static final String AUTH_TOKEN = "19b15520d87af189348982183500bd50";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("whatsapp:+5583996386694"),
                new PhoneNumber("whatsapp:+14155238886"),
                "Hello there!")
            .create();

        System.out.println(message.getSid());
    }

}
