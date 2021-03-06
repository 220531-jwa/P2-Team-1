function loadTicket(){
    let ticket = JSON.parse(sessionStorage.ticket);
    console.log(ticket);
    var tbody = document.getElementById("ticketTable");

    var subTime = new Date(parseInt(ticket.submissionTime));
        var sub = (subTime.getMonth() + 1)+'/'+ subTime.getDate() + '/' + subTime.getFullYear();

        tbody.innerHTML = 
        `
            <tr><th>ID</th><td>${ticket.id}</td></tr>
            <tr><th>Status</th><td>${ticket.status}</td></tr>
            <tr><th>Subject</th><td>${ticket.subject}</td></tr>
            <tr><th>Description</th><td>${ticket.description}</td></tr>
            <tr><th>Submission time</th><td>${sub}</td></tr>
            `;
}

async function getTicketByNum(){
    let id = sessionStorage.ticketId;

    let activeUser = JSON.parse(sessionStorage.activeUser);
    let res = await fetch(
        
        `${baseURL}user/${activeUser.id}/tickets/${id}`,
        {
            method: 'GET'
        }
    )
    
    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            sessionStorage.setItem('ticket' , JSON.stringify(resp));
        })

    loadTicket();
}

function tryDelete(){
    document.getElementById('deleteConfirm').removeAttribute('hidden');
}

async function trueDelete(){
    let ticket = JSON.parse(sessionStorage.ticket);
    let ticketId = ticket.id;
    let activeUser = JSON.parse(sessionStorage.activeUser);

    console.log(ticketId);

    let res = fetch(
        `${baseURL}user/${activeUser.id}/tickets/${ticketId}`,
        {
            method: 'DELETE'
        }
    )
    window.location = `${baseURL}allTickets.html`
}

function hideBox(){
    document.getElementById('deleteConfirm').setAttribute('hidden', 'hidden');
}