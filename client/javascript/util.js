/*
 * A small helper function that takes the (HTML) id of a field,
 * gets the value, and encodes it (so people can't inject query param
 * elements into the field).
 */
function getFieldValue(fieldId) {
  return encodeURIComponent(document.getElementById(fieldId).value);
}

/*
 * The next two functions `status` and `json` are used by
 * `get` below to check the status of an HTTP response
 * and convert the response to JSON.
 */
function status(response) {
  if (response.status >= 200 && response.status < 300) {
    return Promise.resolve(response)
  } else {
    return Promise.reject(new Error(response.statusText))
  }
}

function json(response) {
  return response.json()
}

/**
 * Utility function to make generating http requests easier.
 * Sends a GET request to the URL described by 'aUrl' with
 * our 'aCallback' function to be executed when the server
 * sends a response.
 *
 * Uses functions `status` and `json` to process the status
 * and parse the results to JSON.
 *
 * Based on: https://stackoverflow.com/a/38297729 and
 * https://developers.google.com/web/updates/2015/03/introduction-to-fetch?hl=en#chaining_promises
 */
function get(aUrl, aCallback) {
  fetch(aUrl)
    .then(status)
    .then(json)
    .then(aCallback)
    .catch(function(error) {
      window.alert("There was a problem accessing this URL: " + aUrl + "\nError: <" + error + ">");
    });
}

// Syntax highlighting for JSON
// from https://stackoverflow.com/questions/4810841/how-can-i-pretty-print-json-using-javascript
function syntaxHighlight(json) {
  json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
  return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
    var cls = 'number';
    if (/^"/.test(match)) {
      if (/:$/.test(match)) {
        cls = 'key';
      } else {
        cls = 'string';
      }
    } else if (/true|false/.test(match)) {
      cls = 'boolean';
    } else if (/null/.test(match)) {
      cls = 'null';
    }
    return '<span class="' + cls + '">' + match + '</span>';
  });
}
