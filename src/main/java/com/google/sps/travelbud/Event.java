package com.google.sps.travelbud;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
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
  public static List<Event> getEventsInCity(DatastoreService datastore, long cityId) {
    List<Event> events = new ArrayList<>();
    Query query = new Query("Event");
    PreparedQuery results = datastore.prepare(query);

    // iterate though each event entity
    for (Entity entity : results.asIterable()) {
      long city = (long) entity.getProperty("cityId");
      // only add ones with the specific cityId
      if (city == cityId) {
        Event e = new Event((long) entity.getKey().getId(), (String) entity.getProperty("name"),
            (long) entity.getProperty("cityId"), (String) entity.getProperty("description"),
            (String) entity.getProperty("date"), (String) entity.getProperty("location"),
            (double) entity.getProperty("pricing"));

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
      Event e = new Event((long) entity.getKey().getId(), (String) entity.getProperty("name"),
          (long) entity.getProperty("cityId"), (String) entity.getProperty("description"),
          (String) entity.getProperty("date"), (String) entity.getProperty("location"),
          (double) entity.getProperty("pricing"));
      events.add(e);
    }
    return events;
  }

  public static Event getEvent(DatastoreService datastore, long eventId) {
    // search for each country element in the List
    Query query = new Query("Event");
    PreparedQuery results = datastore.prepare(query);

    // iterate throuhg each event entity
    for (Entity entity : results.asIterable()) {
      long entityId = (long) entity.getKey().getId();
      // only return the event with the correspoding eventId
      if (entityId == eventId) {
        Event e = new Event(entityId, (String) entity.getProperty("name"),
            (long) entity.getProperty("cityId"), (String) entity.getProperty("description"),
            (String) entity.getProperty("date"), (String) entity.getProperty("location"),
            (double) entity.getProperty("pricing"));
        return e;
      }
    }
    return null;
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