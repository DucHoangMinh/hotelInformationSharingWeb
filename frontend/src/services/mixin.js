import store from "@/store";
import storage from "@/services/storage";

const mixin = {
    formatDateToYYYYMMDD: (date) => {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');

        return `${year}-${month}-${day}`;
    },
    formatLinkLists: (string) => {
        let result = string.split('"')
        result.forEach((item, index) => {
            if(item.length <= 1) result.splice(index,1)
        })
        return result
    },
    showSuccessMessage: (message) => {
        store.dispatch('showSnack', {
            message: message,
            color: 'green'
        })
    },
    showWarningMessage: (message) => {
        store.dispatch('showSnack', {
            message: message,
            color: 'yellow'
        })
    },
    showErrorMessage: (message) => {
        store.dispatch('showSnack', {
            message: message,
            color: 'red'
        })
    },
    password: {
        validate: (password) => {
            return password.match(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"/)
        }
    },
    logout: async () => {
            await storage.removeItem("accesstoken");
            await storage.removeItem("user_info")
            location.href = "/"
    }
}
export default mixin