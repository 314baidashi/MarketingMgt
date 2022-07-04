<template>
  <el-container>
    <el-main class="container">
      <div  >
        <!-- 搜索-->
        <div class="search" >            
          <!--     操作     -->
          <router-link to="/user/index"><el-button style="float: left; margin-right: 10px;" class="ml-5" type="primary" ><i class="el-icon-arrow-left"></i> 产品管理</el-button></router-link>  
          <el-button style="float: left;" type="primary" @click="sortDialog" ><i class="el-icon-circle-plus-outline"></i>增加新分类</el-button>
          <el-popconfirm
              style="margin-left: 5px;"
              confirm-button-text='确认'
              cancel-button-text='考虑一下'
              icon="el-icon-info"
              icon-color="red"
              title="确定要删除这些内容？"
              @confirm="delbatch"
          >
            <el-button style="float: left; margin: 0px 3px;" type="danger" slot="reference"   ><i class="el-icon-remove-outline"></i>批量删除</el-button>
          </el-popconfirm>    
        </div>
        <!--          数据主体展示-->
        <el-table :data="tableData" border stripe @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column type="index" label="序号" width="55" header-align="center" align="center">
            <template slot-scope="scope">
              {{(currentPage - 1) * pageSize + scope.$index + 1}}
            </template>
          </el-table-column>
          <el-table-column align="center" prop="sortId" label="分类编号" >
          </el-table-column>
          <el-table-column align="center" prop="sortName" label="分类名称" >
          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">              
              <el-popconfirm
                  style="margin-left: 5px;"
                  confirm-button-text='确认'
                  cancel-button-text='考虑一下'
                  icon="el-icon-info"
                  icon-color="red"
                  title="确定要删除此内容？"
                  @confirm="deleteSort(scope.row)"
              >
                <el-button type="danger" slot="reference" ><i class="el-icon-delete"></i>删除</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <!--          分页-->
        <div style="padding: 10px">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :page-sizes="[5, 10, 15, 20]"
              :current-page="currentPage"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
        <!--新增分类-->
        <el-dialog title="新增分类" :visible.sync="dialogFormVisible" width="30%">
          <el-form label-width="70px" ref="addForm" :rules="rules" :model="editproduct">
            <el-form-item label="分类名称" prop="sortName" label-width="22%">
              <el-input v-model="editproduct.sortName" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveSort()">确 定</el-button>
          </div>
        </el-dialog>
        </div>
    </el-main>
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
        sortName: [
          { required: true, message: "请输入正确的分类名称", trigger: "blur" }
        ],
      },
      showCreateDialog: false,
      tableData: [],  //表格内容
      dialogFormVisible: false,
      total: 0,
      multipleSelection: [],
      /**
       * 当前页码
       */
      currentPage: 1,
      /**
       * 每页条数
       */
      pageSize: 15,
      /**
       * 总页数
       */
      total: 0,
      editproduct: {}
    };
  },
  methods: {
    saveSort() {
      this.$refs["addForm"].validate(valid => {
        if (valid) {
          let loading = Loading.service({
            text: "正在保存分类信息....",
            background: "rgba(0, 0, 0, 0.1)"
          });          
          this.$axios({
            method: "post",
            url: this.$config.proxy() + "/sort/add?sortName=" + this.editproduct.sortName
          })
          .then(resp => {
            loading.close();
            if (resp) {
              this.dialogFormVisible = false;
              this.$message.success(resp.msg);
              this.queryTableData();
            }
          })
          .catch(err => {
              loading.close();
              this.$message.error(err.msg);
          });
        } else {
          return false;
        }
      });     
    },
    deleteSort(row) {      
      let loading = Loading.service({
        text: "正在删除分类....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.$axios({
        method: "post",
        url: this.$config.proxy() + "/sort/del/" + row.sortId
      })
        .then(resp => {
          loading.close();
          if(resp.code == "200") {
            this.$message.success("删除成功")
            this.queryTableData();
          } else {
            this.$message.error("删除失败")
          }
          
          
        })
        .catch(err => {
          loading.close();
        })
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    delbatch() {
      let ids = this.multipleSelection;
      for (let i = 0; i < ids.length; i++) {
        this.deleteSort(ids[i]);
      }
    },
    /**
     * @description: 打开创建用户对话框
     * @return:
     */
    sortDialog() {
        this.editproduct = {
          sortName: ""
        };
        this.dialogFormVisible = true;
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
        text: "数据加载中....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.$axios({
        method: "get",
        url: this.$config.proxy() + "/sort/list"
      }).then(resp => {
        loading.close();
        this.tableData = resp.data;
        this.total = this.tableData.length;
      });
    },
  },
  mounted() {
    this.queryTableData();
    //this.maxheight = document.body.scrollHeight - 220;
  }
};
</script>

<style  lang="scss" scoped>
.search {
  display: block;
  width: 100%;
  height: 40px;
  margin: 5px 10px;
}
</style>