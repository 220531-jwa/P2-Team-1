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
updateCartButton();
}

function appendItemData(resp){
    var mainContainer = document.getElementById('itemData');

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
        <tr><td><button class="btn btn-info" id="AddButt${i}" onclick="addToCart(${i})">Add to Cart</button></td>
        <td><button class="btn btn-primary" onclick="viewMore(${resp[i].id})">View More Info</button></td></tr>
        `;
        
        Items.push(resp[i]);
    }
    
}

function addToCart(id){
    totalCost = totalCost + Items[id].cost;
    totalCost = Math.round(totalCost * 100)/100;

    console.log(Items[id]);
    var mainContainer = document.getElementById('itemData');
    var li = document.createElement("li");

    Cartarr.push(Items[id]);
    li.innerHTML = Items[id].name;
    mainContainer.appendChild(li);
    console.log(totalCost);

    sessionStorage.setItem('cart', JSON.stringify(Cartarr));
    sessionStorage.setItem('total', JSON.stringify(totalCost));

    updateCartButton();
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
        btn.setAttribute("onclick", `removeFromCart(${x})`);
        btn.innerHTML = "Remove";
        btn.id = `RemoveButt${x}`;


        mainContainer.innerHTML += 
        `<tr id="cartData${x}">
        <th scope="row">${x+1}</th> 
        <td> ${Cartarr[x].name}</td>

        <td> $${Cartarr[x].cost}</td>

        <td> ${Cartarr[x].id}</td>
        </tr>`;


        mainContainer.appendChild(btn);
    }

    mainContainer.innerHTML += `<tr> <td> $${JSON.parse(sessionStorage.total)}</td></tr>`;

}

function removeFromCart(x){
    let cart = sessionStorage.getItem('cart');
    let Cartarr = JSON.parse(cart);
    console.log(Cartarr);
    console.log(x);
    let TotalCost = (JSON.parse(sessionStorage.total) - Cartarr[x].cost).toFixed(2);
    Cartarr.splice(x, 1);

    sessionStorage.setItem('cart', JSON.stringify(Cartarr));
    sessionStorage.setItem('total', JSON.stringify(TotalCost));
    window.location.assign("./CartListPage.html");
    updateCartButton();
}


async function checkOut(){
    console.log(activeUser);
    console.log("checkout button pressed");
    let totalCost = sessionStorage.getItem('total');
    let cart = sessionStorage.getItem('cart');
    let CartArr = JSON.parse(cart);
    console.log(totalCost);
    let uid = activeUser.id;
    console.log(uid);

    //get individual item ids 
    let itemIdArr = [];
    console.log("Getting item IDs...");
    for(i = 0; i < CartArr.length; i++){
        itemIdArr.push(CartArr[i].id);
    }
    console.log(itemIdArr);

    let request = {
        total: totalCost, 
        itemIds: itemIdArr
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
        sessionStorage.removeItem('cart');
        sessionStorage.removeItem('total');
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
            sessionStorage.setItem('activeItem', JSON.stringify(resp));
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
            
             let image = document.getElementById('coverImage');
             image.src = `./img/${resp.imglink}`;

        })
        .catch((error) => {
            console.log(error);
        });
}

function updateCartButton(){
    let cartbtn = document.getElementById('cartButton');
    cartbtn.innerText = `Cart (${JSON.parse(sessionStorage.cart).length} items $${sessionStorage.total})`;
}

