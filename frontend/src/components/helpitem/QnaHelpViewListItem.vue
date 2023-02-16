<template>
  <div v-if="(limit-1)*5 <= index && index < limit * 5">
    <div class="container">
      <div class="row" @click="moveTo" style="cursor: pointer">
        <div class="col-1" style="text-align: center; margin:auto;" id="qnaId">
          {{ Boardarticle.id }}
        </div>
        <div class="col-8" style="margin:auto;">
          {{ Boardarticle.title }}
          <br>
          {{ Boardarticle.writerId }} ID
        </div>
        <div class="col-1" style="text-align: center; margin:auto;">
          {{ Boardarticle.commentCount }}
        </div>
        <div class="col-2" style="text-align: center; margin:auto;">
          {{ this.date }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'QnaHelpViewListItem',
  props: {
    Boardarticle: Object,
    index: Number,
    limit: Number,
  },
  data(){
    return{
      date: this.Boardarticle.createdAt.split(' ')[0]
    }
  },
  methods: {
    moveTo(){
      this.$router.push({name:'QnaDetailView', params: { id: this.Boardarticle.id }  })
    },
  }
}
</script>

<style>
#QnaHelpViewListItem {
  margin-left: 60px;
  margin-right: 60px;
}
#qnaId{
  padding:20px
}

.flex-container{
            display: flex;
            padding: 10px;
            border: 1px solid lightgray;
            border-radius: 10px;
            margin:5px;
            background-color:white;
        }
        .flex-container.flex-start{justify-content: flex-start;}
        .flex-container.flex-end{justify-content: flex-end;}
        .flex-container.center{justify-content: center;}
        .flex-container.space-around{justify-content: space-around;}
        .flex-container.space-between{justify-content: space-between;}
        .flex-container.space-evenly{justify-content: space-evenly;}
</style>

