<template>
  <h1>Demo App</h1>
  <FullCalendar :options='calendarOptions'>

  </FullCalendar>
</template>

<script>
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'

export default {
  components: {
    FullCalendar // make the <FullCalendar> tag available
  },
  data: function() {
    return {
      calendarOptions: {
        plugins: [dayGridPlugin, timeGridPlugin],
        initialView: 'dayGridMonth',
        weekends: true,
        headerToolbar: {
          left: 'prev, next today',
          center: 'title',
          right: 'dayGridMonth, timeGridWeek, timeGridDay'
        },  
        editable: true,
        selectable:true,
        events: [
          { title: 'Meeting', start: new Date() }
        ]
      }
    }
  }
}
</script>
