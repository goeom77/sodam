<template>
    <div>
      <div class="calendar">
        <h1>일정</h1>
        <!-- <FullCalendar v-bind:options="options"/> -->
        <FullCalendar :options="calendarOptions" />
      </div>
    </div>
  </template>
  
  <script>
  import FullCalendar from '@fullcalendar/vue3'
  import dayGridPlugin from '@fullcalendar/daygrid'
  import timeGridPlugin from '@fullcalendar/timegrid'
  import interactionPlugin from '@fullcalendar/interaction'
  import {INITIAL_EVENTS, createEventId} from './event-utils'
  // import { reactive } from 'vue'
  
  // const options = reactive({
  //   plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
  //   initialView: 'dayGridMonth',
  //   headerToolbar: {
  //     left:'prev.next today',
  //     center: 'title',
  //     right:'dayGridMonth,dayGridWeek,listDay'
  //   },
  //   editable: true,
  //   selectable: true,
  //   weekends: true,
  //   select: (arg) =>{
  //     id.value = id.value+1
  //     console.log(arg.start + arg.end)
  //   }
  // })
  
  
  export default {
    name:'CalendarView',
    components: {
      FullCalendar // make the <FullCalendar> tag available
    },
    data() {
      return {
        calendarOptions: {
          plugins: [ dayGridPlugin, timeGridPlugin, interactionPlugin ],
          initialView: 'dayGridMonth',
          initialEvents: INITIAL_EVENTS,
          headerToolbar:{
            left: 'prev, next today',
            center: 'title',
            right: 'dayGridMonth, timeGridWeek, timeGridDay'
          },
          editable: true,
          selectable: true,
          selectMirror: false,
          dayMaxEvents: true,
          weekends: true,
          select: this.handleDateSelect,
          eventClick: this.handleEventClick,
          eventsSet: this.handleEvents,
          locale:'ko',
        },
        currentEvent:['가나다라'],
      }
    },
    methods: {
      handleWeekendsToggle() {
        this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
      },
      handleDateSelect(selectInfo) {
        let title = prompt('상담 일정을 입력해주세요')
        let calendarApi = selectInfo.view.calendar
        calendarApi.unselect() // clear date selection
        if (title) {
          calendarApi.addEvent({
            id: createEventId(),
            title,
            start: selectInfo.startStr,
            end: selectInfo.endStr,
            allDay: selectInfo.allDay
          })
          console.log(selectInfo)
        }
      },
      handleEventClick(clickInfo) {
        if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
          clickInfo.event.remove()
        }
      },
      handleEvents(events) {
        this.currentEvents = events
      },
    }
  
  }
  
  </script>
  
  <style>
  .calendar{
    margin:50px;
    
  
  }
  </style>