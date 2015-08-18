package br.com.chatreact.tests.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.chatreact.entities.dao.tests.suite.DAOTests;
import br.com.chatreact.services.tests.suite.ServicesTests;

@RunWith(Suite.class)
@SuiteClasses({
	DAOTests.class,
	ServicesTests.class
})
public class AllTests {}