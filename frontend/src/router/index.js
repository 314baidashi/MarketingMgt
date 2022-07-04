/*
 * @Author: 原建顺 yjsh824@163.com
 * @Date: 2022-05-30 06:37:04
 * @LastEditors: 原建顺 yjsh824@163.com
 * @LastEditTime: 2022-05-30 07:17:33
 * @FilePath: \frontend\src\router\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import Vue from 'vue'
import VueRouter from 'vue-router'
import myConfig from "../plugins/config"

Vue.use(VueRouter)

const routes = [

    {
        path: "/404",
        name: "notFound",
        component: () =>
            import ("../views/Error404.vue")
    },
    {
        path: '/',
        name: 'home',
        component: resolve => require(['../views/Welcome.vue'], resolve),
        meta: { title: "首页" }
    },
    {
        path: '/about',
        name: 'about',
        component: function() {
            return import ( /* webpackChunkName: "about" */ '../views/About.vue')
        }
    },
    {
        path: '/user/index',
        name: 'userIndex',
        component: resolve => require(['../views/user/index.vue'], resolve),
        meta: { title: "用户管理" }
    },
    {
        path: '/user/role',
        name: 'userRole',
        component: resolve => require(['../views/user/role.vue'], resolve),
        meta: { title: "角色管理" }
    },
    {
        path: '/stores/index',
        name: 'storesIndex',
        component: resolve => require(['../views/stores/index.vue'], resolve),
        meta: { title: "库存管理" }
    },
    {
        path: '/login',
        name: 'login',
        component: resolve => require(['../views/login.vue'], resolve),
    },
    {
        path: '/resetpwd',
        name: 'resetpwd',
        component: resolve => require(['../views/resetpwd.vue'], resolve),
    },
    {
        path: '/batch/index',
        name: 'batchIndex',
        component: resolve => require(['../views/batch/index.vue'], resolve),
        meta: { title: "进货管理" }
    },
    {
        path: '/batch/addBatch',
        name: 'addBatch',
        component: resolve => require(['../views/batch/addBatch.vue'], resolve),
        meta: { title: "新增进货单" }
    },
    {
        path: '/batch/detailBatch',
        name: 'detailBatch',
        component: resolve => require(['../views/batch/detailBatch.vue'], resolve),
        meta: { title: "进货单详情" }
    },
    {
        path: '/client/index',
        name: 'clientIndex',
        component: resolve => require(['../views/client/index.vue'], resolve),
        meta: { title: "客户管理" }
    },
    {
        path: '/product/index',
        name: 'productIndex',
        component: resolve => require(['../views/product/index.vue'], resolve),
        meta: { title: "产品管理" }
    },
    {
        path: '/product/sort',
        name: 'sort',
        component: resolve => require(['../views/product/sort.vue'], resolve),
        meta: { title: "分类管理" }
    },
    {
        path: "*", // 此处需特别注意置于最底部
        redirect: "/404"
    }
]

/**
 * 重写路由的push方法
 */
const routerPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return routerPush.call(this, location).catch(error => error)
}
const router = new VueRouter({
    mode: "history",
    base: myConfig.router(),
    //base:"/marketingweb/",
    routes: routes
})

router.afterEach((to, from) => {
    if (to.meta.title) {
        router.app.$store.commit("setTitle", to.meta.title);
    } else {
        router.app.$store.commit("setTitle", "");
    }
})

// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
    if (to.path === '/login' || to.path === '/resetpwd') {
        next();
    } else {
        let token = sessionStorage.getItem('token');

        if (token === null || token === '') {
            next('/login');
        } else {
            next();
        }
    }
});
export default router