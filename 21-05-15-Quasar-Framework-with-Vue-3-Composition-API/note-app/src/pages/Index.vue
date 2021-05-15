<script>
import Container from 'src/components/Container.vue'
import NoteCard from 'src/components/NoteCard.vue'
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'
import { useLocalNotes } from 'src/helper'


export default defineComponent({
  components: { Container, NoteCard },
  name: 'Index',
  setup() {
    const notes = useLocalNotes();
    const router = useRouter();
    return { router, notes }
  },
})
</script>

<template>
  <q-page padding>
    <Container>
      <div class="row items-center justify-between">
        <h3> Your Notes </h3>
        <div>
          <q-btn round color='positive' icon='add' to='/new' ></q-btn>
        </div>
      </div>

      <NoteCard
        v-for="({ title, description }, idx) in notes"
        :key="idx"
        :title="title"
        :description="description"
        @click="router.push(`/note/${idx}`)"
      />
      
      <div v-if="notes.length === 0">You have not created any notes.</div>
    </Container>
  </q-page>
</template>
