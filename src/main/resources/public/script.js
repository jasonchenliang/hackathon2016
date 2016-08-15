$(document).ready(function () {
  var client = new $.es.Client({
    hosts: 'localhost:9200'
  });

  $("#searchBtn").click(function () {
    client.search({
      q: $("#searchField").val()
    }).then(function (body) {
      displayResult(body.hits.hits);
    }, function (error) {
      console.trace(error.message);
    });
  });
});

function displayResult(hits) {
  var resultTable = $("#resultTable");
  resultTable.find("thead").empty();
  resultTable.find("tbody").empty();

  if (hits.length !== 0) {
    var tr = $('<tr/>');
    generateRow(hits[0], tr, "th");
    resultTable.find("thead").append(tr);

    $.each(hits, function (index, value) {
      var tr = $('<tr/>');
      generateRow(value, tr, "td");
      resultTable.find("tbody").append(tr);
    });
  }
  resultTable.show();
}

function generateRow(obj, row, tag) {
  for (var o in obj) {
    if (obj.hasOwnProperty(o)) {
      if (typeof obj[o] === "object") {
        generateRow(obj[o], row, tag);
      }
      else {
        row.append("<" + tag + ">" + (tag === "th" ? o : obj[o]) + "</" + tag + ">");
      }
    }
  }
}