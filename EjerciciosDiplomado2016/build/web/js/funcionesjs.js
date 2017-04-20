function avisa() {
    alert('Has hecho click');
}

function escribe(){
    /*document.write(texto);*/
    var texto = document.getElementById('htmlAIncrustar').value;
    var body = document.getElementsByTagName("body")[0];
    body.innerHTML = body.innerHTML + texto;
    document.getElementById('htmlAIncrustar').value = texto;
}
function envia(){
    document.forms[0].submit();
}
function mayusculas(elemento){
    elemento.value = elemento.value.toUpperCase();
}

function validaNumero(elemento){
    var numero = Number(elemento.value);
    if ( numero.toString() != elemento.value  ){
        alert(elemento.value + ', No es número válido');
        elemento.value = '';
    }
}