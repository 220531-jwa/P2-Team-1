let baseUrl = "http://localhost:8081";
let totalCost = 0.00;
const Cartarr = [];
const Items = [];
let activeUser = JSON.parse(sessionStorage.activeUser);


async function getItemData(){
    let res = await fetch(`${baseUrl}/item`);//the url where we're sending this request.

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
        btn.innerHTML = "Add to Cart";
        btn.type = "submit";
        btn.className = "btn btn-primary ";
        btn.setAttribute("onclick", "addToCart(" + i /*+ ", '" + resp[i].name + "', " + resp[i].cost */ +")");
        //btn.onclick = addToCart(i);
        btn.style = "width:130px";
        Items.push(resp[i]);

        li.innerHTML = "<br>Name: <br>"  + resp[i].name + "<br>Cost: $" + resp[i].cost + "<br>Description: <br>" + resp[i].desc
        + "<br>";

        mainContainer.appendChild(li);
        mainContainer.appendChild(btn);

    }
    
}

function addToCart(id, /*name, cost*/){
  //  var totalContainer = document.getElementById("item-container");
totalCost = totalCost + Items[id].cost;
//totalCost = totalCost + resp.cost;
//var item = [
//id,

//name,

//cost

//];


console.log(Items[id]);
var mainContainer = document.getElementById('itemData');
var li = document.createElement("li");

Cartarr.push(Items[id]);
li.innerHTML = Items[id].name;
mainContainer.appendChild(li);

sessionStorage.setItem('cart', JSON.stringify(Cartarr));
sessionStorage.setItem('total', JSON.stringify(totalCost));


}



function populateCart(){
    let cart = sessionStorage.getItem('cart');
    let Cartarr = JSON.parse(cart);
    var mainContainer = document.getElementById("CartBody");
    console.log(Cartarr);
    let total = sessionStorage.getItem('total');
    let TotalCost = JSON.parse(total);


    var tr = document.createElement("tr");
    for(x = 0; x < Cartarr.length; x++){
        console.log(Cartarr[x]);
       // var th = document.createElement("th");
        var btn = document.createElement("button");
        btn.type = "submit";
        btn.className = "btn btn-primary";
        btn.setAttribute("onclick", "removeFromCart(" + x + ", "+ Cartarr + ", "+ TotalCost + ")");
        btn.innerHTML = "Remove";
      //  btn.onclick = removeFromCart(x);
     //   th.scope = "row";
      //  th.innerHTML = x+1;
    //    tr.appendChild(th);
    //    var td = document.createElement("td");
 //       td.innerHTML = Cartarr[x].name;
    //    tr.appendChild("td");
 //       td.innerHTML = Cartarr[x].cost;
  //      tr.appendChild("td");
   //     td.innerHTML = Cartarr[x].id;
  //      tr.appendChild("td");

        mainContainer.innerHTML += 
        `<tr>
        <th scope="row">${x+1}</th> 
        <td> ${Cartarr[x].name}</td>

        <td> ${Cartarr[x].cost}</td>

        <td> ${Cartarr[x].id}</td>
        </tr>` 

        //mainContainer.appendChild("tr");
        mainContainer.appendChild(btn);
    }
    mainContainer.innerHTML += `<tr> <td> ${TotalCost}</td></tr>`
}

function removeFromCart(x, Cartarr, TotalCost){
    Cartarr.splice(x, 0);
    TotalCost = TotalCost - Cartarr[x].cost;
    window.location.assign("CartListPage.html");
}

function returnTotal(){
    return totalCost;
}

async function checkOut(){
    console.log(activeUser);
    console.log("checkout button pressed");
    let totalCost = sessionStorage.getItem('total');
    console.log(totalCost);
    let uid = activeUser.id;
    console.log(uid);
    let request = {
        total: totalCost
    }

    let requestJson = JSON.stringify(request);
    console.log(requestJson);

    let res = await fetch(
        `${baseUrl}/user/${uid}/checkout`,
        {
            method : 'PATCH',
            header : {'Content-Type': 'application/json'},
            body : requestJson
        }
    );
    
    let resJson = await res.json()
    .then((resp) =>{
        console.log(resp);
        Cartarr.length = 0; //empty cart 
        window.alert("You have successfully checked out!");
    })
    .catch((error) =>{
        console.log(error);
        window.alert("You have not checked out. Make sure your account balance is sufficient and you have at least one item in your cart!")
    });
}




