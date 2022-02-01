// gets users from the api.
// It adds the values of the various inputs to the requested URl to filter and order the returned users.
function getFilteredUsers() {
  console.log("Getting users");

  var url = "/api/users?";
  if(document.getElementById("age").value != "") {
    url = url + "&age=" + getFieldValue("age");
  }
  if(document.getElementById("company").value != "") {
    url = url + "&company=" + getFieldValue("company");
  }

  get(url, function(returned_json){
    document.getElementById("requestUrl").innerHTML = url;
    document.getElementById('jsonDump').innerHTML = syntaxHighlight(JSON.stringify(returned_json, null, 2));
  });
}


