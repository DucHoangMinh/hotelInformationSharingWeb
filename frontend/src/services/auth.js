import storage from "@/services/storage";
import api from "@/services/api";
const auth = {
    login : async (loginRequest) => {
        let loginResponse = (await api.post("api/login", loginRequest));
        console.log(loginResponse)
        if(loginResponse.status === "ok"){
            storage.setItem('user_info', JSON.stringify(loginResponse.data.userDTO))
            storage.setItem('accesstoken',JSON.stringify(loginResponse.data.accessToken))
            location.href = "/home"
            return true;
        }
        else {
            return false;
        }
    }
}
export default auth