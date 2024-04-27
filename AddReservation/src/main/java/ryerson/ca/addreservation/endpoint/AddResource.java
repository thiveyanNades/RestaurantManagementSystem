/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.addreservation.endpoint;

import java.io.StringWriter;
import static java.lang.Integer.parseInt;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import ryerson.ca.addreservation.business.AddReservation;

import ryerson.ca.addreservation.helper.ReservationSlot;

/**
 *
 * @author student
 */
@Path("add")
public class AddResource {
    
    @Context
    private UriInfo context;
    
    public AddResource(){
    }
    
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("isAdded/{date}")
    public String getXML(@PathParam("date") String date){
        System.out.println(date);
        AddReservation add = new AddReservation();
        ReservationSlot reservation = add.getReservation(date);
        if(reservation == null)
            return("");
        
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(ReservationSlot.class);
            
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw=new StringWriter();
            jaxbMarshaller.marshal(reservation, sw);
            
            return(sw.toString());
        } catch (JAXBException ex){
            Logger.getLogger(AddResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        
            
        }
    }
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("update")
    public String updateReservation(@FormParam("date") String date, @FormParam("time") String time, @FormParam("tableOccupancy") int tableOccupancy){
        AddReservation add = new AddReservation();
        boolean bs;
        bs = add.addReservation(date, time, tableOccupancy);
        if(bs)
            return("Inserted");
        else
            return("Not Inserted");
    }
}