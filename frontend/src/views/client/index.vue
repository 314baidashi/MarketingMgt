<template>
  <el-container>
    <el-main class="container">
      <table class="querybar">
        <tr>
          <td style="text-align:left;">
            <el-button icon="el-icon-circle-plus" @click="userDialog('create')">新建</el-button>           
          </td>

          <td style="text-align:right;padding-right: 10px;">
            <table style="float: right;">
              <tr>
                <td>
                  <el-input
                    v-model="info"
                    class="searchtable-input"
                    placeholder="查询客户编号"
                    @change="queryClient"
                    style="width:260px;"
                    suffix-icon="el-icon-search"
                  ></el-input>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <el-table :data="client" height="calc(100vh - 213px)" fit stripe border style="width:100%;">
        <el-table-column type="index" label="序号" width="55" header-align="center" align="center">
          <template slot-scope="scope">
            {{(currentPage - 1) * pageSize + scope.$index + 1}}
          </template>
        </el-table-column>
        <el-table-column
          prop="clientId"
          label="ID"
          header-align="center"
          align="center"
          min-width="60"
        ></el-table-column>
        <el-table-column
          prop="clientName"
          label="姓名"
          header-align="center"
          align="center"
          min-width="60"
        ></el-table-column>
        <el-table-column
          prop="clientPhone"
          label="电话"
          header-align="center"
          align="center"
          min-width="120"
        ></el-table-column>
        <el-table-column
          prop="clientAddress"
          label="地址"
          header-align="center"
          align="center"
          min-width="120"
        ></el-table-column>
        <el-table-column label="操作" align="center" header-align="center" width="240">
          <template slot-scope="scope">
            <el-button @click="userDialog('edit',scope.row)" type="text" size="small">编辑</el-button>
            <el-popconfirm
                style="margin-left: 5px;"
                confirm-button-text='确认'
                cancel-button-text='考虑一下'
                icon="el-icon-info"
                icon-color="red"
                title="确定要删除此内容？"
                @confirm="deleteClient(scope.row)"
            >
              <el-button type="text" slot="reference" >删除</el-button>
            </el-popconfirm>
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
    <el-dialog
      :visible="showCreateDialog"
      title="用户信息"
      top="20vh"
      width="450px"
      @close="showCreateDialog=false"
      :close-on-click-modal="false"
    >
      <div>
        <el-form :model="editClient" style="width:100%" ref="form" :rules="rules" label-width="80px">
          <el-form-item label="客户编号" prop="clientId"  v-if="editClient.clientId != ''">
            <el-input :disabled="true" v-model="editClient.clientId"></el-input>
          </el-form-item>
          <el-form-item label="客户姓名" prop="clientName">
            <el-input v-model="editClient.clientName"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="clientPhone">
            <el-input v-model="editClient.clientPhone"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="clientAddress">
            <el-input v-model="editClient.clientAddress"></el-input>
          </el-form-item>
        </el-form>
        <div style="width:100%;text-align:center">
          <el-button icon="el-icon-check" @click="saveClient(mode)">保存</el-button>
        </div>
      </div>
    </el-dialog>
    <!-- 查询结果 -->
    <el-dialog
      :visible="resultVisable"
      title="客户信息"
      top="20vh"
      width="450px"
      @close="resultVisable=false"
      :close-on-click-modal="false"
    >
      <div>
        <el-form :model="result" style="width:100%" ref="form"  label-width="80px">
          <el-form-item label="客户编号" prop="clientId" >
            <el-input :disabled="true" v-model="result.clientId"></el-input>
          </el-form-item>
          <el-form-item label="客户姓名" prop="clientName">
            <el-input :disabled="true" v-model="result.clientName"></el-input>
          </el-form-item>
          <el-form-item label="联系电话" prop="clientPhone">
            <el-input :disabled="true" v-model="result.clientPhone"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="clientAddress">
            <el-input :disabled="true" v-model="result.clientAddress"></el-input>
          </el-form-item>
        </el-form>
        <div style="width:100%;text-align:center">
          <el-button icon="el-icon-close" @click="reset()">关闭</el-button>
        </div>
      </div>
    </el-dialog>
  </el-container>
</template>
<script>
import { Loading } from "element-ui";
export default {
  data() {
    return {
      rules: {
        clientName: [{ required: true, message: "客户姓名不能为空", trigger: "blur" }],
        clientPhone: [
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
        clientAddress: [{ required: true, message: "客户地址不能为空", trigger: "blur" }],
      },
      /**
       *弹出对话框的模式
       */
      mode: "create",
      /**
       * 显示新增对话框
       */
      showCreateDialog: false,
      resultVisable: false,
      client: [],  //表格中展示的数据
      info: "",  //查询条件
      result: {},  //查询结果
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
      editClient: {} //临时编辑对象
    };
  },
  methods: {
    reset() {  //重置编辑客户表单
      this.result.clientId = "";
      this.result.clientName = "";
      this.result.clientPhone = "";
      this.result.clientAddress = "";
      this.resultVisable = false;
    },
    deleteClient(row) {  //删除客户信息
          let loading = Loading.service({
            text: "正在删除用户....",
            background: "rgba(0, 0, 0, 0.1)"
          });
          this.$axios({
            method: "post",
            url: this.$config.proxy() + "/client/delete?clientId=" + row.clientId
          })
            .then(resp => {
              loading.close();
              if(resp.code == "200"){
                this.$message.success(resp.msg)
                this.queryTableData();
              } else {
                this.$message.error(resp.msg)
              }
              
            })
            .catch(err => {
              loading.close(err.msg);
            });
    },
    /**
     * @description: 打开创建客户对话框
     */
    userDialog(mode, row) {
      this.mode = mode;
      if (mode == "create") {
        this.editClient = {
          clientId: "",
          clientName: "",
          clientPhone: "",
          clientAddress: "",
        };
      } else if (mode == "edit"){
        this.editClient = { ...row };
      }

      this.showCreateDialog = true;
    },
    saveClient(mode) {  //保存客户信息，用于新增或编辑
      this.$refs["form"].validate(valid => {
        if (valid) {
          let loading = Loading.service({
            text: "正在保存用户信息....",
            background: "rgba(0, 0, 0, 0.1)"
          });
          let update = { ...this.editClient }
          if(mode == "create"){  //新增客户
            this.$axios({
              method: "post",
              url: this.$config.proxy() + "/client/add?clientName=" + update.clientName + "&clientPhone=" + update.clientPhone + "&clientAddress=" + update.clientAddress
            }).then(resp => {
              loading.close();
              if (resp) {
                loading.close();
                this.$message.success(resp.msg)
                this.queryTableData()
              }
            })
            .catch(err => {
              loading.close(err.msg);
            });
          } else if(mode == "edit") {     //编辑客户
            this.$axios({
              method: "post", 
              url: this.$config.proxy() + "/client/update?clientName=" + update.clientName + "&clientPhone=" + update.clientPhone + "&clientAddress=" + update.clientAddress + "&clientId=" + update.clientId
            }).then(resp => {
              loading.close();
              if (resp.code == 200) {
                loading.close()
                this.$message.success(resp.msg)
                this.queryTableData()
              } else {
                this.$message.error(resp.msg)
              }
            })
            .catch(err => {
              loading.close(err.msg);
            });
          }
          this.showCreateDialog = false;
        } else {
          return false;
        }
      });
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
    queryTableData() {  //初始化数据
      let loading = Loading.service({
        fullscreen: true,
        text: "数据加载中....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.$axios({
        method: "post",
        url: this.$config.proxy() + "/client/select?pageNum=" +  this.currentPage + "&pageSize=" + this.pageSize
      }).then(resp => {          
          loading.close();
          this.client = resp.list;
          this.total = resp.total;
        
      });
    },
    queryClient() {  //查询客户
      let loading = Loading.service({
        fullscreen: true,
        text: "查询数据中....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      if(this.info == "") {
        this.queryTableData();
      } else {
        this.$axios({
          method: "get",
          url: this.$config.proxy() + "/client/query?clientId=" +  this.info
        }).then(resp => {
          if(resp) {
            loading.close();
            this.result= resp
            this.resultVisable = true;
          } else {
            loading.close();
            this.$message.error("用户不存在")
          }
          
        });
      }
      
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