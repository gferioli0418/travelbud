/**
 * @param {query} input
 */
async function searching(input) {
  
  const headingContainer = document.getElementById('Heading');
  headingContainer.innerText = input.toUpperCase();

  input = input.toLowerCase();
  console.log(encodeURI(input));
  const searchResponse = await fetch('/api/search?name=' + encodeURI(input));
  const results = await searchResponse.json();


  console.log(cityArray);
    displayCityResults(cityArray, countries);  
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
function displayCityResults(results, countries) {
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

    var countryName ="";
    for (let country in countries) {
        if(countries[country]['id'] === results[result]['countryId']){
          var countryName = countries[country]['name'];
          break;
        }
    }

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
    for (let result = 0, l = results.length; result < l; ++result) {

        const country = document.getElementById('country-name');
        country.innerText = results[result]['name'];

        const languagesContainer = document.getElementById('Languages');
        languagesContainer.innerText = 'Language(s): ' + results[result]['languages'];

        const discriptionContainer = document.getElementById('country-description');
        discriptionContainer.innerText = results[result]['description'];
    }
}
