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
          style="float: left;"
        >
          <v-img src="https://cdn.vuetifyjs.com/images/cards/halcyon.png"></v-img>
        </v-avatar>
        <div class=" justify-space-between">
          <div>
            <v-card-title class="text-h5">
            </v-card-title>
            <v-card-subtitle>
              {{ consult }}

            </v-card-subtitle>
          </div>
          
          <div>
            <div>
              <v-btn 
                outlined 
                runded 
                text 
                @click="moveTo" 
                style="color:black; background-color: white;"
                >자세히보기
              </v-btn>
            </div>
            <div>
              <v-card-actions>
                <v-btn
                  outlined
                  rounded
                  text
                  @click="deleteFav"
                  style="color:black; text-align: end;"
                >
                  X
                </v-btn>
              </v-card-actions>
            </div>
          </div>

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
        console.log('카드 삭제 댐')
        this.$emit('delete-card',)
      })
      .catch(res=>{
        console.log('카드 삭제 안댐')
      })
    },
    moveTo(){
      this.$router.push({ name: 'consultantcarditem', params: {id: this.counselorId} })
    }
  }
}
</script>

<style>

</style>