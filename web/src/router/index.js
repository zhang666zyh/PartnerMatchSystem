import * as VueRouter from "vue-router"

const routes = [
    {path: '/', component: () => import("../pages/Index.vue")},
    {path: '/team', component: () => import("../pages/Team.vue")},
    {path: '/user', component: () => import("../pages/User.vue")},
    {path: '/search', component: () => import("../pages/Search.vue")},
]

export const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes,
})
