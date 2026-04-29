--changeset users
create table users(
  ${id},
  firstName text not null,
  lastName text not null,
  role text not null,
  email text unique not null
);

--changeset users:createdAt
alter table users add column createdAt timestamptz not null default now();

--changeset users:updatedAt
alter table users add column updatedAt timestamptz not null default now();

--changeset users:avatarUrl
alter table users add column avatarUrl text default null;

--changeset users:isAdmin
alter table users add column isAdmin boolean not null default false;

--changeset users:replace-role-with-isadmin
alter table users drop column role;
