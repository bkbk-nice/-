<template>
  <div>


    <el-card class="box-card" shadow="hover" body-style=""  header="个人信息" style=" padding: 18px 0; width: 450px;">
      <el-form ref="form" :model="form"  style="width:600px" label-width="80px">


        <el-form-item label="管理员ID" label-width="200">
          <el-input v-model="form.id"   disabled maxlength="10"  style="width: 300px"></el-input>
        </el-form-item>


        <el-form-item label="管理员名称" label-width="200">
          <el-input v-model="form.name"  maxlength="10"  style="width: 300px"></el-input>
        </el-form-item>

        <el-form-item label="管理员密码" label-width="200">
          <el-input show-password  maxlength="10"  v-model="form.password"  style="width: 300px"></el-input>
        </el-form-item>


        <el-form-item label="管理员手机" label-width="200">
          <el-input v-model="form.phone"  maxlength="10"   style="width: 300px"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">确定修改</el-button>
          <el-button type="danger" @click="logout">退出登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>



  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Person",
  data() {
    return {
      man:localStorage.getItem("man") ?JSON.parse( localStorage.getItem("man")):{},

      form: {
        id:'',
        name:'',
        password: '',
        phone:'',
      }
    }
  },
  created() {
    this.form.id = this.man.id;
    this.form.name = this.man.name;
    this.form.password = this.man.password;
    this.form.phone = this.man.phone;

    //this.loadself()
    document.title = '个人信息'
  },

  methods: {
    logout() {
      this.$router.push("/");
      localStorage.removeItem("man")
      this.$message.success("退出成功")
    },

    onSubmit() {

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

      this.request.post("/admin",this.form).then(res=>{
        if(res){
          this.$message.success("保存成功")
          this.$router.push("/");
          localStorage.removeItem("man")
          this.$message.success("请重新登录")

         // this.load()
        }else{
          this.$message.warning("保存失败")
        }
      })

      console.log('submit!');
    },

    // loadself(){
    //   this.request.get("/admin/name" , {
    //     params: {
    //       name: this.man.name,
    //     }
    //   }).then(res => {
    //     console.log(res.data)
    //     this.form.name = res.data.name;
    //     this.form.phone = res.data.phone;
    //   })
    // },

  }
}
</script>

<style scoped>

</style>