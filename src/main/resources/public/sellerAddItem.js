let activeUser = JSON.parse(sessionStorage.activeUser);

async function newItem(){
    let gname = document.getElementById("gameName").value;
    let price = document.getElementById("cost").value;
    let description = document.getElementById("desc").value
    let number = document.getElementById("inventory").value;

    if(gname === "" || description === "" || price === "" || number === ""){
        document.getElementById('errormsg').removeAttribute('hidden');
        return;
    }

    let request = {
        name: gname,
        cost: price, 
        desc: description,
        inventory: number
    }

    let requestJson = JSON.stringify(request);
    console.log(requestJson); 

    let res = await fetch(
        `${baseURL}seller/${activeUser.id}/items`, 
        {
            method: 'POST', 
            header: {'Content-Type': 'application/json'},
            body: requestJson
        }
    ); 

    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            document.getElementById('message').innerHTML = "Item successfully created!";
        })
        .catch((error) => {
            console.log(error);
        });
}