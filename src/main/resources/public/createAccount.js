async function createAccount(){
    console.log("submit button pressed");

    let un = document.getElementById("un").value;
    let pw = document.getElementById("pw").value;
    let name = document.getElementById("name").value;
<<<<<<< HEAD
    let type = document.getElementById('accType').value;
=======
    let type = document.getElementById("accType").value;
>>>>>>> a082397323e6d4888318146297654c65beb4fd69

    let request = {
        username: un,
        password: pw,
<<<<<<< HEAD
        name: name,
        accountType:type
=======
        name: name, 
        accountType: type
>>>>>>> a082397323e6d4888318146297654c65beb4fd69
    }

    console.log(request);

    let requestJson = JSON.stringify(request);
    console.log(requestJson);

    let res = await fetch(
        `${baseURL}/createAccount`, 
        {
            method: 'POST',
            header : {'Content-Type': 'application/json'},
            body: requestJson
        }
    );

    let resJson = await res.json()
    .then((resp) => {
        console.log(resp);
        window.location = `${baseURL}login.html`;
    })
    .catch((error) => {
        console.log(error);
        window.alert("Account not created. Try a different username.");
    });
}