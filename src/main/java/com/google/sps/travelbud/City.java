package com.google.sps.travelbud;
import java.util.*;
public class City {
  static City COPENHAGEN = new City(
      1, "Copenhagen", Country.DENMARK.getId(), "Copenhagen description", Event.COPENHAGEN_EVENTS);
  static City BUSAN =
      new City(2, "Busan", Country.SOUTH_KOREA.getId(), "Busan description", Event.BUSAN_EVENTS);
  static List<City> CITIES = Arrays.asList(COPENHAGEN, BUSAN);

  private int id;
  private String name;
  private int countryId;
  private String description;
  private List<Event> events;

  City(int id, String name, int countryId, String description, List<Event> events) {
    this.id = id;
    this.name = name;
    this.countryId = countryId;
    this.description = description;
    this.events = events;
  }
  int getId() {
    return id;
  }
  String getName() {
    return name;
  }
  int getCountryId() {
    return countryId;
  }
  String getDescription() {
    return description;
  }
  List<Event> getEvents() {
    return events;
  }
}