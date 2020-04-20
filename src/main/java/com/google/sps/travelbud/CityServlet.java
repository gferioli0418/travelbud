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

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. */
@WebServlet(urlPatterns = {"/api/cities/*"})
public class CityServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");

    String endpoint = request.getPathInfo();
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    Gson gson = new Gson();

    if (endpoint == null) {
      // return the list of cities
      List<City> cities = City.getAll(datastore);
      response.getWriter().println(gson.toJson(cities));

    } else {
      long cityId = Long.parseLong(endpoint.substring(1));
      City city = City.getCity(datastore, cityId);
      if (city == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "City Not Found");
      } else {
        response.getWriter().println(gson.toJson(city));
      }
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.

    String name = getParameter(request, "name", "");
    long countryId = Long.parseLong(getParameter(request, "countryId", ""));
    String description = getParameter(request, "description", "");

    Entity cityEntity = new Entity("City");
    cityEntity.setProperty("name", name);
    cityEntity.setProperty("countryId", countryId);
    cityEntity.setProperty("description", description);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(cityEntity);
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}