<template>
  <el-container>
    <el-main class="container">
      <table class="querybar">
        <tr>
          <td style="text-align:left;">
            <el-input
              v-model="searchObj.query"
              class="searchtable-input"
              placeholder="查询产品名称"
              @change="doSearch"
              style="width:260px;"
              suffix-icon="el-icon-search"
            ></el-input>
          </td>
          <td style="text-align:right;padding-right: 10px;">
            <table style="float: right;">
              <tr>
               
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <el-table :data="tableData" height="calc(100vh - 213px)" fit stripe border style="width:100%;">
        <el-table-column type="index" label="序号" width="55" header-align="center" align="center">
          <template slot-scope="scope">
            {{(pageSize - 1) * pageNum + scope.$index + 1}}
          </template>
        </el-table-column>
        <el-table-column prop="productId" label="产品编号" header-align="center" align="center" min-width="60"></el-table-column>
        <el-table-column
          prop="sortName"
          label="产品分类"
          header-align="center"
          align="center"
          min-width="60"
        ></el-table-column>
        <el-table-column
          prop="productName"
          label="产品名称"
          header-align="center"
          align="center"
          min-width="120"
        ></el-table-column>
        <el-table-column
          prop="total"
          label="产品数量"
          header-align="center"
          align="center"
          min-width="120"
        ></el-table-column>
       <el-table-column label="操作" align="center" header-align="center" width="240">
          <template slot-scope="scope">
            <el-button @click="storeDialog(scope.row)" type="text" size="small">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--分页栏-->
      <div class="pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-sizes="[10,15,20,30,40]"
          layout="sizes,total,prev,pager,next"
          :current-page="pageSize"
          :page-size="pageNum"
          :total="total"
        ></el-pagination>
      </div>
    </el-main>
    <el-dialog
      :visible="showStoreDialog"
      title="修改库存"
      top="20vh"
      width="450px"
      @close="showStoreDialog=false"
      :close-on-click-modal="true"
    >
      <div>
        <el-form style="width:100%" ref="form" :rules="rules" :model="editproduct" label-width="80px">
          <el-form-item label="产品编号：" :label-width="'100px'" prop="productId">
            <el-input :disabled="true" size="medium" v-model="editproduct.productId"></el-input>
          </el-form-item> 
          <el-form-item label="产品分类：" :label-width="'100px'" prop="sortName">
            <el-input :disabled="true" size="medium" v-model="editproduct.sortName"></el-input>
          </el-form-item> 
          <el-form-item label="产品名称：" :label-width="'100px'" prop="productName">
            <el-input :disabled="true" size="medium" v-model="editproduct.productName"></el-input>
          </el-form-item> 
          <el-form-item label="产品数量：" :label-width="'100px'" prop="total">
            <el-input-number size="medium" v-model="editproduct.total"></el-input-number>
          </el-form-item> 
        </el-form>
        <div style="width:100%;text-align:center">
          <el-button icon="el-icon-check" @click="saveProduct">保存</el-button>
        </div>
      </div>
    </el-dialog>
  </el-container>
</template>
<script>
import { Loading } from "element-ui";
export default {
  computed: {
  },
  data() {
    return {
      rules: {
        total: [
          { required: true, message: "请输入正确的产品库存", trigger: "blur" },
          {
            validator: (rule, value, callback) => {
              if (value < 0) {
                callback(new Error("产品库存不能小于0"));
              } else {
                callback();
              }
            }
          }
        ]
      },
      searchObj: { query: ""},
      /**
       * 显示新增对话框
       */
      showStoreDialog: false,
      /**
       * table内容
       */
      tableData: [],
      /**
       * 每页条数
       */
      pageNum: 10,
      /**
       * 当前页码
       */
      pageSize: 1,
      /**
       * 总页数
       */
      total: 0,
      editproduct: {}
    };
  },
  methods: {
    /**
     * @description: 打开创建用户对话框
     */
    storeDialog(row) { 
      this.editproduct = { ...row };
      this.showStoreDialog = true;
    },

    saveProduct() {
      let newStore = { };
      newStore.storeId = this.editproduct.storeId;
      newStore.sortId = this.editproduct.sortId;
      newStore.state = this.editproduct.state;
      newStore.productId = this.editproduct.productId;
      newStore.productName = this.editproduct.productName;
      newStore.total = this.editproduct.total;
      this.$refs["form"].validate(valid => {
        if (valid) {
          let loading = Loading.service({
            text: "正在保存库存信息....",
            background: "rgba(0, 0, 0, 0.1)"
          });

          this.$axios({
            method: "post",
            data: newStore,
            url: this.$config.proxy() + "/store/update"
          })
            .then(resp => {
              loading.close();
              this.$message.success("保存成功");
              this.showStoreDialog = false;
              this.queryTableData();
            })
            .catch(err => {
              loading.close();
              this.$message.error("保存失败");
            });
        } else {
          return false;
        }
      });
      this.queryTableData();
    },

    getRouteName(row) {
      let routeName = "";
      this.typeUrlData.forEach(item => {
        if (row.type == item.type) {
          routeName = item.routeName;
          return;
        }
      });
      return routeName;
    },
    handleSizeChange(val) {
      this.pageNum = val;
      this.pageSize = 1;
      this.queryTableData();
    },
    handleCurrentChange(val) {
      this.pageSize = val;
      this.queryTableData();
    },
    /**
     * @description: 查询
     */
    doSearch() {
      this.pageNum = 10;
      this.pageSize = 1;
      this.queryTableData();
    },
    /**
     * @description: 请求列表数据
     */
    queryTableData() {
      let loading = Loading.service({
        fullscreen: true,
        text: "查询数据加载中....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      let queryparams = { ...this.searchObj };
      queryparams.pageNum = this.pageNum;
      queryparams.pageSize = this.pageSize;
      this.$axios({
        method: "post",
        data: queryparams,
        url: this.$config.proxy() + "/store/page/list"
      }).then(resp => {
        loading.close();
        this.tableData = resp.data.list;
        this.total = resp.data.total;
      });
    }
  },
  mounted() {
    this.queryTableData();
    //this.maxheight = document.body.scrollHeight - 220;
  }
};
</script>

<style  lang="scss" scoped>
#checkbox {
  border: 1px solid gray;
  background-color: #fff;
  padding-left: 10px;
  height: 150px;
  overflow-y: scroll;
}
/* 设置滚动条的样式 */
::-webkit-scrollbar {
  width: 8px;
  background-color: white;
}
/* 滚动槽 */
::-webkit-scrollbar-track {
  border-radius: 7px;
}
/* 滚动条滑块 */
::-webkit-scrollbar-thumb {
  border-radius: 7px;
  background: grey;
}
.el-checkbox {
  color: #606266;
  font-weight: 500;
  font-size: 14px;
  cursor: pointer;
  width: 100px;
  margin-right: 0;
}
.el-radio-button__orig-radio:disabled:checked .el-radio-button__inner {
  background-color: #409eff;
  color: white;
}
.querybar {
  position: relative;
  height: 35px;
  width: 100%;
  margin-bottom: 0px;
}

.el-select /deep/ .el-input {
  width: 120px;
}
.el-select /deep/ .el-input.is-disabled > .el-input__inner {
  background-color: #f5f7fa;
  border-color: #e4e7ed;
  color: black;
}

/*._ui-title-1 > span {
        margin-left: 0;
        border-color: #2384b7;
      }*/
.elmenu {
  margin-left: 10px;
}

/*.hrStyle {
        background: #d9d9d9;
        height: 1px;
        width: 100%;
      }*/
.dialogSpan {
  margin-top: 4px;
  font-size: 18px;
  width: 22%;
  height: 35px;
  float: left;
  border-bottom: 5px solid #2384b7;
}

.el-form {
  margin-top: 2%;
  border: 1px solid #0000;
}

@keyframes arrowRotate1 {
  from {
    transform: rotate(90deg);
  }
  to {
    transform: rotate(-90deg);
  }
}

@keyframes arrowRotate2 {
  from {
    transform: rotate(-90deg);
  }
  to {
    transform: rotate(90deg);
  }
}
</style>