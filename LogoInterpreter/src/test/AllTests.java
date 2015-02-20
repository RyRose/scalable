package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GrammarTest.class, EvaluatorTest.class, TurtleTest.class})
public class AllTests { }
