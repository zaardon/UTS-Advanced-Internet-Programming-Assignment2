
package au.edu.uts.aip.detentiontracker.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the ROOT response object used by the system
 */
@XmlRootElement(name = "response")
public class Response implements Serializable {

    private List<PINResponse> PINResponse = new ArrayList<>();

    public List<PINResponse> getResponse() {
        return PINResponse;
    }

    public void setResponse(List<PINResponse> pINResponse) {
        this.PINResponse = pINResponse;
    }

    @Override
    public String toString() {
        return "Response{" + "pINResponse=" + PINResponse + '}';
    }
}
