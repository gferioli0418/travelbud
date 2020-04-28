/**
 * @param {query} id
 */
async function searching(id) {
  const cityResponse = await fetch('/api/cities/' + id);
  const city = await cityResponse.json();
  const countryResponse = await fetch('/api/countries/' + city.countryId);
  const country = await countryResponse.json();

  displayCityResults(city, country);
}

document.addEventListener('DOMContentLoaded', (event) => {
  loading();
});

/**
 * Loads seach paramerter.
 */
function loading() {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const query = urlParams.get('id');
  searching(query);
}

/**
 * displays city
 * @param {city} city
 * @param {country} country
 */
function displayCityResults(city, country) {
  // add name of city as header element
  const cityElement = document.createElement('H1');
  cityElement.innerText = city.name;
  document.getElementById('city-name').appendChild(cityElement);

  // add description as paragraph element
  const cityDescElement = document.createElement('P');
  cityDescElement.innerText = city.description;
  document.getElementById('city-description').appendChild(cityDescElement);

  // add do's and dont's as two seperate unordered lists
  const doElement = document.createElement('ul');
  for (const doTip of country.cultureDos) {
    if (doTip != null) {
      const doItem = document.createElement('li');
      doItem.appendChild(doTip);
      doElement.appendChild(doItem);
    }
  }
  document.getElementById('dos').appendChild(doElement);

  const dontElement = document.createElement('ul');
  for (const dontTip of country.cultureDonts) {
    if (dontTip != null) {
      const dontItem = document.createElement('li');
      dontItem.appendChild(dontTip);
      dontElement.appendChild(dontItem);
    }
  }
  document.getElementById('donts').appendChild(dontElement);

  // add language spoken to "dos" list
  const languageElement = document.createElement('li');
  languageElement.innerText('Learn a few words in ' + country.languages);
  document.getElementById('language').appendChild(languageElement);

  // add table with events as individual rows
  const table = document.getElementById('eventsTable');
  for (const event of city.events) {
    if (event != null) {
      const row = table.insertRow(-1);

      const cell1 = row.insertCell(0);
      const cell2 = row.insertCell(1);
      const cell3 = row.insertCell(2);
      const cell4 = row.insertCell(3);
      const cell5 = row.insertCell(4);

      cell1.innerHTML = event.name;
      cell2.innerHTML = event.description;
      cell3.innerHTML = event.date;
      cell4.innerHTML = event.location;
      cell5.innerHTML = event.pricing;
    }
  }
}
