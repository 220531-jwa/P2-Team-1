const Items = [];
let activeUser = JSON.parse(sessionStorage.activeUser);


async function getItemData(){
    let res = await fetch(`${baseURL}seller/${activeUser.id}/items`);//the url where we're sending this request.
 //  let res = await fetch(`${baseURL}seller/2/items`); //FOR TESTING ONLY
if(res.status == 200){
    let data = await res.json()

    .then((resp) => {
        appendItemData(resp);
        console.log(resp);
    })

    .catch((error) =>{
        console.log(error);
    });
} else{
    console.log("It got away!");
}
}

function appendItemData(resp){
    console.log(resp + " this is the resp");
    var itemResp = (resp);
    console.log(itemResp);
    var mainContainer = document.getElementById('sellerData');

    for(var i = 0; i < resp.length; i++){
        mainContainer.innerHTML += 
        `
        <div class="card">
                <div class="row g-0">
                    <div class="col-2">
                        <img src="./img/${resp[i].imglink}" class="bd-placeholder-image" 
                        width="130"
                        height="240">
                    </div>
                    <div class="col-3">
                        <div>
                            <table id="infoTable${resp[i].id}" class="table table-hover">
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        `;

        document.getElementById(`infoTable${resp[i].id}`).innerHTML +=
        `
        <tr><th>Name:</th><td>${resp[i].name}</td></tr>
        <tr><th>Description:</th><td>${resp[i].desc}</td></tr>
        <tr><th>Cost:</th><td id="currentCost">${resp[i].cost}</td></tr>
        <tr><th>In Stock:</th><td>${resp[i].inventory}</td></tr>
        <tr><td><button class="btn btn-primary" onclick="ViewItem(${resp[i].id})">View More Info</button></td></tr>
        `;
    }
    
}

function ViewItem(i){
    sessionStorage.setItem('currentItemId', JSON.stringify(i));
    window.location = `${baseURL}sellerItemView.html`;
}

//function ChangeStock(){
    
//}