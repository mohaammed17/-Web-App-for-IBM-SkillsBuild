const input = document.getElementById('search');
const button = document.getElementById('search-input');
const error = document.getElementById('search-error');

button.addEventListener('click', enter);

const node = document.getElementsByClassName("input1")[0];
input.addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        enter();
    }
});

function enter () {
    const value = input.value;
    if (value === '') {
        error.innerHTML = 'Please enter a name';
        error.style.visibility = 'visible';
    } else {
        window.location = `/findFriend?name=${value}`;
    }
}