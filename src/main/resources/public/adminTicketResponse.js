let baseUrl = "http://localhost:8081/";

async function setAdminTicket(){
    var ticketId = sessionStorage.activeAdminTicket;
    console.log(ticketId);

    let res = await fetch(
        `${baseURL}admin/ticket/${ticketId}`,
        {
            method: 'GET'
        }
    );

    let resJson = await res.json()
        .then((resp) => {
            sessionStorage.setItem('activeTicketObj', JSON.stringify(resp));
            ticket = resp;

            var tbody = document.getElementById("ticketTable");
            var subTime = new Date(parseInt(ticket.submissionTime));
            var sub = (subTime.getMonth() + 1)+'/'+ subTime.getDate() + '/' + subTime.getFullYear();

            tbody.innerHTML = 
                `
                    <tr><th>ID</th><td>${ticket.id}</td></tr>
                    <tr><th>Subject</th><td>${ticket.subject}</td></tr>
                    <tr><th>Submission Date</th><td>${sub}</td></tr>
                    <tr><th>Description</th><td>${ticket.description}</td></tr>
                    <tr><th>Status</th><td>${ticket.status}</td></tr>
                    `
        })
}

async function statusUpdate(){
    var newStatus = document.getElementById('newStatus').value;
    var ticketId = sessionStorage.activeAdminTicket;

    let res = await fetch(
        `${baseUrl}admin/ticket/${ticketId}`,
        {
            method: 'PUT',
            header: {'Content-Type': 'application/json'},
            body: newStatus
        }
    );

    let resJson = await res.json()
        .then((resp) =>{
            console.log(resp);
            sessionStorage.setItem('activeTicketObj', JSON.stringify(resp));
            setAdminTicket();
            alert("Ticket has been updated successfully!");
        })
}