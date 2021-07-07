// Function para adicionar as linhas da tabela
// Function para adicionar as linhas da tabela
function appendToUserTable (ID_USER, USERNAME, DEFAULT_TABLESPACE, TEMP_TABLESPACE, ACCOUNT_STATUS) {
  
  var table = document.getElementById('userTable');
  var newRow = document.createElement('tr');
  newRow.innerHTML = `
    <th>${ID_USER}</th>
    <th>${USERNAME}</th>
    <th>${DEFAULT_TABLESPACE}</th>
    <th>${TEMP_TABLESPACE}</th>
    <th>${ACCOUNT_STATUS}</th>
  `;
  
  table.appendChild(newRow)
}

$(document).ready(function() {

  var data = 'http://localhost:8080/user'
  $.getJSON(data, function (json) {
        
    // Ciclo for para cada item -> linha
    for (var item of json.rows) {
      var ts = new Date(item.last_login)
      appendToUserTable(item.id, item.nome, item.default_tablespace, item.temp_tablespace, item.account_status)
    }
  
  })

  $().DataTable();
})