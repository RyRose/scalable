package test;

import static org.junit.Assert.*;

import org.junit.*;

import parse.Grammar;

public class GrammarTest {
	@Test
	public void test98() {
		edu.hendrix.grambler.Grammar g = new Grammar();
		edu.hendrix.grambler.Tree t = g.parse2("");
		assertEquals(true, t.isError());
	}

	@Test
	public void test264() {
		edu.hendrix.grambler.Grammar g = new Grammar();
		edu.hendrix.grambler.Tree t = g.parse2("");
		assertEquals("lines: \"Syntax error: Input exhausted\"\n", t.toTextTree());
	}

}

