
<template>
  <div id="app">
    <v-header></v-header>
    <keep-alive :include="includes">
    <router-view />
</keep-alive>
    <div class="footerdiv">
      <el-footer height="40px" class="footer">鲁ICP备17053951号-1</el-footer>
    </div>
  </div>
</template>
 
<script>
import vHeader from "@/components/Header.vue";
export default {
  components: {
    vHeader
  },
  data(){
    return {
      includes:['home']
    }
  }
};
</script>
 

<style>

.footerdiv {
  position: absolute;
  bottom: 0px;  
  width: 100%;
}
.footer {
  height: 40px;
  background: #252b3a;
  line-height: 40px;
  text-align: center;
  color: rgb(160, 162, 168);
  font-weight: 600;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  overflow: hidden;
  /* background-image: url("./assets/images/background.jpg"); */
  background-size: 100% 100%;
  overflow-x: hidden;
}
::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;
  height: 0;
}

/* #nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
