import * as VueRouter from "vue-router"

const routes = [
    {path: '/', component: () => import("../pages/Index.vue")},
    {path: '/team', component: () => import("../pages/Team.vue")},
    {path: '/user', component: () => import("../pages/User.vue")},
    {path: '/editUser', component: () => import("../pages/EditUser.vue")},
    {path: '/search', component: () => import("../pages/Search.vue")},
    {path: '/searchResult', component: () => import("../pages/SearchResult.vue")},
]

export const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes,
})
