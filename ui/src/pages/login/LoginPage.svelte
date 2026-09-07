<script lang="ts">
  import MainPageLayout from 'src/layout/MainPageLayout.svelte'
  import {replaceValues, t} from 'i18n'
  import api from 'src/api/api'
  import {initSession} from 'src/stores/auth'
  import Form from 'src/forms/Form.svelte'
  import FormField from 'src/forms/FormField.svelte'
  import Button from 'src/components/Button.svelte'
  import {navigate} from '@keksworks/svelte-tiny-router'
  import type {User} from 'src/api/types'

  let waitingForCode = false
  let email = '', code = ''

  async function submit() {
    if (waitingForCode) {
      const u = await api.post<User>('auth/email/code', {email, code})
      initSession(u)
      let to = location.hash.substring(1)
      if (to == '/') to = '/entry'
      navigate(to)
    } else {
      await api.post('auth/email', {email})
      waitingForCode = true
      code = ''
    }
  }
</script>

<MainPageLayout>
  <Form {submit} class="w-full md:w-96 md:mt-10 mx-auto flex flex-col gap-4">
    {#if waitingForCode}
      <p>{replaceValues(t.login.codeSent, {email})}</p>
      <FormField type="tel" class="lg" label={t.login.code} bind:value={code} autofocus/>
      <div class="flex gap-2">
        <Button variant="outlined" class="default lg w-full" label={t.general.close} onclick={() => waitingForCode = false}/>
        <Button type="submit" class="lg w-full primary" label={t.general.confirm}/>
      </div>
    {:else}
      <FormField type="email" class="lg" label={t.contacts.email} bind:value={email} autofocus/>
      <Button type="submit" class="lg w-full primary" label={t.login.sendCodeToEmail}/>
    {/if}

    <a href="/oauth" class="btn lg w-full default flex items-center justify-center gap-2">
      {t.login.googleLogin}
      <img src="/img/google.svg" class="size-4" title="Google Login" alt="">
    </a>
  </Form>
</MainPageLayout>
