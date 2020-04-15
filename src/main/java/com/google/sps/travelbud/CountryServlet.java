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
@WebServlet(urlPatterns = {"/api/countries/*"})
public class CountryServlet extends HttpServlet {
  @Override

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");

    List<Country> countries = Country.COUNTRIES;
    String endpoint = request.getPathInfo();

    Gson gson = new Gson();

    // return the list of countries
    if (endpoint == null) {
      response.getWriter().println(gson.toJson(countries));

    } else {
      Country country = getCountry(countries, endpoint);
      if (country == null) {
        // if not found then output "not found"
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Country Not Found");
      } else {
        response.getWriter().println(gson.toJson(country));
      }
    }
  }
  public Country getCountry(List<Country> countries, String path) {
    // adjust endpoint
    String countryName = path.substring(1).toLowerCase();

    // search for each country element in the List
    for (Country tempCountry : countries) {
      String tempCountryName = tempCountry.getName().toLowerCase();

      if (tempCountryName.equals(countryName)) {
        return tempCountry;
      }
    }
    return null;
  }
}