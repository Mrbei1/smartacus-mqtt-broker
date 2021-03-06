import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        component: resolve => require(['../components/common/Home.vue'], resolve),
        children:[
            {
                path: '',
                component:resolve => require(['../components/Connections/ConnectionsList.vue'], resolve),
            },
            {
                path: '/connections',
                component:resolve => require(['../components/Connections/ConnectionsList.vue'], resolve),
            },
            {
                path: '/sessions',
                component:resolve => require(['../components/Connections/ConnectionsList.vue'], resolve),
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
