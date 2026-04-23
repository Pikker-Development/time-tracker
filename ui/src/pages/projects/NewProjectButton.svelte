<script lang="ts">
  import type {Project} from 'src/api/types'
  import Modal from 'src/components/Modal.svelte'
  import {t} from 'i18n'
  import Button from 'src/components/Button.svelte'
  import Form from 'src/forms/Form.svelte'
  import FormField from 'src/forms/FormField.svelte'
  import TextAreaField from 'src/forms/TextAreaField.svelte'
  import api from 'src/api/api'
  import {showToast} from 'src/stores/toasts'
  import {navigate} from '@keksworks/svelte-tiny-router'

  export let project = {} as Project
  export let label = t.projects.new
  export let show = false

  async function submit() {
    project = await api.post('projects' + (project.id ? '/' + project.id : ''), project)
    showToast(t.general.saved)
    show = false
    setTimeout(() => navigate(`/projects/${project.id}`), 500)
  }
</script>

<Button {label} onclick={() => show = true}/>
<Modal bind:show title={label}>
  <Form {submit}>
    <FormField label={t.projects.name} bind:value={project.name}/>
    <TextAreaField label={t.projects.description} bind:value={project.description} rows={3} required={false}/>
    <FormField label={t.projects.hourlyRate} bind:value={project.hourlyRate}/>
    <FormField label={t.projects.storyTrackerId} bind:value={project.storyTrackerId} required={false}/>
    <div class="flex justify-end items-center pb-2 mt-4">
      <Button type="submit" label={t.general.save} class="primary" />
    </div>
  </Form>
</Modal>
