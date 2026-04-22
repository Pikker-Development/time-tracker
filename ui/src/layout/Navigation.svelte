<script lang="ts">
  import {user} from 'src/stores/auth'
</script>

{#if $user}
  <a href="/customers">Customers</a>
  <a href="/projects">Projects</a>
{/if}
