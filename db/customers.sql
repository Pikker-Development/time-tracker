--changeset customers
create table customers(
  ${id},
  name text not null,
  legalName text,
  businessRegistryCode text,
  legalAddress text,
  vatId text,
  invoiceEmail text,
  phone text
);

