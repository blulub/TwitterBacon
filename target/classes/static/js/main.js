window.addEventListener("keydown", keysPressed, false);
window.addEventListener("keyup", keysReleased, false);

var keys = [];
var req = null;
// function that selects a suggestion based on key press
function keysPressed(e) {
  keys[e.keyCode] = true;
  
  
  // alt + 1 selects first
  if (keys[18] && keys[49]) {
	e.preventDefault();
    var suggestion = document.getElementById("0").textContent;
    suggestion = suggestion.replace("Alt + 1", "");
    $('#searchbar').val(suggestion);
    $('#search_suggestions').html("");
  }
  
  // alt + 2 selects second
  if (keys[18] && keys[50]) {
	e.preventDefault();
    var suggestion = document.getElementById("1").textContent;
    suggestion = suggestion.replace("Alt + 2", "");
    $('#searchbar').val(suggestion);
    $('#search_suggestions').html("");
  }
  
  // alt + 3 selects third
  if (keys[18] && keys[51]) {
	e.preventDefault();
    var suggestion = document.getElementById("2").textContent;
    suggestion = suggestion.replace("Alt + 3", "");
    $('#searchbar').val(suggestion);
    $('#search_suggestions').html("");
  }
  
  // alt + 4 selects fourth
  if (keys[18] && keys[52]) {
    e.preventDefault();
    var suggestion = document.getElementById("3").textContent;
    suggestion = suggestion.replace("Alt + 4", "");
    $('#searchbar').val(suggestion);
    $('#search_suggestions').html("");
  }
  
  // alt + 5 selects fifth
  if (keys[18] && keys[53]) {
	e.preventDefault();
    var suggestion = document.getElementById("4").textContent;
    suggestion = suggestion.replace("Alt + 5", "");
    $('#searchbar').val(suggestion);
    $('#search_suggestions').html("");
  }
}

// marks the key as false after being released
function keysReleased(e) {
  keys[e.keyCode] = false;
}

function clickInsert(id) {
  // get the suggestion that the user clicked on
  var suggestion = document.getElementById(id).textContent;
  var intID = parseInt(id);
  suggestion = suggestion.replace("Alt + " + (intID + 1), "");
  
  // get the searchbar element and update its value to suggestion
  var searchbar = $('#searchbar');
  searchbar.val(suggestion);
  
  // clear the suggestions
  $('#search_suggestions').html("");
  
  searchSuggest();
  
}

// main function that retrieves the suggestions
function searchSuggest() {
	
	if (req != null) {
		req.abort();
		req = null;
	}
  
	// get input from the searchbar and turn it into JSON object
	var input = document.getElementById("searchbar").value;
	
	// when the user clears the input, give no suggestions
	if (input === "") {
		document.getElementById("search_suggestions").innerHTML = "";
		return;
	}
	
	// see what options the user selected for suggestions
	var levOn = document.getElementById("lev").checked;
	var levDist = parseInt(document.getElementById("levDistance").value);
	// error check the edit distance the user wants
	if (levOn) {
	  if (isNaN(levDist) || levDist < 0) {
		  alert("Please Enter a positive integer as edit distance!");
		  return;
	  }
	// make sure to give levDist value, so we can pass something to compute
	} else {
		levDist = 0;
	}
	var prefixOn = document.getElementById("prefix").checked;
	var whitespaceOn = document.getElementById("whitespace").checked;
	var smartOn = document.getElementById("smart").checked;
	
	var JSONinput = JSON.stringify(input);
	// add user options and input to postParameters for requestHandler
	var postParameters = {input: JSONinput, levOn : levOn, levDist: levDist
		, prefixOn : prefixOn, whitespaceOn: whitespaceOn, smartOn: smartOn};
	req = $.post("/suggest", postParameters, function(responseJSON){
		
		responseObject = JSON.parse(responseJSON);
		// get suggestions out of responseObject and inject into div
		var suggestions = responseObject.suggestions;
		
		// only inject suggestions if suggestions is checked
		if (document.getElementById("checkbox").checked) {
		  document.getElementById("search_suggestions").innerHTML = 
			  suggestions;
		  
		  // find if we have any suggestions, use below
		  var length = suggestions.length;
		  
		  // glow blue if there are suggestions, glow red if there aren't
		  if (length === 0) {
		    document.getElementById("searchbar").style.boxShadow =
		    "0px 0px 20px #F08B8B";
		  } else {
		    document.getElementById("searchbar").style.boxShadow =
	        "0px 0px 20px #9BDCF2";
		  }
		} else {
			document.getElementById("searchbar").style.boxShadow = "";
		}
	});
}

// check the state of the turn suggestions on checkbox on click
function handleClick() {
	var isClicked = document.getElementById("checkbox").checked;
	// if clicked, automatically give suggestions for the current value
	if (isClicked) {
		searchSuggest();
    // if unchecked, clear the suggestions
	} else {
		document.getElementById("search_suggestions").innerHTML = "";
		document.getElementById("searchbar").style.boxShadow = "";
	}
}

// call the update function whenever somebody clicks an option change
function updateOptions() {
	searchSuggest();
}

