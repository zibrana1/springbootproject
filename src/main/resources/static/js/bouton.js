
    const btninfo = document.getElementById("voirinfo");
    const info = document.getElementById("info");
    btninfo.addEventListener("click" , function(event){
        if (info.getAttribute("class") === "hidden") {
            info.style.display = "block"
            return info.setAttribute("class", "visible");
        } else if(info.getAttribute("class") === "visible") {
            info.style.display = "none";
            return info.setAttribute("class", "hidden");
        }
    });
