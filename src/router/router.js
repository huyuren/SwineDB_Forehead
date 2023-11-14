import { createRouter, createWebHashHistory } from 'vue-router'
import Menu from "../components/Menu.vue";
import Data from "../components/Data.vue"
import website from "../views/website.vue"
import Tools from "../views/Tools.vue"
import Browse from '../views/Browse.vue'
import Search from '../views/Search.vue'
import direction from "../views/direction.vue"
import Team from "../components/Team.vue"
import We from "../views/We.vue"
import Help from "../views/help.vue"
import Pie from "../components/Pie.vue"
import TriDBar from '../components/TriDBar.vue'
import Radial from "../components/Radial.vue"
import NotFound from "../views/not-found.vue"
const routes = [
    {
        name: "Login",
        path: "/",
        redirect:'/website',
        component: ()=>import('../views/website.vue'),
    },
    {name:"Website",
     path:'/website',
     component:website,
    },
    {name:"Browse",
    path:'/Browse',
    component:Browse,

    },
    {name:"Search",
    path:'/Search',
    component:Search,

    },
    {name:"Tools",
    path:'/Tools',
    component:Tools,

    },
    {
        name:"direction",
        path:'/direction',
        component:direction,

    },
    {
        name:"Team",
        path:'/team',
        component:Team,

    },
    {
        name:"We",
        path:'/we',
        component:We,

    },
    // {
    //     name: "Microbe",
    //     path: 'data/microbe',
    //     component: Data,
    // },
    {
        name: "Menu",
        path: '/menu',
        component: Menu,
        children: [

            {
                name: "Microbe",
                path: 'data/Microbe',
                component: Data,
            },
            {
                name: "Metabolism",
                path: 'data/metabolism',
                component: Data,
            },
            {
                name: "Feed",
                path: 'data/feed',
                component: Data,
            },
            {
                name: "Swine",
                path: 'data/swine',
                component: Data,
            },
            {
                name: "Microbial",
                path: 'data/microbial',
                component: Data,
            },
            {
                name: "Samples",
                path: 'data/samples',
                component: Data,
            },

        ]
    },
    {
        name: "Pie",
        path: '/pie',
        component: Pie,
    },
    {
        name: "TriDBar",
        path: '/triDBar',
        component: TriDBar,
    },
    {
        name:"Radial",
        path:'/radial',
        component: Radial,
    },
    {
        name: "Help",
        path: "/help",
        component: ()=>import('../views/help.vue'),
    },
    // 404页面需要放在最后！
    { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
];

const router = createRouter({
    // hash模式
    history: createWebHashHistory(),
    // routes: routes
    routes,
});

export default router

/*
path：字符串，表示路由的路径。
name：字符串，表示路由的名称。
component：组件类型，表示路由所匹配的组件。
children：子路由配置数组，用于描述嵌套路由。
meta：对象，用于存储额外的路由元数据，例如需要验证用户权限的信息。
*/

