let baseUrl = "http://localhost:8080";
let totalCost = 0.00;
const Cartarr = [];

async function getItemData(){
    let res = await fetch(`${baseUrl}/items`);//the url where we're sending this request.

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

function appendItemData(data){
    var mainContainer = document.getElementById('ItemData');

    for(var i = 0; i < data.length; i++){
        var li = document.createElement("li"); //maybe add the class for bootstrap?
        li.className = "list-group-item";
        let btn = document.createElement("button");
        btn.innerHTML = "Add to Cart";
        btn.type = "submit";
        btn.className = "btn btn-primary";
        btn.onclick = addToCart(data[i]);

        li.innerHTML = "Name: \n" + data[i].name + "\nCost: $" + data[i].cost + "\nDescription: \n" + data[i].description
        + "\nStock: " + data[i].inventory + "\n" + btn + "\n";

        mainContainer.appendChild(li);

    }
}

function addToCart(data){
totalCost = totalCost + data.cost;
var item = [
data.name,

data.cost,

data.description,

data.inventory

];

Cartarr.push(item);


}

function populateCart(){
    var tr = document.createElement("tr");
    for(x = 0; x < Cartarr.length; x++){
        var th = document.createElement("th");
        var btn = document.createElement("button");
        btn.type = "submit";
        btn.className = "btn btn-primary";
        btn.onclick = removeFromCart(x);
        th.scope = "row";
        th.innerHTML = x+1;
        tr.appendChild(tr);
        var td = document.createElement("td");
        td.innerHTML = Cartarr[x].name;
        tr.appendChild("td");
        td.innerHTML = Cartarr[x].cost;
        tr.appendChild("td");
        td.innerHTML = Cartarr[x].description;
        tr.appendChild("td");
        td.innerHTML = Cartarr[x].inventory;
        tr.appendChild("td");
        td.innerHTML = btn;
        tr.appendChild("td");
    }
}

function removeFromCart(x){
    Cartarr.splice(x, 1);
    window.location.assign("CartListPage.html");
}