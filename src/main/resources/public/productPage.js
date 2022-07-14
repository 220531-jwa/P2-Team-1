async function getItems(){

    let res = await fetch(
        `${baseUrl}item`,
        {
            method: 'GET',
        }
    );

    let resJson = await res.json()
        .then((resp) => {
            console.log(resp);
            var tbody = document.getElementById("productsTable");

            for(var i = 0; i < resp.length; i++){
                tbody.innerHTML +=
                `
                    <tr>
                        <td>${resp[i].id}</td>
                        <td>${resp[i].name}</td>
                        <td>${resp[i].cost}</td>
                        <td>${resp[i].desc}</td>
                        <td>${resp[i].inventory}</td>
                    </tr>
                `
            }
        })
        .catch((error) => {console.log(error)})

}