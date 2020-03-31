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
class Country {
  int id;
  String name;
  String CultureNorms;
  String Languages;

  Country(int id, String name, String CultureNorms, String Languages) {
    this.id = id;
    this.name = name;
    this.CultureNorms = CultureNorms;
    this.Languages = Languages;
  }
}
/** Servlet that returns some example content. */
@WebServlet(urlPatterns = {"/api/countries/*"})
public class CountryServlet extends HttpServlet {
  @Override

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json;");

    List<Country> Countries = new ArrayList<Country>();
    Country c1 = new Country(1, "Denmark",
        "Shake hands with women first, Speak in a moderate tone and try not to attract attention, Communicate very directly in any conversation",
        "Danish");
    Country c2 = new Country(2, "South Korea",
        "Always bow to individuals when departing,Handshakes often accompany the bow among men, andDo not use excessive or overt body language",
        "Korean");

    Countries.add(c1);
    Countries.add(c2);

    String endpoint = request.getPathInfo();

    Gson gson = new Gson();

    // return the list of countries
    if (endpoint == null) {
      response.getWriter().println(gson.toJson(Countries));

    } else {
      boolean found = false;

      // adjust endpoint
      String countryName = endpoint.substring(1, endpoint.length()).toLowerCase();

      // search for each country element in the List
      for (Country temp : Countries) {
        String tempName = temp.name.toLowerCase();

        if (tempName.equals(countryName)) {
          // if found then output the JSON
          found = true;
          response.getWriter().println(gson.toJson(temp));
        }
      }
      if (!found) {
        // if not found then output "not found"
        response.getWriter().println("Country Not Found");
      }
    }
  }
}