<script lang="ts">
  import MainPageLayout from 'src/layout/MainPageLayout.svelte'
  import AddCustomerButton from 'src/pages/customers/AddCustomerButton.svelte'
  import SortableTable from 'src/components/SortableTable.svelte'
  import {onMount} from 'svelte'
  import api from 'src/api/api'
  import type {Customer} from 'src/api/types'
  import {t} from 'i18n'

  const columns: [string, any][] = [
    [t.customers.name, 'name'],
    [t.customers.legalName, 'legalName'],
    [t.customers.businessRegistryCode, 'businessRegistryCode'],
    [t.customers.legalAddress, 'legalAddress'],
    [t.customers.vatId, 'vatId'],
    [t.customers.invoiceEmail, 'invoiceEmail'],
    [t.customers.phone, 'phone']
  ]
  let customers: Customer[]

  onMount(
    async () => customers = await api.get('customers')
  )
</script>

<MainPageLayout class="relative flex flex-col gap-4">
  <div class="flex justify-end">
    <AddCustomerButton/>
  </div>
  <SortableTable {columns} items={customers}/>
</MainPageLayout>
