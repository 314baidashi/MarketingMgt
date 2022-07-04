<template>
  <div>
    <div id="main">
      <img class="bg-img" src="../assets/images/login.png"/>
      <div id="login-input">
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">欢迎登录</h3>
      <el-form-item label= "账号" prop="username">
        <el-input style="margin-left: 20px; width: 400px;" type="text" placeholder="请输入账号" v-model="form.username"/>
      </el-form-item>
      <el-form-item label= "密码" prop="password">
      <el-input style="margin-left: 20px; width: 400px;" type="password" placeholder= "请输入密码" v-model="form.password"/>
      </el-form-item>
      <el-form-item>
        <el-button style="width:200px; margin-left:20px;" type="primary" @click="onSubmit('loginForm')">登录</el-button>
        <el-button type="text" @click="() => $router.push({path: '/resetpwd'})">忘记密码？点击这里修改密码</el-button>
      </el-form-item>
      </el-form>
    </div>
  </div>
  </div>
</template>

<script>
  import { Loading } from "element-ui";
  export default {
    name: "Login",
    data() {
      return {
        form: {
          username: '' ,
          password: '',
        },
        user:{},
        //表单验证，需要在el-form-item 元素中增加prop 属性
        rules: {
          username: [
              {required: true, message:  "账号不可为空", trigger: blur}
          ],
          password: [
            {required: true, message:  "密码不可为空" , trigger: blur}
          ]
        },
      }
    },
  methods: {
      onSubmit(formName) {
        //为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let loading = Loading.service({
              text: "登陆中....",
              background: "rgba(0, 0, 0, 0.1)"
            });
            //使用vue-router路由到指定页面，该方式称之为编程式导航
            this.$axios({
              method: "post",
              data: this.form,
              url: this.$config.proxy() + "/login"
              // url: "http://110.40.152.26:8080/login"
            })
              .then(resp => {
                loading.close();
                  if(resp.code == 200){
                    this.$message.success('登陆成功！');
                    sessionStorage.setItem('token', resp.data)  //在session中设置token
                    this.$axios({  //拿到token后请求用户信息
                      method: "post",
                      url: this.$config.proxy() + "/user/current"
                      // url: "http://110.40.152.26:8080/user/current"
                    }).then(resp => {
                      if(resp.code == 200) {                        
                        sessionStorage.setItem('user', JSON.stringify(resp.data) ) //将用户信息存储在session中
                      } else {
                        this.$message.error("未获取到用户信息 T_T ")
                        return false
                      }
                    }).catch(err => {
                      this.$message.error(err.msg)
                      return
                    })
                    this.$router.push('/')
                  }else{
                    return this.$message.error(resp.msg)
                  }
              })
              .catch(err => {
                loading.close();
                console.log(err)
              });
          } else {
            return false;
          }
        });
      },
    },
    mounted() {
      sessionStorage.clear()
    }
  }
</script>
<style lang="scss" scoped>
  .log-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 180px auto;
    padding: 35px 35px 15px 35px;
  }
  #login-input {
    background-color: #F5F7FA;
    border-radius: 4px;
    width: 550px;
    height: 300px;
    margin: 130px auto;
    float: left;
  }
  #main {
    background-color: rgb(245, 247, 250);
    border-radius: 4px;
    width: 1450px;
    height: 600px;
    margin: 150px auto;
  }
  .bg-img {
    display: block;
    height: 100%;
    width: auto;
    float:left;
  }
</style>

