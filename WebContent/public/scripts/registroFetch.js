var form = document.getElementById("form");
var boton = document.getElementById("boton");

form.addEventListener('submit', function(e){
    e.preventDefault();
    var fd = new FormData(form);

    var datos = {
        method: "POST",
        body: fd
    };

    fetch('https://p1-w2.herokuapp.com/Register', datos)
    .then( res => res.json())
    .then( data => {
        if(data.status == 200){
            window.open('https://p1-w2.herokuapp.com/public/views/new.html', "_self");
            alert(data.message);
        }else{
            window.open('https://p1-w2.herokuapp.com/public/views/exist.html', "_self");
            alert(data.message);
        }
    })
    .catch(error => console.error());

});