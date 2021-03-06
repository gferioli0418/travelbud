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
import java.util.stream.Collectors;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. */
@WebServlet(urlPatterns = {"/api/countries/*"})
public class CountryServlet extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");

    String endpoint = request.getPathInfo();
    String countryName = request.getParameter("name");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    Gson gson = new Gson();
    if (endpoint == null) {
      List<Country> countries =
          Country.getAll(datastore)
              .stream()
              .filter(c
                  -> countryName == null
                      || c.getName().toLowerCase().contains(countryName.toLowerCase()))
              .collect(Collectors.toList());

      response.getWriter().println(gson.toJson(countries));

    } else {
      long countryId = Long.parseLong(endpoint.substring(1));
      Country country = Country.getCountry(datastore, countryId);
      if (country == null) {
        // if not found then output "not found"
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Country Not Found");
      } else {
        response.getWriter().println(gson.toJson(country));
      }
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    // Get the input from the form.
    String name = getParameter(request, "name", "");
    String description = getParameter(request, "description", "");
    List<String> cultureDos = Arrays.asList(request.getParameterValues("cultureDos"));
    List<String> cultureDonts = Arrays.asList(request.getParameterValues("cultureDonts"));
    String language = getParameter(request, "language", "");

    Entity countryEntity = new Entity("Country");

    countryEntity.setProperty("name", name);
    countryEntity.setProperty("description", description);
    countryEntity.setProperty("cultureDos", cultureDos);
    countryEntity.setProperty("cultureDonts", cultureDonts);
    countryEntity.setProperty("language", language);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(countryEntity);
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}