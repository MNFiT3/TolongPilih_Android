class ServerHandlerService {

    httpGet = (url, callback) => {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
                try{
                    //setToken(this)
                    callback(null, JSON.parse(this));
                }catch{
                    callback(null, this);
                }
            }else{
                if(this.status == 401){
                    
                }
            }
        };
        xmlhttp.open("GET", API_ENDPOINT + url, true);
        xmlhttp.setRequestHeader('auth', getToken())
        xmlhttp.send();
    }

    httpPost = (url, json, callback) => {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
                // console.log(this.response)
                // try{
                //     //setToken(this)
                //     callback(null, JSON.parse(this));
                // }catch{
                //     callback(null, this);
                // }
                callback(null, this)
            }else{
                if(this.status == 401){
                    
                }
            }
        };
        xmlhttp.open("POST", API_ENDPOINT + url, true);
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.setRequestHeader('auth', getToken())
        xmlhttp.send(JSON.stringify(json));
    }
}

const setToken = (response) => {
    let token = response.getResponseHeader('token')
    if(token != null || token != undefined){
        localStorage.setItem('token', token)
    }
}

const getToken = () => {
    let token = localStorage.getItem('token')
    if (token != null || token != undefined || token == ''){
        return token
    }
    return null
}

const jsonForm = (formData) => {
    let json = {};
    formData.forEach((item) => {
        json[item.name] = item.value;
    });
    return json;
}

const serv = new ServerHandlerService();