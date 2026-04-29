<script lang="ts">
  import {user} from 'src/stores/auth'
</script>

{#if $user}

  <a href="/">Home</a>
  <a href="/customers">Customers</a>
  <a href="/projects">Projects</a>
  <a href="/users">Users</a>
{/if}
