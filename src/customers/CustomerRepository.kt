package customers

import db.CrudRepository
import javax.sql.DataSource

class CustomerRepository(db: DataSource): CrudRepository<Customer>(db, "customers") {}
