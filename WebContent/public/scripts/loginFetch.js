var form = document.getElementById("form");

form.addEventListener('submit', function(e){
    e.preventDefault();
    var fd = new FormData(form);

    var datos = {
        method: "POST",
        body: fd 
    };

    

    fetch('https://p1-w2.herokuapp.com/Login', datos)
    .then( res => res.json())
    .then( data => {
        if(data.status == 200 && data.cedula == fd.get('cedula') && data.password == fd.get('password')){
            window.open('https://p1-w2.herokuapp.com/Sesion', "_self");
            alert(data.message);
        }else{
            window.open('https://p1-w2.herokuapp.com/public/views/notlog.html', "_self");
            alert(data.message);
        }
    })
    .catch(error => console.error());

});