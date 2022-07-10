let baseUrl = 'http://localhost:8081/'

function home(){

    if(sessionStorage.activeUser != null){
        console.log(sessionStorage.activeUser);
        pullAccount();
        document.getElementById('loginLink').setAttribute('hidden', 'hidden');
    }
}

function pullAccount(){
        let activeUser = JSON.parse(sessionStorage.activeUser);
        document.getElementById('userMessage').innerHTML = `Hello, ${activeUser.name} what would you like to do today?`
}

async function addBal(){
    let amount = document.getElementById('balanceInput').value;
    let activeUser = JSON.parse(sessionStorage.activeUser);

    let res = await fetch(
        `${baseUrl}user/${activeUser.id}/balance`,
        {
            method: 'PATCH',
            header: {'Content-Type': 'application/json'},
            body: amount
        }
    );

    if(res.status === 404){
        toggleError(noneFound);
        return;
    }

    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            toggleError("newBalanceAlert");
            document.getElementById("newBalanceHere").innerHTML = `Your new balance is ${resp}`;
        })
        .catch((error) => {console.log(error)});
}

function  toggleError(id){
    var x = document.getElementById(id);
    if(x.hasAttribute('hidden'))
    {
        x.removeAttribute('hidden');
    }
    else x.setAttribute('hidden', 'hidden');
}

function browseProducts(){
    window.location = 'http://localhost:8081/ItemPage.html';
}

function supportTicket(){
    window.location = 'http://localhost:8081/newTicket.html';
}