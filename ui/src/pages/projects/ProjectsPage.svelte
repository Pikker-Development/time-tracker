<script lang="ts">
import MainPageLayout from 'src/layout/MainPageLayout.svelte'
import NewProjectButton from 'src/pages/projects/NewProjectButton.svelte'
import type {Customer, Project} from 'src/api/types'
import {onMount} from 'svelte'
import api from 'src/api/api'
import {t} from 'i18n'
import {Link} from 'src/router'

let projects: Project[]
let customerMap: Record<string, string> = {}

onMount(async () => {
  projects = await api.get('projects')
  const customerList: Customer[] = await api.get('customers')
  customerMap = Object.fromEntries(customerList.map(c => [c.id, c.name]))
})
</script>

<MainPageLayout class="relative">
  <div class="flex justify-end"><NewProjectButton/></div>
  <div class="grid md:grid-cols-2 lg:grid-cols-4 gap-6 my-3 text-lg">
    {#each projects ?? [] as p}
      <Link to="/projects/{p.id}" class=" py-3 bg-white hover:bg-stone-50">
        <div class="project border rounded-lg px-4 py-3 bg-white hover:bg-stone-50">
          <div class="font-semibold">{p.name}</div>
          <div class="flex justify-between text-muted text-sm">
            <div>{t.customers.customer} {customerMap[p.customerId]}</div>
            {#if p.storyTrackerId}
              <div>{t.projects.storyTrackerId} {p.storyTrackerId}</div>
            {/if}
          </div>
        </div>
      </Link>
    {/each}
  </div>
</MainPageLayout>
