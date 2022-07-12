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



function  toggleError(id){
    var x = document.getElementById(id);
    if(x.hasAttribute('hidden'))
    {
        x.removeAttribute('hidden');
    }
    else x.setAttribute('hidden', 'hidden');
}
