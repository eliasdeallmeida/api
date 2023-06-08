const formulario = document.querySelector('form')
const inputLogin = document.querySelector('#email');
const inputSenha = document.querySelector('#senha');

function logar() {
    const dados = {
        login: inputLogin.value,
        senha: inputSenha.value
    }

    fetch("http://localhost:8081/login", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            //Authentication: 'Bearer {token}'
        },
        method: "POST",
        body: JSON.stringify(dados)
    })
    .then(response => response.json())
    //.then(json => console.log(JSON.stringify(json)))
    .then(data => {
      if (data.token) {
        localStorage.setItem('token', data.token);
        console.log(localStorage)
        window.location.href = '/home/index.html';
        } else {
            alert('Login inválido');
        }
    })
    .catch(function (res) { console.log(res) })

    // .then((data) => {
    //     console.log(data);
    //     // code here //
    //     if(data.error) {
    //         alert("Error Password or Username");
    //     } else {
    //         window.open("test.html");
    //     }
    //     })
    //     .catch((err) => {
    //     console.log(err);
    // })

    //.then(function (res) { console.log(res) })
}

function limpar() {
    inputLogin.value = "",
    inputSenha.value = ""
}

formulario.addEventListener('submit', function(event) {
    event.preventDefault();
    logar();
    limpar();
});