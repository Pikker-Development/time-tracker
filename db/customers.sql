--changeset customers
create table customers(
  ${id},
  name text not null,
  legalName text,
  BusinessRegistryCode,
  vatId int,
  invoiceEmail text,
  phone int
);

