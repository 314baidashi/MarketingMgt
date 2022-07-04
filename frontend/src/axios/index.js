// 封装ajax请求get 与post
// 使用promise对象异步请求
import axios from 'axios'
import { Message } from 'element-ui'
export default function ajax(json) {
    axios.interceptors.request.use(config => {
        config.headers.Authorization = window.sessionStorage.getItem('token')
            // 在最后必须 return config
        return config
    })
    var url = json.url;
    var data = json.data || {};
    var method = json.method || "get";
    // console.log(json)
    return new Promise(function(resolve, reject) {
        let promise;
        // console.log("URL:" + url)
        if (method === 'get') { // 准备 url query 参数数据 
            let dataStr = ''
                //数据拼接字符串
            Object.keys(data).forEach(key => { dataStr += key + '=' + data[key] + '&' })
            if (dataStr !== '') {
                dataStr = dataStr.substring(0, dataStr.lastIndexOf('&'))
                url = url + '?' + dataStr
            }
            // 发送 get 请求
            promise = axios.get(url)
        } else if (method == "post") {
            // 发送 post 请求
            promise = axios(json); // axios.post(url, data)
        } else if (method == "patch") {
            // 发送 post 请求
            promise = axios.patch(url, data)
        } else if (method == "put") {
            // 发送 post 请求
            promise = axios(json); //axios.put(url, data)
        } else if (method == "delete") {

            // 发送 post 请求
            promise = axios.delete(url, data)
        }
        promise.then(response => { resolve(response.data) })
            .catch(error => {
                console.log(error.response);
                if (error.response) {
                    if (error.response.data && error.response.data.errorType == "业务错误") {
                        Message({
                            message: error.response.data.message,
                            type: "warning",
                            duration: 3000,
                            showClose: false,
                            customClass: "el-message--error-custom",
                            dangerouslyUseHTMLString: true,
                            onClose: function() { return }

                        });

                    } else {
                        Message({
                            message: "错误：" + error.response.data.message,
                            type: "error",
                            duration: 3000,
                            showClose: false,
                            customClass: "el-message--error-custom",
                            dangerouslyUseHTMLString: true,
                            onClose: function() { return }

                        });
                    }
                    // this.$message({
                    //     type: "error",
                    //     message: error.response.data.message
                    // });
                } else {
                    Message({
                        message: "错误信息未捕捉",
                        type: "error",
                        duration: 3000,
                        showClose: false,
                        customClass: "el-message--error-custom",
                        dangerouslyUseHTMLString: true,
                        onClose: function() { return }
                    });
                }
                reject(error)
            })
    })
}