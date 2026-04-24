<script lang="ts">

import MainPageLayout from 'src/layout/MainPageLayout.svelte'
import type {Customer, Id, Project} from 'src/api/types'
import {onMount} from 'svelte'
import api from 'src/api/api'
import {t} from 'i18n'
import NewProjectButton from 'src/pages/projects/NewProjectButton.svelte'

export let id: Id<Project>

let project: Project
let customerMap: Record<string, string> = {}

onMount(async () => {
  project = await api.get('projects/' + id)
  const customerList: Customer[] = await api.get('customers')
  customerMap = Object.fromEntries(customerList.map(c => [c.id, c.name]))
})
</script>

<MainPageLayout class="relative">
  {#if project}
    <div class="flex justify-end"><NewProjectButton {project} label={t.projects.edit}/></div>

    <h2 class="text-2xl font-bold mb-4">{project?.name}</h2>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
      <div>
        <p class="text-sm text-gray-500">{t.customers.customer}</p>
        <p class="text-lg font-medium">{customerMap[project.customerId]}</p>
      </div>
      <div>
        <p class="text-sm text-gray-500">{t.projects.description}</p>
        <p class="text-lg font-medium">{project.description}</p>
      </div>
      <div>
        <p class="text-sm text-gray-500">{t.projects.hourlyRate}</p>
        <p class="text-lg font-medium">{project.hourlyRate}</p>
      </div>
      <div>
        <p class="text-sm text-gray-500">{t.projects.storyTrackerId}</p>
        <p class="text-lg font-medium">{project.storyTrackerId}</p>
      </div>
    </div>
  {/if}
</MainPageLayout>
