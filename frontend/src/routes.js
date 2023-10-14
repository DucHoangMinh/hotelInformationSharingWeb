import LoginPage from "@/components/account/LoginPage.vue";
import HomePage from "@/components/WelcomePage.vue";
import RegisterPage from "@/components/account/RegisterPage.vue";
import { createRouter, createWebHistory } from 'vue-router'
const routes = [
    {path: '/', component: HomePage},
    {path: '/login', component: LoginPage},
    {path: '/register', component: RegisterPage},
];
const router = createRouter({
    history: createWebHistory(),
    routes: routes
})
export default router