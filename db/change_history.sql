--changeset change_history
create table change_history(
  "table" varchar not null,
  rowId varchar not null,
  "column" varchar not null,
  oldValue varchar,
  newValue varchar,
  changedAt timestamptz not null default clock_timestamp(),
  changedBy bigint
);

--changeset change_history.idx
create index change_history_id_idx on change_history("table", rowId);

--changeset change_history:revoke-app-update
revoke update on change_history from app;

--changeset set_app_user onChange:RUN separator:none
create or replace procedure set_app_user(userId bigint) language plpgsql as $$
begin
  perform set_config('app.user', userId::text, true);
end; $$

--changeset get_app_user onChange:RUN separator:none
create or replace function get_app_user() returns bigint language plpgsql as $$
declare
  userId varchar;
begin
  userId := current_setting('app.user', true);
  return case when userId = '' then null else userId end;
end; $$

--changeset add_change_history onChange:RUN separator:none
create or replace function add_change_history() returns trigger language plpgsql as $$
declare
  col text;
  oldValue text;
  newValue text;
  newRec jsonb := to_jsonb(new);
  oldRec jsonb := to_jsonb(old);
begin
  for col, newValue in select * from jsonb_each_text(newRec) loop
      if (col not in ('updatedat', 'id')) then
        oldValue := oldRec->>col;
        if (oldValue is distinct from newValue) then
          insert into change_history ("table", rowId, "column", oldValue, newValue, changedBy)
          values (tg_table_name, new.id, col, oldValue, newValue, get_app_user());
        end if;
      end if;
    end loop;
  return new;
end; $$
