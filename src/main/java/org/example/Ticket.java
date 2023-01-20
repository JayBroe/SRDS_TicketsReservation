//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example;

import Cassandra.BackendSession;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ticket {
    private int TicketID;
    private static final String TABLE_NAME = "Tickets";
    private List<Object> result = new ArrayList();
    private Session session;

    public Ticket(BackendSession backend) {
        this.session = backend.getSession();
    }

    public List<Object> getTickets() {
        StringBuilder sb = new StringBuilder("SELECT * FROM TICKET;");
        String query = sb.toString();
        this.session.execute(query);
        ResultSet rs = this.session.execute(query);
        List<Object> tickets = new ArrayList();
        rs.forEach((r) -> {
            tickets.add(r.getInt("TicketID"));
            tickets.add(r.getInt("PassID"));
            tickets.add(r.getInt("ClientID"));
            tickets.add(r.getInt("PassID"));
        });
        return tickets;
    }

    public void addTicket(UUID TicketID, UUID  PassID, UUID ClientID, Integer PlaceID) {
        StringBuilder sb = (new StringBuilder("INSERT INTO "))
                .append(TABLE_NAME).append("(TicketID, PassID, ClientID, PlaceID) ")
                .append("VALUES (").append(TicketID)
                .append(", ").append(PassID)
                .append(", ").append(ClientID)
                .append(", ").append(PlaceID)
                .append(");");
        String query = sb.toString();
        this.session.execute(query);
    }

    public void deleteTicket(UUID TicketID) {
        StringBuilder sb = (new StringBuilder("DELETE FROM "))
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append("TicketID ")
                .append("= ")
                .append(TicketID)
                .append(";");
        String query = sb.toString();
        this.session.execute(query);
    }
}