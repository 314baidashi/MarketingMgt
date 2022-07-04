<template>
  <el-container>
    <el-main class="container">
      <div  >
        <!-- 搜索-->
        <div class="search" >            
          <!--     操作     -->
          <el-button style="float: left;" type="primary" @click="roleDialog('create')" ><i class="el-icon-circle-plus-outline"></i>增加</el-button>
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
          <router-link to="/user/index"><el-button style="float: right; margin-right: 20px;" class="ml-5" type="primary" >用户管理</el-button></router-link>   
        </div>
        <!--          数据主体展示-->
        <el-table :data="tableData" border align="center" stripe @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column type="index" label="序号" width="55" header-align="center" align="center">
            <template slot-scope="scope">
              {{(currentPage - 1) * pageSize + scope.$index + 1}}
            </template>
          </el-table-column>
          <el-table-column align="center" prop="name" label="权限名" >
          </el-table-column>
          <el-table-column align="center" prop="keyname" label="唯一标识" >
          </el-table-column>
          <el-table-column align="center" prop="description" label="描述" >
          </el-table-column> 


          <el-table-column align="center" label="操作">
            <template slot-scope="scope">              
              <el-button type="success" @click="roleDialog('edit', scope.row)"><i class="el-icon-edit"></i>编辑</el-button>
              <el-popconfirm
                  style="margin-left: 5px;"
                  confirm-button-text='确认'
                  cancel-button-text='考虑一下'
                  icon="el-icon-info"
                  icon-color="red"
                  title="确定要删除此内容？"
                  @confirm="deleteRole(scope.row)"
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
        <!--编辑角色信息-->
        <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%">
          <el-form label-width="70px" ref="addForm" :rules="rules" :model="editproduct">
            <el-form-item label="角色名称" prop="name" label-width="22%">
              <el-input v-model="editproduct.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="唯一标识" prop="keyName" label-width="22%">
              <el-input v-model="editproduct.keyName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="角色描述" prop="description" label-width="22%">
              <el-input v-model="editproduct.description" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveRole(mode)">确 定</el-button>
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
        keyName: [
          { required: true, message: "权限标识不能为空", trigger: "blur" }
        ],
        name: [{ required: true, message: "角色名称不能为空", trigger: "blur" }],
      },
      /**
       *弹出对话框的模式
       */
      mode: "create",
      /**
       * table的max-height属性，为了使table出现垂直滚动条
       */
      //maxheight: 700,
      searchObj: { queryKey: "", checked: false },
      /**
       * 显示新增对话框
       */
      showCreateDialog: false,
      dialogFormVisibleEdit: false,
      MenuVisible: false,
      /**
       * table内容
       */
      tableData: [],
      pageNum: 1,
      form: {},
      searchName: "",
      expends: [],
      checks: [],
      search: {

      },
      pageSize: 5,
      name: "",
      dialogFormVisible: false,
      total: 0,
      multipleSelection: [],
      roles:[],
      sources:[],
      props: {
        children: "children",
        label: "name",
        value: "id"
      },
      /**
       * 当前页码
       */
      currentPage: 1,
      /**
       * 每页条数
       */
      pageSize: 10,
      menuData: [],
      /**
       * 总页数
       */
      total: 0,
      editproduct: {}
    };
  },
  methods: {
    reset() {
      this.searchName="";
      this.queryTableData();
    },
    saveRole(mode) {
      this.$refs["addForm"].validate(valid => {
        if (valid) {
          let loading = Loading.service({
            text: "正在保存角色信息....",
            background: "rgba(0, 0, 0, 0.1)"
          });
          if (mode === "create") {
            this.$axios({
            method: "post",
            data: { ...this.editproduct },
            url: this.$config.proxy() + "/roles/save"
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
          } else if(mode === "edit"){
              this.$axios({
                method: "put",
                data: { ...this.editproduct },
                url: this.$config.proxy() + "/roles/update"
              })
              .then(resp => {
                loading.close();
                this.dialogFormVisible = false;
                this.$message.success(resp.msg);
                this.queryTableData();
              })
              .catch(err => {
                loading.close();
                this.$message.error(err.msg);
              });
              
          }        
          } else {
            return false;
          }
          });
      
      
    },
    deleteRole(row) {      
      let loading = Loading.service({
        text: "正在删除角色....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.$axios({
        method: "delete",
        url: this.$config.proxy() + "/roles/delete/" + row.id
      })
        .then(resp => {
          loading.close();
          this.queryTableData();
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
        this.deleteRole(ids[i]);
      }
    },
    /**
     * @description: 打开创建用户对话框
     * @return:
     */
    roleDialog(mode, row) {
      this.mode = mode;
      if (mode == "create") {
        this.editproduct = {
          name: "",
          keyName: "",
          description: "",
        };
      } else {
        this.editproduct = { ...row };
      }
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
     * @author: huangzhen
     * @description: 请求列表数据
     * @param:
     * @date: 16:21:26  2020-01-03
     * @return:
     */
    queryTableData() {
      let loading = Loading.service({
        fullscreen: true,
        text: "数据加载中....",
        background: "rgba(0, 0, 0, 0.1)"
      });

      let queryparams = { ...this.search};
      queryparams.pageNum = this.currentPage;
      queryparams.pageSize = this.pageSize;
      this.$axios({
        method: "get",
        url: this.$config.proxy() + "/roles/query?pageNum=" + queryparams.pageNum + "&pageSize=" + queryparams.pageSize
      }).then(resp => {
        loading.close();
        this.tableData = resp.records;
        this.total = resp.total;
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