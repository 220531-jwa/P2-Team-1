let baseURL = 'http://localhost:8081/'

function toHome(){
    window.location =`${baseURL}homePage.html`;
}

function toLogin(){
    window.location =`${baseURL}login.html`;
}

function toCreateAccount(){
    window.location = `${baseURL}`
}

function toSubmitNew(){
    window.location = `${baseURL}newTicket.html`;
}

function toProductsPage(){
    window.location =`${baseURL}ItemPage.html`
}

function toAllTickets(){
    window.location =`${baseURL}allTickets.html`
}

function toCart(){
    window.location =`${baseURL}CartListPage.html`
}

function viewAllAdminTicket(){
    window.location =`${baseURL}adminAllTickets.html`;
}

function toAdminSingleTicket(){
    window.location =`${baseURL}adminTicketResponse.html`
}