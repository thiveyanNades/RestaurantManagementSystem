package ryerson.ca.searchreservation.endpoint;

import java.io.StringWriter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.searchreservation.business.SearchBusiness;
import ryerson.ca.searchreservation.helper.ReservationXML;
import ryerson.ca.searchreservation.helper.ReservationSlot;

@Path("search")
public class SearchResource {

    private static final Logger LOGGER = Logger.getLogger(SearchResource.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Path("get")
    public String getXML(@FormParam("date") String date) {
        ReservationXML reservations;

        // Assuming you want to search reservations by date
        SearchBusiness search = new SearchBusiness();
        if(!(date==null || date.isEmpty()))
            reservations = search.getReservationsByQuery(date);
        else
            reservations=null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ReservationXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(reservations, sw);
            return sw.toString();
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, "Error marshalling XML", ex);
            return "<error>An error occurred while processing the request.</error>";
        }
    }

    // PUT method implementation if required
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void updateReservation(String content) {
        // Implementation for updating reservation
    }
}