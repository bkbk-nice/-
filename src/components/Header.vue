<template>
<!--  -->
  <div  style=" font-size: 18px; line-height:60px ;display:flex;  " >
    <div  style=" font-size: 20px; flex:1; ">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="isCollapse"></span>
    </div>

    <el-dropdown style="width: 100px ; cursor: pointer">

      <i class="el-icon-caret-bottom" style="margin-right:10px"></i>
      <span>{{ man.name }}</span>

<!--      <i class="el-icon-arrow-down" style="margin-left:10px"></i>-->

      <el-dropdown-menu slot="dropdown" style="width: 120px ; cursor: pointer" >


<!--        <el-dropdown-item style="font-size: 14px ; padding: 5px 0" >-->
<!--          <span style="text-decoration: none" @click="editself">个人信息</span>-->
<!--        </el-dropdown-item>-->



        <el-dropdown-item style="font-size: 14px ; padding: 5px 0" >
          <span style="text-decoration: none" @click="logout">退出</span>
          </el-dropdown-item>


      </el-dropdown-menu>
    </el-dropdown>





  </div>


</template>

<script>
import Aside from "@/components/Aside";
import AdminViewPlus from "@/views/AdminViewPlus";
import request from "@/utils/request";


export default {
  name: "Header",
  props:{
    collapseBtnClass: String,
    isCollapse: Boolean,
  },
  data(){
    return{
      man:localStorage.getItem("man") ?JSON.parse( localStorage.getItem("man")):{},

      dialogFormVisible:false,

      form: {name: '',password: '',phone: '',},
    }
  },
  methods : {

    logout(){
      this.$router.push("/");
      localStorage.removeItem("man")
      this.$message.success("退出成功")
    },



  }
}
</script>

<style scoped>

</style>