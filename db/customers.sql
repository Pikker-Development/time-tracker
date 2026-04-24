--changeset customers
create table customers(
  ${id},
  name text unique not null,
  legalName text,
  businessRegistryCode text,
  legalAddress text,
  vatId text,
  invoiceEmail text,
  phone text
);

--changeset customers:createdAt
alter table customers add column createdAt timestamptz not null default now();
