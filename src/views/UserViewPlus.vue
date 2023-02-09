<template>

  <el-container style="height:100vh">



    <el-aside :width="sideWidth +'px'" style="background-color: rgb(238, 241, 246);height:100%;box-shadow: 2px 0 6px rgb(0,21,30)">
      <UserAside :isCollapse2="isCollapse" :logo-text-show="logoTextShow"/>
      <!--      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>-->
    </el-aside>


    <el-container>

      <el-header  style=" border-bottom:1px solid #ccc; ">
        <UserHeader :collapse-btn-class="collapseBtnClass" :is-collapse="isCollapse"/>
        <!--        <Header :collapseBtnClass="collapseBtnClass" :isCollapse="isCollapse" />-->
      </el-header>
<!--      style=" background-color: bisque"-->
      <el-main   class="wrapper" >
        <!--        //子路由-->
        <router-view/>
      </el-main>

    </el-container>

  </el-container>
</template>

<script>



import UserAside from "@/components/UserAside";
import UserHeader from "@/components/UserHeader";

export default {
  name: "UserViewPlus",
  data() {

    return {

      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,

    }


  },
  components:{

    UserAside,
    UserHeader,
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
<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B, #3F5EFB);
  overflow: hidden;
}
</style>

<style scoped>

</style>