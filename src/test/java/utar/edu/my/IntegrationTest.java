package utar.edu.my;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   IntegrationTestBooking.class,
   IntegrationTestUser.class,
   IntegrationTestWaitingList.class,
})

public class IntegrationTest {

}
