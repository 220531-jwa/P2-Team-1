async function loadTicketsAdmin(){
    let res = await fetch(
        `${baseURL}admin/ticket`,
        {
            method: 'GET'
        }
    )
    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            var tbody = document.getElementById('tickets');

            tbody.innerHTML = 
            `<tr>
                <th>ID</th>
                <th>Subject</th>
                <th>Description</th>
                <th>Submission Time</th>
                <th>Status</th>
            </tr>`

            for(var i = 0; i<resp.length; i++){
                if(resp[i].status == "open"){
                    var subTime = new Date(parseInt(resp[i].submissionTime));
                    var sub = (subTime.getMonth() + 1)+'/'+ subTime.getDate() + '/' + subTime.getFullYear();

                    tbody.innerHTML +=
                `
                    <tr>
                        <td>
                            <button class="btn btn-outline-info" id="id1" onclick="setReq('${resp[i].id}')"> ${resp[i].id} </button>
                        </td>
                        <td>${resp[i].subject}</td>
                        <td>${resp[i].description}</td>
                        <td>${sub}</td>
                        <td>${resp[i].status}</td>
                    </tr>
                `
                }
                }
            }
        )
        .catch((error) => {console.log(error)});
}

function setReq(object){
    sessionStorage.setItem('activeAdminTicket', object);
    toAdminSingleTicket();
}