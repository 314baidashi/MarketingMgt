<template>
  <div class="about">
    <h1>This is an about page</h1>
     <el-input type="text" v-model="searchObj.queryKey" placeholder="请输入样本库名称" class="searchtable-input" clearable></el-input>
  </div>
</template>
 

<script>
export default {
  data(){
    return{
      searchObj:{queryKey:''}
    }
  }

}
</script>

<style  lang="scss" scoped>
.searchtable-input {
  width:250px;
}
.el-input--suffix  /deep/ .el-input__inner{ 
  // line-height: 28px;
  height:128px !important; 
  color: #409eff;
}

</style>
