package com.github.abrarsyed.jastyle;

import java.security.Permission;

import junit.framework.TestCase;

/**
 * stolen from stack-overflow.
 * http://stackoverflow.com/questions/309396/java-how-to-test-methods-that-call-system-exit
 */
public abstract class NoExitTestCase extends TestCase
{

	@SuppressWarnings("serial")
	protected static class ExitException extends SecurityException
	{
		public final int	status;

		public ExitException(int status)
		{
			super("There is no escape!");
			this.status = status;
		}
	}

	private static class NoExitSecurityManager extends SecurityManager
	{
		public final SecurityManager old;
		
		public NoExitSecurityManager(SecurityManager securityManager)
		{
			old = securityManager;
		}

		@Override
		public void checkPermission(Permission perm)
		{
			if (old != null)
				old.checkPermission(perm);
			
			// allow anything.
		}

		@Override
		public void checkPermission(Permission perm, Object context)
		{
			if (old != null)
				old.checkPermission(perm, context);
			
			// allow anything.
		}

		@Override
		public void checkExit(int status)
		{
			super.checkExit(status);
			throw new ExitException(status);
		}
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		System.setSecurityManager(new NoExitSecurityManager(System.getSecurityManager()));
	}

	protected void tearDown() throws Exception
	{
		NoExitSecurityManager manager = (NoExitSecurityManager) System.getSecurityManager();
		System.setSecurityManager(manager.old); // or save and restore original
		super.tearDown();
	}

	public void testDUMMY() throws Exception
	{
		// DUMMY TEST
	}
}
