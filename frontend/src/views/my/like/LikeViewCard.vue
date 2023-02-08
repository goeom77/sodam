<template>
    <v-container>
  <v-card
    max-width="400"
    class="mx-auto"
  >
    <v-row dense>
      <v-col cols="12">
        <v-card
        color="white"
        theme="dark"
        >
        <v-avatar
          class="ma-3"
          size="125"
          rounded="0"
        >
          <v-img src="https://cdn.vuetifyjs.com/images/cards/halcyon.png"></v-img>
        </v-avatar>
        <div class="d-flex flex-no-wrap justify-space-between">
          <div>
            <v-card-title class="text-h5">
            </v-card-title>
            <v-card-subtitle>
              {{ consult }}

            </v-card-subtitle>
          </div>

          <v-card-actions>
            <v-btn
              outlined
              rounded
              text
              @click="deleteFav"
            >
              X
            </v-btn>
          </v-card-actions>
        </div>
      </v-card>
    </v-col>
  </v-row>
</v-card>
</v-container>
</template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL
export default {
  name:'LikeViewCard',
  props:{
    consult:Object
  },
  data(){
    return{
      clientId:this.$store.state.payload.id,
      CounselorId:null,
    }
  },
  methods:{
    deleteFav(){
      axios({
        method:'delete',
        url: `${VUE_APP_API_URL}/api/client/${this.clientId}/fav/${this.counselorId}`,
        data:{
          clientId: this.clientId,
          counselorId: this.counselorId
        },
        headers: {
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(res=>{
        console.log(res)
      })
    }
  }
}
</script>

<style>

</style>