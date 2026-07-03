package com.api.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.api.services.AuthService;
import com.api.services.DashboardService;
import com.api.services.JobService;
import com.api.services.MasterService;
import com.api.services.UserService;

@Listeners(com.listeners.APITestListeners.class)
public abstract class BaseTest {

	protected JobService jobService;
	protected AuthService authService;
	protected DashboardService dashboardService;
	protected UserService userService;
	protected MasterService masterService;

	@BeforeMethod(alwaysRun = true)
	public void baseSetUp() {
		jobService = new JobService();
		authService = new AuthService();
		dashboardService = new DashboardService();
		userService = new UserService();
		masterService = new MasterService();
	}
}
