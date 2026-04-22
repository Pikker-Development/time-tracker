<script lang="ts">
  import {t} from 'i18n'
  import type {Customer} from 'src/api/types'
  import api from 'src/api/api'
  import {showToast} from 'src/stores/toasts'
  import Button from 'src/components/Button.svelte'
  import Modal from 'src/components/Modal.svelte'
  import Form from 'src/forms/Form.svelte'
  import FormField from 'src/forms/FormField.svelte'

  export let customer = {} as Customer
  export let show = false

  async function submit() {
    await api.post('customers', customer)
    showToast(t.general.saved)
    show = false
  }

</script>

<Button label={t.customers.addCustomer} onclick={() => show = true}/>
<Modal bind:show title={t.customers.addCustomer}>
  <Form class="flex flex-col gap-2" {submit}>
    <FormField label={t.customers.name} bind:value={customer.name}/>
    <FormField required={false} label={t.customers.legalName} bind:value={customer.legalName}/>
    <FormField required={false} label={t.customers.vatId} bind:value={customer.vatId}/>
    <FormField required={false} label={t.customers.email} bind:value={customer.invoiceEmail}/>
    <FormField required={false} label={t.customers.phone} bind:value={customer.phone}/>
    <div>
      <Button type="submit" label={t.general.save}></Button>
    </div>
  </Form>
</Modal>

