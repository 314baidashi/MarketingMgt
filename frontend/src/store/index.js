/*
 * @Author: 原建顺 yjsh824@163.com
 * @Date: 2022-05-30 06:37:04
 * @LastEditors: 原建顺 yjsh824@163.com
 * @LastEditTime: 2022-05-30 07:05:05
 * @FilePath: \frontend\src\store\index.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%A
 */
import Vue from 'vue'
import Vuex from 'vuex'
import VueXAlong from 'vuex-along'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        title: ""
    },
    getters: {
        getTitle(state) {
            return state.title;
        }
    },
    plugins: [VueXAlong({
        name: 'along', //存放在localStroage或者sessionStroage 中的名字
        local: false, //是否存放在local中  false 不存放 如果存放按照下面session的配置配
        session: { list: [], isFilter: true } //如果值不为false 那么可以传递对象 其中 当isFilter设置为true时， list 数组中的值就会被过滤调,这些值不会存放在seesion或者local中
    })],
    mutations: {
        setTitle(state, r) {
            state.title = r;
        }
    },
    actions: {},
    modules: {}
})