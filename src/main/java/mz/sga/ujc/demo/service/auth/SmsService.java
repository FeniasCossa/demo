package mz.sga.ujc.demo.service.auth;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

// SMS Service with Twilio
public class SmsService {
    // Install the Java helper library from twilio.com/docs/java/install

        // Find your Account SID and Auth Token at twilio.com/console
        // and set the environment variables. See http://twil.io/secure
        public static final String ACCOUNT_SID = System.getenv("ACbf992e8d7358e2ae95e02bbcb20850bd");
        public static final String AUTH_TOKEN = System.getenv("13a37819f74b8658d00999d692eb8483");

        public static void main(String[] args) {
            Twilio.init("ACbf992e8d7358e2ae95e02bbcb20850bd", "13a37819f74b8658d00999d692eb8483","ACbf992e8d7358e2ae95e02bbcb20850bd");
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber("+258870519311"),
                            new com.twilio.type.PhoneNumber("845435593"),
                            "Hi there")
                    .create();

            System.out.println(message.getBody());
        }
    }

