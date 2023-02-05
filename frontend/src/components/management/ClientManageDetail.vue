<template>
  <v-card
    class="mx-auto"
    max-width="344"
  >
  <v-card-text>
    <p class="text-h4 text--primary">
      성함 : {{ clientData.name }}
    </p>
    
    <div class="text--primary">
      <!-- 연락처 -->
      <h3>연락처 : {{ clientData.tel }}</h3><br>
      <h3>이메일 : {{ clientData.email }} </h3>
    </div>
    </v-card-text>
    
    <v-card-actions>
      <v-btn
        text
        color="deep-purple accent-4"
      >
        Learn More
      </v-btn>
      <router-link to="/VideoPage" style="color: white; background-color: red;">비디오 페이지로</router-link>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name:'ClientManageDetail',
  props:{
    clientData:Object
  },
  data(){
    return{
      client:null
    }
  },
  methods:{
    // propsToData(){
    //   this.client = this.clientData
    // }
  },
  created(){
    // this.propsToData()
  }
}
</script>

<style>

</style>