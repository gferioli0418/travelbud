
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

public class Country {
  private long id;
  private String name;
  private String description;
  private List<String> cultureDos;
  private List<String> cultureDonts;
  private String languages;

  Country(long id, String name, String description, List<String> cultureDos,
      List<String> cultureDonts, String languages) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.cultureDos = cultureDos;
    this.cultureDonts = cultureDonts;
    this.languages = languages;
  }

  Country(Entity entity) {
    this.id = (long) entity.getKey().getId();
    this.name = (String) entity.getProperty("name");
    this.description = (String) entity.getProperty("description");
    this.cultureDos = (List<String>) (entity.getProperty("cultureDos"));
    this.cultureDonts = (List<String>) (entity.getProperty("cultureDonts"));
    this.languages = (String) entity.getProperty("languages");
  }

  public static List<Country> getAll(DatastoreService datastore) {
    List<Country> countries = new ArrayList<>();
    Query queryCountry = new Query("Country");
    PreparedQuery countriesQuery = datastore.prepare(queryCountry);
    // iterate through each of the countries and add it to the list
    for (Entity entity : countriesQuery.asIterable()) {
      Country c = new Country(entity);
      countries.add(c);
    }
    return countries;
  }

  public static Country getCountry(DatastoreService datastore, long countryId) {
    Key key = KeyFactory.createKey("Country", countryId);
    try {
      Entity entity = datastore.get(key);
      Country c = new Country(entity);
      return c;
    } catch (EntityNotFoundException e) {
      return null;
    }
  }

  long getId() {
    return id;
  }

  String getName() {
    return name;
  }

  String getDescription() {
    return description;
  }

  List<String> getCultureDos() {
    return cultureDos;
  }

  List<String> getCultureDonts() {
    return cultureDonts;
  }

  String getLanguages() {
    return languages;
  }
}