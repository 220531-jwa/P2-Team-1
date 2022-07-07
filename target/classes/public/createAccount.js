let baseURL = "http://localhost:8081"

async function createAccount(){
    console.log("submit button pressed");

    let un = document.getElementById("un").value;
    let pw = document.getElementById("pw").value;
    let name = document.getElementById("name").value;

    let request = {
        username: un,
        password: pw,
        name: name
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
        window.location.assign("loginPage.html");
    })
    .catch((error) => {
        console.log(error);
        window.alert("Account not created. Try a different username.");
    });
}