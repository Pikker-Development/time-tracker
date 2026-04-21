<script lang="ts">
  import {t} from 'i18n'

  export let title = ''
</script>

<header class="bg-stone-50 border-b px-4 py-3 flex flex-wrap gap-3 justify-between items-center">
  <div class="flex gap-2.5 items-center">
    {#if title}
      <h1 class="font-bold text-2xl mr-4">{title}</h1>
    {/if}
  </div>

  <div>
    <a href="/oauth?redirect=/">{t.login} <img src="/public/image/google.svg" class="h-4 w-4" title="Google Login" alt=""></a>

  </div>

</header>
