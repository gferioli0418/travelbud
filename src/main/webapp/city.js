/**
 * @param {query} id
 */
async function searching(id) {
  const cityResponse = await fetch("/api/cities/" + id);
  const city = await cityResponse.json();
  const countryResponse = await fetch("/api/countries/" + city.countryId);
  const country = await countryResponse.json();

  displayCityResults(city,country);
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
function displayCityResults(city,country) {

  // add name of city as header element 
  const cityElement = document.createElement("H1");
  cityElement.innerText = city.name;
  document.getElementById("city-name").appendChild(cityElement);

  // add description as paragraph element 
  const cityDescElement = document.createElement("P");
  cityDescElement.innerText = city.description;
  document.getElementById("city-description").appendChild(cityDescElement);

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

  // add table with events as individual rows
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
