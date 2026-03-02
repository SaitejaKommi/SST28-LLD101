package com.example.tickets;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer that creates and manages tickets immutably.
 *
 * Now uses Builder pattern for construction and returns new instances
 * instead of mutating existing tickets.
 */
public class TicketService {

    /**
     * Create a new ticket with required fields and sensible defaults.
     */
    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        return new IncidentTicket.Builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .build();
    }

    /**
     * Return a new ticket instance with critical priority and escalation tag.
     * The original ticket is unchanged.
     */
    public IncidentTicket escalateToCritical(IncidentTicket t) {
        List<String> escalatedTags = new ArrayList<>(t.getTags());
        escalatedTags.add("ESCALATED");

        return new IncidentTicket.Builder()
                .from(t)
                .priority("CRITICAL")
                .tags(escalatedTags)
                .build();
    }

    /**
     * Return a new ticket instance with the assignee set.
     * The original ticket is unchanged.
     */
    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return new IncidentTicket.Builder()
                .from(t)
                .assigneeEmail(assigneeEmail)
                .build();
    }
}