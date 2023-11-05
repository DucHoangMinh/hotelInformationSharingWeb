import fixcorsapi from "@/services/fixcorsapi";
import storage from "@/services/storage";
let accesstoken = storage.getItem("accesstoken")
let api = {};
if(accesstoken == null){
    api = {get : async (url) => {
        const data = await fixcorsapi.get(url);
        return data.data;
    },
        post : async (url, body) => {
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
        post : async (url, body) => {
            console.log(accesstoken)
            const data = await fixcorsapi.post( url, body, axiosConfig);
            return data.data;
        }
    }
}
export default api