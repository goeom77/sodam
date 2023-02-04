<template>
  <v-card
    class="mx-auto"
    max-width="344"
  >
  <v-card-text>
    <p class="text-h4 text--primary">
        {{ this.client }}
        NAME : 
        <!-- {{this.client.clientId}} -->
      </p>
      
      <div class="text--primary">
        <h3>최근 상담 일자 :</h3><br>
        <h3>뭐든 넣겠찌 뭐 </h3>
      </div>
    </v-card-text>
    
    <v-card-actions>
      <v-btn
        text
        color="deep-purple accent-4"
      >
        Learn More
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name:'ClientManageDetail',
  props:{
    clienData:Object
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