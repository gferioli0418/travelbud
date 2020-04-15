package com.google.sps.travelbud;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import java.util.*;

public class City {
  private long id;
  private String name;
  private long countryId;
  private String description;
  private List<Event> events;

  City(long id, String name, long countryId, String description, List<Event> events) {
    this.id = id;
    this.name = name;
    this.countryId = countryId;
    this.description = description;
    this.events = events;
  }

  public static List<City> getAll(DatastoreService datastore) {
    HashMap<Long, List<Event>> cityEvents = new HashMap<>();
    Query queryEvent = new Query("Event");
    PreparedQuery events = datastore.prepare(queryEvent);

    // iterate through each event entity
    for (Entity entity : events.asIterable()) {
      long cityId = (long) entity.getProperty("cityId");
      Event e = new Event((long) entity.getKey().getId(), (String) entity.getProperty("name"),
          (long) entity.getProperty("cityId"), (String) entity.getProperty("description"),
          (String) entity.getProperty("date"), (String) entity.getProperty("location"),
          (double) entity.getProperty("pricing"));
      // add the event to the list with the corresponding key cityId;
      if (cityEvents.get(cityId) == null) {
        cityEvents.put(cityId, new ArrayList<Event>());
      }
      cityEvents.get(cityId).add(e);
    }

    List<City> cities = new ArrayList<>();
    Query queryCity = new Query("City");
    PreparedQuery citiesQuery = datastore.prepare(queryCity);
    // iterate throuch each city entity and add it to the list;
    for (Entity entity : citiesQuery.asIterable()) {
      long entityId = (long) entity.getKey().getId();
      City c = new City(entityId, (String) entity.getProperty("name"),
          (long) entity.getProperty("countryId"), (String) entity.getProperty("description"),
          cityEvents.get(entityId));
      cities.add(c);
    }
    return cities;
  }

  public static City getCity(DatastoreService datastore, long id) {
    // first search for events with cityID
    List<Event> cityEvents = Event.getEventsInCity(datastore, id);

    Query queryCity = new Query("City");
    PreparedQuery cities = datastore.prepare(queryCity);
    // iterate through each city event
    for (Entity entity : cities.asIterable()) {
      long entityId = (long) entity.getKey().getId();
      // only return the city with the right id
      if (entityId == id) {
        City c = new City(entityId, (String) entity.getProperty("name"),
            (long) entity.getProperty("countryId"), (String) entity.getProperty("description"),
            cityEvents);
        return c;
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

  long getCountryId() {
    return countryId;
  }

  String getDescription() {
    return description;
  }

  List<Event> getEvents() {
    return events;
  }
}