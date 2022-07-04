# 线上营销平台——前端工程

## 路由配置说明

为了方便讲项目发布在二级目录，在@plugins/config.js文件中定义了router，路由文件引用了这个变量，作为路由的base地址。
项目发布时，仍需手动修改vue.config.js文件中的publicPath属性，与二级目录保持一致<br />
config.js文件中还配置了代理，方便开发环境和生产环境的路由切换，开发环境的代理需手动修改vue.config.js。

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Run your tests
```
npm run test
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
