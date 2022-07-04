<template>
  <el-container>
    <el-main class="container">
        <table class="querybar">
          <tr>          
            <td style="width: 1200em">
              <router-link to="/batch/index"><el-button style="float: left; margin-right: 10px;" icon="el-icon-arrow-left" type="primary"></el-button> </router-link>
                <el-button style="float: left;" icon="el-icon-circle-plus" type="primary" @click="dialogAuthorVisible=true;disabled=true;">添加产品到进货单</el-button>        
                <el-button style="float: right;" icon="el-icon-check" type="primary" @click="submitOrder">提交进货单</el-button>        
            </td>     
          </tr>
        </table>
      <el-table :data="tableData" height="calc(100vh - 213px)" fit stripe border style="width:100%;" :highlight-current-row="false">
        <el-table-column type="index" label="序号" width="55" header-align="center" align="center"></el-table-column>
        <el-table-column prop="sortName" label="产品分类" header-align="center" align="center" min-width="80"></el-table-column>
        <el-table-column
          prop="productName"
          label="产品名称"
          header-align="center"
          align="center"
          min-width="160"
        ></el-table-column>
        <el-table-column
          prop="productPricePurchase"
          label="产品进价(元)"
          header-align="center"
          align="center"
          min-width="60"
        ></el-table-column>
        <el-table-column
          prop="purchaseCount"
          label="进货数量"
          header-align="center"
          align="center"
          min-width="80"
        >
          <template slot-scope="scope">
            <el-input-number size="mini" @change="sum(row)" v-model="scope.row.purchaseCount"></el-input-number>
          </template> 
        </el-table-column>
        
        <el-table-column
          prop="msg"
          label="备注"
          header-align="center"
          align="center"
          min-width="160"
        ></el-table-column>
        <el-table-column
          prop="totalPrice"
          label="合计(元)"
          header-align="center"
          align="center"
          min-width="60"
        >
        </el-table-column>
        <el-table-column label="操作" align="center" header-align="center" width="160">
          <template slot-scope="scope">
            <el-button @click.native.prevent="deleteAuthor(scope.$index, tableData)" type="text" size="small">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 新增进货子项弹窗 -->
      <el-dialog title="添加产品" :visible.sync="dialogAuthorVisible" width="500px" @close="resetnewProduct">
          <el-form :model="newProduct" :rules="rules"  ref="newProduct">           
            <el-form-item label="产品种类：" :label-width="'100px'" prop="sortName">
             <el-select
                  v-model="newProduct.sortName"
                  placeholder="请选择产品种类"
                  @change="changeset"
                >
                  <el-option
                    v-for="item in sortlist"
                    :key="item.value"
                    :label="item.sortName"
                    :value="item.sortName"
                  >
                  </el-option>
                </el-select>  
            </el-form-item>
            <el-form-item label="分类编号：" :label-width="'100px'" prop="sort_id" hidden>
              <el-input v-model="newProduct.sort_id"></el-input>
            </el-form-item> 
            <el-form-item label="产品名称：" :label-width="'100px'" prop="productName">
              <el-select v-model="newProduct.productName" placeholder="请选择产品" :disabled="disabled" @change="changePrice">
                  <el-option
                    v-for="item in productlist"
                    :key="item.value"
                    :label="item.productName"
                    :value="item.productName"
                  >
                  </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="产品进价：" :label-width="'100px'" prop="productPricePurchase" >
              <el-input size="medium" v-model="newProduct.productPricePurchase" :disabled="true"></el-input>
            </el-form-item>   
            <el-form-item label="产品编号：" :label-width="'100px'" prop="productId" >
              <el-input size="medium" v-model="newProduct.productId" :disabled="true"></el-input>
            </el-form-item>  
            <el-form-item label="产品数量：" :label-width="'100px'" prop="purchaseCount">
              <el-input-number size="medium" v-model="newProduct.purchaseCount"></el-input-number>
            </el-form-item> 
            <el-form-item label="备注" :label-width="'100px'" prop="msg">
              <el-input v-model="newProduct.msg" autocomplete="off" style="width: 300px"></el-input>
            </el-form-item> 
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogAuthorVisible = false">取 消</el-button>
            <el-button type="primary" @click="addNewProduct('newProduct')">确 定</el-button>
          </div>
        </el-dialog>

        <!-- 确认提交弹窗 -->
        <el-dialog title="是否确认提交进货单？" :visible.sync="submitVisible" width="500px" @close="resetSubmit()">
          <el-form :model="allSubmit"  ref="allsubmit">
            <el-form-item label="备注" :label-width="'100px'" prop="msg">
              <el-input v-model="allSubmit.msg" autocomplete="off" style="width: 300px" placeholder="在此处添加进货单备注"></el-input>
            </el-form-item> 
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="submitVisible = false">取 消</el-button>        
            <el-button type="primary" @click="submitAll">确 定</el-button>
          </div>
        </el-dialog>
    </el-main>
  </el-container>
</template>
<script>
import { Loading } from "element-ui";
export default {
  data() {
      return {
        rules: { 
          sortName: [
            { required: true ,  message: '产品分类不能为空',tigger: 'change'}
          ],
          productName: [
            { required: true , message: '产品名称不能为空',tigger: 'change'}
          ],
          purchaseCount: [
            { required: true , type: "number", message: '请输入正确的产品数量',tigger: 'blur'},
            {
              validator: (rule, value, callback) => {
                if (value < 1) {
                  callback(new Error("产品库存不能小于1"));
                } else {
                  callback();
                }
              }
          }
          ]
        },
      /**
       *弹出对话框的模式
       */
      mode: "create",
      
      searchObj: { queryKey: "", checked: false },
      /**
       * 显示新增对话框
       */
      submitVisible: false,  //提交弹窗显示/隐藏
      /**
       * table内容
       */
      tableData: [],
      newProduct: {  //新增进货子项
        sort_id: "",  //进货子项分类编号
        sortName: "",  //进货子项分类名称
        productName: "",  //进货子项产品名称
        purchaseCount: 1,  //进货子项数量
        productPricePurchase: "",  //进货子项产品进货单价
        productId: "",  //进货子项产品id
        msg: "",  //进货子项备注
      },
      sortlist: [], //分类列表
      allSubmit: {  //新增进货总表
        purchaseChargePersonId: JSON.parse(sessionStorage.getItem('user')).id,  //操作员
        purchaseSonList: [],  //进货子项数组
        msg: "",  //备注
      },
      
      //分类下产品
      productlist: [], //级联选择产品名称列表
      product: [],  //所有产品列表
      disabled: true, //产品名称选择器禁用属性
      dialogAuthorVisible: false,  //添加子项弹窗隐藏属性
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
    handleCurrentChange(val) {  //页码改变时触发
      this.currentPage = val;
      this.queryTableData();
    },     
    changeset() {//获取分类下产品数据      
      let data = this.product;
      let sortId = this.sortlist;
      sortId.forEach((e) => {  //获取选中分类的的sort_id
        if(e.sortName === this.newProduct.sortName) {
          this.newProduct.sort_id = e.sortId
        }
      })
      this.disabled = true
      
      // this.newProduct.
      //如果分类不为空
      if (this.newProduct.sortName) {
        //切换的时候产品清空，防止bug
        this.productlist = [];
        //禁用取消        
        this.disabled = false;
        //遍历产品里面的数据
        data.forEach((e) => {
          //如果data里面sort等于this.newProduct.sort
          if (e.sortName === this.newProduct.sortName) {
            //将符合的数据push进产品数组
            this.productlist.push(e);
          }
        });
        //选中分类，产品默认选中第一个
        if (this.productlist.length > 0) {
          this.newProduct.productName = this.productlist[0].productName
          this.newProduct.productPricePurchase = this.productlist[0].productPricePurchase
          this.newProduct.productId = this.productlist[0].productId
        }
        else {
      //选中的分类为空，则清空产品列表
          this.newProduct.productName = ""
          this.newProduct.productPricePurchase = ""
          this.newProduct.productId = ""
          this.productlist = [];
          this.disabled = true;
        }
      } 
    },
    changePrice() {  //获取进货子项进货单价与产品编号
      this.product.forEach(e => {
        if(this.newProduct.productName == e.productName) {
          this.newProduct.productPricePurchase = e.productPricePurchase
          this.newProduct.productId = e.productId
        }
      })
      
    },
    sum(row) {  //计算进货子项的单品总价
      row.totalPrice = row.productPricePurchase * row.purchaseCount
    },
    resetSubmit() { //重置提交表单内容
      this.allSubmit.purchaseSonList = []
      this.allSubmit.msg = ''
      this.$refs['allsubmit'].resetFields();
    },
    resetnewProduct() {  //重置添加进货子项表单内容
      this.newProduct.sort_id = '';  //产品分类
      this.newProduct.productName = '';  //产品名称
      this.newProduct.purchaseCount = '';  //进货数量
      this.newProduct.msg = '';  //产品备注
      this.$refs['newProduct'].resetFields();
    },
    addNewProduct(formName) { //添加进货商品
      this.$refs[formName].validate((valid) => { //添加表单验证
        if (!valid) { //表单验证不通过
          this.$message.warning('请添加正确的商品')
        } else { //表单验证通过
          this.newProduct.totalPrice = this.newProduct.purchaseCount * this.newProduct.productPricePurchase
          if(this.tableData.length==0){ //商品列表为空
          this.tableData.push(Object.assign({},this.newProduct));
          }else { //商品列表不为空
          
            for(let i=0;i<this.tableData.length;i++){ //遍历商品列表
              if(this.tableData[i].productName==this.newProduct.productName){//判断商品是否已存在
                  this.$message({
                    message: '该产品已存在',
                    type: 'warning'
                  });
                  return;
              }
            }
            this.tableData.push(Object.assign({},this.newProduct));//如果不存在，则将表单对象添加到进货单
          }
          this.total = this.tableData.length;   //变更进货单条目
          this.dialogAuthorVisible = false;//关闭弹窗
        }
      });
    },
    deleteAuthor(index, rows) {  //移除进货子项
      this.$confirm("是否从进货单移除此商品?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        iconClass: "el-icon-question red"
      })
        .then(_ => {
      rows.splice(index, 1); //删除选中行的数据
       this.total = this.tableData.length;   //变更进货单条目
      })
    },
    getSortId() {
      this.$axios({ //获取产品分类列表
        method: "get",
        url: this.$config.proxy() + "/sort/list"
        // url: "http://110.40.152.26:8080/sort/list"
      }).then(resp => {
        this.sortlist = resp.data;        
      });
    },
    getProductList() {
      this.$axios({ //获取产品名称列表
        method: "get",
        url: this.$config.proxy() + "/product/list"
        // url: "http://110.40.152.26:8080/product/list"
      }).then(resp => {
        this.product = resp.data.data;        
      });
    },
    submitOrder() {  //提交所有进货子项到进货总表
      let loading = Loading.service({
        fullscreen: true,
        text: "添加进货单到提交队列....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.submitVisible = true;
      if (this.tableData.length > 0) {
        for(var i=0;i<this.tableData.length;i++) {
          this.$axios({
            method: "post",
            data: { ...this.tableData[i]},
            url: this.$config.proxy() + "/purchase/son/add"
          }).then(resp => {
            if(resp.code == "200") {
              this.allSubmit.purchaseSonList.push(resp.data)
            } else {
              this.$message.err("添加失败")
            }
          })
        }
      } else {
        loading.close()
        this.submitVisible = false
        this.$message.warning("进货表为空")
        return false;
      }
      loading.close()
    },
    submitAll() { //提交进货总表
      let loading = Loading.service({
        fullscreen: true,
        text: "进货单提交中....",
        background: "rgba(0, 0, 0, 0.1)"
      });
      this.$axios({
        method: "post",
        data: { ...this.allSubmit },
        url: this.$config.proxy() + "/purchase/add"
      }).then(resp => {
        if(resp.code == "200") {
          loading.close()
          this.submitVisible = false
          this.$message.success("提交完成")
          this.$router.push("/batch/index")
        } else {
          loading.close()
          this.$message.error("提交失败")
        }
      })
    }   
  },
  mounted() {
    this.getSortId();
    this.getProductList();
  },
};

</script>

<style  lang="scss" scoped>

</style>