/**
 * @param {query} input
 */
function searching(input) {
  input = input.toLowerCase();

  if (input === 'south korea') {
    southKoreaResults();
  } else if (input === 'brazil') {
    brazilResults();
    console.log('true - brazil');
  } else if (input === 'malaysia') {
    malaysiaResults();
    console.log('true - malaysia');
  }
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
 * loads Malaysia info.
 */
function malaysiaResults() {
  const headingContainer = document.getElementById('Heading');
  headingContainer.innerText = 'Malaysia';

  const results = [
    {
      country: 'Malaysia',
      name: 'Kuala Lumpur',
      description: 'Kuala Lumpur, the capital of Malaysia, its known for its' +
          ' shopping and dining, with options ranging from large malls and' +
          ' contemporary restaurants to shophouses and food stalls.',
      id: 101,
    },
    {
      country: 'Malaysia',
      name: 'Kuching',
      description: 'Kuching is a bustling, diverse city of old colonial' +
          ' buildings and modern towers. Along its Sarawak River waterfront' +
          ' are food vendors, shophouses selling handicrafts and an esplanade' +
          ' with views of 19th-century landmarks.',
      id: 102,
    },
    {
      country: 'Malaysia',
      name: 'Langkawi',
      description: 'Langkawi, the Jewel of Kedah, is a district and an ' +
          'archipelago of 99 islands in the Andaman Sea some 30 km off the' +
          ' mainland coast of northwestern Malaysia.',
      id: 103,
    },
  ];
  displayResults(results);
}

/**
 * loads Brazil info to page.
 */
function brazilResults() {
  const headingContainer = document.getElementById('Heading');
  headingContainer.innerText = 'Brazil';

  const results = [
    {
      country: 'Brazil',
      name: 'Salvador',
      description: 'Salvador, the capital of Brazil’s' +
          ' northeastern state of Bahia, is known for its Portuguese colonial' +
          ' architecture, Afro-Brazilian culture and a tropical coastline.',
      id: 201,
    },
    {
      country: 'Brazil',
      name: 'Rio de Janeiro',
      description: 'Rio de Janeiro is a huge seaside' +
          ' city in Brazil, famed for its Copacabana and Ipanema beaches,' +
          ' 38m Christ the Redeemer statue atop Mount Corcovado and for' +
          ' Sugarloaf Mountain, a granite peak with cable cars to its summit.',
      id: 202,
    },
    {
      country: 'Brazil',
      name: 'Langkawi',
      description: 'Salvador, the capital of Brazil’s' +
          ' northeastern state of Bahia, is known for its Portuguese colonial' +
          ' architecture, Afro-Brazilian culture and a tropical coastline.',
      id: 203,
    },
  ];
  displayResults(results);
}

/**
 * Loads South Korea info to page.
 */
function southKoreaResults() {
  const headingContainer = document.getElementById('Heading');
  headingContainer.innerText = 'SOUTH KOREA';

  const results = [
    {
      country: 'South Korea',
      name: 'Seoul',
      description: 'Seoul, the capital of South Korea,' +
          ' is a huge metropolis where modern skyscrapers, high-tech subways' +
          ' and pop culture meet Buddhist temples, palaces and street markets.',
      id: 201,
    },
    {
      country: 'South Korea',
      name: 'Busan',
      description: 'Busan, a large port city in South' +
          ' Korea, is known for its beaches, mountains and temples.',
      id: 202,
    },
    {
      country: 'South Korea',
      name: 'Jeju-do',
      description: 'Jeju province encompasses the ' +
          'South Korean island of Jeju in the Korea Strait. Its known for' +
          'its beach resorts and volcanic landscape of craters and cavelike' +
          ' lava tubes.',
      id: 203,
    },
  ];
  displayResults(results);
}

/**
 * @param {results} results
 */
function displayResults(results) {
  // Create a flex-container div if necessary or grab last flex-container
  const flexElement = document.getElementById('flex-container');

  for (let result = 0, l = results.length; result < l; ++result) {
    // Create a container div
    const divElement = document.createElement('div');

    // Place image in div container
    const imgUrl = 'images.jpg';
    const imgElement = document.createElement('img');
    imgElement.src = imgUrl;

    divElement.appendChild(imgElement);

    // Create a container div
    const divInfoElement = document.createElement('div');
    divInfoElement.className = 'container';

    // populate city name
    const cityElement = document.createElement('h1');
    cityElement.innerText = results[result]['name'];

    // populate country name
    const countryElement = document.createElement('h2');
    countryElement.innerText = results[result]['country'];

    const cityDescElement = document.createElement('p');
    cityDescElement.innerText = results[result]['description'];

    // populate city information
    const exploreButtonElement = document.createElement('button');
    exploreButtonElement.className = 'button';
    exploreButtonElement.innerText = 'EXPLORE';

    // append the information elements to the container div
    divInfoElement.appendChild(cityElement);
    divInfoElement.appendChild(countryElement);
    divInfoElement.appendChild(cityDescElement);
    divInfoElement.appendChild(exploreButtonElement);
    divElement.appendChild(divInfoElement);

    // append the card div to the flex-container div
    flexElement.appendChild(divElement);
  }
}
