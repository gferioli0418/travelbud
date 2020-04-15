package com.google.sps.travelbud;
import java.util.*;
public class Country {
  static Country DENMARK = new Country(1, "Denmark",
      Arrays.asList("Shake hands with women first", "Speak in a moderate tone",
          "Communicate very directly in any conversation"),
      Arrays.asList("Try not to attract attention"), "Danish");

  static Country SOUTH_KOREA = new Country(2, "South Korea",
      Arrays.asList("Always bow to individuals when departing",
          "Handshakes often accompany the bow among men",
          "Communicate very directly in any conversation"),
      Arrays.asList("Do not use excessive or overt body language"), "Korean");
  static List<Country> COUNTRIES = Arrays.asList(DENMARK, SOUTH_KOREA);

  private int id;
  private String name;
  private List<String> cultureDos;
  private List<String> cultureDonts;
  private String languages;

  Country(
      int id, String name, List<String> cultureDos, List<String> cultureDonts, String languages) {
    this.id = id;
    this.name = name;
    this.cultureDos = cultureDos;
    this.cultureDonts = cultureDonts;
    this.languages = languages;
  }
  int getId() {
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