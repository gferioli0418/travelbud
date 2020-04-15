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

/** Servlet that returns some example content. */
@WebServlet(urlPatterns = {"/api/events/*"})
public class EventServlet extends HttpServlet {
  @Override

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");

    // add events
    List<Event> events = Event.ALL_EVENTS;

    String endpoint = request.getPathInfo();

    Gson gson = new Gson();

    // return the list of events
    if (endpoint == null) {
      response.getWriter().println(gson.toJson(events));

    } else {
      Event event = getEvent(events, endpoint);
      if (event == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Event Not Found");
      } else {
        response.getWriter().println(gson.toJson(event));
      }
    }
  }
  public Event getEvent(List<Event> events, String path) {
    // adjust endpoint
    int eventId = Integer.parseInt(path.substring(1));

    // search for each country element in the List
    for (Event tempEvent : events) {
      int tempEventId = tempEvent.getId();

      if (tempEventId == eventId) {
        return tempEvent;
      }
    }
    return null;
  }
}