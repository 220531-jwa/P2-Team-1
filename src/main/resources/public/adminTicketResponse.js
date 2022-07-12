let baseUrl = "http://localhost:8081/";

async function setAdminTicket(){
    var ticketId = sessionStorage.activeAdminTicket;

    let res = await fetch(
        `${baseURL}admin/ticket/${ticketId}`,
        {
            method: 'GET'
        }
    );

    let resJson = await res.json()
        .then((resp) => {
            sessionStorage.setItem('activeTicketObj', JSON.stringify(resp));
        })
}