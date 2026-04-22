<script lang="ts">
  import MainPageLayout from 'src/layout/MainPageLayout.svelte'
  import {onMount} from 'svelte'
  import api from 'src/api/api'

  let customers

  onMount(async () => {
    customers = await api.get('customers')
    console.log(customers)
  })
</script>



<MainPageLayout class="relative">
</MainPageLayout>
