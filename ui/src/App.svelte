<script lang="ts">
  import {t} from 'i18n'
  import Toasts from 'src/components/Toasts.svelte'
  import {Route, Router} from 'src/router'
  import HomePage from 'src/pages/HomePage.svelte'
  import CustomersPage from 'src/pages/customers/CustomersPage.svelte'
</script>

<svelte:head>
  <title>{t.title}</title>
</svelte:head>

<Toasts/>

<slot/>

<Router>
 <Route path="/" component={HomePage}/>
  <Route path="/customers" component={CustomersPage}/>
</Router>

