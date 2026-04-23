<script lang="ts">
import MainPageLayout from 'src/layout/MainPageLayout.svelte'
import NewProjectButton from 'src/pages/projects/NewProjectButton.svelte'
import type {Customer, Project} from 'src/api/types'
import {onMount} from 'svelte'
import api from 'src/api/api'
import {t} from 'i18n'

let projects: Project[]

onMount(async () => {
  projects = await api.get('projects')
})
</script>

<MainPageLayout class="relative">
  <NewProjectButton/>

  <div class="grid md:grid-cols-2 lg:grid-cols-4 gap-6 my-3 text-lg">
    {#each projects ?? [] as p}
      <div class="project border rounded-lg px-4 py-3 bg-white hover:bg-stone-50">
        <div class="font-semibold">{p.name}</div>
      </div>
      {/each}
  </div>

</MainPageLayout>
