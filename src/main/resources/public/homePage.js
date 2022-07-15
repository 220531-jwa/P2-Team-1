function home(){

    if(sessionStorage.activeUser != null){
        console.log(sessionStorage.activeUser);
        pullAccount();
        document.getElementById('loginLink').setAttribute('hidden', 'hidden');
        document.getElementById('logoutButton').removeAttribute('hidden');
    }

    let activeUser = JSON.parse(sessionStorage.activeUser);
    console.log(activeUser.accountType);

    if(activeUser.accountType == 2){
        document.getElementById("sellerHomeBtn").removeAttribute('hidden');
    }
    if(activeUser.accountType == 3){
        document.getElementById("adminHomeBtn").removeAttribute('hidden');
    }
}

function pullAccount(){
        let activeUser = JSON.parse(sessionStorage.activeUser);
        document.getElementById('userMessage').innerHTML = `Hello, ${activeUser.name}. What would you like to do today?`
}

async function displayRewardPoints(){
    let activeUser = JSON.parse(sessionStorage.activeUser); 
    let res = await fetch(
        `${baseURL}user/${activeUser.id}`, 
        {
            method: 'GET', 
            header: {'Content-Type': 'application/json'}
        }
    ); 
    let resJson = await res.json()
    .then((resp) => {
        document.getElementById('RewardPoints').removeAttribute('hidden');
        document.getElementById('RewardPoints').innerHTML = `You have ${resp} AchievePoints!`;
    })
    .catch((error) => {
        console.log(error);
    });
}

async function addBal(){
    let amount = document.getElementById('balanceInput').value;
    let activeUser = JSON.parse(sessionStorage.activeUser);

    let res = await fetch(
        `${baseURL}user/${activeUser.id}/balance`,
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

function logout(){
    sessionStorage.clear;
    
    toLogin();
}