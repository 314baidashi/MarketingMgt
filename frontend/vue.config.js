const CompressionPlugin = require("compression-webpack-plugin")

module.exports = {
    //基本路径
    //publicPath: './',
    publicPath: '/marketing/login/',
    //输出文件目录
    outputDir: 'dist',
    assetsDir: 'assets',
    lintOnSave: false,

    chainWebpack: (config) => {
        config.resolve.symlinks(true);
    },
    // webpack配置
    /*

    transpileDependencies: [
		'_ant-design-vue@1.3.8@ant-design-vue',
		"ant-design-vue",//使用了antd
		"resize-detector",
    ],
   
    configureWebpack: (config) => {
    },*/
    configureWebpack: {
        //关闭 webpack 的性能提示
        performance: {
            hints: false
        }
    },
    configureWebpack: config => {
        //config.entry.app = ["babel-polyfill", "./src/main.js"];
        if (process.env.NODE_ENV === 'production') {
            return {
                plugins: [
                    new CompressionPlugin({
                        //匹配文件名
                        test: /\.js$|\.html$|.\css/,
                        //对超过10k的数据压缩
                        threshold: 10240,
                        //不删除源文件
                        deleteOriginalAssets: false
                    })
                ]
            }
        }

    },
    //生产环境是否生成sourceMap文件
    productionSourceMap: false,
    //css相关配置
    css: {
        //是否使用css分离插件ExtractTextPlugin
        extract: ['production', 'test'].includes(process.env.NODE_ENV),
        //开启CSS source maps?
        sourceMap: false,
        //css预设器配置项
        loaderOptions: {
            //这里的选项会传递给css-loader
            css: {},
            //这里的选项会传递给postcss-loader
            postcss: {}
        },
        //启用 CSS modules for all css / pre-processor files.
        modules: false
    },
    //是否为Babel或TypeScript使用thread-loader。该选项在系统的 CPU 有多于一个内核时自动启用，仅作用于生产构建。
    parallel: require('os').cpus().length > 1,
    //PWA 插件相关配置
    pwa: {},
    //webpack-dev-server相关配置
    devServer: {
        open: true,
        //允许外部ip访问
        host: '127.0.0.1',
        //端口
        port: 8091,
        //启用https
        https: false,
        hotOnly: false,
        hot: false,
        //错误、警告在页面弹出
        overlay: {
            warnings: true,
            errors: true
        },
        proxy: {
            '/marketing_proxy/': {
                target: 'http://localhost:8080',
                changeOrigin: true,
                pathRewrite: {
                    '^/marketing_proxy/': '/'
                }
            },

        }
    },
    //第三方插件配置
    pluginOptions: {}
}