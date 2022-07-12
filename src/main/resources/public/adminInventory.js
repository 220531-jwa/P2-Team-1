async function getAdminData(){
    let res = await fetch(`${baseUrl}/item`);//the url where we're sending this request.

if(res.status == 200){
    let data = await res.json()

    .then((resp) => {
        appendAdminData(resp);
         
    })

    .catch((error) =>{
        console.log(error);
    });
} else{
    console.log("It got away!");
}
}

function appendAdminData(resp){
    var mainContainer = document.getElementById('adminData');
    for(var i = 0; i < resp.length; i++){
        var li = document.createElement("li"); //maybe add the class for bootstrap?
        li.className = "list-group-item";
        let btn = document.createElement("button");
        btn.innerHTML = "Delete";
        btn.type = "submit";
        btn.className = "btn btn-primary ";
        btn.id = "AddButt";
        btn.setAttribute("onclick", "deleteItem(" + i +")");
        //btn.onclick = addToCart(i);
        btn.style = "width:130px";
        Items.push(resp[i]);

        li.innerHTML = "<br>Name: <br>"  + resp[i].name + "<br>Cost: $" + resp[i].cost + "<br>Description: <br>" + resp[i].desc
        + "<br>";

        mainContainer.appendChild(li);
        mainContainer.appendChild(btn);

    }
    
}

function deleteItem(id){
    //will be a PATCH request
    let res = await fetch(
        `${baseUrl}/item/${id}`, //the url where we're sending this request.
        {
            method: 'DELETE',
            header: {'Content-Type': 'application/json'},
            body: userJson
        }
    );
}