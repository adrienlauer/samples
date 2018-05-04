/**
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.samples.kotlin.domain.model

import org.seedstack.business.domain.*
import org.seedstack.business.util.inmemory.BaseInMemoryRepository
import java.util.stream.Stream

data class CustomerId(val firstName: String, val lastName: String) : BaseValueObject()

class Customer(val id: CustomerId) : BaseAggregateRoot<CustomerId>()

interface CustomerFactory : Factory<Customer> {
    fun createCustomer(firstName: String, lastName: String): Customer
}

open class DefaultCustomerFactory : BaseFactory<Customer>(), CustomerFactory {
    override fun createCustomer(firstName: String, lastName: String): Customer {
        return Customer(CustomerId(firstName, lastName))
    }
}

interface CustomerRepository : Repository<Customer, CustomerId> {
    fun searchByName(term: String): Stream<Customer>
}

class InMemoryCustomerRepository : BaseInMemoryRepository<Customer, CustomerId>(), CustomerRepository {
    override fun searchByName(term: String): Stream<Customer> {
        return Stream.empty()
    }
}
