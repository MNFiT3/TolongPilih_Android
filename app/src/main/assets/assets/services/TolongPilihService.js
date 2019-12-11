//let token = localStorage.getItem('token')
//if (token == null || token == undefined || token == '') {
//    window.location.href = 'login.html'
//}
var token, groupId, API_ENDPOINT, ENDPOINT, data

const onLoad = (groupId, token, api_endpoint, endpoint, callback) => {
 localStorage.setItem('token', token)
 API_ENDPOINT = api_endpoint
 ENDPOINT = endpoint
 this.groupId = groupId
 this.endpoint = endpoint
}


onLoad("66a6e2c4-bd4f-4589-b0bb-0049c34104dd",
'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJhODIwMjI3ZC1lOGI3LTQ1NjYtYmZhNi0wOTRmYTc0ZDRkZjEiLCJ1c2VybmFtZSI6IkFkbWluIiwiaWF0IjoxNTc2MDc0NTY2LCJleHAiOjE1NzYwNzgxNjZ9.fgqTbpLEcAJ-EFSoajwg5HvNruCfz0KWzMQ_yeLHmA8',
'http://localhost:3001/api',
'/tolongpilih/group/item/list')