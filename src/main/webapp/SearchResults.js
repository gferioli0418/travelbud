/**
 * @param {query} input
 */
async function searching(input) {
  const headingContainer = document.getElementById('Heading');
  headingContainer.innerText = input.toUpperCase();

  input = input.toLowerCase();
  const searchResponse = await fetch('/api/search?name=' + encodeURI(input));
  const results = await searchResponse.json();

  const countriesResponse = await fetch('/api/countries');
  const countries = await countriesResponse.json();
  const citiesResponse = await fetch('/api/cities');
  const cities = await citiesResponse.json();
  displayCityResults(results, cities, countries);
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
  const query = urlParams.get('q');
  searching(query);
}

/**
 * @param {results} results
 * @param {cities} cities
 * @param {countries} countries
 */
function displayCityResults(results, cities, countries) {
  const flexElement = document.getElementById('flex-container');

  for (const result of results) {
    const divElement = document.createElement('div');

    const imgUrl = 'images/' + result.name.toLowerCase() + '.jpg';
    const imgElement = document.createElement('img');
    imgElement.src = imgUrl;

    divElement.appendChild(imgElement);

    const divInfoElement = document.createElement('div');
    divInfoElement.className = 'container';

    const cityElement = document.createElement('h1');
    cityElement.innerText = result.name;

    divInfoElement.appendChild(cityElement);

    if (result.type == 'City') {
      const city = cities.filter((c) => c.id == result.id)[0];
      const countryId = city == undefined ? '' : city.countryId;

      const country = countries.filter((c) => c.id == countryId)[0];
      const countryName = country == undefined ? '' : country.name;

      const countryElement = document.createElement('h2');
      countryElement.innerText = countryName;
      divInfoElement.appendChild(countryElement);
    }

    const cityDescElement = document.createElement('p');
    cityDescElement.innerText = result.description;

    const exploreButtonElement = document.createElement('button');
    exploreButtonElement.className = 'button';
    exploreButtonElement.innerText = 'EXPLORE';
    let link = '';
    if (result.type == 'Country') {
      link = '/country.html?id=' + result.id;
    } else {
      link = '/city.html?id=' + result.id;
    }
    exploreButtonElement.onclick = function() {
      location.href = link;
    };

    divInfoElement.appendChild(cityDescElement);
    divInfoElement.appendChild(exploreButtonElement);
    divElement.appendChild(divInfoElement);
    flexElement.appendChild(divElement);
  }
}
