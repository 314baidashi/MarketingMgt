<template>
  <div>
    <div>
      <el-form style="width:100%" ref="form" :rules="rules" :model="client" label-width="80px">
         <el-form-item label="客户姓名" prop="id">
          <el-input v-model="client.id"></el-input>
        </el-form-item>
        <el-form-item label="客户姓名" prop="name">
          <el-input v-model="client.realname"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="tel">
          <el-input v-model="client.tel"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="addr">
          <el-input v-model="client.addr"></el-input>
        </el-form-item>
      </el-form>
      <div style="width:100%;text-align:center">
        <el-button icon="el-icon-check" @click="saveUser">保存</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { Loading } from "element-ui";
import { mapGetters } from "vuex";
export default {
  props: ["client"],
  // watch: {
  //   user(val) {
  //     this.newvalue = val.pcount;
  //   }
  // },
  data() {
    return {
      roles: [],
      rules: {
        id: [{ required: true, message: "客户编号不能为空", trigger: "blur" }],
        name: [{ required: true, message: "客户姓名不能为空", trigger: "blur" }],
        // password: [
        //   { required: true, message: "请输入初始密码", trigger: "blur" }
        // ],
        tel: [
          { required: true,
            message : '手机号不能为空',
            trigger: "blur" },
          { min: 11, max: 11, message: "手机号长度为11位", trigger: "blur" },
          { validator:function(rule,value,callback){
                if(/^1[34578]\d{9}$/.test(value) == false){
                    callback(new Error("请输入正确的手机号"));
                }else{
                    callback();
                }
            }, trigger: 'blur'},
        ],
        addr: [{ required: true, message: "客户地址不能为空", trigger: "blur" }],
      }
    };
  },
  computed: {
    ...mapGetters({
      projectId: "getProjectId"
    })
  },
  mounted() {
    this.$axios({
      method: "get",
      url: this.$config.proxy() + "/roles/" + this.projectId + "/query"
    }).then(resp => {
      this.roles = resp.list;
    });
  },
  methods: {
    change(e) {
      this.$forceUpdate();
    },
    saveUser() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          let loading = Loading.service({
            text: "正在保存用户信息....",
            background: "rgba(0, 0, 0, 0.1)"
          });

          this.$axios({
            method: "post",
            data: { ...this.user },
            url: this.$config.proxy() + "/users/" + this.projectId + "/save"
          })
            .then(resp => {
              loading.close();
              if (resp) {
                this.$alert(resp, this.user.name + "的初始化密码");
              }
              this.$emit("save1Click");
            })
            .catch(err => {
              loading.close();
            });
        } else {
          return false;
        }
      });
    }
  }
};
</script>

<style  lang="scss" scoped>
.el-form /deep/ .el-form-item__content .el-input__inner {
  width: 250px;
  color: #000000 !important;
}
</style>