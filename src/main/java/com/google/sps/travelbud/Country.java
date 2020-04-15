package com.google.sps.travelbud;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import java.util.*;

public class Country {
  private long id;
  private String name;
  private List<String> cultureDos;
  private List<String> cultureDonts;
  private String languages;

  Country(
      long id, String name, List<String> cultureDos, List<String> cultureDonts, String languages) {
    this.id = id;
    this.name = name;
    this.cultureDos = cultureDos;
    this.cultureDonts = cultureDonts;
    this.languages = languages;
  }

  public static List<Country> getAll(DatastoreService datastore) {
    List<Country> countries = new ArrayList<>();
    Query queryCountry = new Query("country");
    PreparedQuery countriesQuery = datastore.prepare(queryCountry);
    // iterate through each of the countries and add it to the list
    for (Entity entity : countriesQuery.asIterable()) {
      Country c = new Country((long) entity.getProperty("id"), (String) entity.getKey().getName(),
          (List<String>) (entity.getProperty("cultureDos")),
          (List<String>) (entity.getProperty("cultureDonts")),
          (String) entity.getProperty("languages"));
      countries.add(c);
    }
    return countries;
  }

  public static Country getCountry(DatastoreService datastore, long countryId) {
    Query queryCountry = new Query("Country");
    PreparedQuery countries = datastore.prepare(queryCountry);
    // iterate through the country entity
    for (Entity entity : countries.asIterable()) {
      long entityId = (long) entity.getKey().getId();
      // only add the country with the correct countryId
      if (entityId == countryId) {
        Country c = new Country(entityId, (String) entity.getProperty("name"),
            (List<String>) entity.getProperty("cultureDos"),
            (List<String>) entity.getProperty("cultureDonts"),
            (String) entity.getProperty("languages"));
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