/**
 * Adds a random location to the page.
 */
// function addRandomLocation() {  // eslint-disable-line no-unused-vars

function searching(input) { 
    input=input.toLowerCase();
     
    //console.log(input);
    //location.search = "q="

    if(input === 'south korea'){
        southKorea()
        //location.search = "q=southkorea"
    }else if(input === 'brazil'){
        brazil()
        console.log('true - brazil');
    }else if(input === 'malaysia'){
        malaysia()
        console.log('true - malaysia');
    }

} 

document.addEventListener("DOMContentLoaded", (event) => {
  loading();
});

function loading(){
  const queryString = window.location.search; // this is something like ?id=23 that comes from the end of the URL
  const urlParams = new URLSearchParams(queryString);
  var query  =urlParams.get('q')
  searching(query)
}

function malaysia() {
  const headingContainer = document.getElementById('Heading')
  headingContainer.innerText = 'Malaysia'

  const city1Container = document.getElementById('city1')
  city1Container.innerText = 'Kuala Lumpur'

  const cityDesc1Container = document.getElementById('cityDesc1')
  cityDesc1Container.innerText = 'Seoul, the capital of South Korea, is a huge metropolis where modern skyscrapers, high-tech subways and pop culture meet Buddhist temples, palaces and street markets.'

  const city2Container = document.getElementById('city2')
  city2Container.innerText = 'Busan'

  const cityDesc2Container = document.getElementById('cityDesc2')
  cityDesc2Container.innerText = 'Busan, a large port city in South Korea, is known for its beaches, mountains and temples.'

  const city3Container = document.getElementById('city3')
  city3Container.innerText = 'Jeju-do'

  const cityDesc3Container = document.getElementById('cityDesc3')
  cityDesc3Container.innerText = 'Jeju province encompasses the South Korean island of Jeju in the Korea Strait. Its known for its beachresorts and volcanic landscape of craters and cavelike lava tubes.'
}


function brazil() {
  const headingContainer = document.getElementById('Heading')
  headingContainer.innerText ='Brazil'

  const city1Container = document.getElementById('city1')
  city1Container.innerText = 'Salvador'

  const cityDesc1Container = document.getElementById('cityDesc1')
  cityDesc1Container.innerText = 'Salvador, the capital of Brazil’s northeastern state of Bahia, is known for its Portuguese colonial architecture, Afro-Brazilian culture and a tropical coastline.'

  const city2Container = document.getElementById('city2')
  city2Container.innerText = 'Rio de Janeiro'

  const cityDesc2Container = document.getElementById('cityDesc2')
  cityDesc2Container.innerText = 'Rio de Janeiro is a huge seaside city in Brazil, famed for its Copacabana and Ipanema beaches, 38m Christ the Redeemer statue atop Mount Corcovado and for Sugarloaf Mountain, a granite peak with cable cars to its summit.'

  const city3Container = document.getElementById('city3')
  city3Container.innerText = 'Jeju-do'

  const cityDesc3Container = document.getElementById('cityDesc3')
  cityDesc3Container.innerText = 'São Paulo, Brazil’s vibrant financial center, is among the worlds most populous cities, with numerous cultural institutions and a rich architectural tradition.'
}

function southKorea() {
  const headingContainer = document.getElementById('Heading')
  headingContainer.innerText = 'South Korea'

  const city1Container = document.getElementById('city1')
  city1Container.innerText = 'Seoul'

  const cityDesc1Container = document.getElementById('cityDesc1')
  cityDesc1Container.innerText = 'Seoul, the capital of South Korea, is a huge metropolis where modern skyscrapers, high-tech subways and pop culture meet Buddhist temples, palaces and street markets.'

  const city2Container = document.getElementById('city2')
  city2Container.innerText = 'Busan'

  const cityDesc2Container = document.getElementById('cityDesc2')
  cityDesc2Container.innerText = 'Busan, a large port city in South Korea, is known for its beaches, mountains and temples.'

  const city3Container = document.getElementById('city3')
  city3Container.innerText = 'Jeju-do'

  const cityDesc3Container = document.getElementById('cityDesc3')
  cityDesc3Container.innerText = 'Jeju province encompasses the South Korean island of Jeju in the Korea Strait. Its known for its beachresorts and volcanic landscape of craters and cavelike lava tubes.'
}

