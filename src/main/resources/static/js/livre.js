$(document).ready(function() {
    $(".status-button").click(function() {
      var status = $(this).data("status");
      var newStatus = "";
  
      if (status === "livre") {
        newStatus = "ocupado";
        $(this).removeClass("btn-success").addClass("btn-warning").text("Ocupado");
      } else if (status === "ocupado") {
        newStatus = "livre";
        $(this).removeClass("btn-warning").addClass("btn-success").text("Livre");
      }
  
      $(this).data("status", newStatus);
    });
  });


  document.getElementById("searchInput").addEventListener("input", function() {
    var input = this.value.toLowerCase();
    var table = document.getElementById("dataTable");
    var rows = table.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var armarioCell = rows[i].getElementsByTagName("td")[0];
        var alunoCell = rows[i].getElementsByTagName("td")[1];
        var statusCell = rows[i].getElementsByTagName("td")[2];

        if (armarioCell || alunoCell || statusCell) {
            var armarioText = armarioCell.textContent || armarioCell.innerText;
            var alunoText = alunoCell.textContent || alunoCell.innerText;
            var statusText = statusCell.textContent || statusCell.innerText;

            if (armarioText.toLowerCase().indexOf(input) > -1 || alunoText.toLowerCase().indexOf(input) > -1 || statusText.toLowerCase().indexOf(input) > -1) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }
});

function mostrarInformacoes() {
  var informacoes = document.getElementById("informacoes");
  if (informacoes.style.display === "none") {
      informacoes.style.display = "block";
  } else {
      informacoes.style.display = "none";
  }
}

function mostrarTabelaArmarios() {
  var tabelaArmarios = document.getElementById("tabelaArmarios");
  if (tabelaArmarios.style.display === "none") {
    tabelaArmarios.style.display = "block";
  } else {
    tabelaArmarios.style.display = "none";
  }
}

/* const excluirBtn = document.getElementById('excluir-btn');
const tabelaArmarios = document.getElementById('dataTable');
const tabelaVinculada = document.getElementById('tabelaArmarios');

excluirBtn.addEventListener('click', () => {
  tabelaArmarios.remove();
  tabelaVinculada.remove();
  });

*/

const excluirBtns = document.querySelectorAll('.excluir-btn');

  excluirBtns.forEach((btn) => {
    btn.addEventListener('click', (event) => {
      const row = event.target.closest('tr');
      row.remove();
    });
  });

