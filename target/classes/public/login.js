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
        `${baseURL}login`, 
        {
            method : 'POST',
            header : {'Content-Type': 'application/json'},
            body: userJson
        }
    );

    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            sessionStorage.setItem("activeUser", JSON.stringify(resp));
            if(resp.accountType == 1){
                window.location.assign("homePage.html");
            } else if(resp.accountType == 2){
                window.location.assign("sellerHomePage.html");
            } else if(resp.accountType == 3){
                window.location.assign("adminHome.html");
            }
                
        })
        .catch((error) => {
            console.log(error);
        });
}

