<template>
  
      <el-container>     <img src="../assets/images/404.png" @click="imgclick"> </el-container>
 
    
</template>

<script>
export default {
    methods:{
        imgclick(){
            this.$router.push("/");
        }
    }

}
</script>

<style scoped>
.el-container
{
    text-align: center;
}

img{
    padding-top: 130px;
    margin: auto;
}


</style>