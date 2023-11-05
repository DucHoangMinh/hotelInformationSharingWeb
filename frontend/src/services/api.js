import axios from "axios";
const uri = "http://localhost:8080/api/"
axios.create({
    baseURL: 'http://localhost:8081'
})
const api = {
    get : async (url) => {
        const data = await axios.get(uri + url);
        return data.data;
    },
    post : async (url, body) => {
        const data = await axios.post(uri + url, body);
        return data.data;
    }
}
export default api