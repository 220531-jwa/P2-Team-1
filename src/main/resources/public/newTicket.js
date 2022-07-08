let baseUrl = "http://localhost:8081/";
let activeUser = sessionStorage.activeUser;

async function newTicket(){

    let subject = document.getElementById('subj').value;
    let desc = document.getElementById('desc').value;

    let tickets = {

        subject:subject,
        description:desc,
    }

    let ticketJSON= JSON.stringify(tickets);
    console.log(ticketJSON);


    let res = await fetch(`${baseUrl}user/${activeUser.id}/tickets`, {

            Method:'POST',
            header: {'Content-Type': 'application/json'},
            body: ticketJSON
            });

    let resJon = await res.json()
        .then((resp) =>{ 
        console.log(resp);
        sessionStorage.setItem('ticket' ,JSON.stringify(resp));
        window.location.assign("viewTicket.html");  
         })  
                                
         .catch()   //will catch if there is an error
                            
         .catch((error) => {
          console.log(error);
        });
    
  }


    