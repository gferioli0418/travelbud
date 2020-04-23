/**
 * @param {query} id
 */
async function searching(id) {
  const cityResponse = await fetch("/api/cities/" + id);
  const city = await cityResponse.json();

  displayCityResults(city);
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
function displayCityResults(city) {
  const cityElement = document.createElement("H1");
  cityElement.innerText = city.name;
  document.getElementById("city-name").appendChild(cityElement);

  const cityDescElement = document.createElement("P");
  cityDescElement.innerText = city.description;
  document.getElementById("city-description").appendChild(cityDescElement);

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
