export type Id<T extends Entity<T>> = string & {_of?: T}
export type Entity<T extends Entity<T>> = {id: Id<T>}

// class customers.Customer
export interface Customer {businessRegistryCode?: string; id: Id<Customer>; invoiceEmail?: Email; legalAddress?: string; legalName?: string; name: string; phone?: Phone; vatId?: string}
// class project.Project
export interface Project {currency: string; customerId: Id<Customer>; description?: string; hourlyRate: number; id: Id<Project>; name: string; storyTrackerId?: number}
// class users.Role
export enum Role {ADMIN = 'ADMIN', USER = 'USER'}
// class users.User
export interface User {createdAt: Instant; email: Email; firstName: string; id: Id<User>; lastName: string; role: Role; updatedAt?: Instant}

// java.time.Instant
export type Instant = `${number}-${number}-${number}T${number}:${number}:${number}Z`
// klite.Email
export type Email = `${string}@${string}`
// klite.Phone
export type Phone = `+${number}`
