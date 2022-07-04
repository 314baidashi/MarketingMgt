export default {
    proxy: () => {
        if ("development" == process.env.NODE_ENV)
            return "/marketing_proxy";
        else
            return "/proxy_marketing";
    },
    router: () => {
        //修改根路由，要同时修改vue.config.js的publicPath属性
        return "/marketing/";
    }
};