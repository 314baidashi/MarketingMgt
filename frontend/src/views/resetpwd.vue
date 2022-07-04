<template>
  <div>
    <div id="main">
      <img class="bg-img" src="../assets/images/login.png"/>
      <div id="login-input">
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">修改密码</h3>
      <el-form-item label= "新密码" prop="newPwd">
        <el-input style="margin-left: 20px; width: 400px;" type="password" placeholder="请输入新密码" v-model="form.newPwd"/>
      </el-form-item>
      <el-form-item label= "确认密码" prop="confirmPwd">
      <el-input style="margin-left: 20px; width: 400px;" type="password" placeholder= "请再次输入新密码进行确认" v-model="form.confirmPwd"/>
      </el-form-item>
      <el-form-item>
        <el-button  type="primary" @click="onSubmit('loginForm')">修改密码</el-button>
        <el-button  type="primary" @click="cancel">取消</el-button>
      </el-form-item>
      </el-form>
    </div>
  </div>
  <!--选择用户-->
    <el-dialog title="请填写您的账户信息" 
              :visible.sync="dialogFormVisible" 
              width="30%"
              :show-close="false"
              :close-on-click-modal="false">
      <el-form label-width="70px" ref="editForm" :rules="rules" :model="editForm">
        <el-form-item label="账号" prop="name" label-width="22%">
          <el-input v-model="editForm.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限类型" prop="roleName" label-width="22%">
          <el-select clearable v-model="editForm.roleName" placeholder="请选择用户角色" style="width: 100%">
              <el-option
                v-for="item in roles"
                :key="item.keyname"
                :label="item.name"
                :value="item.keyname">
              </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="姓名" prop="realName" label-width="22%">
          <el-input v-model="editForm.realName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="telNumber" label-width="22%">
          <el-input v-model="editForm.telNumber" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="queryUser()">确 定</el-button>
      </div>
    </el-dialog>
  </div>  
</template>




<script>
  import { Loading } from "element-ui";
  export default {
    name: "Login",
    data() {
      return {
        form: {
          newPwd: '' ,
          confirmPwd: ''
        },
        editForm: {},
        userId: "",
        roles: [],
        dialogFormVisible: false,
        //表单验证，需要在el-form-item 元素中增加prop 属性
        rules: {
          newPwd: [
              {required: true, message:  "新密码不可为空", trigger: blur}
          ],
          confirmPwd: [
            {required: true, message:  "确认密码不可为空" , trigger: blur},
            {validator: (rule, value, callback) => {
              if (value === this.form.newPwd) {
                callback();
              } else {
                callback(new Error('两次输入的密码不一致'));
              }
            }}
          ],
          roleName: [
            { required: true, message: "请选择用户角色", trigger: "blur" }
          ],
          name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
          realName: [
            { required: true, message: "请输入真实姓名", trigger: "blur" }
          ], 
          telNumber: [
            { required: true, message: "请输入电话号码", trigger: "blur" },
            {
              pattern: /^1[3456789]\d{9}$/,
              message: "请输入正确的电话号码",
              trigger: "blur"
            }
          ]
        },
      }
    },
    methods: {
      cancel() {
        this.dialogFormVisible = false;
        this.$router.push({path: '/login'});
      },
      onSubmit(formName) {
        //为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let loading = Loading.service({
              text: "修改中....",
              background: "rgba(0, 0, 0, 0.1)"
            });
            console.log(this.userId);
            //使用vue-router路由到指定页面，该方式称之为编程式导航
            this.$axios({
              method: "put",
              url:
                  this.$config.proxy() + "/users/set/" + this.userId + "?password=" + this.form.newPwd, 
              // this.$config.proxy + "/users/set/" + this.userId + "?password=" + this.form.newPwd
            })
              .then(resp => {
                loading.close();
                  if(resp.code == 200){
                    this.$message.success('修改成功, 请重新登录!');
                    this.$router.push('/login')
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
      userDialog() { //打开对话框     
        this.$axios({ //获取角色列表
          method: "get",
          url: this.$config.proxy() + "/roles/query?pageNum=1&pageSize=100"
        }).then(resp => {
          this.roles = resp.records;
        });
        this.editFrom = {
          name: "",
          realname: "",
          roleName: "",
          telNumber: "",
        };
        this.dialogFormVisible = true;
      },
      queryUser() { //请求用户列表
        this.$refs["editForm"].validate(valid => {
          if (valid) {
            this.dialogFormVisible = false;
            let loading = Loading.service({
              fullscreen: true,
              text: "搜索用户中....",
              background: "rgba(0, 0, 0, 0.1)"
            });
            let queryparams = { ...this.editForm };
            queryparams.pageNum = 1;
            queryparams.pageSize = 100;
            this.$axios({
              method: "get",
              url: this.$config.proxy() + "/users/query?pageNum=" + queryparams.pageNum + "&pageSize=" + queryparams.pageSize + "&realname=" + queryparams.realName + "&roleName=" + queryparams.roleName + "&telnumber=" + queryparams.telNumber
            }).then(resp => {
              loading.close();
              this.userId = resp.records[0].id;
            });
          } else {
            return false;
          }
        });
      },
    },
    mounted() {
      //获取用户信息
      this.userDialog();
    },
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

