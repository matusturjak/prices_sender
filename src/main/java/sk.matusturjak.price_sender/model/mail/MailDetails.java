package sk.matusturjak.price_sender.model.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
}
