/**
 * Adds a random location to the page.
 */
function addRandomLocation() {  // eslint-disable-line no-unused-vars
  const locations = ['Canada', 'Mexico', 'US'];

  // Pick a random location.
  const location = locations[Math.floor(Math.random() * locations.length)];

  // Add it to the page.
  const locationContainer = document.getElementById('location-container');
  locationContainer.innerText = location;
}
