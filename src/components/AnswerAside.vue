<template>
  <el-menu :default-openeds="['1', '3']" style="min-height:100%;overflow-x:hidden"
           background-color="rgb(46, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >

    <div style="height: 60px ; line-height: 60px;  text-align: center">
      <img src="../assets/logo.png" alt="" style="width:20px;position: relative;top:5px ;margin-right: 5px ">
      <b style="color:white" v-show="logoTextShow">
        答者系统
      </b>
    </div>


    <el-menu-item index="/answer">
      <i class="el-icon-house"></i>
      主页</el-menu-item>

    <el-submenu index="1">
      <template slot="title"><i class="el-icon-s-management"></i>
        <span slot="title">答者系统</span>
      </template>

      <el-menu-item index="/answer/answerView">
        <i class="el-icon-user"></i>
        问卷</el-menu-item>

      <el-menu-item index="/answer/answerPerson">
        <i class="el-icon-s-custom"></i>
        个人信息</el-menu-item>

    </el-submenu>

  </el-menu>
</template>

<script>
export default {
  name: "AnswerAside",
  props:{
    isCollapse: Boolean,
    logoTextShow: Boolean,


  },
  methods:{

  }
}
</script>

<style scoped>

</style>