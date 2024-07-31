package mz.sga.ujc.demo.model.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Email {


    private String subject;
    private String to;
    private String message;
    private boolean html;

    public void setMessage(String message, boolean html) {
        this.message = message;
        this.html =html;
    }

    public Email(String subject, String to, String message, boolean html) {
        this.subject = subject;
        this.to = to;
        this.message = message;
        this.html = html;
    }
}
