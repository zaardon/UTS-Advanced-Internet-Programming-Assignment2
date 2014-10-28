/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alex
 */
@XmlRootElement(name = "card")
public class CustomerToken  implements Serializable{
    
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
