import Vue from 'vue'
import VueRouter from 'vue-router'
// import Main from '../views/Main.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Home',
        component: resolve => require(['../components/common/Home.vue'], resolve),
        children:[
            {
                path: '/categories/create',component:CategoryEdit
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
