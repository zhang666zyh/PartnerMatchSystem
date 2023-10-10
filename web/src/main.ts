import {createApp} from 'vue'
import App from './App.vue'
import {
    NavBar, Icon, Tabbar, TabbarItem, Search,
    Divider, Tag, TreeSelect, Row, Col, Cell,
    Form, Field, Button, Card, Empty, Form, Field, CellGroup, DatetimePicker, Stepper,
    RadioGroup, Radio, Popup
} from 'vant'
import {router} from "./router/index.js"

const app = createApp(App)
app.use(NavBar).use(Icon).use(Tabbar)
    .use(TabbarItem).use(Search).use(Divider)
    .use(Tag).use(TreeSelect).use(Row).use(Col)
    .use(Cell)
    .use(Form).use(Field).use(CellGroup).use(Button).use(Card).use(Empty)
    .use(DatetimePicker).use(Stepper).use(RadioGroup).use(Radio).use(Popup)
app.use(router)


app.mount('#app')
