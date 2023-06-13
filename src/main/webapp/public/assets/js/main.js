const burger = document.querySelector('.burger');
const sidebar = document.querySelector('.sidebar');
const bgSidebar = document.querySelector('.bg-sidebar');

burger.addEventListener('click', function() {
    this.classList.toggle('change');
    sidebar.classList.toggle('change');
    bgSidebar.classList.toggle('change');
});

bgSidebar.addEventListener('click', function() {
    this.classList.remove('change');
    sidebar.classList.remove('change');
    burger.classList.remove('change');
});

// not logged-in
const navLogin = document.getElementById("trigger-login");
const loginPanel = document.getElementById("login-panel");
// logged-in
const navUser = document.getElementById("trigger-user");
const userPanel = document.getElementById("user-panel");

let focusedElement = null;

const focusElement = (element) => {
    if (focusedElement) focusedElement.classList.remove('focussed');
    focusedElement = element;
    focusedElement.classList.add('focussed');
}

const unfocusElement = () => {
    if (!focusedElement) return;
    focusedElement.classList.remove('focussed');
    focusedElement = null;
    hideContainers();
}

document.addEventListener("click", (evt) => {
    if (!focusedElement) return;
    let targetEl = evt.target; // clicked element
    do {
        if(targetEl === focusedElement) {
            return;
        }
        // Go up the DOM
        targetEl = targetEl.parentNode;
    } while (targetEl);
    // This is a click outside.
    unfocusElement();
});

document.querySelectorAll('.focusable').forEach(value => {
    value.addEventListener("click", (ev) => {
        ev.stopPropagation();
        focusElement(value);
    });
});

navUser.addEventListener('click', (ev) => {
    ev.stopPropagation();
    focusElement(userPanel);
});

console.log("A");

document.getElementById("nav-login").addEventListener('click', (ev) => {
    ev.stopPropagation();
    console.log("B");
    focusElement(loginPanel);
});