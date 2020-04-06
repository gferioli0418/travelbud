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
@WebServlet(urlPatterns = {"/api/cities/*"})
public class CityServlet extends HttpServlet {
  @Override

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");

    // list of cities
    List<City> cities = City.CITIES;

    String endpoint = request.getPathInfo();

    Gson gson = new Gson();

    if (endpoint == null) {
      // return the list of cities
      response.getWriter().println(gson.toJson(cities));

    } else {
      City city = getCity(cities, endpoint);
      if (city == null) {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "City Not Found");
      } else {
        response.getWriter().println(gson.toJson(city));
      }
    }
  }
  public City getCity(List<City> cities, String path) {
    // adjust endpoint
    int cityId = Integer.parseInt(path.substring(1));

    // search for each country element in the List
    for (City tempCity : cities) {
      int tempCityId = tempCity.getId();

      if (tempCityId == cityId) {
        return tempCity;
      }
    }
    return null;
  }
}