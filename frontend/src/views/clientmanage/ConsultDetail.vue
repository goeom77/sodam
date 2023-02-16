<template>
    <v-container style="width:calc(60%); height:800px; margin-top: 5%;">
    <div>
        detail
           id: {{ id }}
           key: {{ mykey }}

    </div>
    <v-btn @click="getAudioFile">download?</v-btn>
    <v-sheet
          class="pa-3"
          color="grey-lighten-3"
        >
          <v-sheet
            rounded=true
            class="mx-auto"
            min-height="100"
            width="100%"
          >
        
        {{ data.script }}
        
        </v-sheet>
        </v-sheet>
    </v-container>
</template>

<script>
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
    name:'ConsultDetail',
    components:{

    },
    data(){
        return {
            dialog: false,
            counselorId:this.$store.state.payload.id,
            data: this.$store.state.sttdata
        }
    },
    props:{
        id:String,
    },
    created() {
		// console.log(this.data.script)
	},
    methods:{
        getAudioFile(){
            console.log('get audio')
            console.log(this.id)
            axios({
                method:'post',
                url: `${VUE_APP_API_URL}/api/gcs/audio/${this.id}`,
                data:{
                    key:this.$store.getters.getSttKey,
                }
            })
            .catch(error => {
                if (error.response) {
                    console.log(error.response);
                    console.log(error.response.status);
                }
                
            });
        }
        // getConsultDetail() {
        //     console.log(this.id)
        //     axios({
        //         method:'post',
        //         // url: `${STT_API_URL}/stt/text`,
        //         url: `http://localhost:8280/stt/text`,
        //         data:{
        //             schedule_id:this.id,
        //             key:this.$store.getters.getSttKey,
        //         }
        //     })
        //     .then(res=>{
        //         console.log(res)
        //         this.data = res.data
        //     })
        //     .catch(error => {
        //         if (error.response) {
        //             console.log(error.response);
        //             this.data = error.response.data
        //             console.log(error.response.status);
        //         }
                
        //     });
        // }
    }
}
</script>

<style>

</style>