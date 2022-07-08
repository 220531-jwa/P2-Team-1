let baseURL = "http://localhost:8081";

async function login(){
    console.log("login button pressed");

    let un = document.getElementById("un").value;
    let pw = document.getElementById("pw").value;

    let user = {
        username: un, 
        password: pw
    }
    console.log(user);

    let userJson = JSON.stringify(user);
    console.log(userJson);

    let res = await fetch(
        `${baseURL}/login`, 
        {
            method : 'POST',
            header : {'Content-Type': 'application/json'},
            body: userJson
        }
    );

    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            console.log("User logged in successfully!");
            sessionStorage.setItem("activeUser", JSON.stringify(resp));
                // change below to window.location("homePage.html")
        })
        .catch((error) => {
            console.log(error);
        });
}