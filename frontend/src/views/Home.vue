<template>
  <div class="logindiv">
    <el-carousel height="calc(100vh - 115px) " indicator-position="outside">
    <el-carousel-item v-for="item in pics" :key="item">
     
      <img :src="item"  style="height:100%"/>

    </el-carousel-item>
  </el-carousel>
     
  </div>
</template>

<script> 
var portal1 = require('../assets/portal1.jpg')
var portal2 = require('../assets/portal2.jpg')
var portal3 = require('../assets/portal3.jpg')
export default {
  name: 'home',
  data(){
    return {
      pics:[portal1,portal2,portal3]
    }
  },
 
}
</script>
<style scoped>
.logindiv {
  width: 100%;
  height: calc(100vh - 120px);
  text-align: center;
  vertical-align: middle;
}
</style>