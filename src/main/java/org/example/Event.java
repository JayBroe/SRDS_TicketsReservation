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

public class Event {
    private static final String TABLE_NAME = "Events";
    private List<Object> result = new ArrayList();
    private Session session;

    public Event(BackendSession backend) {
        this.session = backend.getSession();
    }

    public List<Object> getEvents() {
        StringBuilder sb = new StringBuilder("SELECT * FROM Event;");
        String query = sb.toString();
        this.session.execute(query);
        ResultSet rs = this.session.execute(query);
        List<Object> events = new ArrayList();
        rs.forEach((r) -> {
            events.add(r.getInt("EventID"));
            events.add(r.getString("event_name"));
            events.add(r.getString("event_type"));
            events.add(r.getTimestamp("event_start"));
            events.add(r.getTimestamp("event_stop"));
            events.add(r.getInt("Tickets_CompanyID"));
        });
        return events;
    }

    public String addEvent(String event_name, String event_type, String event_start, String event_stop, String Tickets_CompanyID) {
        UUID EventID = UUID.randomUUID();
        StringBuilder sb = (new StringBuilder("INSERT INTO "))
                .append(TABLE_NAME).append(" (EventID, event_name, event_type, event_start, event_stop, Tickets_CompanyID) ")
                .append("VALUES (").append(EventID)
                .append(", '").append(event_name)
                .append("', '").append(event_type)
                .append("', '").append(event_start)
                .append("', '").append(event_stop)
                .append("', ").append(Tickets_CompanyID)
                .append(");");
        String query = sb.toString();
        System.out.println(query);
        this.session.execute(query);
        return EventID.toString();
    }

    public void deleteEvent(String EventID) {
        StringBuilder sb = (new StringBuilder("DELETE FROM "))
                .append(TABLE_NAME)
                .append(" WHERE ")
                .append("EventID ")
                .append("= ")
                .append(EventID)
                .append(";");
        String query = sb.toString();
        this.session.execute(query);
    }
}
