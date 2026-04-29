<script lang="ts">
  import {t} from 'i18n'
  import Toasts from 'src/components/Toasts.svelte'
  import {Route, Router} from 'src/router'
  import HomePage from 'src/pages/HomePage.svelte'
  import CustomersPage from 'src/pages/customers/CustomersPage.svelte'
  import ProjectsPage from 'src/pages/projects/ProjectsPage.svelte'
  import ProjectPage from 'src/pages/projects/ProjectPage.svelte'
</script>

<svelte:head>
  <title>{t.title}</title>
</svelte:head>

<Toasts/>

<slot/>

<Router>
 <Route path="/" component={HomePage}/>
  <Route path="/customers" component={CustomersPage}/>
  <Route path="/projects" component={ProjectsPage}/>
  <Route path="/projects/:id" component={ProjectPage}/>
  <Route path="/customers/:customerId/projects" component={ProjectsPage}/>
</Router>

