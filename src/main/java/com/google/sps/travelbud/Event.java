package com.google.sps.travelbud;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import java.util.*;

public class Event {
  private long id;
  private String name;
  private long cityId;
  private String description;
  private String date;
  private String location;
  private double pricing;

  Event(long id, String name, long cityId, String description, String date, String location,
      double pricing) {
    this.id = id;
    this.name = name;
    this.cityId = cityId;
    this.description = description;
    this.date = date;
    this.location = location;
    this.pricing = pricing;
  }

  Event(Entity entity) {
    this.id = (long) entity.getKey().getId();
    this.name = (String) entity.getProperty("name");
    this.cityId = (long) entity.getProperty("cityId");
    this.description = (String) entity.getProperty("description");
    this.date = (String) entity.getProperty("date");
    this.location = (String) entity.getProperty("location");
    this.pricing = (double) entity.getProperty("pricing");
  }

  public static List<Event> getEventsInCity(DatastoreService datastore, long cityId) {
    List<Event> events = new ArrayList<>();
    Query query = new Query("Event");
    PreparedQuery results = datastore.prepare(query);

    // iterate though each event entity
    for (Entity entity : results.asIterable()) {
      long city = (long) entity.getProperty("cityId");
      // only add ones with the specific cityId
      if (city == cityId) {
        Event e = new Event(entity);
        events.add(e);
      }
    }
    return events;
  }
  public static List<Event> getAll(DatastoreService datastore) {
    List<Event> events = new ArrayList<>();
    Query query = new Query("Event");
    PreparedQuery results = datastore.prepare(query);
    // iterate through each event entity and add it to the list
    for (Entity entity : results.asIterable()) {
      Event e = new Event(entity);
      events.add(e);
    }
    return events;
  }

  public static Event getEvent(DatastoreService datastore, long eventId) {
    Key key = KeyFactory.createKey("Event", eventId);
    try {
      Entity entity = datastore.get(key);
      Event event = new Event(entity);
      return event;
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  public static List<Event> filter(DatastoreService datastore, String name) {
    List<Event> filteredEvents = new ArrayList<>();
    List<Event> events = Event.getAll(datastore);
    for (Event E : events) {
      String nameOfE = E.getName().toLowerCase();
      String substr = nameOfE.substring(0, name.length());
      if (nameOfE.length() >= name.length() && substr.equals(name.toLowerCase())) {
        filteredEvents.add(E);
      }
    }
    return filteredEvents;
  }

  long getId() {
    return id;
  }

  String getName() {
    return name;
  }

  long getCityId() {
    return cityId;
  }

  String getDescription() {
    return description;
  }

  String getDate() {
    return date;
  }

  String getLocation() {
    return location;
  }

  double getPricing() {
    return pricing;
  }
}
