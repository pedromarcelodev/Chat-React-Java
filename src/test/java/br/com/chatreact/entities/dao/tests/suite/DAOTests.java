package br.com.chatreact.entities.dao.tests.suite;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.chatreact.entities.dao.tests.UsuarioDAOTest;
import br.com.chatreact.entities.dao.tests.ChatDAOTest;
import br.com.chatreact.entities.dao.tests.MensagemDAOTest;

@RunWith(Suite.class)
@SuiteClasses({
	UsuarioDAOTest.class,
	ChatDAOTest.class,
	MensagemDAOTest.class
})
public class DAOTests {}