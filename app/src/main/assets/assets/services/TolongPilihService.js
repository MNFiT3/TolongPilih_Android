//let token = localStorage.getItem('token')
//if (token == null || token == undefined || token == '') {
//    window.location.href = 'login.html'
//}
var token, groupId, API_ENDPOINT , data

const onLoad = (groupId, token, api_endpoint, callback) => {
 localStorage.setItem('token', token)
 API_ENDPOINT = api_endpoint
 this.groupId = groupId
}