/**
 * @param {query} id
 */
async function searching(id) {
  const countryResponse = await fetch('/api/countries/' + id);
  const country = await countryResponse.json();
  const citiesResponse = await fetch('/api/cities/');
  const cities = await citiesResponse.json();

  const citiesInCountry = [];
  for (const city in cities) {
    if (city.countryID === id) {
      citiesInCountry.push(city);
    }
  }

  displayCityResults(citiesInCountry, country);
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
 * @param {citiesInCountry} citiesInCountry
 * @param {country} country
 */
function displayCityResults(citiesInCountry, country) {
  // add name of country as header element
  const countryElement = document.createElement('H1');
  countryElement.innerText = country.name;
  document.getElementById('country-name').appendChild(countryElement);

  // add do's and dont's as two seperate unordered lists
  const doElement = document.createElement('ul');
  for (const doTip in country.cultureDos) {
    if (doTip != null) {
      const doItem = document.createElement('li');
      doItem.appendChild(doTip);
      doElement.appendChild(doItem);
    }
  }
  document.getElementById('dos').appendChild(doElement);

  const dontElement = document.createElement('ul');
  for (const dontTip in country.cultureDonts) {
    if (dontTip != null) {
      const dontItem = document.createElement('li');
      dontItem.appendChild(dontTip);
      dontElement.appendChild(dontItem);
    }
  }
  document.getElementById('donts').appendChild(dontElement);

  // add language spoken to "dos" list
  const languageElement = document.createElement('li');
  languageElement.appendChild('Learn a few words in ' + country.languages);
  document.getElementById('language').appendChild(languageElement);

  // add table with cities as individual rows
  const citiesTable = document.getElementById('citiesTable');
  for (const city in citiesInCountry) {
    if (city != null) {
      const row = citiesTable.insertRow(-1);

      const cell1 = row.insertCell(0);
      const cell2 = row.insertCell(1);

      cell1.innerHTML = city.name;
      cell2.innerHTML = city.description;

      // add table with events from various cities as individual rows
      const eventsTable = document.getElementById('eventsTable');
      for (const event in city.events) {
        if (event != null) {
          const row = eventsTable.insertRow(-1);

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
  }
}
