let activeUser = JSON.parse(sessionStorage.activeUser);
let itemId = JSON.parse(sessionStorage.currentItemId);
let itemData;

console.log(itemId);

async function editItem(){
    
    let newName = document.getElementById('newName');
    let newDesc = document.getElementById('newDesc');
    let newCost = document.getElementById('newCost');
    let newInven = document.getElementById('newInven');
    let newLink = document.getElementById('newLink');


    itemData.name = newName.value;
    itemData.desc = newDesc.value;
    itemData.cost = newCost.value;
    itemData.inventory = newInven.value;
    itemData.imglink = newLink.value;
    

    console.log(`The altered item values are now: ${itemData}`);

    itemJSON = JSON.stringify(itemData);

    let res = await fetch(
        `${baseURL}seller/${activeUser.id}/items/${itemData.id}`,
        {
            method: 'PUT',
            header: {'Content-Type': 'application/json'},
            body: itemJSON
        });
    
        let resJson = await res.json()
            .then((resp) => {
                console.log(resp);
                sessionStorage.setItem('currentItem', JSON.stringify(resp));
                sessionStorage.setItem('currentItemId', resp.id);
                pullItem();
                window.alert("Update Successful!");
            })
            .catch((error) =>  {console.log(error)})
}

function openTable(){
    let tbl = document.getElementById('editsTable');
    if(tbl.hasAttribute('hidden')){
        tbl.removeAttribute('hidden');
        newName.value = itemData.name;
        newDesc.value = itemData.desc;
        newCost.value = itemData.cost;
        newInven.value = itemData.inventory;
        newLink.value = itemData.imglink;
    }
    else{
        tbl.setAttribute('hidden', 'hidden');
    }
}

async function pullItem(){
    itemId = JSON.parse(sessionStorage.currentItemId);
    var tbody = document.getElementById('itemTable');

    let res = await fetch(`${baseURL}item/${itemId}`);

        let resp = await res.json()
        .then((resp) => {
            itemData = resp;
        })

    tbody.innerHTML =
    `
        <tr><th>Name:</th><td>${itemData.name}</td></tr>
        <tr><th>Description:</th><td>${itemData.desc}</td></tr>
        <tr><th>Cost:</th><td id="currentCost">${itemData.cost}</td></tr>
        <tr><th>In Stock:</th><td>${itemData.inventory}</td></tr>
    `

    let image = document.getElementById('coverImage');
             image.src = `./img/${itemData.imglink}`;
}

function deleteItem(){
    document.getElementById('deleteConfirm').removeAttribute('hidden');
}

async function trueDelete(){
    let itemId = itemData.id;
    let activeUser = JSON.parse(sessionStorage.activeUser);

    console.log(itemId);

    let res = fetch(
        `${baseURL}seller/${activeUser.id}/items/${itemId}`,
        {
            method: 'DELETE'
        }
    )
    toSellerInventory();
}

function hideBox(){
    document.getElementById('deleteConfirm').setAttribute('hidden', 'hidden');
}