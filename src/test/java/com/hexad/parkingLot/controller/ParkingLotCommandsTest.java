package com.hexad.parkingLot.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.shell.ConfigurableCommandRegistry;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.standard.StandardMethodTargetRegistrar;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;

import com.hexad.parkingLot.common.TestCommon;
import com.hexad.parkingLot.service.ConsoleService;
import com.hexad.parkingLot.service.ParkingLotService;

//@RunWith(SpringJUnit4ClassRunner.class)
public class ParkingLotCommandsTest extends TestCommon {

	@Mock
	private ParkingLotService parkingLotService;

	@Mock
	private ConsoleService consoleService;

	@Mock
	private ConfigurableApplicationContext context;
	
	@InjectMocks
	private ParkingLotCommands parkingLotCommands;


	private StandardMethodTargetRegistrar registrar = new StandardMethodTargetRegistrar();
	private ConfigurableCommandRegistry registry = new ConfigurableCommandRegistry();

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
		Mockito.mock(ParkingLotService.class);
	}
	
//	@Test
	public void test_Success_CreateParkingLot() {
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ParkingLotCommands.class);
		registrar.setApplicationContext(applicationContext);
		registrar.register(registry);

		MethodTarget methodTarget = registry.listCommands().get("create_parking_lot");
		assertNotNull(methodTarget);
		assertThat(methodTarget.getHelp(), is("creates a parking lot with the given number of slots"));
		assertThat(methodTarget.getMethod(),
				is(ReflectionUtils.findMethod(ParkingLotCommands.class, "createParkingLot")));
		assertThat(methodTarget.getAvailability().isAvailable(), is(true));
	}

}
