<script lang="ts">
  import MainPageLayout from 'src/layout/MainPageLayout.svelte'
  import SortableTable from 'src/components/SortableTable.svelte'
  import {t} from 'i18n'
  import {onMount} from 'svelte'
  import api from 'src/api/api'
  import type {User} from 'src/api/types'

  let users: User[]

  const columns: [string, any][] = [
    [t.users.name, 'name'],
    [t.users.email, 'email'],
    [t.users.admin, 'admin'],
  ]

  onMount(
    async () => users = await api.get('users')
  )

</script>

<MainPageLayout class="relative flex flex-col gap-4">
  <SortableTable class="w-1/2" {columns} items = {users} let:item>
    <tr>
      <td>{item.firstName} {item.lastName}</td>
      <td>{item.email}</td>
        {#if item.isAdmin}
          <td>✓</td>
        {/if}
    </tr>
  </SortableTable>
</MainPageLayout>
