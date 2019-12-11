//let token = localStorage.getItem('token')
//if (token == null || token == undefined || token == '') {
//    window.location.href = 'login.html'
//}

const onLoad = (groupId, token) => {
    document.getElementById('test').innerHTML = groupId + " " + token
}
