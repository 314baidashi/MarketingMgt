<template>
  <el-container>
    <el-main class="container">
      <div class="search" >
        <!--     操作     -->
        <el-button style="margin-left: 0px; float: left;" type="primary" @click="userDialog('create')" ><i class="el-icon-circle-plus-outline"></i>增加</el-button>
        <el-popconfirm
            style="margin-left: 5px;"
            confirm-button-text='确认'
            cancel-button-text='考虑一下'
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除这些内容？"
            @confirm="delbatch"
        >
          <el-button style="margin-left: 3px; float: left;" type="danger" slot="reference"><i class="el-icon-remove-outline"></i>批量删除</el-button>
        </el-popconfirm>
      <!-- 搜索-->      
        <el-select style="width: 200px; margin-right: 5px; float: left;" clearable v-model="search.roleName" placeholder="选择角色进行查询" >
            <el-option
              v-for="item in roles"
              :key="item.keyname"
              :label="item.name"
              :value="item.keyname">
            </el-option>
        </el-select>
        <el-input style="width: 200px; margin-right: 5px; float: left;" v-model="search.realName" placeholder="输入姓名进行查询"></el-input>
        <el-input style="width: 200px; margin-right: 5px; float: left;" placeholder="输入手机进行查询"  v-model="search.telNumber" ></el-input>
        <el-button style="float: left;" class="ml-5" type="primary" @click="doSearch">搜索</el-button>        
        <el-button style="float: left;" class="ml-5" type="warning" @click="reset">重置</el-button>
        <router-link to="/user/role"><el-button style="float: right; margin-right: 20px;" class="ml-5" type="primary" >角色管理</el-button></router-link> 
      </div>
      <!--数据主体展示-->
      <el-table id="user-table" ref="userTable" :data="tableData" border stripe @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
         <el-table-column type="index" label="序号" width="55" header-align="center" align="center">
          <template slot-scope="scope">
            {{(currentPage - 1) * pageSize + scope.$index + 1}}
          </template>
         </el-table-column>
        <el-table-column prop="name" align="center" label="账号" >
        </el-table-column>
        <el-table-column prop="roleName" label="权限类型" align="center" >
          <template slot-scope="scope" >
            <el-tag type="primary" v-if="scope.row.roleName=='admin'">管理员</el-tag>
            <el-tag type="primary" v-if="scope.row.roleName==='stoadmin'">库管员</el-tag>
            <el-tag type="primary" v-if="scope.row.roleName==='user'">业务员</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="realName" align="center" label="姓名" >
        </el-table-column>
        <el-table-column prop="telNumber" align="center" label="电话">
        </el-table-column>
        <el-table-column label="操作" align="center" width="300">
          <template slot-scope="scope">
            <el-button type="success" @click="userDialog('edit',scope.row)"><i class="el-icon-edit"></i>编辑</el-button>
            <el-popconfirm
                style="margin-left: 5px;"
                confirm-button-text='确认'
                cancel-button-text='考虑一下'
                icon="el-icon-info"
                icon-color="red"
                title="确定要删除此内容？"
                @confirm="deleteUser(scope.row)"
            >
              <el-button type="danger" slot="reference" size="medium"><i class="el-icon-delete"></i>删除</el-button>
            </el-popconfirm>
            <el-popconfirm
                style="margin-left: 5px;"
                confirm-button-text='确认'
                cancel-button-text='考虑一下'
                icon="el-icon-info"
                icon-color="red"
                title="确定要重置该账户的密码吗？"
                @confirm="resetPwd(scope.row)"
            >
              <el-button type="primary" slot="reference" size="medium"><i class="el-icon-refresh-right"></i>重置密码</el-button>
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
      <!--新增用户信息-->
      <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
        <el-form label-width="70px" ref="addForm" :rules="rules" :model="editproduct">
          <el-form-item label="账号" prop="name" label-width="22%">
            <el-input v-model="editproduct.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="权限类型" prop="roleName" label-width="22%">
            <el-select clearable v-model="editproduct.roleName" placeholder="请选择用户角色" style="width: 100%">
               <el-option
                  v-for="item in roles"
                  :key="item.keyname"
                  :label="item.name"
                  :value="item.keyname">
                </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="姓名" prop="realName" label-width="22%">
            <el-input v-model="editproduct.realName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="telNumber" label-width="22%">
            <el-input v-model="editproduct.telNumber" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="saveUser(mode)">确 定</el-button>
        </div>
      </el-dialog>
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
        roleName: [
          { required: true, message: "请选择用户角色", trigger: "blur" }
        ],
        name: [{ required: true, message: "请输入用户名", trigger: "blur" }],
        realName: [
          { required: true, message: "请输入真实姓名", trigger: "blur" }
        ], 
        telNumber: [
          { required: true, message: "请输入电话号码", trigger: "blur" },
          {
            pattern: /^1[3456789]\d{9}$/,
            message: "请输入正确的电话号码",
            trigger: "blur"
          }
        ]
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
      /**
       * table内容
       */
      tableData: [],
      pageNum: 1,
      form: {},
      search: {
        realName: "",
        roleName: "",
        telNumber: "",
      },
      pageSize: 5,
      name: "",
      dialogFormVisible: false,
      total: 0,
      multipleSelection: [],
      roles:[],
      sources:[],
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
      editproduct: {}
    };
  },
  methods: {
    reset() { //重置搜索条件
      this.search.realName = "";
      this.search.roleName = "";
      this.search.telNumber = "";
      this.queryTableData();
    },
    resetPwd(row) { //重置密码
    let loading = Loading.service({
      text: "正在重置密码....",
      background: "rgba(0, 0, 0, 0.1)"
    });

    this.$axios({
      method: "put",
      url:
        this.$config.proxy() + "/users/reset/" + row.id
    })
      .then(resp => {
        loading.close();
        this.$alert(resp.data, row.realName + "【" + row.name + "】的初始化密码"
        );
      })
      .catch(err => {
        loading.close();
      });
    },
    saveUser(mode) { //新增或修改用户
      this.$refs["addForm"].validate(valid => {
        if (valid) {
          let loading = Loading.service({
            text: "正在保存用户信息....",
            background: "rgba(0, 0, 0, 0.1)"
          });
          if (mode === "create") {
            this.$axios({
            method: "post",
            data: { ...this.editproduct },
            url: this.$config.proxy() + "/users/save"
          })
            .then(resp => {
              loading.close();
              if (resp) {
                this.dialogFormVisible = false;
                this.$alert(resp.data, this.editproduct.name + "的初始化密码");
                this.queryTableData();
              }
            })
            .catch(err => {
              loading.close();
            });
          } else if(mode === "edit"){
              this.$axios({
                method: "put",
                data: { ...this.editproduct },
                url: this.$config.proxy() + "/users/update"
              })
              .then(resp => {
                loading.close();
                this.dialogFormVisible = false;
                this.queryTableData();
              })
              .catch(err => {
                loading.close();
              });
              
          }        
          } else {
            return false;
          }
          });            
    },
    deleteUser(row) {      //删除用户
      let loading = Loading.service({
        text: "正在删除用户....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.$axios({
        method: "delete",
        url: this.$config.proxy() + "/users/delete/" + row.id
      })
        .then(resp => {
          loading.close();
          this.queryTableData();
        })
        .catch(err => {
          loading.close();
        })
    },
    handleSelectionChange(val) {  //多选框选择
      this.multipleSelection = val;
    },
    delbatch() {  //批量删除
      let ids = this.multipleSelection;
      for(var i=0;i<ids.length;i++){
        this.deleteUser(ids[i]);
      }
    },
    /**
     * @description: 打开创建用户对话框
     */
    userDialog(mode, row) { //打开创建&编辑用户信息对话框      
      this.mode = mode;
      if (mode == "create") { //新增
        this.editproduct = {
          enabled: null,
          name: "",
          realname: "",
          roleName: "",
          telNumber: "",
        };
      } else if("edit") { //编辑
        this.editproduct = { ...row };
      }
      this.dialogFormVisible = true;
    },
    handleSizeChange(val) { //每页条数改变
      this.currentPage = 1;
      this.pageSize = val;
      this.queryTableData();
    },
    handleCurrentChange(val) { //当前页码改变
      this.currentPage = val;
      this.queryTableData();
    },
    doSearch() {
      this.currentPage = 1;
      this.pageSize = 10;
      this.queryTableData();
    },
    queryTableData() { //请求用户列表
      let loading = Loading.service({
        fullscreen: true,
        text: "数据初始化中....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.$axios({ //获取角色列表
        method: "get",
        url: this.$config.proxy() + "/roles/query?pageNum=1&pageSize=100"
      }).then(resp => {
        this.roles = resp.records;
      });
      let queryparams = { ...this.search };
      queryparams.pageNum = this.currentPage;
      queryparams.pageSize = this.pageSize;
      this.$axios({
        method: "get",
        url: this.$config.proxy() + "/users/query?pageNum=" + queryparams.pageNum + "&pageSize=" + queryparams.pageSize + "&realname=" + queryparams.realName + "&roleName=" + queryparams.roleName + "&telnumber=" + queryparams.telNumber
      }).then(resp => {
        loading.close();
        this.tableData = resp.records;
        this.total = resp.total;
      });
    },
    lookSource(sources) {
      this.sources = sources
      this.vis = true
    },      
  },
  mounted() {
    this.queryTableData();
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