<template>
  <div class="wrapper"   >

    <div style="margin: 200px auto; background-color : #fff; width: 450px; height: 450pX; padding: 20px; border-radius: 10px">

      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注 册</b></div>


      <el-input size="medium" style="margin: 10px 0" placeholder="请输入用户名" prefix-icon="el-icon-user" v-model="register.name"></el-input>
      <el-input size="medium" style="margin: 10px 0" placeholder="请输入密码" prefix-icon="el-icon-lock" show-password v-model="register.password"></el-input>

      <el-input size="medium" style="margin: 10px 0" placeholder="请确认密码"prefix-icon="el-icon-user"show-password  v-model="sure"></el-input>
      <el-input size="medium" style="margin: 10px 0" placeholder="请输入手机" prefix-icon="el-icon-user" v-model="register.phone"></el-input>


      <el-select size="medium" v-model="man"   placeholder="请选择身份"  class="ml-50" >
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>


      <div style="margin: 30px  0; text-align: center"     >
        <el-button type="primary" size="small" autocomplete="off" @click="regist">确定</el-button>
        <el-button type="warning" size="small" autocomplete="off"   @click="login">返回</el-button>

      </div>

    </div>


  </div>


</template>

<script>
export default {
  name:"register" ,
  data() {
    return {
      register:{name: "",password :"",phone:""},
      registerAnswer:{answerName: "",answerPassword :"",answerPhone:""},

      sure:"",
      options: [ {
        value: 'rent',
        label: '租户'
      },  {
        value: 'answer',
        label: '答者'
      },  ],
      man: ''

    }

  },
  created() {
    document.title = '注册'
  },
  methods:{

    login(){
      this.$router.push("/")

    },
    regist(){

      if(this.register.name ==="" || this.register.password ==="" ||this.sure ==="" ){
        this.$message.warning("请完善用户名或密码")
        return
      }
      if(this.man === ""){
        this.$message.warning("请选择身份")
        return
      }
      if( this.register.password !== this.sure ){
        this.$message.warning("密码不一致")
        return
      }

         if(this.man === "rent"){
        this.request.get("/rent/register",{
          params: {
            name : this.register.name,
            password: this.register.password,
            phone: this.register.phone


            //userUp: this.userUp,
          }}).then(res=>{
          if(res.code === '200'){
            //localStorage.setItem("man",JSON.stringify(res.data))
            this.$router.push("/")
            this.$message.success("租户注册成功")
          }else if(res.code ==='400'){
            this.$message.error("用户名已存在")
          }else if(res.code ==='401'){
            this.$message.error("手机已存在")
          }
        })
      } else if(this.man === "answer"){

           this.request.get("/answer/register",{
             params: {
               answerName : this.register.name,
               answerPassword: this.register.password,
               answerPhone: this.register.phone


               //userUp: this.userUp,
             }}).then(res=>{
          if(res.code === '200'){
            //localStorage.setItem("man",JSON.stringify(res.data))
            this.$router.push("/")
            this.$message.success("答者注册成功")
          }else if(res.code ==='400'){
            this.$message.error("用户名已存在")
          }else if(res.code ==='401'){
            this.$message.error("手机已存在")
          }
        })
      }

    }
  }
}
</script>

<style scoped>

</style>