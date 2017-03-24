function borrar(){
    if (document.getElementById("p1")) {
        document.getElementById("p1").parentNode.removeChild(document.getElementById("p1"));
    }
}
function validar(){
    borrar();
    var opciones = document.getElementsByName("reloj");
    var seleccionado = false;
    for(var i=0; i<opciones.length; i++) {    
      if(opciones[i].checked) {
        seleccionado = true;
        break;
      }
    }
    if(!seleccionado) {
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id","p1");
        titulo.appendChild(document.createTextNode("Seleccione una marca."));
        padre.appendChild(titulo);
        return false;
    }
    return true;
}
