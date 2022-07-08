let baseUrl = "http://localhost:8081/";
async function newTicket(){

    let status = document.getElementById('status').value;
    let desc = document.getElementById('desc').value;
    let submitdate = document.getElementById("submitdate").value;

    let tickets = {

        status:status,
        description:desc,
        submissiontime:submitdate,
    }

    let employeeJson = JSON.stringify(tickets);
    console.log(employeeJson);


    let res = await fetch(`${baseUrl}/tickets`, {

                            Method:'POST',
                            header: {'Content-Type': 'application/json'},
                            body: employeeJson      

                                });

    let resJon = await res.json()

        .then((resp) =>{        
        console.log(resp);   
        window.location.assign("viewTicket.html");  
         })  
                                
         .catch()   //will catch if there is an error
                            
         .catch((error) => {
          console.log(error);
        });
                            
  }


    