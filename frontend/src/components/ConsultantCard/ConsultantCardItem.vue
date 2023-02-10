<template>
  <div class="login-page" ng-app="">

<div class="login-content login-content-signin" ng-hide="showSignIn">
  <div>
    <h2>Log in</h2>
    <form class="wrapper-box" role="form" ng-submit="login()">
      <input type="email"
        ng-model="email"
        class="form-control form-control-email"
        placeholder="Email address"
        required>
      <input type="password"
        ng-model="password"
        class="form-control form-control-password"
        placeholder="Password"
        required >
      <!-- <div class="checkbox pull-left">
        <label>
          <input type="checkbox"> Remember me.
        </label>
      </div> -->
      <a class="outer-link pull-left" href="#/forgot">Forgot Password</a>
      <button type="submit" class="btn btn-submit btn-default pull-right">Log in</button>
    </form>
  </div>
</div>

<div class="login-content login-content-signup ng-hide" ng-show="showSignIn">
  <div>
    <h2>Sign Up</h2>
    <form class="wrapper-box" role="form" ng-submit="register()">
      <input type="text"
        ng-model="username"
        class="form-control form-control-username"
        placeholder="Username"
        required >
      <input type="email"
        ng-model="email"
        class="form-control form-control-email"
        placeholder="Email address"
        required >
      <input type="password"
        ng-model="password"
        class="form-control form-control-password"
        placeholder="Password"
        required >
      <!-- <div class="checkbox pull-left">
        <label>
          <input type="checkbox"> Remember me.
        </label>
      </div> -->
      <button type="submit" class="btn btn-submit btn-default pull-right">Sign up</button>
    </form>
  </div>
</div>



<div class="login-switcher">
  <div class="login-switcher-signin" ng-show="showSignIn">
    <h3>Have an account?</h3>
    <button ng-click="showSignIn=false">Login</button>
  </div>
  <div class="login-switcher-signup" ng-hide="showSignIn">
    <h3>Don't have an account?</h3>
    <button ng-click="showSignIn=true">Sign Up</button>
  </div>
</div>


</div>
<!-- <div id="fh5co-main">
  <div class="fh5co-narrow-content">
    <div>

      <div>
        <ConsultantCardItemInfo
        :counselorData="this.counselorData"/>
      </div>
    </div>
      <v-card>
        <v-tabs v-model="tab">
          <v-tab value="one">
            상담 예약
          </v-tab>
          <v-tab value="two">
            상담 후기 
          </v-tab>
        </v-tabs>
        <v-card-text>
          <v-window v-model="tab">
            <v-window-item
            value="one"
            style="height:1500px"
            >
            <ConsultantCardItemReserve
            :counselorData="this.counselorData"/>
            </v-window-item>
  
            <v-window-item  
              value="two"
              style="height:1500px"
            >
            <ConsultantCardItemReview
            :counselorData="this.counselorData"/>
            </v-window-item>
          </v-window>
        </v-card-text>
      </v-card>


   </div>
  </div> -->
</template>

<script>
import ConsultantCardItemReserve from '@/components/ConsultantCard/ConsultantCardItemReserve.vue'
import ConsultantCardItemReview from '@/components/ConsultantCard/ConsultantCardItemReview.vue'
import ConsultantCardItemInfo from '@/components/ConsultantCard/ConsultantCardItemInfo.vue'
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
    name:'ConsultantCardItem',
    components: {
      ConsultantCardItemReserve,
      ConsultantCardItemReview,
      ConsultantCardItemInfo
    },
    props:{
      id:String
    },
    data(){
      return{
        tab:null,
        counselorId:null,
        counselorData:[],
      }
    },
    methods:{ 
      changePropstoData(){
        this.counselorId = this.id
      },  
      getCounselorData(){
        axios({
          method:'get',
          url:`${VUE_APP_API_URL}/api/counselor/${this.id}`,
          data:{
            id:this.counselorId
          },

        })
        .then(res=>{
          this.counselorData = res.data
        })
      }
    },
    created() {
      this.changePropstoData()
      this.getCounselorData()
  }
}
</script>

<style>

</style>