<template>
  <el-container>
    <el-main class="container">
      <table class="querybar">
        <tr>
          <td style="text-align:left;">
           <router-link to="/batch/addBatch"><el-button icon="el-icon-circle-plus" >添加进货订单</el-button>  </router-link>   
          </td>

          <td style="text-align:right;padding-right: 10px;">
            <el-button type="primary" plain style="float: right; margin-top: 3px;" icon="el-icon-refresh-right" @click="reset()" >重置</el-button>
            <table style="float: right;">
              <tr>
                <td style="padding-right: 15px;">
                  <div class="block">
                    <span class="demonstration">请选择进货日期</span>
                    <el-date-picker
                      style="margin-left: 20px"
                      v-model="search.beginTime"
                      align="right"
                      type="date"
                      :clearable="false"
                      value-format="yyyy-MM-dd"
                      @change="queryTableData()"
                      placeholder="选择日期"
                      :picker-options="pickerOptions">
                    </el-date-picker>
                  </div>
                </td>
                <td>
                  <el-input
                    v-model="search.person"
                    class="searchtable-input"
                    placeholder="请输入进货单号或负责人"
                    @change="queryTableData()"
                    style="width:260px;"
                    suffix-icon="el-icon-search"
                  ></el-input>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <el-table :data="tableData" height="calc(100vh - 213px)" fit stripe border style="width:100%;">
        <el-table-column type="index" label="序号" width="55" header-align="center" align="center">
          <template slot-scope="scope">
            {{(currentPage - 1) * pageSize + scope.$index + 1}}
          </template>
        </el-table-column>
        <el-table-column prop="purchaseId" label="进货单号" header-align="center" align="center" min-width="180"></el-table-column>
        <el-table-column
          prop="createDatetime"
          label="进货日期"
          header-align="center"
          align="center"
          min-width="60"
        ></el-table-column>
        <el-table-column
          prop="name"
          label="负责人"
          header-align="center"
          align="center"
          min-width="60"
        ></el-table-column>
        <el-table-column
          prop="purchaseState"
          label="进货状态"
          header-align="center"
          align="center"
          min-width="60"
        >
          <template slot-scope="scope" >
            <el-tag type="primary" v-if="scope.row.purchaseState=='1'">订单已完成</el-tag>
            <el-tag type="primary" v-if="scope.row.purchaseState==='0'">订单处理中</el-tag>
            <el-tag type="primary" v-if="scope.row.purchaseState==='-1'">订单异常</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="msg"
          label="备注"
          header-align="center"
          align="center"
          min-width="160"
        ></el-table-column>
        <el-table-column label="操作" align="center" header-align="center" width="60">
          <template slot-scope="scope" >            
            <el-button type="text" slot="reference" @click="detail(scope.row)">详情</el-button>
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
      search: {  //搜索进货单条件
        beginTime: "",
        person: "",
      },
      purchaseId: "",  //获得进货单号
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
      editproduct: {},
      pickerOptions: {  //日期选择器
          disabledDate(time) {
            return time.getTime() > Date.now();
          },
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        searchDate: { queryKey: "", checked: false }, //搜索日期
    };
  },
  methods: {
    reset() {  //重置搜索框
      this.search.beginTime = "";
      this.search.person = "";
      this.queryTableData();
    },          
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
    /**
     * @description: 请求列表数据
     */
    queryTableData() {
      let loading = Loading.service({
        fullscreen: true,
        text: "查询数据加载中....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      let queryparams = { ...this.search };
      queryparams.pageSize = this.currentPage;
      queryparams.pageNum = this.pageSize;
      this.$axios({
        method: "post",
        data: queryparams,
        url: this.$config.proxy() + "/purchase/page/list"
      }).then(resp => {
        loading.close();
        this.tableData = resp.data.pageList;
        this.total = resp.total
      });
    },
    detail(row) { //跳转到进货单详情页
      this.purchaseId = row.purchaseId; //获取所选行的进货单号
      this.$router.push({path: '/batch/detailBatch', query: {purchaseId: this.purchaseId}})  //跳转到进货单详情页并将进货单号传过去
    }
  },
  mounted() {
    this.queryTableData();
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