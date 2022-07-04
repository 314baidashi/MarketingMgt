<template>
  <table class="header" v-if="this.$route.path != '/login' && this.$route.path != '/resetpwd' && this.$route.path != '/login/'">
    <tr>
      <td class="title">
        <table>
          <tr>
            <td>
              <span
                style="font-size: 34px; margin-left: 20px; font-weight: bold"
                >线上营销管理平台</span
              >

              <span class="module-title">{{ title }}</span>
            </td>
          </tr>
        </table>
      </td>
      <td>
        <el-menu
          :default-active="activeIndex2"
          style="float: right"
          mode="horizontal"
          :router="true"
          @select="handleSelect"
          background-color="#0072d2"
          text-color="#ffffff"
          active-text-color="#F89805"
        >
          <el-menu-item index="1" route="/" v-if="role == 'admin' || role == 'user' || role == 'stoadmin'">首页</el-menu-item>
          <el-menu-item index="2-1" route="/client/index" v-if="role == 'admin'">客户</el-menu-item>
          <el-menu-item index="2-2" route="/product/index" v-if="role == 'admin' || role == 'user' || role == 'stoadmin'">产品</el-menu-item>
          <el-menu-item index="3-1" route="/batch/index" v-if="role == 'admin' || role == 'stoadmin'">进货</el-menu-item>
          <el-menu-item index="3-2" route="/stores/index" v-if="role == 'admin' || role == 'user' || role == 'stoadmin'">库存</el-menu-item>
          <el-menu-item index="5-1" route="/orders" v-if="role == 'admin' || role == 'user' || role == 'stoadmin'">订单</el-menu-item>
          <el-menu-item index="8-4" route="/statistics/chart" v-if="role == 'admin' || role == 'user' || role == 'stoadmin'"
            >统计</el-menu-item
          >
          <el-menu-item index="4-2" route="/user/index" v-if="role == 'admin' ">用户</el-menu-item>
        </el-menu>
      </td>
      <td class="loginbtn">
        <div>
          <img
            class="exitbtn"
            @click="exitClick"
            src="../assets/images/exit.png"
          />
        </div>
      </td>
    </tr>
  </table>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "vHeader",
  data() {
    return {
      havelogin: false,
      activeIndex2: "1",
      role: JSON.parse(sessionStorage.getItem('user')).roleName,
      // role: "",
      isReaload: false

    };
  },
  computed: {
    ...mapGetters({
      title: "getTitle"
    })
  },
  methods: {
    handleSelect(key, keyPath) {},
    exitClick() {
      sessionStorage.clear()
      this.$router.push("/login");
    },
    reload() {
      if(this.role == "" && !this.isReaload){
        // window.location.reload()
        this.isReaload = true
        this.role = JSON.parse(sessionStorage.getItem('user')).roleName;
      } 
      console.log(this.role)
      }
  },
  mounted() {
    this.reload()
  }
}
</script>

<style lang="scss" scoped>
.el-menu li {
  font-weight: bold;
  color: white;
}

.exitbtn {
  color: white;
  cursor: pointer;
  margin-right: 10px;
  width: 35px;
  height: 35px;
}
.loginbtn {
  padding-right: 10px;
  text-align: right;
}
.title {
  text-align: left;
  color: aliceblue;
}
.title img {
  height: 60px;
}
.header {
  width: 100%;
  height: 60px;
  margin: 0px;
  /* background-image: url("../assets/images/titlebackground.png"); */
  background-color: #0072d2;
}
.module-title {
  font-weight: bold;
}
.module-title::before {
  content: "·";
  padding-left: 15px;
  padding-right: 15px;
}
</style>