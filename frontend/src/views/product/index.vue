<template>
  <el-container>
    <el-main class="container">
      <div class="search" >
        <!--     操作     -->
        <el-button style="margin-left: 0px; float: left;" type="primary" @click="productDialog('create')" ><i class="el-icon-circle-plus-outline"></i>添加商品</el-button>
        <el-popconfirm
            style="margin-left: 5px;"
            confirm-button-text='确认'
            cancel-button-text='考虑一下'
            icon="el-icon-info"
            icon-color="red"
            title="确定要删除这些内容？"
            @confirm="delbatch"
        >
          <el-button style="margin-left: 5px; float: left;" type="danger" slot="reference"><i class="el-icon-remove-outline"></i>批量删除</el-button>
        </el-popconfirm>
          <router-link to="/product/sort"><el-button style="margin-left: 5px; float: left;" type="primary" ><i class="el-icon-tickets"></i>分类管理</el-button></router-link>
      <!-- 搜索-->        
        <el-button style="float: right; margin-right:35px;" class="ml-5" type="warning" @click="reset">重置</el-button>
        <el-button style="float: right; margin-right: 10px;" class="ml-5" type="primary" @click="doSearch">搜索</el-button>
        <el-input style="width: 200px; margin: 0 5px; float: right;" v-model="search.query" placeholder="输入产品名称进行查询"></el-input>
      </div>
      <!--数据主体展示-->
      <el-table id="user-table" ref="userTable" :data="tableData" border stripe @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
         <el-table-column type="index" label="序号" width="55" header-align="center" align="center">
          <template slot-scope="scope">
            {{(currentPage - 1) * pageSize + scope.$index + 1}}
          </template>
         </el-table-column>
         <el-table-column prop="sortName" align="center" label="产品分类" min-width="60">
        </el-table-column>
        <el-table-column prop="productName" align="center" label="产品名称" min-width="70">
        </el-table-column>
        <el-table-column prop="productPricePurchase" align="center" label="进货单价" min-width="60">
        </el-table-column>
        <el-table-column prop="productPriceShipment" align="center" label="出货单价" min-width="60">
        </el-table-column>
        <el-table-column prop="productPriceSuggest" align="center" label="建议零售价" min-width="80">
        </el-table-column>
        <el-table-column prop="productFormat" align="center" label="商品规格" min-width="60">
        </el-table-column>
        <el-table-column prop="state" align="center" label="商品状态"  min-width="60">
          <template slot-scope="scope" >
            <el-tag type="primary" v-if="scope.row.state=='1'">在售</el-tag>
            <el-tag type="danger" v-if="scope.row.state=='0'">停售</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="name" align="center" label="操作员" min-width="60" >
        </el-table-column>
        <el-table-column prop="productDescribe" label="产品描述" align="center"  min-width="160">
        </el-table-column>
        <el-table-column label="操作" align="center" width="300">
          <template slot-scope="scope">
            <el-button type="success" @click="productDialog('edit',scope.row)"><i class="el-icon-edit"></i>编辑</el-button>
            <el-popconfirm
                style="margin-left: 5px;"
                confirm-button-text='确认'
                cancel-button-text='考虑一下'
                icon="el-icon-info"
                icon-color="red"
                title="确定要删除此内容？"
                @confirm="deleteProduct(scope.row)"
            >
              <el-button type="danger" slot="reference" size="medium"><i class="el-icon-delete"></i>删除</el-button>
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
      <el-dialog title="产品信息" :visible.sync="dialogFormVisible" width="40%">
        <el-form label-width="70px" ref="addForm" :rules="rules" :model="editproduct">
          <el-form-item label="产品名称" prop="productName" label-width="22%">
            <el-input v-model="editproduct.productName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="产品分类" prop="sortId" label-width="22%">
            <el-select clearable v-model="editproduct.sortId" placeholder="请选择产品分类" style="width: 100%">
               <el-option
                  v-for="item in sortList"
                  :key="item.sortId"
                  :label="item.sortName"
                  :value="item.sortId">
                </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="产品描述" prop="productDescribe" label-width="22%">
            <el-input v-model="editproduct.productDescribe" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="进货单价" prop="productPricePurchase" label-width="22%">
            <el-input v-model="editproduct.productPricePurchase" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="出货单价" prop="productPriceShipment" label-width="22%">
            <el-input v-model="editproduct.productPriceShipment" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="建议零售价" prop="productPriceSuggest" label-width="22%">
            <el-input v-model="editproduct.productPriceSuggest" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="产品规格" prop="productFormat" label-width="22%">
            <el-input v-model="editproduct.productFormat" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="产品状态" prop="state" label-width="22%" v-if="editproduct.productId != ''">
            <el-select clearable v-model="editproduct.state" placeholder="请选择产品状态" style="width: 100%">
               <el-option
                  v-for="item in productState"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="操作员" prop="personId" label-width="22%" hidden>
            <el-input v-model="editproduct.personId" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="savePruduct(mode)">确 定</el-button>
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
        productName: [
          { required: true, message: "请输入产品名称", trigger: "blur" }
        ],
        sortName: [
          { required: true, message: "请选择产品种类", trigger: "blur" }
        ],
        productPricePurchase: [
          { required: true, message: "请输入正确的产品进货单价", trigger: "blur" },
          { pattern: /^(\\+)?\d+(\.\d+)?$/ ,message: "请输入正确的进货单价", trigger: "blur"}
        ], 
        productPriceShipment: [
          { required: true, message: "请输入产品出货单价", trigger: "blur" },
          { pattern: /^(\\+)?\d+(\.\d+)?$/ ,message: "请输入正确的出货单价", trigger: "blur"}
        ], 
        productPriceSuggest: [
          { required: true, message: "请输入产品建议零售价", trigger: "blur" },
          { pattern: /^(\\+)?\d+(\.\d+)?$/ ,message: "请输入正确的建议零售价", trigger: "blur"}
        ], 
        productFormat: [
          { required: true, message: "请输入产品规格", trigger: "blur" },
        ], 
      },
      /**
       *弹出对话框的模式
       */
      mode: "create",
      /**
       * 显示新增对话框
       */
      showCreateDialog: false,
      /**
       * table内容
       */
      tableData: [],
      sortList: [],  //分类列表
      productState:[  //产品状态列表
        {
          label: "在售",
          value: 1
        }, 
        {
          label: "停售",
          value: 0
        }, 
      ],
      search: {
        query: "",
      },
      dialogFormVisible: false,
      total: 0,
      multipleSelection: [],
      userId: JSON.parse(sessionStorage.getItem('user')).id,  //获取操作员编号
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
      editproduct: {  
        personId: this.userId
      }
    };
  },
  methods: {
    reset() { //重置搜索条件
      this.search.query = "";
      this.queryTableData();
    },
    savePruduct(mode) { //新增或修改用户
      this.$refs["addForm"].validate(valid => {
        if (valid) {
          let loading = Loading.service({
            text: "正在保存产品信息....",
            background: "rgba(0, 0, 0, 0.1)"
          });
          if (mode === "create") {
            this.$axios({
            method: "post",
            data: { ...this.editproduct },
            url: this.$config.proxy() + "/product/add"
          })
            .then(resp => {
              loading.close();
              this.dialogFormVisible = false;
              if (resp.code == "200") {                
                this.$message.success("添加成功")
                this.queryTableData();
              } else {
                this.$message.error("添加失败")
              }
            })
            .catch(err => {
              loading.close();
              this.$message.error(err.msg)
            });
          } else if(mode === "edit"){
              this.$axios({
                method: "post",
                data: { ...this.editproduct },
                url: this.$config.proxy() + "/product/update"
              })
              .then(resp => {
                this.dialogFormVisible = false;
                loading.close();
                if (resp.code == "200") {                  
                  this.$message.success("修改成功")
                  this.queryTableData();
                } else {
                  this.$message.error("修改失败")
                }
              })
              .catch(err => {
                loading.close();
                this.$message.error(err.msg)
              });
              
          }        
          } else {
            return false;
          }
          });            
    },
    deleteProduct(row) {      //删除产品
      let loading = Loading.service({
        text: "正在删除产品....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.$axios({
        method: "post",
        url: this.$config.proxy() + "/product/del/" + row.productId
      })
        .then(resp => {
          loading.close();
          if(resp.code == "200") {
            this.$message.success("删除成功")
          } else {
            this.$message.error("删除失败")
          }
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
        this.deleteProduct(ids[i]);
      }
    },
    /**
     * @description: 打开创建用户对话框
     */
    productDialog(mode, row) { //打开创建&编辑用户信息对话框      
      this.mode = mode;
      
      if (mode == "create") { //新增
        this.editproduct = {
          productId: "",  // 产品编号
          productName: "",  //产品名称
          productDescribe: "",  //产品描述
          productPricePurchase: "",  //进货单价
          productPriceShipment: "", //出货单价
          productPriceSuggest: "", //建议零售价
          productFormat: "", //产品规格
          state: "",  //产品销售状态
          personId: this.userId  //操作员
        };
      } else if("edit") { //编辑
        this.editproduct = { ...row };
        this.editproduct.personId = this.userId
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
      this.$axios({ //获取分类列表
        method: "get",
        url: this.$config.proxy() + "/sort/list"
      }).then(resp => {
        this.sortList = resp.data;
      });
      let queryparams = { ...this.search };
      queryparams.pageNum = this.pageSize;
      queryparams.pageSize = this.currentPage;
      this.$axios({
        method: "post",
        data: { ...queryparams },
        url: this.$config.proxy() + "/product/query/page" 
      }).then(resp => {
        if(resp.code == "200") {
          loading.close();
          this.tableData = resp.data.productShowList;
          this.total = resp.data.total;
        } else {
          loading.close();
          this.$message.error("查询失败")
        }
        
      });
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