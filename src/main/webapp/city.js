/**
 * @param {query} id
 */
async function searching(id) {
  var cityId = null

  const citiesResponse = await fetch("/api/city.html?id=" + id)
  const cities = await citiesResponse.json()

  for (let city in cities) {
    if ((cities[city]["id"] === id)) {
      cityId = cities[city]["id"]
    }
  }

  const eventsResponse = await fetch("/api/events")
  const events = await eventsResponse.json()
  var eventName = []
  var eventDescription = []
  var eventDate = []
  var eventLocation = []
  var eventPricing = []

  for (let event in events) {
    if ((events[event]["cityId"] === cityId)) {
      eventName.push(events[event]["name"])
      eventDescription.push(events[event]["description"])
      eventDate.push(events[event]["date"])
      eventLocation.push(events[event]["location"])
      eventPricing.push(events[event]["pricing"])
    }
  }
  displayCityResults(
    cities[city],
    eventName,
    eventDescription,
    eventDate,
    eventLocation,
    eventPricing
  )

  document.addEventListener("DOMContentLoaded", (event) => {
    loading()
  })

  /**
   * Loads seach paramerter.
   */
  function loading() {
    const queryString = window.location.search
    const urlParams = new URLSearchParams(queryString)
    const query = urlParams.get("q")
    searching(query)
  }

  /**
   * @param {results} results
   */
  function displayCityResults(
    results,
    eventName,
    eventDescription,
    eventDate,
    eventLocation,
    eventPricing
  ) {
    const cityElement = document.createElement("H1")
    cityElement.innerText = results["name"]
    document.getElementById("city-name").appendChild(cityElement)

    const cityDescElement = document.createElement("P")
    cityDescElement.innerText = results["description"]
    document.getElementById("city-description").appendChild(cityDescElement)

    var eventsElement = document.createElement("ul")
    for (var i = 0; i < eventName.length; i++) {
      var item = document.createElement("li")
      item.appendChild(
        document.createTextNode(
          "Name: " +
            eventName[i] +
            ", Description: " +
            eventDescription[i] +
            ", Date: " +
            eventDate[i] +
            ", Location: " +
            eventLocation[i] +
            ", Pricing: " +
            eventPricing
        )
      )
      eventsElement.appendChild(item)
    }
    document.getElementById("list-of-events").appendChild(eventsElement)
  }
}
