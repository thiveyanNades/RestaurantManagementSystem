/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.commons.io.IOUtils;
import ryerson.ca.helper.ReservationXML;
import ryerson.ca.helper.ReservationSlot;

/**
 *
 * @author student
 */
public class Business {

    public static boolean isAuthenticated(String username, String password) {
        if("manager".equals(username) && "manager123".equals(password))
            return true;
        return false;
    }

    public static ReservationXML getServices(String query, String token) throws IOException {
        System.out.println("Working?");
        Client searchclient = ClientBuilder.newClient();
        System.out.println("Working?1");
        WebTarget searchwebTarget
                = searchclient.target("http://localhost:8080/SearchReservation/webresources/search/get");
        System.out.println("Working?2");
        Form formData = new Form();
        formData.param("date", query);
        System.out.println("Working?7");
        
        
        InputStream is
                = searchwebTarget.request(MediaType.APPLICATION_XML).post(Entity.entity(formData,MediaType.APPLICATION_XML),InputStream.class);
        
        String xml = IOUtils.toString(is, "utf-8");
        ReservationXML reservations = reservationxmltoObjects(xml);
        System.out.println("Working?4");

        return (reservations);

    }
    
    public static void getAddServices(String date, String time, String tableOccupancy, String token) throws IOException {
            
        Client holdclient = ClientBuilder.newClient();
        WebTarget holdwebTarget
                    = holdclient.target("http://localhost:8080/HoldBook/webresources/add/update");
        System.out.println("Here=----");
        Form formData = new Form();
            formData.param("date", date);
            formData.param("time", time);
            formData.param("tableOccupancy", tableOccupancy);
             
             
            
                System.out.println("Working?100");
                InputStream is = holdwebTarget
                .request()
                .accept(MediaType.WILDCARD)
                .post(Entity.entity(formData, MediaType.APPLICATION_XML), InputStream.class);
                System.out.println("Working?200");
    }

    private static ReservationXML reservationxmltoObjects(String xml) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ReservationXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            ReservationXML reservations = (ReservationXML) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return reservations;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ReservationSlot reservationaddxmltoObjects(String xml) {
        if(xml.isEmpty())
            return null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(ReservationSlot.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            ReservationSlot reservation = (ReservationSlot) jaxbUnmarshaller.unmarshal(new StringReader(xml));
            return reservation;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}