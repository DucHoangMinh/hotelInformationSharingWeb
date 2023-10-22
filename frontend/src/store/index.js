import {createApp} from "vue"
import {createStore} from "vuex"
import App from "@/App.vue";
const store = createStore({
    state(){
        return{
            snackbar: { active: false, color: "", message: "", timer: 4000 }
        }
    },
    mutations: {
        UPDATE_SNACKBAR(state, snackbar) {
            state.snackbar = snackbar;
        }
    },
    actions: {
        showSnack({ commit }, { message, color, timer = 4000 }) {
            commit("UPDATE_SNACKBAR", {
                active: true,
                color: color,
                message: message,
                timer: timer
            });
        }
    }
})
const app = createApp(App)
app.use(store)
export default store
