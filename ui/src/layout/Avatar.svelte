<script lang="ts">
  import {logout, user} from 'src/stores/auth'
  import {t} from 'i18n'
  import Button from 'src/components/Button.svelte'
  import Dropdown from 'src/components/Dropdown.svelte'
  import {navigate} from '@keksworks/svelte-tiny-router'

  let isDropDownOpen = false
</script>

{#if $user}
  <Dropdown class="right-0 z-50!" bind:open={isDropDownOpen}>
    {#if $user.avatarUrl}
      <img src={$user.avatarUrl} alt="" class="rounded-xl w-9">
    {:else}
      {@const initials = ($user.email as string).split(/[@._\-\s]+/).filter(Boolean).slice(0, 2).map(p => p[0]).join('').toUpperCase() || 'U'}
      <div class="rounded-xl size-9 bg-pikker-gold flex items-center justify-center font-bold">
        {initials}
      </div>
    {/if}
    <svelte:fragment slot="open">
      <div class="min-w-56 p-3">
        <div class="mb-3">
          <span class="font-medium text-gray-900 truncate">{$user.name}</span>
          <span class="text-sm text-gray-500 truncate">{$user.email}</span>
        </div>
        <Button class="w-full justify-start! px-0! hover:bg-gray-50" label={t.login.logout} iconClass="size-5!" icon="logout" onclick={logout}/>
      </div>
    </svelte:fragment>
  </Dropdown>
{:else}
  <a href="/oauth" class="link-button gap-2">
    {t.login.google}
    <img src="/img/google.svg" class="size-4" title="Google Login" alt="">
    <button onclick={(e) => {e.preventDefault(); navigate('/login#' + location.pathname)}} title={t.login.email} class="border-l px-2 -my-2 -mr-2 flex items-center">
      <span class="text-lg mr-1">@ {t.login.email}</span>
    </button>
  </a>
{/if}
