package ryerson.ca.searchreservation.business;

import ryerson.ca.searchreservation.helper.ReservationXML;
import ryerson.ca.searchreservation.helper.ReservationSlot;
import ryerson.ca.searchreservation.persistence.Reservation_Search_CRUD;
import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


public class SearchBusiness {
    
    public  ReservationXML getReservationsByQuery(String query){
       ArrayList<ReservationSlot> reservations = Reservation_Search_CRUD.getReservation(query);
        ReservationXML rs = new ReservationXML();
        rs.setReservations(reservations);
        return (rs);
    }
    
      
}
