package utar.edu.my;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   TestBooking.class,
   TestUser.class,
   TestWaitingList.class
})

public class UnitTest {

}
