/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let resultPanel1 = {
    displayCounter: 0,
    hideCounter: 1,
    isClicked: false,
    hrefClicked: false,
    default: true,
    panel: document.getElementById("result-1").getElementsByClassName("result"),
    extend: document.getElementById("result-1").getElementsByClassName("result-extended")
};

function displayInfo1() {
    resultPanel1.displayCounter = 0;
    resultPanel1.hideCounter = 1;
    resultPanel1.hrefClicked = true;
}

function displayPrice1() {
    resultPanel1.displayCounter = 1;
    resultPanel1.hideCounter = 0;
    resultPanel1.hrefClicked = true;
}


function clickedResult1() {
    if (resultPanel1.default) {
        resultPanel1.displayCounter = 0;
    }
    resultPanel1.default = false;
    
    if (!resultPanel1.isClicked) {
        resultPanel1.isClicked = true;
    }
    else {
        resultPanel1.isClicked = false;
    }
}


function hidePanel() {
    for (var i = 0; i < resultPanel1.extend.length; i++) {
        resultPanel1.extend[i].style.display = "none";
    }
}

function extendResult1() {
    if (resultPanel1.isClicked) {
        if (resultPanel1.hrefClicked) {
            resultPanel1.extend[resultPanel1.displayCounter].style.display = "block";
            resultPanel1.extend[resultPanel1.hideCounter].style.display = "none";
            resultPanel1.panel[0].style.borderRadius = "10px 10px 0 0";
            resultPanel1.hrefClicked = false;
            resultPanel1.isClicked = true;
        }
        else {
            resultPanel1.extend[resultPanel1.displayCounter].style.display = "block";
            resultPanel1.panel[0].style.borderRadius = "10px 10px 0 0";
        }
    }
    else {
        if (resultPanel1.hrefClicked) {
            resultPanel1.extend[resultPanel1.displayCounter].style.display = "block";
            resultPanel1.extend[resultPanel1.hideCounter].style.display = "none";
            resultPanel1.panel[0].style.borderRadius = "10px 10px 0 0";
            resultPanel1.hrefClicked = false;
            resultPanel1.isClicked = true;
        }
        else {
            resultPanel1.extend[resultPanel1.displayCounter].style.display = "none";
            resultPanel1.panel[0].style.borderRadius = "10px 10px 10px 10px";
        }
    }
}