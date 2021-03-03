var form = document.getElementById("form");

form.addEventListener('submit', function(e){
    e.preventDefault();
    var fd = new FormData(form);

    if(fd.get('cedula') == "" || fd.get('nombre') == "" || fd.get('apellido') == "" || fd.get('fdn') == "" || fd.get('password') == "" || fd.get('email') == ""){
        alert("Los datos fueron rellenados de manera incorrecta");
        return
    }

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