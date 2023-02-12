<script>
import {defineComponent} from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin, {Draggable} from '@fullcalendar/interaction'
import {INITIAL_EVENTS, createEventId} from './event-utils'



document.addEventListener('DOMContentLoaded', function() {
  var Calendar = FullCalendar.Calendar;
  // var Draggable = FullCalendar.Draggable;

  var containerEl = document.getElementById('external-events');
  var calendarEl = document.getElementById('calendar');
  var checkbox = document.getElementById('drop-remove');

  // initialize the external events
  // -----------------------------------------------------------------

  new Draggable(containerEl, {
    itemSelector: '.fc-event',
    editable: true,
    droppable: true,
    drop: function(info) {
      // is the "remove after drop" checkbox checked?
      if (checkbox.checked) {
        console.log("draggable drop info : "+JSON.stringify(info))
        // if so, remove the element from the "Draggable Events" list
        info.draggedEl.parentNode.removeChild(info.draggedEl);
      }
    },
    eventData: function(eventEl) {
      return {
        title: eventEl.innerText
      };
    }
  });

  // initialize the calendar
  // -----------------------------------------------------------------

  var calendar = Calendar(calendarEl, {
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    editable: true, // this allows things to be dropped onto the calendar
    // drop: function(info) {
    //   // is the "remove after drop" checkbox checked?
    //   if (checkbox.checked) {
    //     // if so, remove the element from the "Draggable Events" list
    //
    //     console.log("calendar drop info : "+JSON.stringify(info))
    //     // if so, remove the element from the "Draggable Events" list
    //     info.draggedEl.parentNode.removeChild(info.draggedEl);
    //     info.draggedEl.parentNode.removeChild(info.draggedEl);
    //   }
    // }
  });

  calendar.render();
});



export default defineComponent({
  components: {
    FullCalendar,
  },
  data() {
    return {
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
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:

        */
      },
      currentEvents: [],
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
      this.currentEvents = events
    },
    onEventDrop ({ event }) {
      console.log("onEventDrop : >> "+JSON.stringify(event))

      this.currentEvents=event
    }
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

            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event fc-event-draggable fc-daygrid-event-harness'>
              <div class='fc-event-main'>My Event 1</div>
            </div>
            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event fc-event-draggable'>
              <div class='fc-event-main'>My Event 2</div>
            </div>
            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event fc-event-draggable'>
              <div class='fc-event-main'>My Event 3</div>
            </div>
            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
              <div class='fc-event-main'>My Event 4</div>
            </div>
            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
              <div class='fc-event-main'>My Event 5</div>
            </div>

            <p>
              <input type='checkbox' id='drop-remove' />
              <label for='drop-remove'>remove after drop</label>
            </p>
          </div>
          <FullCalendar
              class='demo-app-calendar'
              :options=calendarOptions
          >
            <template v-slot:eventContent='arg'>
              <b>{{ arg.timeText }}</b>
              <i>{{ arg.event.title }}</i>
            </template>
          </FullCalendar>
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
