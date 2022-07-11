let baseUrl ="http://localhost:8081/";
let activeUser = JSON.parse(sessionStorage.activeUser);

async function loadUser(){
    
    let res = await fetch(
        `${baseUrl}user/${activeUser.id}/tickets`,
        {
            method: 'GET'
        }
    );

    let resJson = await res.json()
        .then((resp) => {

            console.log(resp);
            var tbody = document.getElementById('supportTicketTable');

            tbody.innerHTML =
            `<tr>
                <th>ID</th>
                <th>Status</th>
                <th>Subject</th>
                <th>Description</th>
                <th>Submission Time</th>
            </tr>`

            for(var i = 0; i <resp.length; i++){
                var subTime = new Date(parseInt(resp[i].submissionTime));
                var sub = (subTime.getMonth() + 1)+'/'+ subTime.getDate() + '/' + subTime.getFullYear();

                tbody.innerHTML +=
                `
                    <tr>
                        <td>${resp[i].id}</td>
                        <td>${resp[i].status}</td>
                        <td>${resp[i].subject}</td>
                        <td>${resp[i].description}</td>
                        <td>${sub}</td>
                    </tr>
                `
            }
        })
        .catch((error) => {console.log(error)})
}

function toHome(){
    window.location = 'http://localhost:8081/homePage.html';
}

function submitNew(){
    window.location = 'http://localhost:8081/newTicket.html';
}