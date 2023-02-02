<template>
  <v-card
    max-width="400"
    class="mx-auto"
  >
  <v-container>
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
              {{ counselor.name}}
            </v-card-title>
            
            <v-card-subtitle>
              {{ counselor.introduce }}
              <br>
              {{ counselor.major}}
            </v-card-subtitle>
          </div>

          <v-card-actions>
            <v-btn
              outlined
              rounded
              text
              @click="moveTo"
            >
              Button
            </v-btn>
          </v-card-actions>
        </div>
      </v-card>
    </v-col>
  </v-row>
</v-container>
  </v-card>


<!-- 
    <v-list-item three-line>
      <v-list-item-content>
\
        <v-list-item-title class="text-h5 mb-1">
          {{ this.counselor.name}}
        </v-list-item-title>

        <v-list-item-subtitle>
          {{ this.counselor.introduce }}
          <br>
          {{ this.counselor.major }}

        </v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
    </v-list-item>

  </v-card> -->
</template>

<script>

// import {useRouter} from 'vue-router';

export default {
  name: 'ConsultantCard',
  // setup() {
  //   const router = useRouter()
    
  //   const moveTo = (dataCounselor) => {
  //     router.push({
  //       name: 'consultantcarditem',
  //       params: {
  //         dataCounselor: JSON.stringify(dataCounselor)
  //       }
  //     })
  //   }
  //   return {
  //     moveTo
  //   }
  // },

  props: {
    counselor: Object,
  },
  methods:{
    moveTo(){
      this.$router.push({ name: 'consultantcarditem' })
    },

  },  
}
</script>

<style>

</style>
