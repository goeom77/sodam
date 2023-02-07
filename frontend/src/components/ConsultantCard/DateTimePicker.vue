<template>
  <input class="datetimepicker"/>
</template>

<script>
export default{
  name: 'DateTimePicker',
  mounted(){
    const self=this;

    $(this.$el).datetimepicker({
      format:'Y-m-d H:i',
      onChangeDateTime: function(value){
        if(value){
          self.$emit('update-date', value);
        }
      }
    })
  },
  beforeDestroy(){
    $(this.$el)
      .datetimepicker('hide')
      .datetimepicker('destroy');
      
  }
}





// import { ref } from 'vue';

// export default {
//   setup() {
//       const date = ref();
      
//       return {
//         date,
//       }
//   }
// }
</script>