<template>
  <Datepicker v-model="date" range :partial-range="false" />
</template>

<script>
import { ref } from 'vue';

export default {
  setup() {
      const date = ref();
      
      return {
        date,
      }
  }
}
</script>