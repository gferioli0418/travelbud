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
@WebServlet(urlPatterns = {"/api/search"})

public class SearchServlet extends HttpServlet {
  public class SearchClass {
    String name;
    String description;
    String type;
    long id;

    SearchClass(String name, String description, String type, long id) {
      this.name = name;
      this.description = description;
      this.type = type;
      this.id = id;
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");
    String name = request.getParameter("name");

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Gson gson = new Gson();
    if (name != null) {
      List<SearchClass> countryResults =
          Country.getAll(datastore)
              .stream()
              .filter(C -> C.getName().toLowerCase().contains(name.toLowerCase()))
              .map(C -> {
                SearchClass search =
                    new SearchClass(C.getName(), C.getDescription(), "Country", C.getId());
                return search;
              })
              .collect(Collectors.toList());

      Set<Long> countryIds =
          countryResults.stream().map(result -> result.id).collect(Collectors.toSet());
      List<SearchClass> cityResults =
          City.getAll(datastore)
              .stream()
              .filter(C
                  -> C.getName().toLowerCase().contains(name.toLowerCase())
                      || countryIds.contains(C.getCountryId()))
              .map(C -> {
                SearchClass search =
                    new SearchClass(C.getName(), C.getDescription(), "City", C.getId());
                return search;
              })
              .collect(Collectors.toList());

      List<SearchClass> results = new ArrayList<>();
      results.addAll(countryResults);
      results.addAll(cityResults);
      response.getWriter().println(gson.toJson(results));
    }
  }
}
