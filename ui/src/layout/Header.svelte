<script lang="ts">
  import Avatar from 'src/layout/Avatar.svelte'

  export let title = ''
</script>

<header class="bg-stone-50 border-b px-4 py-3 flex flex-wrap gap-3 justify-between items-center">
  <div class="flex gap-2.5 items-center">
    {#if title}
      <div class="flex gap-2 items-center">
        <img src="/public/favicon.svg" class="size-10" title="Time Tracker Logo" alt="Logo">
        <h1 class="font-bold text-2xl">{title}</h1>
      </div>
    {/if}
  </div>
  <Avatar />
</header>
