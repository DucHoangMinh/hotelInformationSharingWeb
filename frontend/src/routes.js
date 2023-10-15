import LoginPage from "@/components/account/LoginPage.vue";
import WelcomPage from "@/components/WelcomePage.vue";
import HomePage from "@/views/HomePage.vue";
import RegisterPage from "@/components/account/RegisterPage.vue";
import NotPermitted from "@/components/NotPermitted.vue";
import { createRouter, createWebHistory } from 'vue-router'
const routes = [
    {path: '/', component: WelcomPage},
    {path: '/notpermitted', component: NotPermitted},
    {path: '/home', component: HomePage},
    {path: '/login', component: LoginPage},
    {path: '/register', component: RegisterPage},
];
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})
export default router