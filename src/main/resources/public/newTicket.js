let activeUser = JSON.parse(sessionStorage.activeUser);

async function newTicket(){

    let subject = document.getElementById('subj').value;
    let desc = document.getElementById('desc').value;

    if(subject === "" || desc === ""){
        document.getElementById('errormsg').removeAttribute('hidden');
        return;
    }

    let tickets = {

        subject:subject,
        description:desc,
    }

    let ticketJSON= JSON.stringify(tickets);
    console.log(ticketJSON);

   

    let res = await fetch(`${baseURL}user/${activeUser.id}/tickets`, {

            method:'post',
            header: {'Content-Type': 'application/json'},
            body: ticketJSON
            });

    let resJson = await res.json()
        .then((resp) =>{ 
        console.log(resp);
        sessionStorage.setItem('ticket' ,JSON.stringify(resp));
        sessionStorage.setItem('ticketId', resp.id);
        window.location = `./viewTicket.html`;  
         })  
                                
         .catch()   //will catch if there is an error
                            
         .catch((error) => {
          console.log(error);
        });
    
  }



    