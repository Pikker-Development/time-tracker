<script lang="ts">
import MainPageLayout from 'src/layout/MainPageLayout.svelte'
import NewProjectButton from 'src/pages/projects/NewProjectButton.svelte'
import type {Customer, Project} from 'src/api/types'
import {onMount} from 'svelte'
import api from 'src/api/api'
import {t} from 'i18n'

let projects: Project[]
let customerMap: Record<string, string> = {}

function projectCreated(newProject: Project) {
  projects = [...projects, newProject]
}

onMount(async () => {
  projects = await api.get('projects')
  const customerList: Customer[] = await api.get('customers')
  customerMap = Object.fromEntries(customerList.map(c => [c.id, c.name]))
})
</script>

<MainPageLayout class="relative">
  <NewProjectButton onCreated = {projectCreated}/>
  <div class="grid md:grid-cols-2 lg:grid-cols-4 gap-6 my-3 text-lg">
    {#each projects ?? [] as p}
      <div class="project border rounded-lg px-4 py-3 bg-white hover:bg-stone-50">
        <div class="font-semibold">{p.name}</div>
        <div class="flex justify-between text-muted text-sm">
          <div>{t.customers.customer} {customerMap[p.customerId]}</div>
          {#if p.storyTrackerId}
            <div>{t.projects.storyTrackerId} {p.storyTrackerId}</div>
          {/if}
        </div>
      </div>
    {/each}
  </div>
</MainPageLayout>
