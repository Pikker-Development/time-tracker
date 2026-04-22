--changeset projects
create table projects(
  ${id},
  customerId bigint not null references customers(id),
  name text not null,
  description text,
  currency text not null default 'EUR',
  hourlyRate decimal not null,
  storyTrackerId int
);

--changeset projects.audit
create trigger projects_history after update on projects for each row execute function add_change_history();

