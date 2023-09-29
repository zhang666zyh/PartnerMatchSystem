import {createApp} from 'vue'
import App from './App.vue'
import {
    NavBar, Icon, Tabbar, TabbarItem, Search,
    Divider, Tag, TreeSelect, Row, Col
} from 'vant'
import {router} from "./router/index.js"

const app = createApp(App)
app.use(NavBar).use(Icon).use(Tabbar)
    .use(TabbarItem).use(Search).use(Divider)
    .use(Tag).use(TreeSelect).use(Row).use(Col)
app.use(router)

app.mount('#app')
