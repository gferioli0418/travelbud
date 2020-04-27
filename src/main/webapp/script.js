/**
*************************
Autocomplete Searchbar
**************************
*/
function autocomplete(inp, arr) {
  // Create variable for the item on list currently focused on
  let currentFocus;

  // Function for when the user types into the searchbar
  inp.addEventListener('input', function() {
    const val = this.value;

    // Get and clear the autocomplete list
    const a = document.getElementById('autocomplete-list');
    a.innerHTML = '';

    // Provide no suggestions for empty input
    if (!this.value) {
      return false;
    }
    currentFocus = -1;

    // Check if the item starts with the same letters as the text field value
    arr.filter((c) => c.toUpperCase().startsWith(val.toUpperCase()))
        .forEach((c) => {
          const b = document.createElement('div');
          b.innerHTML = '<strong>' + val + '</strong>';
          b.innerHTML += c.substr(val.length);
          b.innerHTML += '<input type=\'hidden\' value=\'' + c + '\'>';
          // Execute a function when someone clicks on the item value (div
          // element)
          b.addEventListener('click', function(e) {
            // use suggestion as input value and clear autocomplete list.
            inp.value = this.getElementsByTagName('input')[0].value;
            a.innerHTML = '';
          });
          a.appendChild(b);
        });
  });

  // Execute a function presses a key on the keyboard
  inp.addEventListener('keydown', function(e) {
    let x = document.getElementById('autocomplete-list');
    if (x) x = x.getElementsByTagName('div');
    if (e.keyCode == 40) {
      // If the arrow DOWN key is pressed, increase currentFocus
      currentFocus++;
      addActive(x);
    } else if (e.keyCode == 38) {  // up
      // If the arrow UP key is pressed, decrease currentFocus
      currentFocus--;
      addActive(x);
    } else if (e.keyCode == 13) {
      // If the ENTER key is pressed, prevent form from being submitted
      e.preventDefault();
      if (currentFocus > -1) {
        // Simulate click on active item
        if (x) x[currentFocus].click();
      } else {
        // invoke the search function
        search();
      }
    }
  });

  // To classify an item as "active"
  function addActive(x) {
    if (!x) return false;

    // Remove the "active" class on all items
    removeActive(x);
    if (currentFocus >= x.length) currentFocus = 0;
    if (currentFocus < 0) currentFocus = (x.length - 1);

    // Add class "autocomplete-active"
    x[currentFocus].classList.add('autocomplete-active');
  }
  // To remove an item as "active" from all autocomplete items
  function removeActive(x) {
    for (let i = 0; i < x.length; i++) {
      x[i].classList.remove('autocomplete-active');
    }
  }

  // Execute a function when someone clicks on the document
  document.addEventListener('click', function(e) {
    const a = document.getElementById('autocomplete-list');
    a.innerHTML = '';
  });
}

// array of country names
const countries = [
  'Afghanistan',
  'Albania',
  'Algeria',
  'Andorra',
  'Angola',
  'Anguilla',
  'Antigua & Barbuda',
  'Argentina',
  'Armenia',
  'Aruba',
  'Australia',
  'Austria',
  'Azerbaijan',
  'Bahamas',
  'Bahrain',
  'Bangladesh',
  'Barbados',
  'Belarus',
  'Belgium',
  'Belize',
  'Benin',
  'Bermuda',
  'Bhutan',
  'Bolivia',
  'Bosnia & Herzegovina',
  'Botswana',
  'Brazil',
  'British Virgin Islands',
  'Brunei',
  'Bulgaria',
  'Burkina Faso',
  'Burundi',
  'Cambodia',
  'Cameroon',
  'Canada',
  'Cape Verde',
  'Cayman Islands',
  'Central Arfrican Republic',
  'Chad',
  'Chile',
  'China',
  'Colombia',
  'Congo',
  'Cook Islands',
  'Costa Rica',
  'Cote D Ivoire',
  'Croatia',
  'Cuba',
  'Curacao',
  'Cyprus',
  'Czech Republic',
  'Denmark',
  'Djibouti',
  'Dominica',
  'Dominican Republic',
  'Ecuador',
  'Egypt',
  'El Salvador',
  'Equatorial Guinea',
  'Eritrea',
  'Estonia',
  'Ethiopia',
  'Falkland Islands',
  'Faroe Islands',
  'Fiji',
  'Finland',
  'France',
  'French Polynesia',
  'French West Indies',
  'Gabon',
  'Gambia',
  'Georgia',
  'Germany',
  'Ghana',
  'Gibraltar',
  'Greece',
  'Greenland',
  'Grenada',
  'Guam',
  'Guatemala',
  'Guernsey',
  'Guinea',
  'Guinea Bissau',
  'Guyana',
  'Haiti',
  'Honduras',
  'Hong Kong',
  'Hungary',
  'Iceland',
  'India',
  'Indonesia',
  'Iran',
  'Iraq',
  'Ireland',
  'Isle of Man',
  'Israel',
  'Italy',
  'Jamaica',
  'Japan',
  'Jersey',
  'Jordan',
  'Kazakhstan',
  'Kenya',
  'Kiribati',
  'Kosovo',
  'Kuwait',
  'Kyrgyzstan',
  'Laos',
  'Latvia',
  'Lebanon',
  'Lesotho',
  'Liberia',
  'Libya',
  'Liechtenstein',
  'Lithuania',
  'Luxembourg',
  'Macau',
  'Macedonia',
  'Madagascar',
  'Malawi',
  'Malaysia',
  'Maldives',
  'Mali',
  'Malta',
  'Marshall Islands',
  'Mauritania',
  'Mauritius',
  'Mexico',
  'Micronesia',
  'Moldova',
  'Monaco',
  'Mongolia',
  'Montenegro',
  'Montserrat',
  'Morocco',
  'Mozambique',
  'Myanmar',
  'Namibia',
  'Nauro',
  'Nepal',
  'Netherlands',
  'Netherlands Antilles',
  'New Caledonia',
  'New Zealand',
  'Nicaragua',
  'Niger',
  'Nigeria',
  'North Korea',
  'Norway',
  'Oman',
  'Pakistan',
  'Palau',
  'Palestine',
  'Panama',
  'Papua New Guinea',
  'Paraguay',
  'Peru',
  'Philippines',
  'Poland',
  'Portugal',
  'Puerto Rico',
  'Qatar',
  'Reunion',
  'Romania',
  'Russia',
  'Rwanda',
  'Saint Pierre & Miquelon',
  'Samoa',
  'San Marino',
  'Sao Tome and Principe',
  'Saudi Arabia',
  'Senegal',
  'Serbia',
  'Seychelles',
  'Sierra Leone',
  'Singapore',
  'Slovakia',
  'Slovenia',
  'Solomon Islands',
  'Somalia',
  'South Africa',
  'South Korea',
  'South Sudan',
  'Spain',
  'Sri Lanka',
  'St Kitts & Nevis',
  'St Lucia',
  'St Vincent',
  'Sudan',
  'Suriname',
  'Swaziland',
  'Sweden',
  'Switzerland',
  'Syria',
  'Taiwan',
  'Tajikistan',
  'Tanzania',
  'Thailand',
  'Timor L\'Este',
  'Togo',
  'Tonga',
  'Trinidad & Tobago',
  'Tunisia',
  'Turkey',
  'Turkmenistan',
  'Turks & Caicos',
  'Tuvalu',
  'Uganda',
  'Ukraine',
  'United Arab Emirates',
  'United Kingdom',
  'United States of America',
  'Uruguay',
  'Uzbekistan',
  'Vanuatu',
  'Vatican City',
  'Venezuela',
  'Vietnam',
  'Virgin Islands (US)',
  'Yemen',
  'Zambia',
  'Zimbabwe',
];

/* Initiate the autocomplete function on the "myInput" element, and pass along
   the countries array as possible autocomplete values */
autocomplete(document.getElementById('myInput'), countries);

// Generator URL from search bar input
function search() {
  const query = document.getElementById('myInput').value;
  window.location.href = '/SearchResults.html?q=' + encodeURIComponent(query);
}
