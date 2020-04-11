package com.google.sps.travelbud;
import java.util.*;
public class Event {
  static Event COPENHAGEN_EVENT_1 = new Event(1, "Ecstatic Dance", 1, "Some dancing description",
      "2020-04-04 16:45:00 UTC", "s4 Æbeløgade, 2100 København, Denmark", 22.00);
  static List<Event> COPENHAGEN_EVENTS = Arrays.asList(COPENHAGEN_EVENT_1);

  static Event BUSAN_EVENT_1 = new Event(2, "Busan International Boat Show", 2,
      "Some boat show description", "2020-05-21 03:00:00 UTC",
      "84 Haeundaehaebyeon-ro, U-dong, Haeundae-gu, Busan, South Korea", 0.5);
  static List<Event> BUSAN_EVENTS = Arrays.asList(BUSAN_EVENT_1);

  static List<Event> ALL_EVENTS = Arrays.asList(COPENHAGEN_EVENT_1, BUSAN_EVENT_1);

  private int id;
  private String name;
  private int cityId;
  private String description;
  private String date;
  private String location;
  private double pricing;

  Event(int id, String name, int cityId, String description, String date, String location,
      double pricing) {
    this.id = id;
    this.name = name;
    this.cityId = cityId;
    this.description = description;
    this.date = date;
    this.location = location;
    this.pricing = pricing;
  }
  int getId() {
    return id;
  }
  String getName() {
    return name;
  }
  int getCityId() {
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