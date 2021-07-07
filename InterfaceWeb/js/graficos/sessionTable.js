// Function para adicionar as linhas da tabela
// Function para adicionar as linhas da tabela
function appendToUserTable (ID_USER, STATUS, SCHEMA_NAME, MACHINE, PORT, TYPE, LOGON_TIME, TIMESTAMP) {
  
  var table = document.getElementById('userTable');
  var newRow = document.createElement('tr');
  newRow.innerHTML = `
    <th>${ID_USER}</th>
    <th>${STATUS}</th>
    <th>${SCHEMA_NAME}</th>
    <th>${MACHINE}</th>
    <th>${PORT}</th>
    <th>${TYPE}</th>
    <th>${LOGON_TIME}</th>
    <th>${TIMESTAMP}</th>
  `;

  table.appendChild(newRow)
}

$(document).ready(function() {

  var data = 'http://localhost:8080/session'
  $.getJSON(data, function (json) {
        
    // Ciclo for para cada item -> linha
    for (var item of json.rows) {


      var a = new Date(item.logontime);
      var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
      var year = a.getFullYear();
      var month = months[a.getMonth()];
      var date = a.getDate();
      var hour = a.getHours();
      var min = a.getMinutes();
      var sec = a.getSeconds();
      var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;


      var a1 = new Date(item.timestamp);
      var months1 = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
      var year1 = a1.getFullYear();
      var month1 = months1[a1.getMonth()];
      var date1 = a1.getDate();
      var hour1 = a1.getHours();
      var min1 = a1.getMinutes();
      var sec1 = a1.getSeconds();
      var time1 = date1 + ' ' + month1 + ' ' + year1 + ' ' + hour1 + ':' + min1 + ':' + sec1 ;

      appendToUserTable(item.userid, item.status, item.schemaname, item.machine, item.port, item.type, time, time1)
    }
  
  })

  $().DataTable();
})