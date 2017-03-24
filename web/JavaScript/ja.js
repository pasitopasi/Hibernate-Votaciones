function borrar(){
    if (document.getElementById("p1")) {
        document.getElementById("p1").parentNode.removeChild(document.getElementById("p1"));
    }
}
function validar(){
    borrar();
    var pass01 = document.getElementById("contrasena").value;
    var pass00 = document.getElementById("contrasena1").value;
    if(pass01!=pass00) {
        var padre = document.getElementById("padre");
        var titulo = document.createElement("p");
        titulo.setAttribute("id","p1");
        titulo.appendChild(document.createTextNode("Las contraseñas no son iguales."));
        padre.appendChild(titulo);
        return false;
    }
    return true;
}
