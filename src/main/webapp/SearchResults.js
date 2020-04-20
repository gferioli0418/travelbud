/**
 * @param {query} input
 */
async function searching(input) {
  input = input.toLowerCase();
  var countryId = null;
  var countryName = null;

  const countriesResponse = await fetch('/api/countries');
  const countries = await countriesResponse.json();
  
  for (let country in countries) {
    if(countries[country]['name'].toLowerCase() === input){
        displayCountryResults(countries[country]);
        countryId = countries[country]['id'];
        countryName = countries[country]['name'];
        break;
    }
  }

  const citiesResponse = await fetch('/api/cities?name=' + input);
  const cities = await citiesResponse.json();

  for (let city in cities) {
    if(cities[city]['countryId'] !== countryId){
        delete cities[city]; 
    }
  }
    displayCityResults(cities, countryName);  
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
 */
function displayCityResults(results, countryName) {
  const flexElement = document.getElementById('flex-container');

  for (let result = 0, l = results.length; result < l; ++result) {
    if (results[result] !== undefined) {
    const divElement = document.createElement('div');

    const imgUrl = 'images.jpg';
    const imgElement = document.createElement('img');
    imgElement.src = imgUrl;

    divElement.appendChild(imgElement);

    const divInfoElement = document.createElement('div');
    divInfoElement.className = 'container';

    const cityElement = document.createElement('h1');
    cityElement.innerText = results[result]['name'];

    const countryElement = document.createElement('h2');
    countryElement.innerText = countryName;

    const cityDescElement = document.createElement('p');
    cityDescElement.innerText = results[result]['description'];

    const exploreButtonElement = document.createElement('button');
    exploreButtonElement.className = 'button';
    exploreButtonElement.innerText = 'EXPLORE';

    divInfoElement.appendChild(cityElement);
    divInfoElement.appendChild(countryElement);
    divInfoElement.appendChild(cityDescElement);
    divInfoElement.appendChild(exploreButtonElement);
    divElement.appendChild(divInfoElement);

    flexElement.appendChild(divElement);
  }
  }
}

function displayCountryResults(results) {
        const headingContainer = document.getElementById('Heading');
        headingContainer.innerText = results['name'].toUpperCase();

        const country = document.getElementById('country-name');
        country.innerText = results['name'];

        const languagesContainer = document.getElementById('Languages');
        languagesContainer.innerText = 'Language(s): ' + results['languages'];

        const discriptionContainer = document.getElementById('country-description');
        discriptionContainer.innerText = results['description'];
}
