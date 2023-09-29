import {createApp} from 'vue'
import App from './App.vue'
import {NavBar, Icon, Tabbar, TabbarItem} from 'vant'

const app = createApp(App)
app.use(NavBar).use(Icon).use(Tabbar).use(TabbarItem)

app.mount('#app')
