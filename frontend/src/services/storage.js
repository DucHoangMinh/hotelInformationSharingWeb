import {CookieStorage} from "cookie-storage";
let storage = new CookieStorage()
try{
    window.localStorage.setItem('_test', '1')
    storage = window.localStorage
    window.localStorage.removeItem('_test')
}catch (e){
    console.log("Error when trying use storage: ", e)
}
export const cookieStorage = new CookieStorage()
export default storage