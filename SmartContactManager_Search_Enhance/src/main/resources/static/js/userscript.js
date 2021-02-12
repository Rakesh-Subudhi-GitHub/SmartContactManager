console.log("this is script file");


/* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
function openNav() {
  document.getElementById("mySidebar").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
function closeNav() {
  document.getElementById("mySidebar").style.width = "0";
  document.getElementById("main").style.marginLeft = "0";
}

//alert("js is activated")

const search = () =>
{
  console.log("searching...");

  let query= $("#search-input").val();

  if(query=='')
  {
    $(".search-result").hide();
  }

  else{

    //search
    console.log(query);//console print

    //sending request to server

    let url=`http://localhost:8888/search/${query}`  //its not '' its this ( ` ) backtick

    fetch(url)
    .then((response) =>{
      return response.json();   //all data will search that come in json form and collect
    })
    .then((data) =>{
      
      //data result
      console.log(data);  //then all data will print in concole 
    
      //showing the result in html so convert the file as it is
      let text= `<div class= 'list-group' >`//backtick

      data.forEach((contact) => {        //data collect the all data of poticular contact  
                                                      //need one only name thats way take  here
        text += `<a href='/user/contact/${contact.cid}' class='list-group-item list-group-item-action'> ${contact.name} </a>`  //transfer to help of hreaf
      });

      text+=`</div>`

      $(".search-result").html(text);  //convert the html
      $(".search-result").show();   // then print the result  help of jquery
    });


    $(".search-result").show();
  }
};










