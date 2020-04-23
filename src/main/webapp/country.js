/**
 * @param {query} id
 */
async function searching(id) {
  const countryResponse = await fetch("/api/countries/" + id);
  const citiesResponse = await fetch("/api/cities/");
  const cities = await citiesResponse.json();
  const country = await countryResponse.json();

  var citiesInCountry = [];
  for (city in cities){
      if (city.countryID === id){
          citiesInCountry.push(city);
      }
  }

  displayCityResults(citiesInCountry,country);
}

document.addEventListener("DOMContentLoaded", (event) => {
  loading();
});

/**
 * Loads seach paramerter.
 */
function loading() {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const query = urlParams.get("id");
  searching(query);
}

/**
 * @param {results} results
 */
function displayCityResults(citiesInCountry,country) {

  // add name of country as header element 
  const countryElement = document.createElement("H1");
  countryElement.innerText = country.name;
  document.getElementById("country-name").appendChild(countryElement);

  // add do's and dont's as two seperate unordered lists
  var doElement = document.createElement("ul")  
  for (doTip in country.cultureDos){
    var item = document.createElement("li")
    item.appendChild(doTip)
    doElement.appendChild(item);
  }
  document.getElementById("dos").appendChild(doElement);

  var dontElement = document.createElement("ul")
  for (dontTip in country.cultureDonts){
    var item = document.createElement("li")
    item.appendChild(dontTip)
    dontElement.appendChild(item);
  }
  document.getElementById("donts").appendChild(dontElement);

  // add language spoken to "dos" list 
  var languageElement = document.createElement("li")
  languageElement.appendChild("Learn a few important words in " + country.languages)
  document.getElementById("language").appendChild(languageElement);

  // add table with cities as individual rows
  var table = document.getElementById("citiesTable");
  for (var city in citiesInCountry) {
    var row = table.insertRow(-1);

    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    
    cell1.innerHTML = city.name;
    cell2.innerHTML = city.description;

    // add table with events from various cities as individual rows
    var table = document.getElementById("eventsTable");
    for (var event in city.events) {
        var row = table.insertRow(-1);

        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        var cell4 = row.insertCell(3);
        var cell5 = row.insertCell(4);

        cell1.innerHTML = event.name;
        cell2.innerHTML = event.description;
        cell3.innerHTML = event.date;
        cell4.innerHTML = event.location;
        cell5.innerHTML = event.pricing;
    }
  }
}
