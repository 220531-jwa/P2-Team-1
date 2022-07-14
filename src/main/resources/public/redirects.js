let baseURL = 'http://localhost:8081/';

function toHome(){
    window.location =`${baseURL}homePage.html`;
}

function toLogin(){
    window.location =`${baseURL}login.html`;
}

function toCreateAccount(){
    window.location = `${baseURL}createAccount.html`;
}

function toSubmitNew(){
    window.location = `${baseURL}newTicket.html`;
}

function toProductsPage(){
    window.location =`${baseURL}ItemPage.html`;
}

function toAllTickets(){
    window.location =`${baseURL}allTickets.html`;
}

function toCart(){
    window.location =`${baseURL}CartListPage.html`;
}

function viewAllAdminTicket(){
    window.location =`${baseURL}adminAllTickets.html`;
}

function toAdminSingleTicket(){
    window.location =`${baseURL}adminTicketResponse.html`;
}

function toSellerHome(){
    window.location = `${baseURL}sellerHomePage.html`
}

function toAdminHome(){
    window.location = `${baseURL}adminHome.html`
}

function toSellerInventory(){
    window.location = `${baseURL}sellerInventory.html`
}

function toAdminTickets(){
    window.location = `${baseURL}adminAllTickets.html`
}


function toSingleItem(){
    window.location = `${baseURL}singleItem.html`
}