package dev.binhcn;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.binhcn.calculation.CalculatorService;
import dev.binhcn.calculation.MathApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {
	
   //@InjectMocks annotation is used to create and inject the mock object
   @InjectMocks
   private MathApplication mathApplication;

   //@Mock annotation is used to create the mock object to be injected
   @Mock
   private CalculatorService calcService;

//   @Before
//   public void setUp(){
//      mathApplication = new MathApplication();
//      calcService = mock(CalculatorService.class);
//      mathApplication.setCalculatorService(calcService);
//   }

   @Test
   public void test1(){
      //add the behavior of calc service to add two numbers
      when(calcService.add(10.0,20.0)).thenReturn(30.00);

      //add the behavior of calc service to subtract two numbers
      when(calcService.subtract(20.0,10.0)).thenReturn(10.00);

      //test the add functionality
      Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
      Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
      Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

      //test the subtract functionality
      Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0.0);

      //default call count is 1
      verify(calcService).subtract(20.0, 10.0);

      //check if add function is called three times
      verify(calcService, times(3)).add(10.0, 20.0);

      //verify that method was never called on a mock
      verify(calcService, never()).multiply(10.0,20.0);

      //check a minimum 1 call count
      verify(calcService, atLeastOnce()).subtract(20.0, 10.0);

      //check if add function is called minimum 2 times
      verify(calcService, atLeast(2)).add(10.0, 20.0);

      //check if add function is called maximum 3 times
      verify(calcService, atMost(3)).add(10.0,20.0);
   }

   @Test(expected = RuntimeException.class)
   public void test2(){
      //add the behavior to throw exception
      doThrow(new RuntimeException("Add operation not implemented"))
          .when(calcService).add(10.0,20.0);

      //test the add functionality
      Assert.assertEquals(mathApplication.add(10.0, 20.0),50.0,0);
   }
}
