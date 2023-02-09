<template>


  <el-container style="height:100vh">

    <el-aside :width="sideWidth +'px'" style="background-color: rgb(238, 241, 246);height:100%;box-shadow: 2px 0 6px rgb(0,21,30)">
          <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
    </el-aside>

    <el-container>

      <el-header  style=" border-bottom:1px solid #ccc; ">
        <Header :collapseBtnClass="collapseBtnClass" :isCollapse="isCollapse" />
      </el-header>

      <el-main class="wrapper">
<!--        //子路由-->
        <router-view/>
      </el-main>

    </el-container>

  </el-container>

  <!-- Form -->




</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: "AdminViewPlus",
  data() {

    return {

      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,

    }




  },
  components:{
    Aside,
    Header,
  },


  methods: {


      collapse() {
        this.isCollapse = !this.isCollapse;
        if (this.isCollapse) {
          this.sideWidth = 64
          this.collapseBtnClass = 'el-icon-s-unfold'
          this.logoTextShow = false
        } else {
          this.sideWidth = 200
          this.collapseBtnClass = 'el-icon-s-fold'
          this.logoTextShow = true
        }

      },


  }
}
</script>


<style>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>



<style scoped>

</style>