<template>
  <div>
    <input class="datetimepicker"/>
  </div>
</template>

<script>
export default {
  name:'DateTimePicker',
  mounted(){
    const self=this;

    $(this.$el).datetimepicker({
      format:'Y-m-d H:i',
      onChangeDateTime:function(value){
        if (value){
          self.$emit('update-date',value);
        }
      }
    })
  },
  beforeDestroy(){
    $(this.$el)
      .datetimepicker('hide')
      .datetimepicker('destroy');
  },
}
</script>

<style>


</style>