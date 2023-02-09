<template>


  <el-card class="box-card" shadow="hover" body-style=""  header="个人信息" style=" padding: 18px 0; width: 1050px;">

    <div style="padding: 10px 0">
      <el-input style="width: 200px"   maxlength="10"  placeholder="请输入id" suffix-icon="el-icon-search"
                v-model="userId"></el-input>
      <el-input class="ml-5" style="width: 200px;"    maxlength="10"   placeholder="请输入用户名称" suffix-icon="el-icon-search"
                v-model="userName"></el-input>
      <el-input class="ml-5" style="width: 200px"  maxlength="10"  placeholder="请输入手机" suffix-icon="el-icon-search"
                v-model="userPhone"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="clearSearch">重置</el-button>
    </div>


    <div style="padding: 10px 0">
      <el-button type="primary" @click="addBtn">新增<i class="el-icon-circle-plus-outline"></i></el-button>



      <el-popconfirm
          class="ml-15"
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="确定删除吗？"
          @confirm="delBatch"
      >
        <!--            <el-button type="danger" slot="reference" >删除<i class="el-icon-remove-outline"></i></el-button>-->

        <el-button type="danger" slot="reference" >批量删除<i class="el-icon-delete"></i></el-button>
      </el-popconfirm>



      <el-button type="primary" class="ml-15">导入<i class="el-icon-bottom"></i></el-button>
      <el-button type="primary">导出<i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" @selection-change="handleSelectionChange">>


      <el-table-column
          type="selection"
          width="55">
      </el-table-column>


      <el-table-column prop="id" label="id" width="140">
      </el-table-column>
      <el-table-column prop="name" label="用户名称" width="120">
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="220">
      </el-table-column>
      <el-table-column prop="up" label="可发布问卷上限" width="70">
      </el-table-column>
      <el-table-column prop="groupnum" label="群组数量" width="50">
      </el-table-column>
      <el-table-column prop="answernum" label="答者数量" width="50">
      </el-table-column>
      <el-table-column prop="qnnum" label="问卷数量" width="50">
      </el-table-column>

<!--      <el-table-column prop="fee" label="费用" width="100">-->
<!--      </el-table-column>-->


      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>


          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="handleDelete(scope.row.id)"
          >
            <el-button type="danger" slot="reference" >删除<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>

          <!--              @click="handleDelete(scope.row.id)"-->

        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10,15,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

    </div>


    <el-dialog  :visible.sync="dialogFormVisible"  width="30%"  >
      <el-form label-width="80px">
        <el-form-item label="用户名" >
          <el-input v-model="form.name"   maxlength="10"  autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="密码"  >
          <el-input v-model="form.password"  maxlength="10"  autocomplete="off" show-password></el-input>
        </el-form-item>

        <el-form-item label="手机">
          <el-input v-model="form.phone"   maxlength="20" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="问卷上限">
          <el-input v-model="form.up" maxlength="10"  autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="addSave">确 定</el-button>
      </div>
    </el-dialog>


  </el-card>

<!--  <div>-->
<!--    <h1>rentview</h1>-->
<!--  </div>-->

</template>

<script>
export default {
  name: "RentView",
  data(){
    return{

      man:localStorage.getItem("man") ?JSON.parse( localStorage.getItem("man")):{},
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      form: {id:'',leader:'',name: '',password: '',phone: '',up:''},
      dialogFormVisible:false,
      multipleSelection:[],
      userId: "",
      userName: "",
      userPhone: "",
      userUp: "",
      headerBg: 'headerBg',




    }
  },
  created() {

    this.load()
    this.form.leader=this.man.id
    document.title = '用户信息'
  },


  methods :{

    load(){
      this.request.get("/user/page",{
        params: {
          leader : this.man.id,
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userId: this.userId,
          userName: this.userName,
          userPhone: this.userPhone,
          //userUp: this.userUp,
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.records
        this.total = res.total
      })
    },

    cancel(){
      this.form.id=""
      this.form.name=""
      this.form.password=""
      this.form.phone=""
      this.form.up=""
      this.dialogFormVisible=false;
      this.load();
    },
    clearSearch() {
      this.userId = ""
      this.userName = ""
      this.userPhone = ""
      this.load()
    },


    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },


    delBatch(){
      // let ids = this.multipleSelection.map(v=>v.id)
      //
      // this.request.post("/user/del/batch",ids).then(res=>{
      //   if(res){
      //     this.$message.success("批量删除成功")
      //     this.load()
      //   }else{
      //     this.$message.warning("批量删除失败")
      //   }
      // })
    },


    handleSelectionChange(val){
      this.multipleSelection = val;
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },


    addBtn(){

      this.dialogFormVisible=true
       this.form.id=""
      this.form.name=""
      this.form.password=""
      this.form.phone=""
      this.form.up=""
    },


    handleDelete(id){
      this.request.delete("/user/"+id).then(res=>{
        if(res){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.warning("删除失败")
        }
      })
    },

    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },


    addSave(){
      if(this.form.name==null ||this.form.name==="" ){
        this.$message.warning("用户名不能为空")
        return
      }

      if(this.form.password==null ||this.form.password==="" ){
        this.$message.warning("密码不能为空")
        return
      }

      if(this.form.phone==null ||this.form.phone==="" ){
        this.$message.warning("手机不能为空")
        return
      }

      if(this.form.up==null ||this.form.up==="" ){
        this.$message.warning("可发布问卷上限不能为空")
        return
      }

      this.request.post("/user",this.form).then(res=>{
        if(res){
          this.$message.success("保存成功")
          this.dialogFormVisible=false;
          this.load()
        }else{
          this.$message.warning("用户名或手机号已存在")
        }
      })
    }
  }

}
</script>

<style scoped>

</style>
<style>
.headerBg {
  background-color: #eee !important;
}
</style>