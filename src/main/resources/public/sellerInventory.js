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
        var li = document.createElement("li"); //maybe add the class for bootstrap?
        li.className = "list-group-item";
        let btn = document.createElement("button");
        btn.innerHTML = "View Item";
        btn.type = "submit";
        btn.className = "btn btn-primary ";
        btn.id = "viewButt";
        let x = itemResp[i];
        btn.setAttribute("onclick", `ViewItem(${JSON.stringify(x)})`);
        //btn.onclick = addToCart(i);
        btn.style = "width:130px";
        Items.push(itemResp[i]);
        console.log(itemResp[i]);
        
        li.innerHTML = "<br>Name: "  + resp[i].name + "  Cost: $" + resp[i].cost
        + "  Stock: " + resp[i].inventory;

        mainContainer.appendChild(li);
        mainContainer.appendChild(btn);

    }
    
}

function ViewItem(i){
    sessionStorage.setItem('currentItem', JSON.stringify(i));
    window.location = `${baseURL}sellerItemView.html`;
}

//function ChangeStock(){
    
//}