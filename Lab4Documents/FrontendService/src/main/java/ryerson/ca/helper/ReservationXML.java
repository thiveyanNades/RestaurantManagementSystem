/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ryerson.ca.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "reservations")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReservationXML {

    @XmlElement(name = "reservation")
    private ArrayList<ReservationSlot> reservations = new ArrayList<>();

    public ArrayList<ReservationSlot> getReservations() {
        return reservations;
    }

    public ReservationXML() {
    }

    public void setReservations(ArrayList<ReservationSlot> reservations) {
        this.reservations = reservations;
    }
}
