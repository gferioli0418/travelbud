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
class Event {
  int ID;
  String name;
  int City_ID;
  String time;
  String location;
  double price;
  String description;

  Event(int ID, String name, int City_ID, String time, String location, double price,
      String description) {
    this.ID = ID;
    this.name = name;
    this.City_ID = City_ID;
    this.time = time;
    this.location = location;
    this.price = price;
    this.description = description;
  }
}
/** Servlet that returns some example content. */
@WebServlet(urlPatterns = {"/api/events/*"})
public class EventServlet extends HttpServlet {
  @Override

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");

    // add events
    List<Event> events = new ArrayList<Event>();
    Event e1 = new Event(1, "Ecstatic Dance", 1, "April 4th at 5:45 PM(Copenhagen time)",
        "4 Æbeløgade, 2100 København, Denmark", 22.00, "Some cool dance description");
    Event e2 = new Event(2, "Busan International Boat Show", 2, "May 21st at 10:00AM (Busan time)",
        "84 Haeundaehaebyeon-ro, U-dong, Haeundae-gu, Busan, South Korea", 0.50,
        "some cool boat description");

    events.add(e1);
    events.add(e2);

    String endpoint = request.getPathInfo();

    Gson gson = new Gson();

    // return the list of events
    if (endpoint == null) {
      response.getWriter().println(gson.toJson(events));

    } else {
      boolean found = false;

      // adjust endpoint
      String ID = endpoint.substring(1, endpoint.length());
      int event_ID = Integer.parseInt(ID);

      // search for each event element in the List
      for (Event temp : events) {
        int tempID = temp.ID;

        if (event_ID == tempID) {
          // if found then output the JSON
          found = true;
          response.getWriter().println(gson.toJson(temp));
        }
      }
      if (!found) {
        // if not found then output "not found"
        response.getWriter().println("Event Not Found");
      }
    }
  }
}