import axios from "axios";
import {da} from "vuetify/locale";
const uri = "http://localhost:8080/api/"
const api = {
    get : async (url) => {
        const data = await axios.get(uri + url);
        return data.data;
    },
    post : async (url) => {
        const data = await axios.post(uri + url);
        return data.data;
    }
}
export default api