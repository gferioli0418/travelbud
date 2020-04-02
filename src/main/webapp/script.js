/**
 * Adds a random location to the page.
 */
function addRandomLocation() {
  const locations = ['Canada', 'Mexico', 'US']

      // Pick a random location.
      const location = locations[Math.floor(Math.random() * locations.length)]

      // Add it to the page.
      const locationContainer = document.getElementById('location-container')
  locationContainer.innerText = location
}

function addRandomGreeting() {
  const greetings = [
    "Brazil!",
    "Bangkok, Thailand!",
    "Accra, Ghana!",
    "Botswana!",
    "Machu Picchu!",
    "Beijing, China!",
    "Chichen Itza, Mexico!",
  ];
  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById("greeting-container");
  greetingContainer.innerText = greeting;
}