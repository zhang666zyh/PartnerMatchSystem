import * as VueRouter from "vue-router"

const routes = [
    {path: '/', component: () => import("../pages/Index.vue")},
    {path: '/team', component: () => import("../pages/team/Team.vue")},
    {path: '/teamAdd', component: () => import("../pages/team/TeamAdd.vue")},
    {path: '/teamUpdate', component: () => import("../pages/team/TeamUpdate.vue")},
    {path: '/user', component: () => import("../pages/user/User.vue")},
    {path: '/editUser', component: () => import("../pages/user/EditUser.vue")},
    {path: '/search', component: () => import("../pages/Search.vue")},
    {path: '/searchResult', component: () => import("../pages/SearchResult.vue")},
    {path: '/userLogin', component: () => import("../pages/UserLogin.vue")},
]

export const router = VueRouter.createRouter({
    history: VueRouter.createWebHashHistory(),
    routes,
})
