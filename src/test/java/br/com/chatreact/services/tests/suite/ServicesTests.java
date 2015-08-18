package br.com.chatreact.services.tests.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.chatreact.services.tests.ChatServiceTest;
import br.com.chatreact.services.tests.UsuarioServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	ChatServiceTest.class,
	UsuarioServiceTest.class
})
public class ServicesTests {}