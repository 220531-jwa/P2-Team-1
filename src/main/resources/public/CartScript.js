let baseUrl = "http://localhost:8081";
let totalCost = 0.00;
let Cartarr = [];
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
        btn.id = `AddButt${i}`;
        btn.setAttribute("onclick", "addToCart(" + i /*+ ", '" + resp[i].name + "', " + resp[i].cost */ +")");

        let sibtn = document.createElement("button");
        sibtn.innerHTML = "View More";
        sibtn.type = "button";
        sibtn.className = "btn btn-success";
        sibtn.id = `viewMore${i}`;
        sibtn.setAttribute("onclick", "viewMore(" + i  +")");
        sibtn.style = "width:130px";


        btn.style = "width:130px";
        Items.push(resp[i]);

        li.innerHTML = "<br>Name: <br>"  + resp[i].name + "<br>Cost: $" + resp[i].cost + "<br>Description: <br>" + resp[i].desc
        + "<br>";

        mainContainer.appendChild(li);
        mainContainer.appendChild(btn);
        mainContainer.appendChild(sibtn);

    }
    
}

function addToCart(id){
    totalCost = totalCost + Items[id].cost;


    console.log(Items[id]);
    var mainContainer = document.getElementById('itemData');
    var li = document.createElement("li");

    Cartarr.push(Items[id]);
    li.innerHTML = Items[id].name;
    mainContainer.appendChild(li);
    console.log(totalCost);

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
    console.log(TotalCost);


    var tr = document.createElement("tr");
    if(Cartarr == null){
        return;
    }

    for(x = 0; x < Cartarr.length; x++){
        console.log(Cartarr[x]);

        var btn = document.createElement("button");
        btn.type = "submit";
        btn.className = "btn btn-primary";
        btn.setAttribute("onclick", "removeFromCart(" + x + /*", "+ Cartarr + */", "+ TotalCost + ")");
        btn.innerHTML = "Remove";
        btn.id = `RemoveButt${x}`;


        mainContainer.innerHTML += 
        `<tr id="cartData">
        <th scope="row">${x+1}</th> 
        <td> ${Cartarr[x].name}</td>

        <td> $${Cartarr[x].cost}</td>

        <td> ${Cartarr[x].id}</td>
        </tr>`;


        mainContainer.appendChild(btn);
    }
    mainContainer.innerHTML += `<tr> <td> $${TotalCost}</td></tr>`;
    console.log(mainContainer.innerHTML);
}

function removeFromCart(x, TotalCost){
    let cart = sessionStorage.getItem('cart');
    let Cartarr = JSON.parse(cart);
    console.log(Cartarr);
    console.log(x);
    Cartarr.splice(x, 1);
    TotalCost = TotalCost.toFixed(2);
    sessionStorage.setItem('cart', JSON.stringify(Cartarr));
    sessionStorage.setItem('total', JSON.stringify(TotalCost));
    window.location.assign("./CartListPage.html");
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

function viewMore(i){
    
    sessionStorage.setItem('activeItemId', i);
    toSingleItem();
}

async function getItemInformation(){

    let activeItemId = sessionStorage.activeItemId;
    console.log(activeItemId);

    let res = await fetch(
        `${baseURL}item/${activeItemId}`,
        {
            method: 'GET'
        }
    );

    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            sessionStorage.addItem('activeItem', JSON.stringify(resp));
            var tbody = document.getElementById('itemInfoTable');
            tbody.innerHTML =
            `
            <tr>
                <th>Name:</th>
                <td>${resp.name}</td>
            </tr>
            <tr>
                <th>ID:</th>
                <td>${resp.id}</td>
            </tr>
            <tr>
                <th>Description:</th>
                <td>${resp.desc}</td>
            </tr>
            <tr>
                <th>Price:</th>
                <td>${resp.cost}</td>
            </tr>
            <tr>
                <th>In Stock:</th>
                <td>${resp.inventory}</td>
            </tr>
            `;

        })
        .catch((error) => {
            console.log(error);
        });
}

    

