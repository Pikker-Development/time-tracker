--changeset customers
create table customers(
  ${id},
  name text not null,
  legalName text,
  BusinessRegistryCode text,
  vatId text,
  invoiceEmail text,
  phone text
);

