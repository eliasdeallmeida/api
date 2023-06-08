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
  