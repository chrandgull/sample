//Sample rest client that consumes XML

package testrestclient;

import java.io.StringReader;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.glassfish.jersey.client.ClientConfig;


public class TestRest {
	
	public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());
        // Get XML
        String xmlResponse = target.path("rest").request()
                .accept(MediaType.TEXT_XML).get(String.class);
         
        JAXBContext jaxbContext;
        
        try {
        	jaxbContext = JAXBContext.newInstance(Note.class);
        	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        	Note note = (Note) jaxbUnmarshaller.unmarshal(new StringReader(xmlResponse));
        	System.out.println(note.toString());
        }catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri(
                "http://www.mocky.io/v2/5d80306b300000e3288e6d2f").build();
	
	
	

}
}
