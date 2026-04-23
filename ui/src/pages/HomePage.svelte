<script lang="ts">
  import MainPageLayout from 'src/layout/MainPageLayout.svelte'
  import Spinner from 'src/components/Spinner.svelte'
  import {t} from 'i18n'
</script>

<MainPageLayout class="relative flex justify-center" >
  <div class="flex gap-2 items-center">
    <img src="/public/favicon.svg" class="size-40" title="Time Tracker Logo" alt="Logo">
    <h1 class="font-bold text-9xl">{t.title}</h1>
  </div>
</MainPageLayout>
