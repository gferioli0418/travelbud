// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.travelbud;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// hard coded data
class City {
  int id;
  String name;
  int country_ID;
  String description;
  List<Integer> event_ID; // putting event id for now, but will use the event entity later

  City(int id, String name, int country_ID, String description, List<Integer> event_ID) {
    this.id = id;
    this.name = name;
    this.country_ID = country_ID;
    this.description = description;
    this.event_ID = event_ID;
  }
}
/** Servlet that returns some example content. */
@WebServlet(urlPatterns = {"/api/cities/*"})
public class CityServlet extends HttpServlet {
  @Override

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");

    // set up events for each city
    List<Integer> City1_events = new ArrayList<Integer>();
    City1_events.add(1);

    List<Integer> City2_events = new ArrayList<Integer>();
    City2_events.add(2);

    // list of cities
    List<City> cities = new ArrayList<City>();
    City c1 = new City(1, "Copenhagen", 1, "Some cool copenhagen description", City1_events);
    City c2 = new City(2, "Busan", 2, "Some cool Busan description", City2_events);

    cities.add(c1);
    cities.add(c2);

    String endpoint = request.getPathInfo();

    Gson gson = new Gson();
    // return the list of cities
    if (endpoint == null) {
      response.getWriter().println(gson.toJson(cities));

    } else {
      boolean found = false;

      // adjust endpoint
      String ID = endpoint.substring(1, endpoint.length());
      int city_ID = Integer.parseInt(ID);

      // search for each city element in the List
      for (City temp : cities) {
        int tempID = temp.id;

        if (city_ID == tempID) {
          // if found then output the JSON
          found = true;
          response.getWriter().println(gson.toJson(temp));
        }
      }
      if (!found) {
        // if not found then output "not found"
        response.getWriter().println("City Not Found");
      }
    }
  }
}