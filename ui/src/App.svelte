<script lang="ts">
  import {t} from 'i18n'
  import Toasts from 'src/components/Toasts.svelte'
  import {Route, Router} from 'src/router'
  import HomePage from 'src/pages/HomePage.svelte'
</script>

<svelte:head>
  <title>{t.title}</title>
</svelte:head>

<Toasts/>

<slot/>

<Router>
 <Route path="/" component={HomePage}/>
</Router>

