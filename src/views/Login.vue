<template>
  <div class="wrapper"   >

    <div style="margin: 200px auto; background-color : #fff; width: 350px; height: 350pX; padding: 20px; border-radius: 10px">

      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>


      <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="log.name"></el-input>
      <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="log.password"></el-input>



      <el-select size="medium" v-model="man"   placeholder="请选择身份"  class="ml-50" >
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>


      <div style="margin: 30px  0; text-align: center"     >
        <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
        <el-button type="warning" size="small" autocomplete="off"   @click="regis">注册</el-button>

      </div>

    </div>


  </div>


</template>

<script>


export default {
  name: "login",
  data() {
    return {
      log:{name: "",password :""},
      answer:{answerName: "",answerPassword :""},
      options: [{
        value: 'admin',
        label: '管理员'
      }, {
        value: 'rent',
        label: '租户'
      }, {
        value: 'user',
        label: '用户'
      }, {
        value: 'answer',
        label: '答者'
      },  ],
      man: ''

    }

  },
  created() {
    document.title = '登录'
  },
  methods:{
    regis(){
      this.$router.push("/register")
    },

    login(){

      if(this.log.name ==="" || this.log.password ===""){
        this.$message.warning("请完善用户名或密码")
        return
      }
      if(this.man === ""){
        this.$message.warning("请选择身份")
        return
      }

      if(this.man === "admin"){
        this.request.post("/admin/login",this.log).then(res=>{
          if(res.code === '200'){
            localStorage.setItem("man",JSON.stringify(res.data))
            this.$router.push("/admin")
            this.$message.success("管理员登录成功")
          }else{
            this.$message.error("用户名或密码错误")
          }
        })
      }else if(this.man === "rent"){
        this.request.post("/rent/login",this.log).then(res=>{
          if(res.code === '200'){
            localStorage.setItem("man",JSON.stringify(res.data))
            this.$router.push("/rent")
            this.$message.success("租户登录成功")
          }else{
            this.$message.error("用户名或密码错误")
          }
        })
      }else if(this.man === "user"){
        this.request.post("/user/login",this.log).then(res=>{
          if(res.code === '200'){
            localStorage.setItem("man",JSON.stringify(res.data))
             this.$router.push("/user")
            this.$message.success("用户登录成功")
          }else{
            this.$message.error("用户名或密码错误")
          }
        })
      }else if(this.man === "answer"){
        this.answer.answerName=this.log.name
        this.answer.answerPassword=this.log.password
        this.request.post("/answer/login",this.answer).then(res=>{
          if(res.code === '200'){
            localStorage.setItem("man",JSON.stringify(res.data))
            this.$router.push("/answer")
            this.$message.success("答者登录成功")
          }else{
            this.$message.error("用户名或密码错误")
          }
        })
      }

    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
  overflow: hidden;
}
</style>