package org.seedstack.samples.kotlin.domain.model

import org.kametic.specifications.Specification
import org.seedstack.business.domain.*
import org.seedstack.business.specification.dsl.AggregateSelector
import org.seedstack.business.util.inmemory.BaseInMemoryRepository
import java.util.stream.Stream

data class CustomerId(val firstName: String, val lastName: String) : BaseValueObject()

class Customer(val id: CustomerId) : BaseAggregateRoot<CustomerId>()

interface CustomerFactory : Factory<Customer> {
    fun createCustomer(firstName: String, lastName: String): Customer
}

class DefaultCustomerFactory : BaseFactory<Customer>(), CustomerFactory {
    override fun createCustomer(firstName: String, lastName: String): Customer {
        return Customer(CustomerId(firstName, lastName))
    }
}

interface CustomerRepository : Repository<Customer, CustomerId> {
    fun searchByName(term: String): Stream<Customer>
}

class InMemoryCustomerRepository : BaseInMemoryRepository<Customer, CustomerId>(), CustomerRepository {
    override fun searchByName(term: String): Stream<Customer> {
        
    }
}