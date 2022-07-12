const Items = [];
let activeUser = JSON.parse(sessionStorage.activeUser);

async function getItemData(){
    let res = await fetch(`${baseURL}seller/${activeUser.id}/items`);//the url where we're sending this request.

if(res.status == 200){
    let data = await res.json()

    .then((resp) => {
        appendItemData(resp);
         
    })

    .catch((error) =>{
        console.log(error);
    });
} else{
    console.log("It got away!");
}
}

function appendItemData(resp){
    var mainContainer = document.getElementById('itemData');
    for(var i = 0; i < resp.length; i++){
        var li = document.createElement("li"); //maybe add the class for bootstrap?
        li.className = "list-group-item";
        let btn = document.createElement("button");
        btn.innerHTML = "View Item";
        btn.type = "submit";
        btn.className = "btn btn-primary ";
        btn.id = "AddButt";
        btn.setAttribute("onclick", "ViewItem(" + i +")");
        //btn.onclick = addToCart(i);
        btn.style = "width:130px";
        Items.push(resp[i]);
        
        li.innerHTML = "<br>Name: "  + resp[i].name + "  Cost: $" + resp[i].cost
        + "  Stock: " + resp[i].inventory;

        mainContainer.appendChild(li);
        mainContainer.appendChild(btn);

    }
    
}

function ViewItem(i){
    window.location.assign("./sellerItemView.html");
    var li = document.createElement("li");
    li.className = "list-group-item";
        let btn = document.createElement("button");
        btn.innerHTML = "View Item";
        btn.type = "submit";
        btn.className = "btn btn-primary ";
        btn.id = "AddButt";
        btn.setAttribute("onclick", "ViewItem(" + i +")");
        //btn.onclick = addToCart(i);
        btn.style = "width:130px";

        li.innerHTML = "<br>Name: <br>"  + Items[i].name + "<br>Cost: $" + Items[i].cost + "<br>Description: <br>" + Items[i].desc
        + "<br>Stock: " + Items[i].inventory + "<br>";

        mainContainer.appendChild(li);
        mainContainer.appendChild(btn);

}

function ChangeStock(){

}
