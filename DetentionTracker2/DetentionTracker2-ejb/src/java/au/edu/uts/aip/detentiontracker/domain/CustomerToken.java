package au.edu.uts.aip.detentiontracker.domain;

import au.edu.uts.aip.detentiontracker.domain.PINCard;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A 'Customer Token' object that is used to process payments with PIN Payments.
 * This allows a customer to be continuously charged with the one token,
 * compared to a 'Card Token' which can only be used ONCE for ONE payment ONLY.
 */
@XmlRootElement(name = "card")
public class CustomerToken implements Serializable {

    private String email;
    private PINCard card;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PINCard getCard() {
        return card;
    }

    public void setCard(PINCard card) {
        this.card = card;
    }
}
