<template>
  <el-container>
    <el-main class="container">
      <el-form>
        <table class="querybar">
          <tr>          
            <td>
                <el-button icon="el-icon-arrow-left" type="primary" @click="goBack()">返回</el-button>        
            </td>     
          </tr>
        </table>
      </el-form>
      <el-table :data="tableData" height="calc(100vh - 213px)" fit stripe border style="width:100%;">
        <el-table-column type="index" label="序号" width="55" header-align="center" align="center"></el-table-column>
        <el-table-column prop="id" label="产品编号" header-align="center" align="center" min-width="80"></el-table-column>
        <el-table-column prop="sortName" label="产品分类" header-align="center" align="center" min-width="80"></el-table-column>
        <el-table-column
          prop="purchaseName"
          label="产品名称"
          header-align="center"
          align="center"
          min-width="160"
        ></el-table-column>
        <el-table-column
          prop="purchaseCount"
          label="进货数量"
          header-align="center"
          align="center"
          min-width="50"
        ></el-table-column>
        <el-table-column
          prop="totalPrice"
          label="单项总价(元)"
          header-align="center"
          align="center"
          min-width="50"
        ></el-table-column>
        <el-table-column
          prop="msg"
          label="备注"
          header-align="center"
          align="center"
          min-width="130"
        ></el-table-column>
      </el-table>

      <!--分页栏-->
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-sizes="[10,15,20,30,40]"
          layout="sizes,total,prev,pager,next"
          :current-page="currentPage"
          :page-size="pageSize"
          :total="total"
        ></el-pagination>
      </div>
    </el-main>
  </el-container>
</template>
<script>
import { Loading } from "element-ui";
export default {
  data() {
      return {
      /**
       * table内容
       */
      tableData: [],
      /**
       * 当前页码
       */
      currentPage: 1,
      /**
       * 每页条数
       */
      pageSize: 10,
      /**
       * 总页数
       */
      total: 0,
      //人员
      personlist: [],
    };
  },
  methods: {
    /**
     * @description: 每页条数改变时触发
     */
    handleSizeChange(val) {
      this.currentPage = 1;
      this.pageSize = val;
      this.queryTableData();
    },
    /**
     * @description: 页码改变时触发
     */
    handleCurrentChange(val) {
      this.currentPage = val;
      this.queryTableData();
    },
    goBack() {  //返回到上一步
      this.$router.go(-1);
    },
    queryTableData() {    
      let loading = Loading.service({
        fullscreen: true,
        text: "进货单详情信息加载中....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      let queryparams = {pageSize: 0, pageNum: 0, purchaseId: ""};
      queryparams.purchaseId = this.$route.query.purchaseId; //接收进货管理页面传来的进货单号
      queryparams.pageSize = this.currentPage;
      queryparams.pageNum = this.pageSize;
      this.$axios({
        method: "post",
        data: queryparams,
        url: this.$config.proxy() + "/purchase/son/list"
        // url: "http://110.40.152.26:8080/purchase/son/list"
      }).then(resp => {
        loading.close();
        this.tableData = resp.data.purchaseSonList;
        this.total = resp.total
      });
    }
  },
  mounted() {
    this.queryTableData();
  },
};

</script>

<style  lang="scss" scoped>

</style>