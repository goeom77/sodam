<script>
import {defineComponent} from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin, {Draggable} from '@fullcalendar/interaction'
import {INITIAL_EVENTS, createEventId} from './event-utils'
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL


document.addEventListener('DOMContentLoaded', function () {
  var Calendar = FullCalendar.Calendar;
  // var Draggable = FullCalendar.Draggable;

  var containerEl = document.getElementById('external-events');
  var calendarEl = document.getElementById('calendar');
  var checkbox = document.getElementById('drop-remove');

  // initialize the external events
  // -----------------------------------------------------------------

  new Draggable(containerEl, {
    itemSelector: '.fc-event',
    drop: function (info) {
      // is the "remove after drop" checkbox checked?
      if (checkbox.checked) {
        console.log("draggable drop info : " + JSON.stringify(info))
        info.draggedEl.parentNode.removeChild(info.draggedEl);
      }
    },
    eventClick: this.handleEventClick,
    eventsSet: this.handleEvents,
    eventDrop: this.onEventDrop,
    droppable:true,
    eventData: function(eventEl) {
      return {
        title: eventEl.innerText.trim(),
        duration : "02:00"
      }
    }
    // eventData: this.DraggableEvents
  });

  // initialize the calendar
  // -----------------------------------------------------------------

  var calendar = Calendar(calendarEl, {
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    dropAccept: '.fc-event',
    droppable: true,
    editable: true, // this allows things to be dropped onto the calendar
    // eventSources: {
    //   events: [],
    //   color: 'yellow',   // an option!
    //   textColor: 'black' // an option!
    // },
    drop: function (info) {
      // is the "remove after drop" checkbox checked?
      if (checkbox.checked) {
        console.log("draggable drop info : " + JSON.stringify(info))
        info.draggedEl.parentNode.removeChild(info.draggedEl);
      }
    }
  });

  calendar.render();
});


export default defineComponent({
  components: {
    FullCalendar,
  },
  data() {
    return {
      userId: this.$store.state.payload.id,
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin // needed for dateClick
        ],
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialView: 'dayGridMonth',
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents,
        eventDrop: this.onEventDrop,
        // you can update a remote database when these fire:
        // eventAdd: {},
        // eventChange: {},


        // eventSources: {
        //   events: [],
        //   color: 'yellow',
        //   textColor: 'black'
        // }
      },
      // currentEvents: [],
      DraggableEvents: [],
    }
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },
    handleDateSelect(selectInfo) {
      let title = prompt('Please enter a new title for your event')
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
      }
    },
    handleEventClick(clickInfo) {
      if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
      }
    },
    handleEvents(events) {
      console.log("handleEvents : >> "+JSON.stringify(events))
      // console.log("handleEvents : >> ")
      this.DraggableEvents = events

    },
    onEventDrop({event}) {
      console.log("onEventDrop : >> " + JSON.stringify(event))

      this.DraggableEvents = event
      // console.log(event)
    },
    getApprovedData() {
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/schedule/search`,
        data: {
          state: 'APPROVED',
          userId: this.userId
        }
      })
          .then(res => {
            this.DraggableEvents = res.data
            console.log("currentEvents:>>>> " + JSON.stringify(res.data))
          })
    },
    getExpectedData() {
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/schedule/search/monthly`,
        data: {
          counselorId: this.userId
        }
      })
          .then(res => {
            console.log("getExpectedData:>>>> " +JSON.stringify(res.data))
            this.calendarOptions.eventSources.events = res.data
          })
    }
  },
  created() {
    this.getExpectedData()
    this.getApprovedData()
  }
})

</script>

<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content">
      <div class='demo-app'>
        <div class='demo-app-main'>
          <div id='external-events' class="drag-cover">
            <p>
              <strong>Draggable Events</strong>

            </p>
            <div
                class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event fc-event-draggable fc-daygrid-event-harness'
                v-for="(event,idx) in DraggableEvents"
                :key="idx">
              <div class='fc-event-main'>{{ event.name }}</div>
              <div class='fc-event-main'>{{ event.createdDateTime.split('T')[0] }}</div>
            </div>
            <p>
              <input type='checkbox' id='drop-remove'/>
              <label for='drop-remove'>remove after drop</label>
            </p>
          </div>
          <FullCalendar
              class='demo-app-calendar'
              :options=this.calendarOptions
          >
            <template v-slot:eventContent='arg'>
              <i>제발 보여줘</i>
              <i>{{ arg.eventSources.events }}</i>
<!--              <i>{{ arg.eventSources.events.start.split('T')[0] }}</i>-->
            </template>
          </FullCalendar>
<!--          {{ this.DraggableEvents }}-->
        </div>
      </div>
    </div>
  </div>
</template>

<style lang='css'>

h2 {
  margin: 0;
  font-size: 16px;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b { /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  display: flex;
  min-height: 100%;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
}

.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 2em;
}

.demo-app-main {
  flex-grow: 1;
  padding: 3em;
}

.fc { /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}

#external-events {
  position: fixed;
  z-index: 2;
  top: 20px;
  left: 20px;
  width: 150px;
  padding: 0 10px;
  border: 1px solid #ccc;
  background: #eee;
}

#external-events .fc-event {
  cursor: move;
  margin: 3px 0;
}

#calendar-container {
  position: relative;
  z-index: 1;
  margin-left: 200px;
}

#calendar {
  max-width: 1100px;
  margin: 20px auto;
}

</style>
