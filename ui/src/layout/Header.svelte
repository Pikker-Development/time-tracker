<script lang="ts">
  import {t} from 'i18n'
</script>

<header class="bg-stone-50 border-b px-4 py-3 flex flex-wrap gap-3 justify-between items-center">
  <div class="flex gap-2.5 items-center">
    <div class="flex gap-2 items-center">
      <img src="/public/favicon.svg" class="size-10" title="Time Tracker Logo" alt="Logo">
      <h1 class="font-bold text-2xl">{t.title}</h1>
    </div>
  </div>
  <div>
    <a href="/oauth?redirect=/" class="link-button gap-2">
      {t.login}
      <img src="/public/img/google.svg" class="size-4" title="Google Login" alt="">
    </a>
  </div>
</header>
