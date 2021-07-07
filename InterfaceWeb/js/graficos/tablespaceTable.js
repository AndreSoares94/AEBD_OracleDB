// Function para adicionar as linhas da tabela
function appendToTableSpaceTable (NAME, DATAFIELE_ID, USED_BYTES, TOTAL_BYTES, FREE_BYTES, STATUS, CONTENTS) {
  
    var table = document.getElementById('tablespaceTable');
    var newRow = document.createElement('tr');
    newRow.innerHTML = `
      <th>${NAME}</th>
      <th>${DATAFIELE_ID}</th>
      <th>${USED_BYTES}</th>
      <th>${TOTAL_BYTES}</th>
      <th>${FREE_BYTES}</th>
      <th>${STATUS}</th>
      <th>${CONTENTS}</th>
    `;

    table.appendChild(newRow)
  }
  
$(document).ready(function() {
    var data = 'http://localhost:8080/tablespace'
    $.getJSON(data, function (json) {
          
      // Ciclo for para cada item -> linha
      for (var item of json.rows) {
        var ts = new Date(item.timestamp)
        appendToTableSpaceTable(item.nome, item.datafileid, item.usedbytes, item.totalbytes, item.freebytes, item.status, item.contents)
      }
    
    })
    $('tablespaceTable').DataTable(
      {select: true}
    );
})