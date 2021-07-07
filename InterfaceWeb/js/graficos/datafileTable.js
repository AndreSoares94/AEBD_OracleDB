// Function para adicionar as linhas da tabela
function appendToDataFileTable (ID_DATAFILE, NAME, TYPE, USED_BYTES, TOTAL_BYTES, FREE_BYTES, STATUS, AUTOEXTENSIBLE) {
  
    var table = document.getElementById('datafileTable');
    var newRow = document.createElement('tr');
    newRow.innerHTML = `
      <th>${ID_DATAFILE}</th>
      <th>${NAME}</th>
      <th>${TYPE}</th>
      <th>${USED_BYTES}</th>
      <th>${TOTAL_BYTES}</th>
      <th>${FREE_BYTES}</th>
      <th>${STATUS}</th>
      <th>${AUTOEXTENSIBLE}</th>
    `;

    table.appendChild(newRow)
  }
  
$(document).ready(function() {
    var data = 'http://localhost:8080/datafile'
    $.getJSON(data, function (json) {
          
      // Ciclo for para cada item -> linha
      for (var item of json.rows) {
        var ts = new Date(item.timestamp)
        var sp = item.nome.split('/')
        var nomes = sp[sp.length-4] + "/" + sp[sp.length-3] + "/" + sp[sp.length-2] + "/" + sp[sp.length-1]
        console.log(nomes);
        appendToDataFileTable(item.id, nomes, item.type, item.usedbytes, item.totalbytes, item.freebytes, item.status, item.autoextensible)
      }
    
    })
    $().DataTable();
})