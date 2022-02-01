// gets todos from the api.
// It adds the values of the various inputs to the requested URl to filter and order the returned todos.
function getFilteredTodos() {
  console.log("Getting todos");

  var url = "/api/todos?";
  if(document.getElementById("owner").value != "") {
    url = url + "&owner=" + getFieldValue("owner");
  }
  if(document.getElementById("category").value != "") {
    url = url + "&category=" + getFieldValue("category");
  }
  if(document.getElementById("status").value != "") {
    url = url + "&status=" + getFieldValue("status");
  }
  if(document.getElementById("contains").value != "") {
    url = url + "&contains=" + getFieldValue("contains");
  }
  if(document.getElementById("orderBy").value != "") {
    url = url + "&orderBy=" + getFieldValue("orderBy");
  }
  if(document.getElementById("limit").value != "") {
    url = url + "&limit=" + getFieldValue("limit");
  }

  get(url, function(returned_json){
    document.getElementById("requestUrl").innerHTML = url;
    document.getElementById('jsonDump').innerHTML = syntaxHighlight(JSON.stringify(returned_json, null, 2));
  });
}
