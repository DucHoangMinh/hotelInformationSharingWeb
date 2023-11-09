import fixcorsapi from "@/services/fixcorsapi";
import storage from "@/services/storage";
let accesstoken = storage.getItem("accesstoken")
let api = {};
if(accesstoken == null){
    api = {get : async (url) => {
        console.log('Get no token')
        const data = await fixcorsapi.get(url);
        return data.data;
    },
        post : async (url, body) => {
        console.log("Get no token")
        console.log(accesstoken)
        const data = await fixcorsapi.post(url, body);
        return data.data;
    }}
}
else {
    accesstoken = accesstoken.replace(/"/g, '')
    const axiosConfig = {
        headers: {
            'Authorization': `Bearer ${accesstoken}`
        }
    }
    api = {
        get : async (url) => {
            console.log('Get with token')
            let data = await fixcorsapi.get( url, {
                headers: {
                    'Authorization': `Bearer ${accesstoken}`
                }
            });
            return data.data;
        },
        post : async (url, body) => {
            let data = await fixcorsapi.post( url, body, axiosConfig);
            return data.data;
        }
    }
}
export default api