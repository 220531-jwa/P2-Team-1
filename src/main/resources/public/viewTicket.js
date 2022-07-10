function loadTicket(){
    let ticket = JSON.parse(sessionStorage.ticket)

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