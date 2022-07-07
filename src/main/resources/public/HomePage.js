let baseUrl = 'http://localhost:8081/'

function home(){
    if(sessionStorage.activeUser != null){
        pullAccount();
    }
}

function pullAccount(){
        let activeUser = sessionStorage.getItem('activeUser');
        document.getElementById('userMessage').innerHTML = `Hello, ${activeUser.name} what would you like to do today?`
}

async function addBal(){
    let amount = document.getElementById('balanceInput').value;

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
