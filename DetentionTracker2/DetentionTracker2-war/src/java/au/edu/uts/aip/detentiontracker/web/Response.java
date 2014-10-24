/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.detentiontracker.web;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author meowmeow
 */
public class Response {
    
    private List <PINResponse> pINResponse = new ArrayList<>();

    public List<PINResponse> getResponse() {
        return pINResponse;
    }

    public void setResponse(List<PINResponse> pINResponse) {
        this.pINResponse = pINResponse;
    }

    @Override
    public String toString() {
        return "Response{" + "pINResponse=" + pINResponse + '}';
    }

  

   


  
    
    
}
