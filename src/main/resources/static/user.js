"use strict";

window.addEventListener('DOMContentLoaded', loadUserTable);

async function loadUserTable() {
    let tableBody = document.getElementById('tableUser');
    let page = await fetch("/api/auth");
    let currentUser;
    if (page.ok) {
        currentUser = await page.json();
    } else {
        alert(`Error, ${page.status}`)
    }
    let dataHtml = '';
    let roles = [];
    for (let role of currentUser.roles) {
        roles.push(" " + role.name)
    }
    dataHtml +=
        `<tr>
    <td>${currentUser.id}</td>
    <td>${currentUser.name}</td>
    <td>${currentUser.username}</td>
    <td>${roles}</td>
</tr>`
    tableBody.innerHTML = dataHtml;
}

loadUserTable();